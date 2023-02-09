package com.challenge.hotel.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

	/**
	 * Verifica si la cadena de texto solo contiene letras, espacios y acentos
	 * @param texto
	 * @return devuelve true, si se cumple la condición
	 */
    public static boolean contieneSoloLetras(String texto){
		Pattern pattern = Pattern.compile("[a-zA-Z \u00C0-\u017F]+");
		Matcher matcher = pattern.matcher(texto);
		return matcher.matches();
	}

	/**
	 * Verifica si la cadena de texto contiene solo números
	 * @param texto cadena de texto a analizar
	 * @return devuelve true, si se cumple la condición
	 */
    public static boolean contieneSoloNumeros(String texto){
		Pattern pattern = Pattern.compile("[0-9]{10}"); //maximo 10 números
		Matcher matcher = pattern.matcher(texto);
		return matcher.matches();
	}
}
