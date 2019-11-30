package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logica.Administrador;
import logica.Componente;
import logica.DiscoDuro;
import logica.Micro;
import logica.MotherBoard;
import logica.Proveedor;
import logica.RAM;
import logica.Tienda;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AgregarComponente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnDiscoDuro;
	private JRadioButton rdbtnMicro;
	private JRadioButton rdbtnMotherBoard;
	private JRadioButton rdbtnRAM;
	private JScrollPane scrollPane;
	private JPanel panel_DiscoDuro;
	private JPanel panel_Micro;
	private JPanel panel_MotherBoard;
	private JPanel panel_RAM;
	private JPanel panel_Proveedor;
	private JButton btnAgregarVendedor;
	private JLabel lblAlmacenamiento;
	private JLabel lblTipoConexion;
	private JTextField txtNoSerie;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JComboBox cbxAlmacenamiento;
	private JComboBox cbxTipoConexionDD;
	private JComboBox cbxTipoConexionMicro;
	private JComboBox cbxTipoConectorMB;
	private JComboBox cbxTipoRAM;
	private JComboBox cbxTipo;
	private JFormattedTextField ftxtCantMemoria;
	private JFormattedTextField ftxtVelocidad;
	private JSpinner spnPrecioVenta;
	private JSpinner spnCantMax;
	private JSpinner spnCantMin;
	private static DefaultTableModel model;
	private static Object[] row;
	private String codigo="";
	private JTable table;
	private MaskFormatter mascaraVelocidad;
	private MaskFormatter mascaraCantMemoria;
	private JButton btnAsignarPrecio;
	private int index;


	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			AgregarComponente dialog = new AgregarComponente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AgregarComponente(boolean b, Componente auxComp) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarComponente.class.getResource("/Imagenes/IconAgregarComponente.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Tienda.getInstance().getLosQueVendenTemp().clear();	
			}
		});
		setLocationRelativeTo(null);
		setTitle("Crear Componente");
		setBounds(100, 100, 575, 503);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JPanel panel_DatosGeneralesComponentes = new JPanel();
		panel_DatosGeneralesComponentes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos generales del componente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DatosGeneralesComponentes.setBounds(10, 11, 266, 161);
		contentPanel.add(panel_DatosGeneralesComponentes);
		panel_DatosGeneralesComponentes.setLayout(null);

		JLabel lblId = new JLabel("N\u00BA de serie:");
		lblId.setBounds(10, 29, 71, 14);
		panel_DatosGeneralesComponentes.add(lblId);


		JLabel lblPrecioBase = new JLabel("Marca: ");
		lblPrecioBase.setBounds(10, 72, 64, 14);
		panel_DatosGeneralesComponentes.add(lblPrecioBase);

		JLabel lblPrecioUnitario = new JLabel("Modelo: ");
		lblPrecioUnitario.setBounds(10, 115, 64, 14);
		panel_DatosGeneralesComponentes.add(lblPrecioUnitario);

		txtNoSerie = new JTextField();
		txtNoSerie.setEditable(false);
		txtNoSerie.setBounds(110, 26, 96, 20);
		panel_DatosGeneralesComponentes.add(txtNoSerie);
		txtNoSerie.setColumns(10);
		txtNoSerie.setText("CMP-"+Tienda.getInstance().getGeneradorCodigoComponentes());

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(110, 69, 96, 20);
		panel_DatosGeneralesComponentes.add(txtMarca);

		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(110, 112, 96, 20);
		panel_DatosGeneralesComponentes.add(txtModelo);

		JPanel panel_TipoComponente = new JPanel();
		panel_TipoComponente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo de componente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_TipoComponente.setBounds(10, 183, 266, 150);
		contentPanel.add(panel_TipoComponente);
		panel_TipoComponente.setLayout(null);



		rdbtnDiscoDuro = new JRadioButton("Disco Duro");
		rdbtnDiscoDuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDiscoDuro.setSelected(true);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
				panel_DiscoDuro.setVisible(true);
				panel_Micro.setVisible(false);
				panel_MotherBoard.setVisible(false);
				panel_RAM.setVisible(false);

			}
		});
		rdbtnDiscoDuro.setBounds(6, 19, 92, 23);
		panel_TipoComponente.add(rdbtnDiscoDuro);

		rdbtnMicro = new JRadioButton("Microprocesador");
		rdbtnMicro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(true);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
				panel_DiscoDuro.setVisible(false);
				panel_Micro.setVisible(true);
				panel_MotherBoard.setVisible(false);
				panel_RAM.setVisible(false);
			}
		});
		rdbtnMicro.setBounds(6, 53, 128, 23);
		panel_TipoComponente.add(rdbtnMicro);

		rdbtnMotherBoard = new JRadioButton("Motherboard");
		rdbtnMotherBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(true);
				rdbtnRAM.setSelected(false);
				panel_DiscoDuro.setVisible(false);
				panel_Micro.setVisible(false);
				panel_MotherBoard.setVisible(true);
				panel_RAM.setVisible(false);
			}
		});
		rdbtnMotherBoard.setBounds(6, 87, 109, 23);
		panel_TipoComponente.add(rdbtnMotherBoard);

		rdbtnRAM = new JRadioButton("RAM");
		rdbtnRAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(true);
				panel_DiscoDuro.setVisible(false);
				panel_Micro.setVisible(false);
				panel_MotherBoard.setVisible(false);
				panel_RAM.setVisible(true);
			}
		});
		rdbtnRAM.setBounds(6, 120, 65, 23);
		panel_TipoComponente.add(rdbtnRAM);

		try {
			mascaraVelocidad = new MaskFormatter("#.##");
			//mascaraVelocidad.setPlaceholder("_____");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			mascaraCantMemoria = new MaskFormatter("##");
			//mascaraCantMemoria.setPlaceholder("_____");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		panel_RAM = new JPanel();
		panel_RAM.setVisible(false);
		
				panel_DiscoDuro = new JPanel();
				panel_DiscoDuro.setVisible(false);
				panel_DiscoDuro.setLayout(null);
				panel_DiscoDuro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_DiscoDuro.setBounds(10, 344, 535, 70);
				contentPanel.add(panel_DiscoDuro);
				
						lblAlmacenamiento = new JLabel("Almacenamiento: ");
						lblAlmacenamiento.setBounds(12, 27, 104, 14);
						panel_DiscoDuro.add(lblAlmacenamiento);
						
								lblTipoConexion = new JLabel("Tipo Conexi\u00F3n:");
								lblTipoConexion.setBounds(316, 27, 91, 14);
								panel_DiscoDuro.add(lblTipoConexion);
								
										cbxAlmacenamiento = new JComboBox();
										cbxAlmacenamiento.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "256 GB", "512 GB", "1 TB", "2 TB"}));
										cbxAlmacenamiento.setBounds(128, 23, 80, 22);
										panel_DiscoDuro.add(cbxAlmacenamiento);
										
												cbxTipoConexionDD = new JComboBox();
												cbxTipoConexionDD.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "IDE", "SATA", "SCSI", "SAS"}));
												cbxTipoConexionDD.setBounds(419, 23, 104, 22);
												panel_DiscoDuro.add(cbxTipoConexionDD);
												panel_DiscoDuro.setVisible(true);
		ftxtVelocidad = new JFormattedTextField(mascaraVelocidad);
		ftxtVelocidad.setBounds(94, 25, 65, 20);
		
				panel_Micro = new JPanel();
				panel_Micro.setVisible(false);
				panel_Micro.setLayout(null);
				panel_Micro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_Micro.setBounds(10, 344, 535, 70);
				contentPanel.add(panel_Micro);
				
						JLabel lblVelocidad = new JLabel("Velocidad:");
						lblVelocidad.setBounds(12, 28, 70, 14);
						panel_Micro.add(lblVelocidad);
						
								JLabel lblTipoConexin = new JLabel("Tipo Conexi\u00F3n:");
								lblTipoConexin.setBounds(316, 28, 91, 14);
								panel_Micro.add(lblTipoConexin);
								
										cbxTipoConexionMicro = new JComboBox();
										cbxTipoConexionMicro.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "PGA", "BGA", "LGA"}));
										cbxTipoConexionMicro.setBounds(419, 24, 104, 22);
										panel_Micro.add(cbxTipoConexionMicro);
										
										
												panel_Micro.add(ftxtVelocidad);
												
														JLabel lblGhz = new JLabel("GHz");
														lblGhz.setBounds(160, 28, 30, 14);
														panel_Micro.add(lblGhz);
														panel_Micro.setVisible(false);
		
				panel_MotherBoard = new JPanel();
				panel_MotherBoard.setVisible(false);
				panel_MotherBoard.setLayout(null);
				panel_MotherBoard.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_MotherBoard.setBounds(10, 344, 535, 70);
				contentPanel.add(panel_MotherBoard);
				
						JLabel lblTipoConector = new JLabel("Tipo Conector");
						lblTipoConector.setBounds(12, 27, 88, 14);
						panel_MotherBoard.add(lblTipoConector);
						
								JLabel lblTipoRam = new JLabel("Tipo RAM");
								lblTipoRam.setBounds(332, 27, 65, 14);
								panel_MotherBoard.add(lblTipoRam);
								
										cbxTipoConectorMB = new JComboBox();
										cbxTipoConectorMB.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>"}));
										cbxTipoConectorMB.setBounds(112, 23, 104, 22);
										panel_MotherBoard.add(cbxTipoConectorMB);
										
												cbxTipoRAM = new JComboBox();
												cbxTipoRAM.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>"}));
												cbxTipoRAM.setBounds(409, 23, 114, 22);
												panel_MotherBoard.add(cbxTipoRAM);
												panel_MotherBoard.setVisible(false);
		panel_RAM.setBounds(10, 344, 535, 70);
		contentPanel.add(panel_RAM);
		panel_RAM.setLayout(null);
		panel_RAM.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(347, 28, 49, 14);
		panel_RAM.add(lblTipo);

		JLabel lblCantidadDeMemoria = new JLabel("Cantidad de Memoria: ");
		lblCantidadDeMemoria.setBounds(12, 32, 131, 14);
		panel_RAM.add(lblCantidadDeMemoria);

		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "DDR SDRAM", "DDR2 SDRAM", "DDR3 SDRAM ", "DDR4 SDRAM"}));
		cbxTipo.setBounds(396, 24, 127, 22);
		panel_RAM.add(cbxTipo);
		ftxtCantMemoria = new JFormattedTextField(mascaraCantMemoria);
		ftxtCantMemoria.setBounds(155, 28, 58, 22);

		panel_RAM.add(ftxtCantMemoria);

		JLabel lblGb = new JLabel("GB");
		lblGb.setBounds(225, 32, 39, 14);
		panel_RAM.add(lblGb);
		panel_RAM.setVisible(false);



		JPanel panel_DatosTienda = new JPanel();
		panel_DatosTienda.setLayout(null);
		panel_DatosTienda.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la Tienda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DatosTienda.setBounds(288, 11, 266, 161);
		contentPanel.add(panel_DatosTienda);

		JLabel lblCantidadMin = new JLabel("Cantidad M\u00EDnima:");
		lblCantidadMin.setBounds(10, 33, 96, 14);
		panel_DatosTienda.add(lblCantidadMin);

		JLabel lblCantidadMax = new JLabel("Cantidad M\u00E1ximo: ");
		lblCantidadMax.setBounds(10, 80, 96, 14);
		panel_DatosTienda.add(lblCantidadMax);

		JLabel lblPrecioVenta = new JLabel("Precio Venta: ");
		lblPrecioVenta.setBounds(10, 127, 96, 14);
		panel_DatosTienda.add(lblPrecioVenta);

		spnPrecioVenta = new JSpinner();
		spnPrecioVenta.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnPrecioVenta.setBounds(192, 123, 64, 22);
		panel_DatosTienda.add(spnPrecioVenta);

		spnCantMax = new JSpinner();
		spnCantMax.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));		
		spnCantMax.setBounds(192, 76, 64, 22);
		panel_DatosTienda.add(spnCantMax);

		spnCantMin = new JSpinner();
		spnCantMin.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantMin.setBounds(192, 29, 64, 22);
		panel_DatosTienda.add(spnCantMin);

		if(auxComp!=null) {
			txtNoSerie.setText(auxComp.getNumeroSerie());txtNoSerie.setEnabled(false);
			txtModelo.setText(auxComp.getModelo());txtModelo.setEnabled(true);
			txtMarca.setText(auxComp.getMarca());txtMarca.setEnabled(true);
			spnCantMax.setValue(auxComp.getCantMax());spnCantMax.setEnabled(true);
			spnCantMin.setValue(auxComp.getCantMin());spnCantMin.setEnabled(true);
			spnPrecioVenta.setValue(auxComp.getPrecioVentaActual());spnPrecioVenta.setEnabled(true);
			if(!auxComp.getLosQueVenden().isEmpty()) {
				Tienda.getInstance().getLosQueVendenTemp().addAll(auxComp.getLosQueVenden());
			}
			/*
			if(auxComp instanceof DiscoDuro) {
				rdbtnDiscoDuro.setSelected(true);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
			}
			if(auxComp instanceof Micro) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(true);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
			}
			if(auxComp instanceof MotherBoard) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(true);
				rdbtnRAM.setSelected(false);
			}
			if(auxComp instanceof RAM) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(true);
			}*/
		}



		rdbtnDiscoDuro.setSelected(true);
		rdbtnMicro.setSelected(false);
		rdbtnMotherBoard.setSelected(false);
		rdbtnRAM.setSelected(false);

		panel_Proveedor = new JPanel();
		panel_Proveedor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Proveedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Proveedor.setBounds(288, 183, 266, 150);
		contentPanel.add(panel_Proveedor);

		btnAgregarVendedor = new JButton("Lista Proveedores");
		btnAgregarVendedor.setBounds(5, 120, 133, 25);
		btnAgregarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProveedores aux = new ListaProveedores(true);
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		panel_Proveedor.setLayout(null);
		panel_Proveedor.add(btnAgregarVendedor);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()>-1){
					int index = table.getSelectedRow();

					btnAsignarPrecio.setEnabled(true);
					codigo = String.valueOf(table.getValueAt(index, 0));
				}
			}
		});
		scrollPane.setLocation(5, 18);
		scrollPane.setSize(256, 102);
		panel_Proveedor.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		{
			model = new DefaultTableModel();
			String[] header = {"RNC","Nombre", "Precio"};
			model.setColumnIdentifiers(header);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(model);
			scrollPane.setViewportView(table);
		}

		btnAsignarPrecio = new JButton("Asignar Precio");
		btnAsignarPrecio.setEnabled(false);
		btnAsignarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean bien=false;
				Float num=(float) 0;
				while(!bien) {

					try {
						num = Float.valueOf(JOptionPane.showInputDialog("Introduzca el precio del componente"));
						bien = true;
						btnAsignarPrecio.setEnabled(false);
					} catch (NumberFormatException  | NullPointerException e2) {
						JOptionPane.showMessageDialog(null, "Debe introducir un numero");
					}	
				}
				try {
					Tienda.getInstance().getPreciosLosQueVendenTemp().add(index,num);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Los Precios deben ser ingresados en orden");
				}

				try {
					Tienda.getInstance().getPreciosLosQueVendenTemp().remove(index+1);
				} catch (IndexOutOfBoundsException e) {
				}
				cargarProveedoresVentaComp();
			}
		});
		btnAsignarPrecio.setBounds(137, 120, 124, 25);
		panel_Proveedor.add(btnAsignarPrecio);

		{

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Componente aux = null;
						String numeroSerie = txtNoSerie.getText();
						String modelo = txtModelo.getText();
						String marca = txtMarca.getText();
						int cantMax = new Integer(spnCantMax.getValue().toString());  
						int cantMin = new Integer(spnCantMin.getValue().toString());
						float precioVentaI = new Float(spnPrecioVenta.getValue().toString());


						if (cantMax>=cantMin) {
							if (rdbtnDiscoDuro.isSelected()) {
								if(cbxAlmacenamiento.getSelectedIndex() != 0 && cbxTipoConexionDD.getSelectedIndex() !=0) {
									String capacidadAlma = cbxAlmacenamiento.getSelectedItem().toString();
									String tipoConexionDD = cbxTipoConexionDD.getSelectedItem().toString();
									aux = new DiscoDuro(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, capacidadAlma, tipoConexionDD);
									clean();
								}else {
									JOptionPane.showMessageDialog(null, "Escoja una capacidad de alma ó un tipo de conexión validos","Notificación", JOptionPane.INFORMATION_MESSAGE);
								}

							}
							if (rdbtnMicro.isSelected()) {
								if (cbxTipoConexionMicro.getSelectedIndex() !=0 && ftxtVelocidad.getText() != "") {
									String tipoConexionMicro = cbxTipoConexionMicro.getSelectedItem().toString();
									String velocidad = ftxtVelocidad.getText();
									aux = new Micro(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, tipoConexionMicro, velocidad);
									clean();
								}else {
									JOptionPane.showMessageDialog(null, "Escoja una velocidad ó un tipo de conexión validos","Notificación", JOptionPane.INFORMATION_MESSAGE);
								}

							}
							if (rdbtnMotherBoard.isSelected()) {
								if (cbxTipoRAM.getSelectedIndex() !=0 && cbxTipoConectorMB.getSelectedIndex() !=0) {
									String tipoConectorMB = cbxTipoConectorMB.getSelectedItem().toString();
									String tipoRAM = cbxTipoRAM.getSelectedItem().toString();
									aux = new MotherBoard(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, tipoConectorMB, tipoRAM);
									clean();
								}else {
									JOptionPane.showMessageDialog(null, "Escoja un tipo de RAM ó un tipo de conexión validos","Notificación", JOptionPane.INFORMATION_MESSAGE);
								}
							}
							if (rdbtnRAM.isSelected()) {
								if (cbxTipo.getSelectedIndex() != 0 && ftxtCantMemoria.getText() != "") {
									String tipo = cbxTipo.getSelectedItem().toString();
									String cantMemoria = ftxtCantMemoria.getText();
									aux = new RAM(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, cantMemoria, tipo);
									clean();
								}else {
									JOptionPane.showMessageDialog(null, "Escoja una cantidad de memoria ó un tipo validos","Notificación", JOptionPane.INFORMATION_MESSAGE);
								}
							}	
						} else {
							JOptionPane.showMessageDialog(null, "Coloque una cantidad máxima superior a la mínima","Notificación", JOptionPane.INFORMATION_MESSAGE);
						}
						if(auxComp!=null) {
							Tienda.getInstance().getLosComponentes().remove(auxComp);

						}
						if(aux.getLosQueVenden().isEmpty()) {
							aux.getLosQueVenden().addAll((ArrayList<Proveedor>)Tienda.getInstance().getLosQueVendenTemp().clone());
							int i=0;
							for (Proveedor proveedor : aux.getLosQueVenden()) {
								proveedor.getPreciosCompos().add(((ArrayList<Float>) Tienda.getInstance().getPreciosLosQueVendenTemp().clone()).get(i));
							}
						}
						Tienda.getInstance().agregarComponente(aux);
						Tienda.getInstance().primeraOrdenCompra(aux);
						if(!b) {
							Tienda.getInstance().getLosCompTemp().add(aux);
							AgregarProveedor.cargarComponentes();
						}

						Tienda.getInstance().getLosQueVendenTemp().clear();
						clean();
						Tienda.getInstance().getPreciosLosQueVendenTemp().clear();
						if(auxComp!=null) {
							dispose();
							JOptionPane.showMessageDialog(null, "Componente modificado con exito","Notificación", JOptionPane.INFORMATION_MESSAGE);	
						}
						else {
							JOptionPane.showMessageDialog(null, "Componente añadido con exito","Notificación", JOptionPane.INFORMATION_MESSAGE);	
						}
					}

				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);			}
		}

		if(!b) {
			panel_Proveedor.setEnabled(false);
			btnAgregarVendedor.setEnabled(false);
		}
		cargarProveedoresVentaComp();

	}

	public static void cargarProveedoresVentaComp() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		int i=0;
		if(!Tienda.getInstance().getLosQueVendenTemp().isEmpty()) {
			for (Proveedor aux : Tienda.getInstance().getLosQueVendenTemp()) {
				if (aux != null) {
					row[0] = aux.getCodigo();
					row[1] = aux.getNombre();
					try {
						row[2] = Tienda.getInstance().getPreciosLosQueVendenTemp().get(i).toString();
					} catch (IndexOutOfBoundsException e) {
						row[2] = "";
					}
					i++;
					model.addRow(row);
				}
			}
		}
	}

	private void clean() {
		txtNoSerie.setText("N°-"+Tienda.getInstance().getGeneradorCodigoComponentes());
		txtModelo.setText("");
		txtMarca.setText("");
		spnCantMax.setValue(1);  
		spnCantMin.setValue(1);
		spnPrecioVenta.setValue(1);
		cbxAlmacenamiento.setSelectedIndex(0);
		cbxTipoConexionDD.setSelectedIndex(0);;
		cbxTipoConexionMicro.setSelectedIndex(0);
		cbxTipoConectorMB.setSelectedIndex(0);
		cbxTipoRAM.setSelectedIndex(0);
		cbxTipo.setSelectedIndex(0);
		ftxtVelocidad.setText("");
		ftxtCantMemoria.setText("");
	}
}