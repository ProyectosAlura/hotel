package com.challenge.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.challenge.hotel.factory.ConnectionFactory;
import com.challenge.hotel.model.Reserva;

public class ReservaDAO {

    public void leerReservas() {
        ConnectionFactory conexion = new ConnectionFactory();

        try (Connection con = conexion.conectar()) {
            try (PreparedStatement statement = con.prepareStatement("SELECT ID,FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMAPAGO FROM TBRESERVAS")) {
                boolean existeLista = statement.execute();
                System.out.println(existeLista);

                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        System.out.println("ID: " + resultSet.getInt("ID") + ",Fecha entrada: "
                                + resultSet.getString("FECHA_ENTRADA") + ",Valor:" + resultSet.getDouble("VALOR")
                                + ",forma de pago:" + resultSet.getString("FORMAPAGO"));
                    }
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int crearReserva(Reserva reserva){
        ConnectionFactory conexion = new ConnectionFactory();
        try (Connection con = conexion.conectar()) {
            try (PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO TBRESERVAS(FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMAPAGO) VALUES(?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                        statement.setString(1, reserva.getFechaEntrada()); 
                        statement.setString(2, reserva.getFechaSalida());  
                        statement.setDouble(3, reserva.getValor());  
                        statement.setString(4, reserva.getFORMAPAGO());  
                        statement.execute();
                        try(ResultSet resultado = statement.getGeneratedKeys()){
                            while(resultado.next()){
                                System.out.println("el id es: "+resultado.getInt(1));
                                reserva.setId(resultado.getInt(1));
                            }
                            return reserva.getId();
                        }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
