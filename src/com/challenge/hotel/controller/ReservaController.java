package com.challenge.hotel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.challenge.hotel.DAO.ReservaDAO;
import com.challenge.hotel.factory.ConnectionFactory;
import com.challenge.hotel.model.Reserva;


public class ReservaController {
    
    private ReservaDAO recursosReserva = new ReservaDAO();

    public ArrayList<Reserva> verReservas() {
        try {
            return recursosReserva.leerReservas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Los datos no fueron encontrados!","¡Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new ArrayList<Reserva>();
        }
        
    }

    public int crearReserva(Reserva reserva){
        try {
            return recursosReserva.crearReserva(reserva);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Error,La reserva no se creo!","¡Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Reserva> buscarReservas(int valor){
        try {
            return recursosReserva.buscarReservas(valor);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Los datos no fueron encontrados!","¡Error!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int modificarReserva(Reserva reserva){
        try {
            return recursosReserva.modificarReserva(reserva);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            return -1;
        }
    }

    public int eliminarReserva(int id){
        try {
            return recursosReserva.eliminarReserva(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
