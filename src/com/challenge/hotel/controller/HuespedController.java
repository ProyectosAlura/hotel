package com.challenge.hotel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.challenge.hotel.DAO.HuespedDAO;
import com.challenge.hotel.model.Huesped;

public class HuespedController {
    private HuespedDAO recursosHuesped = new HuespedDAO();

    public ArrayList<Huesped> verHuespedes(){
        try {
            return recursosHuesped.leerHuespedes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Los datos no fueron encontrados!","¡Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int crearHuesped(Huesped huesped){
        try {
            return recursosHuesped.crearHuesped(huesped);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡El huesped no se registro!","¡Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Huesped> buscarHuespedes(String valor){
        try {
            return recursosHuesped.buscarHuespedes(valor);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Los datos no fueron encontrados!","¡Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int modificarHuesped(Huesped huesped){
        try {
            return recursosHuesped.modificarHuesped(huesped);
        } catch (SQLException e) {
            return -1;
        }
    }

    public int eliminarHuesped(int id){
        try {
            return recursosHuesped.eliminarHuesped(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
