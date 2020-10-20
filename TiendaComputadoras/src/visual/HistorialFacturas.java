package visual;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Cliente;
import logica.Componente;
import logica.DiscoDuro;
import logica.Factura;
import logica.OrdenCompra;
import logica.Proveedor;
import logica.Tienda;
import sql.SQLConnection;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;



public class HistorialFacturas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnAceptar;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnDetalleCompo = null;
	private JButton btnDetalleCombo = null;
	private String codigo = "";

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			OrdenesPorProcesar dialog = new OrdenesPorProcesar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */




	public HistorialFacturas(Cliente auxCli) {
		if(auxCli==null) {
			setTitle("Historial Facturas");
		}else {
			setTitle("Historial de Facturas de "+auxCli.getNombre()+" Cedula: "+auxCli.getCodigo());
		}
		setResizable(false);
		setBounds(100, 100, 1250, 700);
		setLocationRelativeTo(null);
		setResizable(false);
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
					String[] header = {"C�digo","Fecha","C�dula","Nombre del Cliente","Costo Total"};
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								btnDetalleCompo.setEnabled(true);
								btnDetalleCombo.setEnabled(true);
								codigo = String.valueOf(table.getValueAt(index, 0));
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(234, 238, 249));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			btnAceptar = new JButton("Regresar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			{
				btnDetalleCompo = new JButton("Detalle Componentes");
				btnDetalleCompo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Factura auxFact = Tienda.getInstance().findFacturabyCodigo(codigo);
						ListaComponentesFactura aux = new ListaComponentesFactura(auxFact);
						aux.setModal(true);
						aux.setVisible(true);
						//	btnDetalleCompo.setEnabled(false);

					}
				});
				buttonPane.add(btnDetalleCompo);

				btnDetalleCombo = new JButton("Detalle Combos");
				btnDetalleCombo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Factura auxFact = Tienda.getInstance().findFacturabyCodigo(codigo);
						//ListaCombosFactura aux = new ListaCombosFactura(auxFact);
						ListaCombos aux = new ListaCombos(false,auxFact);
						aux.setModal(true);
						aux.setVisible(true);
						//	btnDetalleCombo.setEnabled(false);
					}
				});
				buttonPane.add(btnDetalleCombo);

				btnDetalleCombo.setEnabled(false);
				btnDetalleCompo.setEnabled(false);
			}
			buttonPane.add(btnAceptar);
		}
		cargarFacturas(auxCli);
	}
	{

	}
	public static void cargarFacturas(Cliente auxCli) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

	/*	try (Connection con = DriverManager.getConnection(SQLConnection.getConnectionURL()); Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM F_Obtener_Factura()");
			while (rs.next()) {
				Cliente cliente = Tienda.getInstance().findClientebyCedula(rs.getString("CodCliente"));
				if(auxCli==cliente || auxCli==null) {
					row[0] = rs.getString("Codigo");
					row[1] = rs.getString("Fecha");
					row[2] = rs.getString("CodCliente");
					row[3] = cliente.getNombre();
					row[4] = rs.getFloat("Costo");
					model.addRow(row);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}	*/

		for (Factura aux : Tienda.getInstance().getLasFacturas()) {
			if(auxCli==aux.getElCliente() || auxCli==null) {
				row[0] = aux.getCodigo();
				row[1] = new SimpleDateFormat("yyyy-MM-dd HH:mm a").format(aux.getFecha());
				row[2] = aux.getElCliente().getCodigo();
				row[3] = aux.getElCliente().getNombre();
				//row[4] = Tienda.getInstance().cantComponentes(aux.getLosComponentes(), aux.getCantiComponentes())+Tienda.getInstance().cantCombos(aux.getLosCombos(), aux.getCantiCombos());
				row[4] = aux.getCosto();
				model.addRow(row);
			}
		}
	}
}


