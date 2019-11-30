package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logica.Administrador;
import logica.Cliente;
import logica.Componente;
import logica.Proveedor;
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
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AgregarProveedor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JFormattedTextField ftxtTelefono;
	private JFormattedTextField ftxtRNC;
	private static DefaultTableModel model;
	private static Object[] row;
	private String codigo="";
	private JTable table;
	private JButton btnListado;
	private JButton btnNuevoCompo;
	private JPanel panel_Componentes;
	private int index;
	private JButton btnAsignarPrecio;
	private ArrayList<Componente> ayudaComp = new ArrayList<Componente>();
	private ArrayList<Float> ayudaPrecio = new ArrayList<Float>();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	public AgregarProveedor(boolean b,Proveedor auxProv) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Tienda.getInstance().getLosCompTemp().clear();
				Tienda.getInstance().getPreciosCadaCompTemp().clear();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarProveedor.class.getResource("/Imagenes/IconAdmin.png")));
		setTitle("Agregar Proveedor");
		setBounds(100, 100, 654, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JPanel panel_RegistroProveedor = new JPanel();
			panel_RegistroProveedor.setBounds(7, 5, 234, 195);
			panel_RegistroProveedor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Registro de Proveedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel_RegistroProveedor);
			panel_RegistroProveedor.setLayout(null);
			{
				JLabel lblRNC = new JLabel("RNC:");
				lblRNC.setBounds(12, 21, 76, 22);
				panel_RegistroProveedor.add(lblRNC);
			}

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(12, 64, 76, 22);
			panel_RegistroProveedor.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtNombre.setBounds(100, 64, 124, 22);
			panel_RegistroProveedor.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblDireccion = new JLabel("Direccion");
			lblDireccion.setBounds(12, 107, 76, 22);
			panel_RegistroProveedor.add(lblDireccion);

			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(100, 107, 124, 22);
			panel_RegistroProveedor.add(txtDireccion);

			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setBounds(12, 150, 76, 22);
			panel_RegistroProveedor.add(lblTelefono);


			{
				MaskFormatter mascaraNumero;
				MaskFormatter mascaraCedula;
				try {

					mascaraNumero = new MaskFormatter("(###) ###-####");
					mascaraNumero.setPlaceholderCharacter('_');
					ftxtTelefono = new JFormattedTextField(mascaraNumero);
					ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtTelefono.setBounds(100, 150, 124, 22);
					panel_RegistroProveedor.add(ftxtTelefono);


					mascaraCedula = new MaskFormatter("###-#######");
					mascaraCedula.setPlaceholderCharacter('_');
					ftxtRNC = new JFormattedTextField(mascaraCedula);
					ftxtRNC.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtRNC.setBounds(100, 21, 124, 22);
					panel_RegistroProveedor.add(ftxtRNC);

					panel_Componentes = new JPanel();
					panel_Componentes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Componentes Proporcionados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					panel_Componentes.setBounds(245, 5, 379, 195);
					contentPanel.add(panel_Componentes);
					panel_Componentes.setLayout(null);

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1&&Tienda.getInstance().getUsuarioActual() instanceof Administrador){
								int index = table.getSelectedRow();

								btnAsignarPrecio.setEnabled(true);
								codigo = String.valueOf(table.getValueAt(index, 0));
							}
						}
					});
					scrollPane.setBounds(12, 16, 355, 140);
					panel_Componentes.add(scrollPane);
					{
						model = new DefaultTableModel();
						String[] header = {"N° de serie","Tipo","Marca","Modelo","Precio"};
						model.setColumnIdentifiers(header);
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								if(table.getSelectedRow()>-1) {
									index = table.getSelectedRow();
									btnAsignarPrecio.setEnabled(true);

								}
							}
						});
						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						table.setModel(model);
						scrollPane.setViewportView(table);
					}

					btnNuevoCompo = new JButton("Nuevo Comp.");
					btnNuevoCompo.setBounds(9, 159, 114, 23);
					panel_Componentes.add(btnNuevoCompo);
					btnNuevoCompo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							AgregarComponente aux = new AgregarComponente(false,null);
							aux.setModal(true);
							aux.setVisible(true);
						}
					});
					btnNuevoCompo.setActionCommand("OK");

					btnListado = new JButton("Listado");
					btnListado.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ListaComponentes aux = new ListaComponentes(true);
							aux.setModal(true);
							aux.setVisible(true);
						}
					});
					btnListado.setBounds(132, 159, 114, 23);
					panel_Componentes.add(btnListado);

					btnAsignarPrecio = new JButton("Asignar Precio");
					btnAsignarPrecio.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							boolean bien=false;
							Float num=(float) 0;
							while(!bien) {
								
								try {
									num = Float.valueOf(JOptionPane.showInputDialog("Introduzca el precio del componente"));
									bien = true;
									btnAsignarPrecio.setEnabled(false);
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Debe introducir un numero");
								}	
							}
							try {
								Tienda.getInstance().getPreciosCadaCompTemp().add(index,num);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Los Precios deben ser ingresados en orden");
							}
							
							try {
								Tienda.getInstance().getPreciosCadaCompTemp().remove(index+1);
							} catch (IndexOutOfBoundsException e) {
							}
							cargarComponentes();
						}
					});
					btnAsignarPrecio.setBounds(255, 158, 114, 23);
					panel_Componentes.add(btnAsignarPrecio);
					btnAsignarPrecio.setEnabled(false);
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
						ayudaComp=arregloComponentes();
						ayudaPrecio=arregloPrecios();
						if (model.getRowCount() != Tienda.getInstance().getPreciosCadaCompTemp().size()) {
							System.out.println(model.getRowCount()+"     "+ Tienda.getInstance().getPreciosCadaCompTemp().size());
							JOptionPane.showMessageDialog(null, "Todos los componentes deben tener un precio");
						}
						else if(Tienda.getInstance().findProveedrobyRNC(ftxtRNC.getText())!=null) {
							JOptionPane.showMessageDialog(null, "Ya existe un proveedor registrado con este RNC");
						}
						else {
							if(ftxtRNC.getText().equalsIgnoreCase("___-_______")||txtNombre.getText().isEmpty()||txtDireccion.getText().isEmpty()||ftxtTelefono.getText().equalsIgnoreCase("(___) ___-____")) {
								JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
							}
							else {
								Proveedor aux = new Proveedor(txtNombre.getText(), ftxtTelefono.getText(), txtDireccion.getText(), ftxtRNC.getText(), ayudaComp, ayudaPrecio);
								Tienda.getInstance().getLosProveedores().add(aux);
								Tienda.getInstance().getLosCompTemp().clear();
								Tienda.getInstance().getPreciosCadaCompTemp().clear();
								cargarComponentes();
								limpiar();
								JOptionPane.showMessageDialog(null, "Proveedor añadido con exito");
							}

						}
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
						
						Tienda.getInstance().getLosCompTemp().clear();
						Tienda.getInstance().getPreciosCadaCompTemp().clear();
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		if(!b) {
			panel_Componentes.setEnabled(false);
			btnNuevoCompo.setEnabled(false);
			btnListado.setEnabled(false);
		}
		if(!Tienda.getInstance().getLosCompTemp().isEmpty()) {
			cargarComponentes();
		}

	}
	public static ArrayList<Componente> arregloComponentes() {
		ArrayList<Componente> compos = (ArrayList<Componente>) Tienda.getInstance().getLosCompTemp().clone();
		return compos;
	}
	
	public static ArrayList<Float> arregloPrecios() {
		ArrayList<Float> precios = (ArrayList<Float>)Tienda.getInstance().getPreciosCadaCompTemp().clone() ;
		return precios;
	}
	public static void cargarComponentes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		int i=0;
		if(!Tienda.getInstance().getLosCompTemp().isEmpty()) {
			for (Componente aux : Tienda.getInstance().getLosCompTemp()) {
				if (aux != null) {
					row[0] = aux.getNumeroSerie();
					row[1] = aux.getClass().getSimpleName();
					row[2] = aux.getMarca();
					row[3] = aux.getModelo();
					try {
						row[4] = Tienda.getInstance().getPreciosCadaCompTemp().get(i).toString();
					} catch (IndexOutOfBoundsException e) {
						row[4] = "";
					}
					i++;
					model.addRow(row);
				}
			}
		}

	}

	protected void limpiar() {
		ftxtRNC.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		ftxtTelefono.setText("");

	}
}