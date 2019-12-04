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

import logica.Cliente;
import logica.Componente;
import logica.DiscoDuro;
import logica.OrdenCompra;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;



public class OrdenesPorProcesar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnAceptar;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnProcesar;
	private String codigo="";
	private JButton btnEliminar;

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




	public OrdenesPorProcesar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrdenesPorProcesar.class.getResource("/Imagenes/IconOrdenesCompra.png")));
		setTitle("Ordenes Por Procesar");

		setResizable(false);
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
					String[] header = {"Codigo Orden","No. Serie","Tipo de Componente","Marca","Modelo","Cantidad"};
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								btnProcesar.setEnabled(true);
								btnEliminar.setEnabled(true);
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
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				btnProcesar = new JButton("Procesar");
				btnProcesar.setEnabled(false);
				btnProcesar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						OrdenCompra aux = Tienda.getInstance().findOrdenComprabyCodigo(codigo);
						SeleccionarProveedor choose = new SeleccionarProveedor(aux);
						choose.setModal(true);
						choose.setVisible(true);
						cargarOrdenes();
						/*dispose();
						OrdenesPorProcesar refresh = new OrdenesPorProcesar();
						refresh.setModal(true);
						refresh.setVisible(true);*/
					}
				});
				buttonPane.add(btnProcesar);
			}
			btnAceptar = new JButton("Regresar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						OrdenCompra aux = Tienda.getInstance().findOrdenComprabyCodigo(codigo);
						Tienda.getInstance().getOrdenesSinProcesar().remove(aux);
						cargarOrdenes();
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			buttonPane.add(btnAceptar);
		}
		cargarOrdenes();
	}
	{

	}
	public static void cargarOrdenes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		/*row[0] = "1";
		row[1] = "22";
		row[2] = "809";
		row[3] = "Aqui";
		row[4] = "1000";
		model.addRow(row);*/

		for (OrdenCompra aux : Tienda.getInstance().getOrdenesSinProcesar()) {
			row[0] = aux.getCodigo();
			row[1] = aux.getCompCompra().getNumeroSerie();
			row[2] = aux.getCompCompra().getClass().getSimpleName();
			row[3] = aux.getCompCompra().getMarca();
			row[4] = aux.getCompCompra().getModelo();
			row[5] = aux.getCantiCompos();
			model.addRow(row);
		}

	}
}


