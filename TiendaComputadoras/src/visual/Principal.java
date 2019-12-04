package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrador;
import logica.Cliente;
import logica.Combo;
import logica.Componente;
import logica.DiscoDuro;
import logica.Micro;
import logica.MotherBoard;
import logica.OrdenCompra;
import logica.Persona;
import logica.Proveedor;
import logica.RAM;
import logica.Tienda;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);z
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imagenes/IconPrincipal.png")));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream tienda2;
				ObjectOutputStream tiendaWrite;
				try {
					tienda2 = new  FileOutputStream("TiendaComputadoras.dat");
					tiendaWrite = new ObjectOutputStream(tienda2);
					tiendaWrite.writeObject(Tienda.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		//Cosas para probar
		/*Persona ho = new Cliente("Juan", "809", "Aqui", "402-1383575-0");
		Tienda.getInstance().getLosClientes().add((Cliente) ho);

		Persona ho1 = new Cliente("Juanaa", "809", "Aqui", "402-1111111-0");
		Tienda.getInstance().getLosClientes().add((Cliente) ho1);
		ArrayList<Componente> aux = new ArrayList<>();
		ArrayList<Float> aux2 = new ArrayList<>();
		aux2.add((float) 250);
		aux2.add((float) 350);
		aux2.add((float) 450);
		aux2.add((float) 550);

		Componente dd = new DiscoDuro("CMP-"+Tienda.getInstance().getGeneradorCodigoComponentes(), "Seagate", "XS", 3, 10, 1000, "2TB", "2");
		aux.add(dd);Tienda.getInstance().agregarComponente(dd);Tienda.getInstance().primeraOrdenCompra(dd);dd.setCantDisponible(5);

		Componente ram = new RAM("CMP-"+Tienda.getInstance().getGeneradorCodigoComponentes(), "Dell", "XS", 1, 13, 800, "No se", "6");
		aux.add(ram);Tienda.getInstance().agregarComponente(ram);Tienda.getInstance().primeraOrdenCompra(ram);ram.setCantDisponible(2);

		Componente micro = new Micro("CMP-"+Tienda.getInstance().getGeneradorCodigoComponentes(), "Ras", "XS", 4, 16, 750,"Se menos", "3");
		aux.add(micro);Tienda.getInstance().agregarComponente(micro);Tienda.getInstance().primeraOrdenCompra(micro);micro.setCantDisponible(51);

		Componente mb = new MotherBoard("CMP-"+Tienda.getInstance().getGeneradorCodigoComponentes(), "HP", "XS", 6, 12, 1800,"Nada", "5");
		aux.add(mb);Tienda.getInstance().agregarComponente(mb);Tienda.getInstance().primeraOrdenCompra(mb);mb.setCantDisponible(3);

	//	OrdenCompra p = new OrdenCompra("OC-"+Tienda.getInstance().getGeneradorCodigoOrdenCompra(),dd, 6);

	//	Combo comb = new Combo(aux, "Combo3", Tienda.getInstance().precioTotalComponentes(aux), 25, 2);
		//Tienda.getInstance().agregarCombo(comb);

		Persona pr1 = new Proveedor("Juan", "0192", "Alla", "12123", aux, aux2);
		Tienda.getInstance().getLosProveedores().add((Proveedor) pr1);*/
		/*Tienda.getInstance().getLasFacturas().clear();
		Tienda.getInstance().getLasOrdenes().clear();
		Tienda.getInstance().getLosClientes().clear();
		Tienda.getInstance().getLosCombo().clear();
		Tienda.getInstance().getLosComponentes().clear();
		Tienda.getInstance().getLosProveedores().clear();
		Tienda.getInstance().getLosUsuarios().clear();
		Tienda.getInstance().getOrdenesSinProcesar().clear();*/
		//Tienda.getInstance().getOrdenesSinProcesar().add(p);
		//Termina

		dim = super.getToolkit().getScreenSize(); 
		super.setSize(dim.width, dim.height);//-100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1937, 1045);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(86, 130, 163));
		setJMenuBar(menuBar); 

		JMenu mnAdministracion = new JMenu("Administracion");
		mnAdministracion.setForeground(new Color(255, 255, 255));
		mnAdministracion.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/gear_in.png")));
		mnAdministracion.setEnabled(false);
		menuBar.add(mnAdministracion);

		JMenuItem mntmAgregarComponentes = new JMenuItem("Agregar Componentes");
		mntmAgregarComponentes.setForeground(new Color(86, 130, 163));
		mntmAgregarComponentes.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconAgregarComponente.png")));
		mntmAgregarComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarComponente aux = new AgregarComponente(true,null);
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnAdministracion.add(mntmAgregarComponentes);


		JMenuItem mntmAgregarProveedores = new JMenuItem("Agregar Proveedores");
		mntmAgregarProveedores.setForeground(new Color(86, 130, 163));
		mntmAgregarProveedores.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconAdmin.png")));
		mntmAgregarProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarProveedor aux = new AgregarProveedor(true, null);
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnAdministracion.add(mntmAgregarProveedores);

		JMenuItem mntmNuevoCombo = new JMenuItem("Nuevo Combo");
		mntmNuevoCombo.setForeground(new Color(86, 130, 163));
		mntmNuevoCombo.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconAgregarCombopng.png")));
		mntmNuevoCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuevoCombo aux = new NuevoCombo();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});

		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mntmNuevoUsuario.setForeground(new Color(86, 130, 163));
		mntmNuevoUsuario.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconUsuario.png")));
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoUsuario aux = new NuevoUsuario(null);
				System.out.println("Why?");
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnAdministracion.add(mntmNuevoUsuario);
		mnAdministracion.add(mntmNuevoCombo);

		JMenuItem mntmAsignarOrdenDe = new JMenuItem("Asignar orden de compra");
		mntmAsignarOrdenDe.setForeground(new Color(86, 130, 163));
		mntmAsignarOrdenDe.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconOrdenesCompra.png")));
		mnAdministracion.add(mntmAsignarOrdenDe);
		mntmAsignarOrdenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdenesPorProcesar aux = new OrdenesPorProcesar();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});

		JMenu mnFacturacion = new JMenu("Facturaci\u00F3n");
		mnFacturacion.setForeground(new Color(255, 255, 255));
		mnFacturacion.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconFacturar.png")));
		menuBar.add(mnFacturacion);

		JMenuItem mntmNuevaFactura = new JMenuItem("Nueva Factura");
		mntmNuevaFactura.setForeground(new Color(86, 130, 163));
		mntmNuevaFactura.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconFactura.png")));
		mntmNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaFactura aux = new NuevaFactura();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnFacturacion.add(mntmNuevaFactura);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Abonar/Saldar Credito Cliente");
		mntmNewMenuItem_1.setForeground(new Color(86, 130, 163));
		mntmNewMenuItem_1.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconAbonarSaldo.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PagarCuentasCliente aux = new PagarCuentasCliente();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnFacturacion.add(mntmNewMenuItem_1);

		JMenu mnListados = new JMenu("Listados");
		mnListados.setForeground(new Color(255, 255, 255));
		mnListados.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconLista.png")));
		menuBar.add(mnListados);

		JMenuItem mntmListaClientes = new JMenuItem("Lista Clientes");
		mntmListaClientes.setForeground(new Color(86, 130, 163));
		mntmListaClientes.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaClientes.png")));
		mntmListaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes aux = new ListaClientes();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		
				JMenuItem mntmListaUsuarios = new JMenuItem("Lista Usuarios");
				mntmListaUsuarios.setForeground(new Color(86, 130, 163));
				mntmListaUsuarios.setEnabled(false);
				mntmListaUsuarios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListaUsuarios aux = new ListaUsuarios();
						aux.setModal(true);
						aux.setVisible(true);
					}
				});
				
				mntmListaUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaUsuario.png")));
				mnListados.add(mntmListaUsuarios);
		mnListados.add(mntmListaClientes);

		JMenuItem mntmListaProveedores = new JMenuItem("Lista Proveedores");
		mntmListaProveedores.setForeground(new Color(86, 130, 163));
		mntmListaProveedores.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaProveedor.png")));
		mntmListaProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProveedores aux = new ListaProveedores(false);
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		if(Tienda.getInstance().getUsuarioActual() instanceof Administrador) {			
			mnAdministracion.setEnabled(true);
			mntmListaUsuarios.setEnabled(true);
			
		}
		mnListados.add(mntmListaProveedores);

		JMenuItem mntmListaComponentes = new JMenuItem("Lista Componentes");
		mntmListaComponentes.setForeground(new Color(86, 130, 163));
		mntmListaComponentes.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaComponentes.png")));
		mntmListaComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaComponentes aux = new ListaComponentes(false);
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnListados.add(mntmListaComponentes);

		JMenuItem mntmListaCombos = new JMenuItem("Lista Combos");
		mntmListaCombos.setForeground(new Color(86, 130, 163));
		mntmListaCombos.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaCombos.png")));
		mntmListaCombos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCombos aux = new ListaCombos();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnListados.add(mntmListaCombos);

		JMenuItem mntmHistorialFacturas = new JMenuItem("Historial Facturas");
		mntmHistorialFacturas.setForeground(new Color(86, 130, 163));
		mntmHistorialFacturas.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaFacturar.png")));
		mntmHistorialFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HistorialFacturas aux = new HistorialFacturas();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnListados.add(mntmHistorialFacturas);

		JMenuItem mntmHistorialOrdenes = new JMenuItem("Historial Ordenes de Compra");
		mntmHistorialOrdenes.setForeground(new Color(86, 130, 163));
		mntmHistorialOrdenes.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconListaOrdenCompra.png")));
		mntmHistorialOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistorialOrdenes aux = new HistorialOrdenes();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnListados.add(mntmHistorialOrdenes);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(13, 14, 940, 461);
		contentPane.add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(966, 14, 940, 461);
		contentPane.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(13, 489, 940, 461);
		contentPane.add(panel3);
		
		JPanel panel4 = new JPanel();
		panel4.setBounds(966, 489, 940, 461);
		contentPane.add(panel4);
	}
}