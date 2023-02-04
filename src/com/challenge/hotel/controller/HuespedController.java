package com.challenge.hotel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.challenge.hotel.DAO.HuespedDAO;
import com.challenge.hotel.factory.ConnectionFactory;
import com.challenge.hotel.model.Huesped;

public class HuespedController {
    private HuespedDAO recursosHuesped = new HuespedDAO();

    public void verHuespedes(){
        recursosHuesped.leerHuespedes();
    }

    public int crearHuesped(Huesped huesped){
        return recursosHuesped.crearHuesped(huesped);
    }
}
