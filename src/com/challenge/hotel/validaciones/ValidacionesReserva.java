package com.challenge.hotel.validaciones;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.JOptionPane;

public class ValidacionesReserva { 
    private static final double precio = 50000;

    //Calcular el costo de la reserva
	/**
	 * Calcula el costo de la reserva
	 * @param fechaEntrada fecha de checkin
	 * @param fechaSalida fecha de checkout
	 * @return devuelve un double
	 */
    public static double calcularCosto(Date fechaEntrada, Date fechaSalida) {
		if (Fecha.verificarFecha(fechaEntrada, fechaSalida)) {
			//
			LocalDate fechaentrada = LocalDate.parse(Fecha.formatearFecha(fechaEntrada));
			LocalDate fechasalida = LocalDate.parse(Fecha.formatearFecha(fechaSalida));
			long fe = ChronoUnit.DAYS.between(fechaentrada, fechasalida); // Calcular los dias entre fechas
			if (fe > 0) { 
				double res = precio * fe;
				return res;
			}
		} else {
			JOptionPane.showMessageDialog(null, "fecha inválida");
		}
		return -1.0;
	}

}
