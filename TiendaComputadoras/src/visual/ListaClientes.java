package visual;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrador;
import logica.Cliente;
import logica.Persona;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;



public class ListaClientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnAceptar;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnModificar;
	private JButton btnEliminar;
	private String codigo="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaClientes dialog = new ListaClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */




	public ListaClientes() {
		setTitle("Lista Clientes");

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
					String[] header = {"Cédula","Nombre","Teléfono","Dirección","Crédito"};
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1&&Tienda.getInstance().getUsuarioActual() instanceof Administrador){
								int index = table.getSelectedRow();
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);
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
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnAceptar);

				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ModificarCliente aux = new ModificarCliente(Tienda.getInstance().findClientebyCedula(codigo));
						aux.setModal(true);
						aux.setVisible(true);
						cargarClientes();
						/*dispose();
						ListaClientes refresh = new ListaClientes();
						refresh.setModal(true);
						refresh.setVisible(true);*/
					}
				});
				buttonPane.add(btnModificar);

				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente aux = Tienda.getInstance().findClientebyCedula(codigo);
						int i=1;
						i = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar al cliente:"+" "+aux.getNombre()+"?");
						if(i==0) {
							Tienda.getInstance().getLosClientes().remove(aux);
							JOptionPane.showMessageDialog(null, "Cliente eliminado con exito");
							cargarClientes();
						}
					}
				});
				buttonPane.add(btnEliminar);
			}
		}
		cargarClientes();
	}
	{

	}
	public static void cargarClientes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Cliente aux : Tienda.getInstance().getLosClientes()) {
			row[0] = aux.getCodigo();
			row[1] = aux.getNombre();
			row[2] = aux.getTelefono();
			row[3] = aux.getDireccion();
			row[4] = aux.getCredito();
			model.addRow(row);
		}

	}
}


