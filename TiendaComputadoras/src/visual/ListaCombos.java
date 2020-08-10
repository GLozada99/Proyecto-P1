package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrador;
import logica.Combo;
import logica.Componente;
import logica.Factura;
import logica.Tienda;
import sql.SQLConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;

public class ListaCombos extends JDialog {

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
	private String[] encabezado = new String[3];
	private String[] encabezadoDistinto = new String[4];



	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListaCombos dialog = new ListaCombos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListaCombos(boolean masVendidos, Factura auxFact) {
		if(auxFact!=null) {
			setTitle("Combos en la factura: "+auxFact.getCodigo());
		}else if(!masVendidos) {
			setTitle("Lista Combos");
		}else if(masVendidos) {
			setTitle("Combos mas Vendidos");
		}
		setResizable(false);
		setBounds(100, 100, 1250, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(234, 238, 249));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel();
					if(auxFact==null) {
						if(!masVendidos) {
							encabezado[0] = "Nombre";
							encabezado[1] =	"Descuento";
							encabezado[2] = "Precio";
							model.setColumnIdentifiers(encabezado);

						}else {
							encabezadoDistinto[0] = "Nombre";
							encabezadoDistinto[1] =	"Cantidad Ventas";
							encabezadoDistinto[2] = "Precio";
							encabezadoDistinto[3] = "Dinero Generado";
							model.setColumnIdentifiers(encabezadoDistinto);
						}
					}else {
						encabezadoDistinto[0] = "Nombre";
						encabezadoDistinto[1] = "Costo";
						encabezadoDistinto[2] =	"Cantidad";
						encabezadoDistinto[3] = "Costo Total";
						model.setColumnIdentifiers(encabezadoDistinto);
					}

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
			}
			if(!masVendidos){
				{
					btnModificar = new JButton("Modificar");
					btnModificar.setEnabled(false);
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							NuevoCombo aux = new NuevoCombo(Tienda.getInstance().findCombobyCodigo(codigo),false);
							aux.setModal(true);
							aux.setVisible(true);
							cargarCombos(masVendidos,auxFact);
						}
					});
					btnModificar.setActionCommand("OK");
					buttonPane.add(btnModificar);
					//getRootPane().setDefaultButton(btnModificar);
				}
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.setEnabled(false);
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Combo aux = Tienda.getInstance().findCombobyCodigo(codigo);
							int i=1;
							i = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el combo:"+" "+aux.getNombre()+"?");
							if(i==0) {
								Tienda.getInstance().eliminarCombo(aux);
								JOptionPane.showMessageDialog(null, "Combo eliminado con exito");
								cargarCombos(masVendidos,auxFact);
							}
						}
					});
					btnEliminar.setActionCommand("Cancel");
					buttonPane.add(btnEliminar);
				}
			}else if(auxFact!=null) {
				JButton btnDetalles = new JButton("Detalles");
				btnDetalles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Combo auxC = Tienda.getInstance().findCombobyCodigo(codigo);
						NuevoCombo aux = new NuevoCombo(auxC,true);
						aux.setModal(true);
						aux.setVisible(true);
					}
				});
				buttonPane.add(btnDetalles);
				btnDetalles.setEnabled(false);
			}
		}
		cargarCombos(masVendidos,auxFact);
	}

	public static void cargarCombos(boolean masVendidos,Factura auxFact) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		try (Connection con = DriverManager.getConnection(SQLConnection.getConnectionURL()); Statement stmt = con.createStatement();) {
			if(auxFact==null) {
				if(!masVendidos) {
					ResultSet rs = stmt.executeQuery("SELECT * FROM F_Obtener_Combo()");
					while (rs.next()) {

						row[0] = rs.getString("Nombre");
						row[1] = ((Integer)rs.getInt("Descuento")).toString()+"%";
						row[2] = rs.getFloat("Precio");
						model.addRow(row);
					}
				}else {
					ResultSet rs = stmt.executeQuery("SELECT * FROM F_Obtener_Combo()");//Cambiar eso por la funcion de Angel
					while (rs.next()) {

						row[0] = rs.getString("Nombre");
						row[1] = "Aqui deberia ir la cantidad de ventas";
						row[2] = rs.getFloat("Precio");
						row[3] = "Aqui deberia ir el dinero generado total, que seria: cantidad de ventas * precio";
						model.addRow(row);
					}
				}
			}else {
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM F_Obtener_Combos_Factura('"+auxFact.getCodigo()+"')");
				while(rs.next()) {
					Combo auxCombo = Tienda.getInstance().findCombobyCodigo(rs.getString("NombreCombo"));
					row[0] = auxCombo.getNombre();
					row[1] = auxCombo.getPrecio();
					row[2] = rs.getInt("CantidadCombo");
					row[3] = rs.getInt("CantidadCombo")*auxCombo.getPrecio();
					model.addRow(row);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}	

		/*
		for (Combo combo : Tienda.getInstance().getLosCombo()) {
			row[0] = combo.getNombre();
			row[1] = combo.getDescuento()+"%";
			row[2] = combo.precioCombo();
			model.addRow(row);
		}*/
	}
}
