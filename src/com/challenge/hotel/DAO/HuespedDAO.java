package com.challenge.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.challenge.hotel.factory.ConnectionFactory;
import com.challenge.hotel.model.Huesped;

public class HuespedDAO {
    
    public ArrayList<Huesped> leerHuespedes(){
        ArrayList <Huesped> listaHuespedes = new ArrayList<>();
        ConnectionFactory conexion = new ConnectionFactory();
        try (Connection con = conexion.conectar()) {
            try(PreparedStatement statement = con.prepareStatement("SELECT ID,NOMBRE,APELLIDO,FECHA_DE_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA FROM TBHUESPEDES")){
                boolean existeLista = statement.execute();
                System.out.println(existeLista);
                try( ResultSet resultSet = statement.getResultSet()){
                    while(resultSet.next()){
                        Huesped huesped = new Huesped(resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"),
                        resultSet.getString("FECHA_DE_NACIMIENTO"), resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"), resultSet.getInt("ID_RESERVA"));
                        /*System.out.println("ID: "+resultSet.getInt("ID")+",Nombre: "+resultSet.getString("NOMBRE")+",Apellido:"+resultSet.getString("APELLIDO")+",Fecha de nacimiento:"+resultSet.getString("FECHA_DE_NACIMIENTO")
                        +"Nacionalidad:"+resultSet.getString("NACIONALIDAD")+",telefono:"+resultSet.getString("TELEFONO")+",#reserva:"+resultSet.getInt("ID_RESERVA"));*/
                        huesped.setId(resultSet.getInt("ID"));
                        listaHuespedes.add(huesped);
                    }
                    return listaHuespedes;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return listaHuespedes;
        }
    }

    public int crearHuesped(Huesped huesped){
        ConnectionFactory conexion = new ConnectionFactory();

        try (Connection con = conexion.conectar()) {
            try(PreparedStatement statement = con.prepareStatement("INSERT INTO TBHUESPEDES(NOMBRE,APELLIDO,FECHA_DE_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA) VALUES(?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)){
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());
                statement.execute();

                try(ResultSet resultado = statement.getGeneratedKeys()){
                    while(resultado.next()){
                        huesped.setId(resultado.getInt(1));
                    }
                    return huesped.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
