package com.challenge.hotel.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements Constantes{

    public Connection conectar() throws SQLException{
        Connection con = DriverManager.getConnection(usuarioConexion,usuario,usuarioPw);
        System.out.println("conectado");
        return con;
    }
    
}
