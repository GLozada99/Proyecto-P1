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
import logica.Factura;
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
import java.util.ArrayList;
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

public class ListaComponentesFactura extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnAceptar;
	private String codigo;
	private JComboBox <String> cbxComp;
	private String[] encabezadoDD = {"No. Serie","Marca","Modelo","Almacenamiento","Tipo Conexión","Costo","Cantidad","Costo total"};
	private String[] encabezadoMicro = {"No. Serie","Marca","Modelo","Velocidad","Tipo Conexión","Costo","Cantidad","Costo total" };
	private String[] encabezadoMother = {"No. Serie","Marca","Modelo","Tipo Conector","Tipo RAM","Costo","Cantidad","Costo total"};
	private String[] encabezadoRAM = {"No. Serie","Marca","Modelo","Cant Memoria","Tipo Memoria","Costo","Cantidad","Costo total" };


	/**
	 * Launch the application.
	 */

	/*
	public static void main(String[] args) {

		try {
			ListaComponentesFactura dialog = new ListaComponentesFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaComponentesFactura(Factura auxFactura) {
		setTitle("Detalle Componentes Factura");

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
						cargarComponentesDD(auxFactura);
					}
					if(cbxComp.getSelectedIndex()==1) {
						model.setColumnIdentifiers(encabezadoMicro);
						cargarComponentesMicro(auxFactura);
					}
					if(cbxComp.getSelectedIndex()==2) {
						model.setColumnIdentifiers(encabezadoMother);
						cargarComponentesMotherBoard(auxFactura);
					}
					if(cbxComp.getSelectedIndex()==3) {
						model.setColumnIdentifiers(encabezadoRAM);
						cargarComponentesRAM(auxFactura);
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
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				btnAceptar.setEnabled(false);
			}
		}
		cargarComponentesDD(auxFactura);
	}

	public static void cargarComponentesDD(Factura auxFactura) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(auxFactura.getLosComponentes()!=null) {
			if(!auxFactura.getLosComponentes().isEmpty()) {
				for (Componente componente : auxFactura.getLosComponentes()) {
					if (componente instanceof DiscoDuro) {
						row[0] = componente.getNumeroSerie();
						row[1] = componente.getMarca();
						row[2] = componente.getModelo();
						row[3] = ((DiscoDuro)componente).getCapacidadAlma();
						row[4] = ((DiscoDuro)componente).getTipoConexion();
						row[5] = componente.getPrecioVentaActual();
						row[6] = auxFactura.getCantiCompo(componente);
						row[7] = componente.getPrecioVentaActual()*auxFactura.getCantiCompo(componente);
						model.addRow(row);

					}
				}
			}
		}

	}

	public static void cargarComponentesMicro( Factura auxFactura) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(auxFactura.getLosComponentes()!=null) {
			if(!auxFactura.getLosComponentes().isEmpty()) {
				for (Componente componente : auxFactura.getLosComponentes()) {
					if (componente instanceof Micro) {
						row[0] = componente.getNumeroSerie();
						row[1] = componente.getMarca();
						row[2] = componente.getModelo();
						row[3] = ((Micro)componente).getVelocidad();
						row[4] = ((Micro)componente).getTipoConexion();
						row[5] = componente.getPrecioVentaActual();
						row[6] = auxFactura.getCantiCompo(componente);
						row[7] = componente.getPrecioVentaActual()*auxFactura.getCantiCompo(componente);
						model.addRow(row);
					}
				}
			}
		}

	}

	public static void cargarComponentesMotherBoard( Factura auxFactura) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(auxFactura.getLosComponentes()!=null) {
			if(!auxFactura.getLosComponentes().isEmpty()) {
				for (Componente componente : auxFactura.getLosComponentes()) {
					if (componente instanceof MotherBoard) {
						row[0] = componente.getNumeroSerie();
						row[1] = componente.getMarca();
						row[2] = componente.getModelo();
						row[3] = ((MotherBoard)componente).getTipoConector();
						row[4] = ((MotherBoard)componente).getTipoRAM();
						row[5] = componente.getPrecioVentaActual();
						row[6] = auxFactura.getCantiCompo(componente);
						row[7] = componente.getPrecioVentaActual()*auxFactura.getCantiCompo(componente);
						model.addRow(row);
					}
				}
			}
		}
	}

	public static void cargarComponentesRAM( Factura auxFactura) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(auxFactura.getLosComponentes()!=null) {
			if(!auxFactura.getLosComponentes().isEmpty()) {
				for (Componente componente : auxFactura.getLosComponentes()) {
					if (componente instanceof RAM) {
						row[0] = componente.getNumeroSerie();
						row[1] = componente.getMarca();
						row[2] = componente.getModelo();
						row[3] = ((RAM)componente).getCantMemoria();
						row[4] = ((RAM)componente).getTipoMemoria();
						row[5] = componente.getPrecioVentaActual();
						row[6] = auxFactura.getCantiCompo(componente);
						row[7] = componente.getPrecioVentaActual()*auxFactura.getCantiCompo(componente);
						model.addRow(row);
					}
				}
			}
		}

	}

}
