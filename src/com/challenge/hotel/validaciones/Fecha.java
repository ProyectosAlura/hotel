package com.challenge.hotel.validaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {

    private final static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    /**
	 * Convierte Date a String con el formato "yyyy-MM-dd"
	 * @param fecha
	 * @return String
	 */
    public static String formatearFecha(Date fecha) { // Devuelve la fecha en formato anho-mes-dia
		String formato = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
		return formato;
	}

	/**
	 * Convierte la fecha de String a Date
	 * @param fecha fecha a convertir
	 * @return devuelve la fecha en tipo Date
	 */
    public static Date formatearFechaDate(String fecha){
		try {
			Date fechaFormat = formato.parse(fecha);
			return fechaFormat;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Verifica que la fecha de checkin sea menor a la fecha del checkout y
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return true si se cumple la condición
	 * @throws ParseException
	 */
	public static boolean verificarFecha(Date fechaEntrada, Date fechaSalida) {
		String fechaActual = Fecha.formatearFecha(new Date()); // fecha y hora actual
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaActualFormateada;
		try {
			fechaActualFormateada = formato.parse(fechaActual);
			int compE = fechaEntrada.compareTo(fechaActualFormateada);
			int compS = fechaSalida.compareTo(fechaActualFormateada);
		if (compE >= 0 && compS >= 0) { // si la fecha es mayor o igual a la fecha actual
			if ((fechaEntrada != null && fechaSalida != null)) {
				int res = fechaEntrada.compareTo(fechaSalida); // si es menor devuelve -1, si es igual devuelve 0
				if (res < 0) {
					return true;
				}
			}
		}
		return false;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}



}
