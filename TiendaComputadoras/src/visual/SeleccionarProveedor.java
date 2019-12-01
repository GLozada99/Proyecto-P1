package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.OrdenCompra;
import logica.Proveedor;
import logica.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeleccionarProveedor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Object[] row;
	private static DefaultTableModel model;
	private String rnc;
	private JButton btnCancelar;
	private JButton btnAceptar;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			SeleccionarProveedor dialog = new SeleccionarProveedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarProveedor(OrdenCompra aux) {
		setTitle("Seleccionar Proveedor");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setBounds(100, 100, 1250, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
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
					String[] encabezado = {"RNC Proveedor","Nombre Proveedor","Telefono Proveedor","Costo Unidad","Costo Total"};
					model.setColumnIdentifiers(encabezado);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							int index = table.getSelectedRow();
							btnAceptar.setEnabled(true);
							rnc = String.valueOf(table.getValueAt(index, 0));


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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Proveedor prov = Tienda.getInstance().findProveedrobyRNC(rnc);
						Tienda.getInstance().hacerCompra(aux, prov);
						JOptionPane.showMessageDialog(null, "La Orden fue procesada exitosamente");
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		cargarProveedores(aux);
		//System.out.println(aux.getCompCompra().getMarca()+" y "+aux.getCompCompra().getModelo());
	}
	public void cargarProveedores(OrdenCompra aux) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

		for (Proveedor proveedor : Tienda.getInstance().getLosProveedores()) {
			if(proveedor.getMisCompos().contains(aux.getCompCompra())) {
				row[0] = proveedor.getCodigo(); 
				row[1] = proveedor.getNombre();
				row[2] = proveedor.getTelefono();
				row[3] = proveedor.getPrecioCompo(aux.getCompCompra());
				row[4] = proveedor.getPrecioCompo(aux.getCompCompra())*aux.getCantiCompos();
				model.addRow(row);
			}

		}

	}


}
