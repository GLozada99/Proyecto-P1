package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Cliente;
import logica.Componente;
import logica.DiscoDuro;
import logica.OrdenCompra;
import logica.Persona;
import logica.Tienda;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Cosas para probar
		Persona ho = new Cliente("Juan", "809", "Aqui", "402-1383575-0");
		Tienda.getInstance().getLosClientes().add((Cliente) ho);
		
		Persona ho1 = new Cliente("Juanaa", "809", "Aqui", "402-1111111-0");
		Tienda.getInstance().getLosClientes().add((Cliente) ho1);
		
		Componente dd = new DiscoDuro("CMP-"+Tienda.getInstance().getGeneradorCodigoComponentes(), "Seagate", "XS", 5, 3, 10, 1000, 9000, "2TB", "2");
		OrdenCompra p = new OrdenCompra("OC-"+Tienda.getInstance().getGeneradorCodigoOrdenCompra(),dd, 6);
		Tienda.getInstance().getOrdenesSinProcesar().add(p);
		//Cierre
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAgregar = new JMenu("Agregar");
		menuBar.add(mnAgregar);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mnAgregar.add(mntmClientes);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mnAgregar.add(mntmProveedores);
		
		JMenuItem mntmCombos = new JMenuItem("Combos");
		mnAgregar.add(mntmCombos);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmListaClientes = new JMenuItem("Lista clientes");
		mntmListaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes aux = new ListaClientes();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnListados.add(mntmListaClientes);
		
		JMenuItem mntmListaProveedores = new JMenuItem("Lista proveedores");
		mnListados.add(mntmListaProveedores);
		
		JMenuItem mntmListaComponentes = new JMenuItem("Lista componentes");
		mnListados.add(mntmListaComponentes);
		
		JMenuItem mntmListaCombos = new JMenuItem("Lista combos");
		mnListados.add(mntmListaCombos);
		
		JMenuItem mntmHistorialOrdenes = new JMenuItem("Historial ordenes de compra");
		mnListados.add(mntmHistorialOrdenes);
		
		JMenu mnFacturacion = new JMenu("Facturaci\u00F3n");
		menuBar.add(mnFacturacion);
		
		JMenuItem mntmNuevaFactura = new JMenuItem("Nueva Factura");
		mnFacturacion.add(mntmNuevaFactura);
		
		JMenuItem mntmAsignarOrdenDe = new JMenuItem("Asignar orden de compra");
		mntmAsignarOrdenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdenesPorProcesar aux = new OrdenesPorProcesar();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnFacturacion.add(mntmAsignarOrdenDe);
		frame.getContentPane().setLayout(null);
	}
}
