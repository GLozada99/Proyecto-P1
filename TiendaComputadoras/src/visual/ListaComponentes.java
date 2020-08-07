package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrador;
import logica.Combo;
import logica.Componente;
import logica.DiscoDuro;
import logica.Micro;
import logica.MotherBoard;
import logica.Proveedor;
import logica.RAM;
import logica.Tienda;
import sql.SQLConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ListaComponentes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private String codigo;
	private JComboBox <String> cbxComp;
	private String[] encabezadoDD = {"No. Serie","Marca","Modelo","Precio Venta Act.","Precio Compra Act.","Cant. Real","Cant. Min","Cant. Max","Almacenamiento","Tipo Conexión" };
	private String[] encabezadoMicro = {"No. Serie","Marca","Modelo","Precio Venta Act.","Precio Compra Act.","Cant. Real","Cant. Min","Cant. Max","Velocidad","Tipo Conexión" };
	private String[] encabezadoMother = {"No. Serie","Marca","Modelo","Precio Venta Act.","Precio Compra Act.","Cant. Real","Cant. Min","Cant. Max","Tipo Conector","Tipo RAM" };
	private String[] encabezadoRAM = {"No. Serie","Marca","Modelo","Precio Venta Act.","Precio Compra Act.","Cant. Real","Cant. Min","Cant. Max","Cant Memoria","Tipo Memoria" };


	/**
	 * Launch the application.
	 */

	/*
	public static void main(String[] args) {

		try {
			ListaComponentes dialog = new ListaComponentes(boolean);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListaComponentes(boolean AgregarCompProveedores) {
		setTitle("Lista Componentes");
		setBounds(100, 100, 1250, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(234, 238, 249));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{	
			JPanel panelList = new JPanel();
			panelList.setBorder(null);
			panelList.setBounds(15, 76, 1219, 558);
			contentPanel.add(panelList);
			panelList.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 1219, 558);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelList.add(scrollPane);
				{
					model = new DefaultTableModel();

					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if(table.getSelectedRow()>-1&&Tienda.getInstance().getUsuarioActual() instanceof Administrador) {
								int index = table.getSelectedRow();
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);
								codigo = String.valueOf(table.getValueAt(index, 0));
							}
						}
					});
					scrollPane.setViewportView(table);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
				}
			}
			JPanel panelComp = new JPanel();
			panelComp.setBackground(new Color(234, 238, 249));
			panelComp.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Componente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelComp.setBounds(15, 5, 1219, 63);
			contentPanel.add(panelComp);
			panelComp.setLayout(null);

			cbxComp = new JComboBox();
			cbxComp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					if(cbxComp.getSelectedIndex()==0) {
						model.setColumnIdentifiers(encabezadoDD);
						cargarComponentesDD();
					}
					if(cbxComp.getSelectedIndex()==1) {
						model.setColumnIdentifiers(encabezadoMicro);
						cargarComponentesMicro();
					}
					if(cbxComp.getSelectedIndex()==2) {
						model.setColumnIdentifiers(encabezadoMother);
						cargarComponentesMotherBoard();
					}
					if(cbxComp.getSelectedIndex()==3) {
						model.setColumnIdentifiers(encabezadoRAM);
						cargarComponentesRAM();
					}
					//	btnModificar.setEnabled(false);
					//btnEliminar.setEnabled(false);
				}
			});
			cbxComp.setModel(new DefaultComboBoxModel(new String[] {"Disco Duro", "Microprocesador", "Motherboard", "RAM"}));
			cbxComp.setBounds(12, 26, 169, 22);
			panelComp.add(cbxComp);
			cbxComp.setSelectedIndex(0);
		}


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(234, 238, 249));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (AgregarCompProveedores) {
							Componente aux = Tienda.getInstance().findComponentebyNumeroSerie(codigo);
							if(!Tienda.getInstance().getLosCompTemp().contains(aux)) {
								Tienda.getInstance().getLosCompTemp().add(aux);
								AgregarProveedor.cargarComponentes();
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "El Componente seleccionado ya se encuentra en la lista");
							}
						}
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				btnAceptar.setEnabled(false);
				if (AgregarCompProveedores) {
					btnAceptar.setEnabled(true);
				}
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Componente auxComp = Tienda.getInstance().findComponentebyNumeroSerie(codigo);
						AgregarComponente aux = new AgregarComponente(true,auxComp);
						aux.setModal(true);
						aux.setVisible(true);
						if(cbxComp.getSelectedIndex()==0) {
							cargarComponentesDD();
						}
						if(cbxComp.getSelectedIndex()==1) {
							cargarComponentesMicro();
						}
						if(cbxComp.getSelectedIndex()==2) {
							cargarComponentesMotherBoard();
						}
						if(cbxComp.getSelectedIndex()==3) {
							cargarComponentesRAM();
						}



					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Componente aux = Tienda.getInstance().findComponentebyNumeroSerie(codigo);
						int i=1;
						i = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el componente:"+" "+aux.getNumeroSerie()+"?");
						if(i==0) {
							boolean result = Tienda.getInstance().eliminarComponente(aux);
							if(result) {
								SQLConnection.EliminarComponente(aux);
								JOptionPane.showMessageDialog(null, "Componente eliminado con exito");
								if (aux instanceof DiscoDuro)
									cargarComponentesDD();
								if (aux instanceof Micro)
									cargarComponentesMicro();
								if (aux instanceof MotherBoard)
									cargarComponentesMotherBoard();
								if (aux instanceof RAM)
									cargarComponentesRAM();
							}else {
								JOptionPane.showMessageDialog(null, "El componente pertenece a un combo. Debe eliminar o modificar el combo");
							}
						}
					}
				});
				btnEliminar.setActionCommand("Cancel");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
		}
		cargarComponentesDD();
	}

	public static void cargarComponentesDD() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Componente componente : Tienda.getInstance().getLosComponentes()) {
			if (componente instanceof DiscoDuro) {
				row[0] = componente.getNumeroSerie();
				row[1] = componente.getMarca();
				row[2] = componente.getModelo();
				row[3] = componente.getPrecioVentaActual();
				row[4] = componente.getPrecioCompraActual();
				row[5] = componente.getCantDisponible();
				row[6] = componente.getCantMin();
				row[7] = componente.getCantMax();
				row[8] = ((DiscoDuro)componente).getCapacidadAlma();
				row[9] = ((DiscoDuro)componente).getTipoConexion();
				model.addRow(row);
			}
		}

	}

	public static void cargarComponentesMicro() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Componente componente : Tienda.getInstance().getLosComponentes()) {
			if (componente instanceof Micro) {
				row[0] = componente.getNumeroSerie();
				row[1] = componente.getMarca();
				row[2] = componente.getModelo();
				row[3] = componente.getPrecioVentaActual();
				row[4] = componente.getPrecioCompraActual();
				row[5] = componente.getCantDisponible();
				row[6] = componente.getCantMin();
				row[7] = componente.getCantMax();
				row[8] = ((Micro)componente).getVelocidad();
				row[9] = ((Micro)componente).getTipoConexion();
				model.addRow(row);
			}
		}

	}

	public static void cargarComponentesMotherBoard() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Componente componente : Tienda.getInstance().getLosComponentes()) {
			if (componente instanceof MotherBoard) {
				row[0] = componente.getNumeroSerie();
				row[1] = componente.getMarca();
				row[2] = componente.getModelo();
				row[3] = componente.getPrecioVentaActual();
				row[4] = componente.getPrecioCompraActual();
				row[5] = componente.getCantDisponible();
				row[6] = componente.getCantMin();
				row[7] = componente.getCantMax();
				row[8] = ((MotherBoard)componente).getTipoConector();
				row[9] = ((MotherBoard)componente).getTipoRAM();
				model.addRow(row);
			}
		}

	}

	public static void cargarComponentesRAM() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Componente componente : Tienda.getInstance().getLosComponentes()) {
			if (componente instanceof RAM) {
				row[0] = componente.getNumeroSerie();
				row[1] = componente.getMarca();
				row[2] = componente.getModelo();
				row[3] = componente.getPrecioVentaActual();
				row[4] = componente.getPrecioCompraActual();
				row[5] = componente.getCantDisponible();
				row[6] = componente.getCantMin();
				row[7] = componente.getCantMax();
				row[8] = ((RAM)componente).getCantMemoria();
				row[9] = ((RAM)componente).getTipoMemoria();
				model.addRow(row);
			}
		}

	}

}
