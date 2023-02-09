package com.challenge.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.challenge.hotel.factory.ConnectionFactory;
import com.challenge.hotel.model.Reserva;

public class ReservaDAO {

    public ArrayList<Reserva> leerReservas() throws SQLException{
        ArrayList <Reserva> lista = new ArrayList<>();
        ConnectionFactory conexion = new ConnectionFactory();

        try (Connection con = conexion.conectar()) {
            try (PreparedStatement statement = con.prepareStatement("SELECT ID,FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMAPAGO FROM TBRESERVAS")) {
                boolean existeLista = statement.execute();

                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva reserva = new Reserva(resultSet.getString("FECHA_ENTRADA"), resultSet.getString("FECHA_SALIDA"),
                        resultSet.getDouble("VALOR"), resultSet.getString("FORMAPAGO"));

                        reserva.setId(resultSet.getInt("ID"));
                        
                        lista.add(reserva);
                        //throw new SQLException();
                    }
                    return lista;
                } 
            }
        }
    }

    public int crearReserva(Reserva reserva) throws SQLException{
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
                        //throw new SQLException();
                        try(ResultSet resultado = statement.getGeneratedKeys()){
                            while(resultado.next()){
                                reserva.setId(resultado.getInt(1));
                            }
                            
                            return reserva.getId();
                        }
            }
    }
}

    public ArrayList<Reserva> buscarReservas(int valor) throws SQLException{
        ArrayList <Reserva> listareservas = new ArrayList<>();
        ConnectionFactory conexion = new ConnectionFactory();
        try (Connection con = conexion.conectar()) {
            try(PreparedStatement statement = con.prepareStatement("SELECT * FROM TBRESERVAS WHERE (id=?)")){
                statement.setInt(1, valor);

                boolean existeLista = statement.execute();
                try( ResultSet resultSet = statement.getResultSet()){
                    while(resultSet.next()){
                        Reserva reserva = new Reserva(resultSet.getString("FECHA_ENTRADA"), resultSet.getString("FECHA_SALIDA"),
                        resultSet.getDouble("VALOR"), resultSet.getString("FORMAPAGO"));
                        reserva.setId(resultSet.getInt("ID"));
                        listareservas.add(reserva);
                    }
                    return listareservas;
                }
            }
    } 
}
    public int modificarReserva(Reserva reserva) throws SQLException{
        ConnectionFactory conexion = new ConnectionFactory();
        try (Connection con = conexion.conectar()) {
            try(PreparedStatement statement = con.prepareStatement("UPDATE TBRESERVAS SET FECHA_ENTRADA=?,FECHA_SALIDA=?,VALOR=?,FORMAPAGO=? WHERE ID= ?")){
                statement.setString(1, reserva.getFechaEntrada());
                statement.setString(2, reserva.getFechaSalida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getFORMAPAGO());
                statement.setInt(5,reserva.getId());
                statement.execute();
                
                int registroAlterado = statement.getUpdateCount();
                return registroAlterado;
            }
        } 
    }

    public int eliminarReserva(int id) throws SQLException{
        ConnectionFactory conexion = new ConnectionFactory();
        try (Connection con = conexion.conectar()) {
            try(PreparedStatement statement = con.prepareStatement("DELETE FROM TBRESERVAS WHERE ID = ?")){
                statement.setInt(1,id);
                statement.execute();
                int registroAlterado = statement.getUpdateCount();
                return registroAlterado;
            }
        }
    }
}
