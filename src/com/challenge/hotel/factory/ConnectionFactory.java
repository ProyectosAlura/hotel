package com.challenge.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory implements Constantes{

    private DataSource datasource;

    public ConnectionFactory(){
        ComboPooledDataSource pool = new ComboPooledDataSource();
        pool.setJdbcUrl(usuarioConexion);
        pool.setUser(usuario);
        pool.setPassword(usuarioPw);
        pool.setMaxPoolSize(15);
        this.datasource=pool;
    }
    
    public Connection conectar() throws SQLException{
        return this.datasource.getConnection();
    }
    
}
