package com.challenge.hotel.validaciones;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ValidacionesHuesped {
    /**
	 * Verifica que todas las condiciones de los campos se cumplan
	 * @return devuelve true si se cumple
	 */
	public static boolean verificarFormulario(JTextField txtNombre,JTextField txtApellido,JDateChooser txtFechaN,JComboBox txtNacionalidad,JTextField txtTelefono){
		//verificar que todo este lleno
		if(!txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && txtFechaN.getDate() != null 
		&& txtNacionalidad.getSelectedItem() != null && !txtTelefono.getText().isEmpty()){

			//verificar las condiciones de los campos
			if(Validaciones.contieneSoloLetras(txtNombre.getText()) && Validaciones.contieneSoloLetras(txtApellido.getText()) 
			&& Validaciones.contieneSoloNumeros(txtTelefono.getText())){
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "Datos inválidos \n - El teléfono debe tener 10 dígitos \n - Verifique nombre y apellido","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
		}
		return false;
	}
}
