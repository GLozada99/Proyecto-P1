package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Combo;
import logica.Componente;
import logica.DiscoDuro;
import logica.Micro;
import logica.MotherBoard;
import logica.RAM;
import logica.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NuevaFactura extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DefaultTableModel model;
	private static Object[] row;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtPrecioTotal;
	private JTextField txtCodigo;
	private JTextField txtTotalComponetes;
	private JTextField txtTotalCombos;
	private JFormattedTextField ftxtTelefono;
	private JFormattedTextField ftxtCedula;
	private JTable tablaStock;
	private JTable tablaCompra;
	private String[] encabezadoCompo = {"No. Serie","Marca","Modelo","Precio"};
	private String[] encabezadoCompra = {"No. Serie/Codigo","Tipo","Informacion"};
	private String[] encabezadoDD = {"No. Serie","Marca","Modelo","Precio","Almacenamiento","Tipo Conexion" };
	private String[] encabezadoMicro = {"No. Serie","Marca","Modelo","Precio ","Velocidad","Tipo Conexion" };
	private String[] encabezadoMother = {"No. Serie","Marca","Modelo","Precio","Tipo Conector","Tipo RAM" };
	private String[] encabezadoRAM = {"No. Serie","Marca","Modelo","Precio","Cant Memoria","Tipo Memoria" };
	private String[] encabezadoCombo = {"Codigo","Disco Duro","Microprocesador","Motherboard","RAM","Descuento"};
	private String codigo;
	private JComboBox<String> cbxComponentes;
	private static ArrayList<Combo> combosVenta=new ArrayList<Combo>();
	private static ArrayList<Componente> componentesVenta=new ArrayList<Componente>();
	private static ArrayList<Integer> cantidadesCompo=new ArrayList<Integer>();
	private static ArrayList<Integer> cantidadesCombo=new ArrayList<Integer>();
	private static DefaultTableModel model2;
	private Integer cantidadC;
	private Integer cantidad;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
			NuevaFactura dialog = new NuevaFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public NuevaFactura() {
		setTitle("Nueva Factura");
		setBounds(100, 100, 1191, 733);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JPanel DatosCliente = new JPanel();
		DatosCliente.setLayout(null);
		DatosCliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DatosCliente.setBounds(12, 540, 618, 98);
		contentPanel.add(DatosCliente);

		JLabel lblCedula = new JLabel("Cedula: ");
		lblCedula.setBounds(19, 25, 76, 22);
		DatosCliente.add(lblCedula);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n: ");
		lblDireccion.setBounds(335, 59, 76, 22);
		DatosCliente.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(399, 60, 207, 22);
		DatosCliente.add(txtDireccion);

		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setBounds(19, 60, 59, 22);
		DatosCliente.add(lblTelefono);

		{
			MaskFormatter mascaraNumero;
			MaskFormatter mascaraCedula;
			try {

				mascaraNumero = new MaskFormatter("(###) ###-####");
				mascaraNumero.setPlaceholderCharacter('_');
				ftxtTelefono = new JFormattedTextField(mascaraNumero);
				ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtTelefono.setBounds(85, 60, 200, 22);
				DatosCliente.add(ftxtTelefono);


				mascaraCedula = new MaskFormatter("###-#######-#");
				mascaraCedula.setPlaceholderCharacter('_');
				ftxtCedula = new JFormattedTextField(mascaraCedula);
				ftxtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtCedula.setBounds(85, 25, 145, 22);
				DatosCliente.add(ftxtCedula);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Tienda.getInstance().getLosClientes().isEmpty()) {
					Cliente aux=Tienda.getInstance().findClientebyCedula(ftxtCedula.getText());
					txtNombre.setEditable(true);
					txtDireccion.setEditable(true);
					ftxtTelefono.setEditable(true);
					if(aux!=null) {
						txtNombre.setText(aux.getNombre()); txtNombre.setEditable(false);
						ftxtTelefono.setText(aux.getTelefono()); ftxtTelefono.setEditable(false);
						txtDireccion.setText(aux.getDireccion()); txtDireccion.setEditable(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "El cliente no esta registrado","Notificación", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay clientes registrados","Notificación", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscar.setIcon(new ImageIcon(NuevaFactura.class.getResource("/Imagenes/Lupa.png")));
		btnBuscar.setActionCommand("Buscar");
		btnBuscar.setBounds(242, 25, 45, 25);
		DatosCliente.add(btnBuscar);
		
				JLabel lblNombre = new JLabel("Nombre: ");
				lblNombre.setBounds(335, 27, 76, 22);
				DatosCliente.add(lblNombre);
				
						txtNombre = new JTextField();
						txtNombre.setBounds(399, 25, 207, 22);
						DatosCliente.add(txtNombre);
						txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
						txtNombre.setColumns(10);

		JPanel ElementosASeleccionar = new JPanel();
		ElementosASeleccionar.setForeground(Color.BLACK);
		ElementosASeleccionar.setLayout(null);
		ElementosASeleccionar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Elementos a seleccionar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ElementosASeleccionar.setBounds(12, 13, 1149, 514);
		contentPanel.add(ElementosASeleccionar);

		JButton btnIzquierda = new JButton("<<");
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tienda.getInstance().findCombobyCodigo(codigo)!=null) {
					Combo auxCombo = Tienda.getInstance().findCombobyCodigo(codigo);
					cantidadesCombo.remove(combosVenta.lastIndexOf(auxCombo));
					combosVenta.remove(auxCombo);
					
				}
				else {
					Componente auxComponente = Tienda.getInstance().findComponentebyNumeroSerie(codigo);
					cantidadesCompo.remove(componentesVenta.lastIndexOf(auxComponente));
					componentesVenta.remove(auxComponente);
				}
				cargarCbx();
				cargarCompras();
			}
		});
		btnIzquierda.setEnabled(false);
		btnIzquierda.setBounds(525, 264, 99, 43);
		ElementosASeleccionar.add(btnIzquierda);

		JButton btnDerecha = new JButton(">>");
		btnDerecha.setEnabled(false);
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxComponentes.getSelectedIndex()==1) {
					Combo auxCombo = Tienda.getInstance().findCombobyCodigo(codigo);
					cantidadC = Integer.valueOf(JOptionPane.showInputDialog("Introduzca cantidad deseada"));
					cantidadesCombo.add(cantidadC);
					combosVenta.add(auxCombo);
					cargarCompras();
				}
				else {
					Componente auxComponente = Tienda.getInstance().findComponentebyNumeroSerie(codigo);
					cantidad = Integer.valueOf(JOptionPane.showInputDialog("La cantidad real es de: "+auxComponente.getCantDisponible()+". Introduzca cantidad deseada"));
					cantidadesCompo.add(cantidad);
					componentesVenta.add(auxComponente);
					cargarCompras();
				}
				/*if(cbxComponentes.getSelectedIndex()==0) {
					model.setColumnIdentifiers(encabezadoCompo);
					cargarComponentes();
				}
				if(cbxComponentes.getSelectedIndex()==1) {
					model.setColumnIdentifiers(encabezadoCombo);
					cargarCombos();
				}
				if(cbxComponentes.getSelectedIndex()==2) {
					model.setColumnIdentifiers(encabezadoDD);
					cargarComponentesDD();
				}
				if(cbxComponentes.getSelectedIndex()==3) {
					model.setColumnIdentifiers(encabezadoMicro);
					cargarComponentesMicro();
				}
				if(cbxComponentes.getSelectedIndex()==4) {
					model.setColumnIdentifiers(encabezadoMother);
					cargarComponentesMotherBoard();
				}
				if(cbxComponentes.getSelectedIndex()==5) {
					model.setColumnIdentifiers(encabezadoRAM);
					cargarComponentesRAM();
				}*/
				cargarCbx();
			}
		});
		btnDerecha.setBounds(525, 221, 100, 43);
		ElementosASeleccionar.add(btnDerecha);

		cbxComponentes = new JComboBox<String>();
		cbxComponentes.setModel(new DefaultComboBoxModel(new String[] {"Componentes", "Combos", "Disco Duro", "Microprocesador", "Mother Board", "RAM"}));
		cbxComponentes.setBounds(12, 24, 501, 22);
		ElementosASeleccionar.add(cbxComponentes);
		cbxComponentes.setSelectedIndex(0);

		cbxComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(cbxComponentes.getSelectedIndex()==0) {
					model.setColumnIdentifiers(encabezadoCompo);
					cargarComponentes();
				}
				if(cbxComponentes.getSelectedIndex()==1) {
					model.setColumnIdentifiers(encabezadoCombo);
					cargarCombos();
				}
				if(cbxComponentes.getSelectedIndex()==2) {
					model.setColumnIdentifiers(encabezadoDD);
					cargarComponentesDD();
				}
				if(cbxComponentes.getSelectedIndex()==3) {
					model.setColumnIdentifiers(encabezadoMicro);
					cargarComponentesMicro();
				}
				if(cbxComponentes.getSelectedIndex()==4) {
					model.setColumnIdentifiers(encabezadoMother);
					cargarComponentesMotherBoard();
				}
				if(cbxComponentes.getSelectedIndex()==5) {
					model.setColumnIdentifiers(encabezadoRAM);
					cargarComponentesRAM();
				}*/
				cargarCbx();
			}
		});


		JLabel lblCarritoDeCompras = new JLabel("Carrito de Compras");
		lblCarritoDeCompras.setBounds(636, 27, 501, 16);
		ElementosASeleccionar.add(lblCarritoDeCompras);

		JScrollPane spStock = new JScrollPane();
		spStock.setBounds(12, 59, 501, 442);
		ElementosASeleccionar.add(spStock);

		model = new DefaultTableModel();
		tablaStock = new JTable();
		tablaStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tablaStock.getSelectedRow();
				btnDerecha.setEnabled(true);
				//btnIzquierda.setEnabled(true);
				codigo = String.valueOf(tablaStock.getValueAt(index, 0));
			}
		});
		spStock.setViewportView(tablaStock);
		tablaStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaStock.setModel(model);


		JScrollPane spCarrito = new JScrollPane();
		spCarrito.setBounds(636, 59, 501, 442);
		ElementosASeleccionar.add(spCarrito);
		
		model2 = new DefaultTableModel();
		tablaCompra = new JTable();
		tablaCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tablaCompra.getSelectedRow();
				//btnDerecha.setEnabled(true);
				btnIzquierda.setEnabled(true);
				codigo = String.valueOf(tablaCompra.getValueAt(index, 0));
			}
		});
		spCarrito.setViewportView(tablaCompra);
		tablaCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCompra.setModel(model2);
		model2.setColumnIdentifiers(encabezadoCompra);
		cargarCompras();
		

		JPanel Factura = new JPanel();
		Factura.setForeground(Color.BLACK);
		Factura.setLayout(null);
		Factura.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Factura.setBounds(646, 540, 515, 98);
		contentPanel.add(Factura);

		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setText("0");
		txtPrecioTotal.setEditable(false);
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(391, 63, 116, 22);
		Factura.add(txtPrecioTotal);

		JLabel lblPrecioTotal = new JLabel("Precio total: ");
		lblPrecioTotal.setBounds(314, 66, 76, 16);
		Factura.add(lblPrecioTotal);

		txtCodigo = new JTextField();
		txtCodigo.setText("N\u00BA: "+Tienda.getInstance().getGeneradorCodigoFactura());
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(431, 15, 76, 20);
		Factura.add(txtCodigo);

		JLabel lblTotalComponentes = new JLabel("Total componentes: ");
		lblTotalComponentes.setBounds(12, 17, 128, 16);
		Factura.add(lblTotalComponentes);

		txtTotalComponetes = new JTextField();
		txtTotalComponetes.setEditable(false);
		txtTotalComponetes.setColumns(10);
		txtTotalComponetes.setBounds(141, 14, 81, 22);
		Factura.add(txtTotalComponetes);

		JLabel lblTotalCombos = new JLabel("Total combos: ");
		lblTotalCombos.setBounds(12, 65, 117, 16);
		Factura.add(lblTotalCombos);

		txtTotalCombos = new JTextField();
		txtTotalCombos.setEditable(false);
		txtTotalCombos.setColumns(10);
		txtTotalCombos.setBounds(141, 63, 81, 22);
		Factura.add(txtTotalCombos);
		
		JLabel lblCodigoFactura = new JLabel("Codigo Factura");
		lblCodigoFactura.setBounds(291, 17, 128, 16);
		Factura.add(lblCodigoFactura);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFacturar = new JButton("Facturar");
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		model.setColumnIdentifiers(encabezadoCompo);
		cargarComponentes();
	}
	
	public void cargarCbx(){
		if(cbxComponentes.getSelectedIndex()==0) {
			model.setColumnIdentifiers(encabezadoCompo);
			cargarComponentes();
		}
		if(cbxComponentes.getSelectedIndex()==1) {
			model.setColumnIdentifiers(encabezadoCombo);
			cargarCombos();
		}
		if(cbxComponentes.getSelectedIndex()==2) {
			model.setColumnIdentifiers(encabezadoDD);
			cargarComponentesDD();
		}
		if(cbxComponentes.getSelectedIndex()==3) {
			model.setColumnIdentifiers(encabezadoMicro);
			cargarComponentesMicro();
		}
		if(cbxComponentes.getSelectedIndex()==4) {
			model.setColumnIdentifiers(encabezadoMother);
			cargarComponentesMotherBoard();
		}
		if(cbxComponentes.getSelectedIndex()==5) {
			model.setColumnIdentifiers(encabezadoRAM);
			cargarComponentesRAM();
		}
	}
	
	public static void cargarCompras() {
		model2.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!componentesVenta.isEmpty()) {
			int i = 0;
			for (Componente componente : componentesVenta) {
				row[0] = componente.getNumeroSerie();
				row[1] = componente.getClass().getSimpleName();
				row[2] = componente.getMarca()+" : "+componente.getModelo()+" : "+cantidadesCompo.get(i);
				i++;
				model2.addRow(row);		
				
			}
		}
		

	}
	public static void cargarComponentes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!Tienda.getInstance().getLosComponentes().isEmpty()) {
			for (Componente componente : Tienda.getInstance().getLosComponentes()) {
				if (componente instanceof DiscoDuro) {
					row[0] = componente.getNumeroSerie();
					row[1] = componente.getMarca();
					row[2] = componente.getModelo();
					row[3] = componente.getPrecioVentaActual();
					model.addRow(row);
				}
			}
		}

	}

	public static void cargarCombos() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!Tienda.getInstance().getLosCombo().isEmpty()) {
			for (Combo combo : Tienda.getInstance().getLosCombo()) {
				row[0] = combo.getCodigo();
				for (Componente componente : combo.getComponentes()) {
					if(componente instanceof DiscoDuro) {
						row[1] = componente.getMarca() +" : "+ componente.getModelo();
					}
					if(componente instanceof Micro) {
						row[2] = componente.getMarca() +" : "+ componente.getModelo();
					}
					if(componente instanceof MotherBoard) {
						row[3] = componente.getMarca() +" : "+ componente.getModelo();
					}
					if(componente instanceof RAM) {
						row[4] = componente.getMarca() +" : "+ componente.getModelo();
					}
				}
				row[5] = combo.getDescuento()+"%";
				model.addRow(row);
			}
		}

	}

	public static void cargarComponentesDD() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!Tienda.getInstance().getLosComponentes().isEmpty()) {
			for (Componente componente : Tienda.getInstance().getLosComponentes()) {
				if (componente instanceof DiscoDuro) {
					row[0] = componente.getNumeroSerie();
					row[1] = componente.getMarca();
					row[2] = componente.getModelo();
					row[3] = componente.getPrecioVentaActual();
					row[4] = ((DiscoDuro)componente).getCapacidadAlma();
					row[5] = ((DiscoDuro)componente).getTipoConexion();
					model.addRow(row);
				}
			}
		}

	}

	public static void cargarComponentesMicro() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!Tienda.getInstance().getLosComponentes().isEmpty()) {
			for (Componente componente : Tienda.getInstance().getLosComponentes()) {
				if (componente instanceof Micro) {
					row[0] = componente.getNumeroSerie();
					row[1] = componente.getMarca();
					row[2] = componente.getModelo();
					row[3] = componente.getPrecioVentaActual();
					row[4] = ((Micro)componente).getVelocidad();
					row[5] = ((Micro)componente).getTipoConexion();
					model.addRow(row);
				}
			}
		}

	}

	public static void cargarComponentesMotherBoard() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!Tienda.getInstance().getLosComponentes().isEmpty()) {
			for (Componente componente : Tienda.getInstance().getLosComponentes()) {
				if (componente instanceof MotherBoard) {
					row[0] = componente.getNumeroSerie();
					row[1] = componente.getMarca();
					row[2] = componente.getModelo();
					row[3] = componente.getPrecioVentaActual();
					row[4] = ((MotherBoard)componente).getTipoConector();
					row[5] = ((MotherBoard)componente).getTipoRAM();
					model.addRow(row);
				}
			}
		}

	}

	public static void cargarComponentesRAM() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(!Tienda.getInstance().getLosComponentes().isEmpty()) {
			for (Componente componente : Tienda.getInstance().getLosComponentes()) {
				if (componente instanceof RAM) {
					row[0] = componente.getNumeroSerie();
					row[1] = componente.getMarca();
					row[2] = componente.getModelo();
					row[3] = componente.getPrecioVentaActual();
					row[4] = ((RAM)componente).getCantMemoria();
					row[5] = ((RAM)componente).getTipoMemoria();
					model.addRow(row);
				}
			}
		}

	}
}