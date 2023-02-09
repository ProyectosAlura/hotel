package com.challenge.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.challenge.hotel.controller.HuespedController;
import com.challenge.hotel.controller.ReservaController;
import com.challenge.hotel.model.Huesped;
import com.challenge.hotel.model.Reserva;
import com.challenge.hotel.validaciones.Validaciones;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private int pestanha=-1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("../imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);

		panel.addChangeListener(new ChangeListener(){ //Identificar la pestaña seleccionada

			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane sel = (JTabbedPane) e.getSource();
				pestanha = sel.getSelectedIndex();
			}
		});
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		//agregar pestaña con barra lateral
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("../imagenes/reservado.png")), new JScrollPane(tbReservas) , null);
		modelo =new DefaultTableModel(){ //Sobre escribiendo el metodo
			@Override
			public boolean isCellEditable(int row, int column) { //Para que las celdas no se puedan editar
				return false;
			}
		};
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setModel(modelo);
		cargarDatosReservas(); //Mostrar los datos de las reservas

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("../imagenes/pessoas.png")), new JScrollPane(tbHuespedes), null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		cargarDatosHuespedes(); //Mostrar los datos de los huespedes

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("../imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			    System.out.println("------------");
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Logica para buscar
				if(!txtBuscar.getText().isEmpty()){

					if(pestanha==0){ //para la pestaña reserva
						try { //try por si se coloca texto y no numeros
							int v = Integer.parseInt(txtBuscar.getText()); //Obtener el texto que se quiere buscar
							ReservaController s = new ReservaController();
							ArrayList<Reserva> listaS = s.buscarReservas(v);
							
							eliminarElementos(modelo);
							if(listaS.size()>=1){
								for(Reserva reserva:listaS){
									modelo.addRow(new Object[]{reserva.getId(),reserva.getFechaEntrada(),reserva.getFechaSalida(),
									reserva.getValor(),reserva.getFORMAPAGO()});
								}
							}else{
								JOptionPane.showMessageDialog(null,"No se encontró la reserva","Error",JOptionPane.ERROR_MESSAGE);
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null,"Solo se acepta la busqueda por número de reserva");
						}
		
					}else if(pestanha==1){
						eliminarElementos(modeloH);
						// Mostrar elementos encontrados
						String valor = txtBuscar.getText();
						if(Validaciones.contieneSoloLetras(valor)){
							HuespedController h = new HuespedController();
							ArrayList<Huesped> listahuespedes = h.buscarHuespedes(valor);
							if(listahuespedes.size()>=1){
								for(Huesped huesped:listahuespedes){
									modeloH.addRow(new Object[]{huesped.getId(),huesped.getNombre(),huesped.getApellido(),
									huesped.getFechaNacimiento(),huesped.getNacionalidad(),huesped.getTelefono(),huesped.getIdReserva()});
								}
							}else{
								JOptionPane.showMessageDialog(null,"No se encontro ningún dato");
							}
						}else{
							JOptionPane.showMessageDialog(null,"Solo se puede buscar por apellido");
							txtBuscar.setText("");;
						}
					}

				}else{ //Si no hay texto carga la tabla
					eliminarElementos(modelo);
					eliminarElementos(modeloH);
					cargarDatosHuespedes();
					cargarDatosReservas();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));


		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//Editar reserva

				if(pestanha==0){ //pestanha reserva
					int fila = tbReservas.getSelectedRow(); //Fila seleccionada
				
					if(fila>=0){ //Si se selecciona el item
						Reserva reserva = new Reserva((String)modelo.getValueAt(fila, 1), (String)modelo.getValueAt(fila, 2)
						, (Double)modelo.getValueAt(fila, 3), (String) modelo.getValueAt(fila, 4));
						reserva.setId((int) modelo.getValueAt(fila, 0));
						EditarReserva editarReserva = new EditarReserva(reserva);
						editarReserva.setVisible(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(null,"Seleccione la reserva a eliminar");
					}
				}else if(pestanha==1){ //pestaña huesped
					int fila = tbHuespedes.getSelectedRow(); //Fila seleccionada
					
					if(fila>=0){ //Si se selecciona el item
						Huesped huesped = new Huesped((String)modeloH.getValueAt(fila, 1), (String)modeloH.getValueAt(fila, 2),
						(String)modeloH.getValueAt(fila, 3), (String)modeloH.getValueAt(fila, 4), 
						(String)modeloH.getValueAt(fila, 5), (int)modeloH.getValueAt(fila, 6));
						huesped.setId((int)modeloH.getValueAt(fila, 0));

						EditarHuesped editarHuesped = new EditarHuesped(huesped);
						editarHuesped.setVisible(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(null,"Seleccione el huésped a eliminar");
					}
				}
			}
		});
		contentPane.add(btnEditar);
		

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//Eliminar reserva

				if(pestanha==0){
					int fila = tbReservas.getSelectedRow(); //Fila seleccionada
				
					if(fila>=0){ //Si se selecciona el item
						ReservaController servicioReserva = new ReservaController();
						int resultado = servicioReserva.eliminarReserva((int)modelo.getValueAt(fila, 0));
						if(resultado==1){
							JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente");
							eliminarElementos(modelo);
							eliminarElementos(modeloH);
							cargarDatosHuespedes();
							cargarDatosReservas();
						}else{
							JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null,"Seleccione la reserva a eliminar");
					}
				}else if(pestanha==1){
					int fila = tbHuespedes.getSelectedRow();
					if(fila>=0){ //Si se selecciona el item
						HuespedController servicioHuesped = new HuespedController();
						int resultado = servicioHuesped.eliminarHuesped((int)modeloH.getValueAt(fila, 0));
						if(resultado==1){
							JOptionPane.showMessageDialog(null, "Huésped eliminado correctamente");
							eliminarElementos(modelo);
							eliminarElementos(modeloH);
							cargarDatosHuespedes();
							cargarDatosReservas();
						}else{
							JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null,"Seleccione el huésped a eliminar");
					}
				}
			}
		});
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	/**
	 * Elimina los elementos de la tabla
	 * @param model 
	 */
	private void eliminarElementos(DefaultTableModel model){
		while(model.getRowCount()>0){ //Eliminar elementos
			model.removeRow(0);
		}
	}

	/***
	 * Mostrar la lista de reservas en la tabla
	 */
	private void cargarDatosReservas(){
		ReservaController servicio = new ReservaController();
		ArrayList<Reserva> lista = servicio.verReservas();

		for(Reserva reserva:lista){
			modelo.addRow(new Object[]{reserva.getId(),reserva.getFechaEntrada(),reserva.getFechaSalida(),
			reserva.getValor(),reserva.getFORMAPAGO()});
		}
	}

	/***
	 * Mostrar la lista de huéspedes en la tabla
	 */
	private void cargarDatosHuespedes(){
		HuespedController serviciohuesped = new HuespedController();
		ArrayList<Huesped> listahuespedes = serviciohuesped.verHuespedes();

		for (Huesped huesped : listahuespedes) {
			modeloH.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getIdReserva() });
		}
	}

}
