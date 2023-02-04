package com.challenge.hotel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.challenge.hotel.DAO.ReservaDAO;
import com.challenge.hotel.factory.ConnectionFactory;
import com.challenge.hotel.model.Reserva;


public class ReservaController {
    
    private ReservaDAO recursosReserva = new ReservaDAO();

    public ArrayList<Reserva> verReservas(){
        return recursosReserva.leerReservas();
    }

    public int crearReserva(Reserva reserva){
        return recursosReserva.crearReserva(reserva);
    }
}
