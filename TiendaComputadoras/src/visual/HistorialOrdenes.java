package visual;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.OrdenCompra;
import logica.Proveedor;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;



public class HistorialOrdenes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnAceptar;
	private static DefaultTableModel model;
	private static Object[] row;

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


 

	public HistorialOrdenes() {
		setTitle("Historial Ordenes de Compra");
		
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
					String[] header = {"Código Orden","No. Serie","Tipo de Componente","Marca","Modelo","Cantidad","RNC","Precio Total"};
					model.setColumnIdentifiers(header);
					table = new JTable();
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
			buttonPane.add(btnAceptar);
		}
		cargarOrdenes();
	}
	{

	}
	public static void cargarOrdenes() {
		int i;
		boolean encontrado;
		Proveedor prov;
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		

		for (OrdenCompra aux : Tienda.getInstance().getLasOrdenes()) {
			row[0] = aux.getCodigo();
			row[1] = aux.getCompCompra().getNumeroSerie();
			row[2] = aux.getCompCompra().getClass().getSimpleName();
			row[3] = aux.getCompCompra().getMarca();
			row[4] = aux.getCompCompra().getModelo();
			row[5] = aux.getCantiCompos();
			i=0;
			encontrado=false;
			while(i<Tienda.getInstance().getLosProveedores().size()&&!encontrado) {
				prov = Tienda.getInstance().getLosProveedores().get(i);
				if (prov.getMisOrdenes().contains(aux)) {
					row[6] = prov.getCodigo();
					encontrado=true;
				}
			}
			row[7] = aux.getCostoTotal();
			model.addRow(row);
		}

	}
}


