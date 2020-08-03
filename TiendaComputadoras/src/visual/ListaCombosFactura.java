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

public class ListaCombosFactura extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnAceptar;
	private JButton btnDetalles;
	private String[] encabezado = {"Nombre","Costo","Cantidad","Costo Total"};
	private String codigo;

	/**
	 * Launch the application.
	 */


	/*public static void main(String[] args) {

		try {
			ListaCombosFactura dialog = new ListaCombosFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public ListaCombosFactura(Factura auxFactura) {
		setTitle("Detalle Combos Factura");

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
			panelList.setBounds(15, 13, 1219, 621);
			contentPanel.add(panelList);
			panelList.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 1219, 621);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelList.add(scrollPane);
				{
					model = new DefaultTableModel();
					model.setColumnIdentifiers(encabezado);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1&&Tienda.getInstance().getUsuarioActual() instanceof Administrador) {
								int index = table.getSelectedRow();
								btnDetalles.setEnabled(true);
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
				{
					btnDetalles = new JButton("Detalles");
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
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				btnAceptar.setEnabled(false);
			}
		}
		cargarCombos(auxFactura);
	}

	public static void cargarCombos(Factura auxFactura) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(auxFactura.getLosCombos()!=null) {
			if(!auxFactura.getLosCombos().isEmpty()) {
				for (Combo combo : auxFactura.getLosCombos()) {
					row[0] = combo.getNombre();
					row[1] = combo.precioCombo();
					row[2] = auxFactura.getCantiUnCombo(combo);
					row[3] = combo.precioCombo()*auxFactura.getCantiUnCombo(combo);
					model.addRow(row);
				}
			}
		}

	}

}
