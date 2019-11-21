package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevaFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtPrecioTotal;
	private JTextField txtCodigo;
	private JTextField txtTotalComponetes;
	private JTextField txtTotalCombos;
	private JFormattedTextField ftxtTelefono;
	private JFormattedTextField ftxtCedula;
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
		setBounds(100, 100, 613, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JPanel DatosCliente = new JPanel();
		DatosCliente.setLayout(null);
		DatosCliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DatosCliente.setBounds(0, 0, 597, 98);
		contentPanel.add(DatosCliente);
		
		JLabel lblCedula = new JLabel("Cedula: ");
		lblCedula.setBounds(19, 25, 76, 22);
		DatosCliente.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(19, 60, 76, 22);
		DatosCliente.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		txtNombre.setBounds(90, 58, 185, 22);
		DatosCliente.add(txtNombre);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n: ");
		lblDireccion.setBounds(322, 25, 76, 22);
		DatosCliente.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(393, 26, 180, 22);
		DatosCliente.add(txtDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setBounds(322, 60, 76, 22);
		DatosCliente.add(lblTelefono);
		
		{
			MaskFormatter mascaraNumero;
			MaskFormatter mascaraCedula;
			try {

				mascaraNumero = new MaskFormatter("(###) ###-####");
				mascaraNumero.setPlaceholderCharacter('_');
				ftxtTelefono = new JFormattedTextField(mascaraNumero);
				ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtTelefono.setBounds(393, 61, 180, 22);
				DatosCliente.add(ftxtTelefono);


				mascaraCedula = new MaskFormatter("###-#######-#");
				mascaraCedula.setPlaceholderCharacter('_');
				ftxtCedula = new JFormattedTextField(mascaraCedula);
				ftxtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtCedula.setBounds(90, 25, 118, 22);
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
		btnBuscar.setIcon(new ImageIcon("C:\\Users\\Oliver\\Desktop\\Lupa.png"));
		btnBuscar.setActionCommand("Buscar");
		btnBuscar.setBounds(229, 25, 46, 25);
		DatosCliente.add(btnBuscar);
		
		JPanel ElementosASeleccionar = new JPanel();
		ElementosASeleccionar.setForeground(Color.BLACK);
		ElementosASeleccionar.setLayout(null);
		ElementosASeleccionar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Elementos a seleccionar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ElementosASeleccionar.setBounds(0, 100, 597, 212);
		contentPanel.add(ElementosASeleccionar);
		
		JButton btnIzquierda = new JButton("<<");
		btnIzquierda.setBounds(244, 125, 112, 27);
		ElementosASeleccionar.add(btnIzquierda);
		
		JButton btnDerecha = new JButton(">>");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDerecha.setBounds(244, 98, 112, 27);
		ElementosASeleccionar.add(btnDerecha);
		
		JComboBox cbxComponentes = new JComboBox();
		cbxComponentes.setModel(new DefaultComboBoxModel(new String[] {"Componentes", "Combos", "Disco Duro", "Microprocesador", "Mother Board", "RAM"}));
		cbxComponentes.setBounds(12, 24, 209, 22);
		ElementosASeleccionar.add(cbxComponentes);
		
		JLabel lblCarritoDeCompras = new JLabel("Carrito de Compras");
		lblCarritoDeCompras.setBounds(353, 27, 234, 16);
		ElementosASeleccionar.add(lblCarritoDeCompras);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 51, 209, 148);
		ElementosASeleccionar.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(378, 54, 209, 148);
		ElementosASeleccionar.add(scrollPane_1);
		
		JPanel Factura = new JPanel();
		Factura.setForeground(Color.BLACK);
		Factura.setLayout(null);
		Factura.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Factura.setBounds(0, 314, 597, 73);
		contentPanel.add(Factura);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setText("0");
		txtPrecioTotal.setEditable(false);
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(471, 11, 116, 22);
		Factura.add(txtPrecioTotal);
		
		JLabel lblPrecioTotal = new JLabel("Precio total: ");
		lblPrecioTotal.setBounds(394, 14, 76, 16);
		Factura.add(lblPrecioTotal);
		
		txtCodigo = new JTextField();
		txtCodigo.setText("N\u00BA: "+Tienda.getInstance().getGeneradorCodigoFactura());
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(511, 44, 76, 20);
		Factura.add(txtCodigo);
		
		JLabel lblTotalComponentes = new JLabel("Total componentes: ");
		lblTotalComponentes.setBounds(10, 14, 117, 16);
		Factura.add(lblTotalComponentes);
		
		txtTotalComponetes = new JTextField();
		txtTotalComponetes.setEditable(false);
		txtTotalComponetes.setColumns(10);
		txtTotalComponetes.setBounds(139, 11, 46, 22);
		Factura.add(txtTotalComponetes);
		
		JLabel lblTotalCombos = new JLabel("Total combos: ");
		lblTotalCombos.setBounds(10, 45, 117, 16);
		Factura.add(lblTotalCombos);
		
		txtTotalCombos = new JTextField();
		txtTotalCombos.setEditable(false);
		txtTotalCombos.setColumns(10);
		txtTotalCombos.setBounds(139, 43, 46, 22);
		Factura.add(txtTotalCombos);
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
	}
}