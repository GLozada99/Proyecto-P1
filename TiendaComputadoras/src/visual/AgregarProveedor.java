package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Componente;
import logica.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

public class AgregarProveedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JFormattedTextField ftxtTelefono;
	private JFormattedTextField ftxtRNC;
	private static DefaultTableModel model;
	private static Object[] row;
	private String codigo="";
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarProveedor dialog = new AgregarProveedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarProveedor() {
		setTitle("Agregar Proveedor");
		setBounds(100, 100, 560, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JPanel panel_RegistroProveedor = new JPanel();
			panel_RegistroProveedor.setBounds(7, 5, 234, 164);
			panel_RegistroProveedor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Registro de Proveedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel_RegistroProveedor);
			panel_RegistroProveedor.setLayout(null);
			{
				JLabel lblRNC = new JLabel("RNC:");
				lblRNC.setBounds(12, 27, 76, 22);
				panel_RegistroProveedor.add(lblRNC);
			}

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(12, 62, 76, 22);
			panel_RegistroProveedor.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtNombre.setBounds(100, 62, 124, 22);
			panel_RegistroProveedor.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblDireccion = new JLabel("Direccion");
			lblDireccion.setBounds(12, 97, 76, 22);
			panel_RegistroProveedor.add(lblDireccion);

			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(100, 97, 124, 22);
			panel_RegistroProveedor.add(txtDireccion);

			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setBounds(12, 132, 76, 22);
			panel_RegistroProveedor.add(lblTelefono);


			{
				MaskFormatter mascaraNumero;
				MaskFormatter mascaraCedula;
				try {

					mascaraNumero = new MaskFormatter("(###) ###-####");
					mascaraNumero.setPlaceholderCharacter('_');
					ftxtTelefono = new JFormattedTextField(mascaraNumero);
					ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtTelefono.setBounds(100, 132, 124, 22);
					panel_RegistroProveedor.add(ftxtTelefono);


					mascaraCedula = new MaskFormatter("###-#######");
					mascaraCedula.setPlaceholderCharacter('_');
					ftxtRNC = new JFormattedTextField(mascaraCedula);
					ftxtRNC.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtRNC.setBounds(100, 27, 124, 22);
					panel_RegistroProveedor.add(ftxtRNC);

					JPanel panel_Componentes = new JPanel();
					panel_Componentes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Componentes Proporcionados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					panel_Componentes.setBounds(251, 5, 283, 164);
					contentPanel.add(panel_Componentes);
					panel_Componentes.setLayout(null);

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(6, 16, 271, 114);
					panel_Componentes.add(scrollPane);
					{

						model = new DefaultTableModel();
						String[] header = {"N° de serie","Marca","Modelo"};
						model.setColumnIdentifiers(header);
						table = new JTable();
						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						table.setModel(model);
						scrollPane.setViewportView(table);
					}
					
									JButton btnNuevoCompo = new JButton("Nuevo Comp.");
									btnNuevoCompo.setBounds(6, 135, 133, 23);
									panel_Componentes.add(btnNuevoCompo);
									btnNuevoCompo.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											AgregarComponente aux = new AgregarComponente(false);
											aux.setModal(true);
											aux.setVisible(true);
										}
									});
									btnNuevoCompo.setActionCommand("OK");
									
									JButton btnListado = new JButton("Listado");
									btnListado.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											ListaComponentes aux = new ListaComponentes();
											aux.setModal(true);
											aux.setVisible(true);
										}
									});
									btnListado.setBounds(144, 135, 133, 23);
									panel_Componentes.add(btnListado);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	public static void cargarComponentes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
			
		for (Componente aux : Tienda.getInstance().getLosComponentes()) {
			row[0] = aux.getNumeroSerie();
			row[1] = aux.getMarca();
			row[2] = aux.getModelo();
			model.addRow(row);
		}
	}

	protected void limpiar() {
		ftxtRNC.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		ftxtTelefono.setText("");
	}
}