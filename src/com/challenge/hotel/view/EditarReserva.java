package com.challenge.hotel.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.challenge.hotel.controller.ReservaController;
import com.challenge.hotel.model.Reserva;
import com.challenge.hotel.validaciones.Fecha;
import com.challenge.hotel.validaciones.ValidacionesReserva;
import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class EditarReserva extends JFrame{
    private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaE;
	public static JDateChooser txtFechaS;
	public static JComboBox<Format> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel lblValorSimbolo;
	private JLabel labelAtras;
	private Double valor;
	private final SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public EditarReserva(Reserva reserva) {
		super("Editar Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("../imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		txtFechaE = new JDateChooser();
		txtFechaE.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaE.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("../imagenes/icon-reservas.png")));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.setBounds(68, 161, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.setBackground(Color.WHITE);
		txtFechaE.setBorder(new LineBorder(SystemColor.window));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaE.setDate(Fecha.formatearFechaDate(reserva.getFechaEntrada())); //Asignando la fecha al campo
		txtFechaE.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				// Activa el evento, después del usuario seleccionar las fechas se debe calcular
				Date fechaEntrada = Fecha.formatearFechaDate(reserva.getFechaEntrada());
				Date fechaSalida = Fecha.formatearFechaDate(reserva.getFechaSalida());
				if(txtFechaS!=null ){
					if(txtFechaE.getDate()!=fechaEntrada && txtFechaS.getDate() != fechaSalida ){
						verificarCampo();
					}
				}
			}
		});    
		panel.add(txtFechaE);

		lblValorSimbolo = new JLabel("$");
		lblValorSimbolo.setVisible(false);
		lblValorSimbolo.setBounds(121, 332, 17, 25);
		lblValorSimbolo.setForeground(SystemColor.textHighlight);
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

		panel.add(lblValorSimbolo);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 220, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 220, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		txtFechaS = new JDateChooser();
		txtFechaS.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("../imagenes/icon-reservas.png")));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.setBounds(68, 246, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.setBackground(Color.WHITE);
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		txtFechaS.setDate(Fecha.formatearFechaDate(reserva.getFechaSalida())); //
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// Activa el evento, después del usuario seleccionar las fechas se debe calcular
				Date fechaEntrada = Fecha.formatearFechaDate(reserva.getFechaEntrada());
				Date fechaSalida = Fecha.formatearFechaDate(reserva.getFechaSalida());
				if(txtFechaE.getDate()!=null ){
					if(txtFechaE.getDate()!=fechaEntrada && txtFechaS.getDate() != fechaSalida ){
						verificarCampo();
					}
				}
			}
		});
		panel.add(txtFechaS);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 250, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        txtValor.setText("$" + String.valueOf(reserva.getValor()) + " COP");
		valor = reserva.getValor();
		panel.add(txtValor);
		txtValor.setColumns(30);

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 220, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de crédito", "Tarjeta de débito", "Dinero en efectivo" }));
        //
        txtFormaPago.setSelectedItem(reserva.getFORMAPAGO());
		panel.add(txtFormaPago);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("EDITAR RESERVA");
		lblTitulo.setBounds(109, 60, 250, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(EditarReserva.class.getResource("../imagenes/Ha-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(EditarReserva.class.getResource("../imagenes/reservas-img-3.png")));

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda();
				busqueda.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

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
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda();
				busqueda.setVisible(true);
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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		JPanel btnguardar = new JPanel();
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (EditarReserva.txtFechaE.getDate() != null && EditarReserva.txtFechaS.getDate() != null) {
					// Enviar datos a la BD
					
					//verificar campos
					if (verificarFecha(txtFechaE.getDate(), txtFechaS.getDate()) && valor>1) {
						String fechaEntrada = Fecha.formatearFecha(txtFechaE.getDate());
						String fechaSalida = Fecha.formatearFecha(txtFechaS.getDate());
						String metodoDePago = String.valueOf(txtFormaPago.getSelectedItem());

						//Editar reserva
						Reserva reservaNueva = new Reserva(fechaEntrada, fechaSalida, valor,metodoDePago);
						reservaNueva.setId(reserva.getId());
						ReservaController objReserva = new ReservaController();
						int res = objReserva.modificarReserva(reservaNueva);
						if(res==1){
							JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente");
							Busqueda busqueda = new Busqueda();
							busqueda.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "¡Error!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(SystemColor.textHighlight);
		btnguardar.setBounds(238, 493, 122, 35);
		panel.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblSiguiente = new JLabel("GUARDAR");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnguardar.add(lblSiguiente);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	} // JDateChooser fecha


	// Devuelve booleano dependiendo si la fecha de entrada es o no menor a la fecha
	// de salida
	private boolean verificarFecha(Date fechaEntrada, Date fechaSalida) {
		if ((fechaEntrada != null && fechaSalida != null)) {
			int res = fechaEntrada.compareTo(fechaSalida); // si es menor devuelve -1, si es igual devuelve 0
			if (res < 0) {
				return true;
			}
		}
		return false;
	}

	private void verificarCampo(){ //Mustra en pantalla el costo
		if(txtFechaE.getDate() != null && txtFechaS.getDate()!=null){
			valor = ValidacionesReserva.calcularCosto(txtFechaE.getDate(), txtFechaS.getDate()); 
			if (valor >= 0) {
				txtValor.setText("$" + valor + " COP");
			} else {
				valor= -1.0;
				txtValor.setText("");
			}
		}
	}

	
	}

	