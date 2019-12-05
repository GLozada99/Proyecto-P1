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
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

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
import logica.Vendedor;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.SystemColor;

public class Principal extends JFrame implements  Runnable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private JLabel lblHora ;
	private int hora, minutos, segundos;
	private Calendar calendario;
	private Thread h1;
	private JPanel panel1;
	private JPanel panel2;
	private DefaultPieDataset data = new DefaultPieDataset();
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1373, 739);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, screenSize.width-100, screenSize.width-75));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(86, 130, 163));
		setJMenuBar(menuBar); 

		JMenu mnAdministracion = new JMenu("Administracion");
		mnAdministracion.setForeground(new Color(255, 255, 255));
		mnAdministracion.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/IconAdministrador.png")));
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
				NuevoCombo aux = new NuevoCombo(null);
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


		panel1 = new JPanel();
		panel1.setBounds(17, 14, 652, 631);
		contentPane.add(panel1);
		new Thread() {
			int i=0;
			public void run() {
				while (true) {

					try {
						Thread.sleep(100);
						if(i>3) {
							data.clear();
							i=-1;
							showGraf1();
						}
						i++;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}

		}.start();
		showGraf1();

		panel2 = new JPanel();
		panel2.setBounds(686, 14, 652, 631);
		contentPane.add(panel2);
		new Thread() {
			int i=0;
			public void run() {
				while (true) {

					try {
						Thread.sleep(100);
						if(i>3) {
							dataset.clear();
							i=-1;
							showGraf2();
						}
						i++;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}

		}.start();
		showGraf2();
		}


	private void showGraf1() {

		// Fuente de Datos
		// DefaultPieDataset data = new DefaultPieDataset();
		ArrayList<Integer> componeteVenta= Tienda.getInstance().componenteMasVendidoTipo();



		try {
			if(componeteVenta.get(0)!=null)
				data.setValue("Disco Duro", componeteVenta.get(0));

		} catch (NullPointerException |IndexOutOfBoundsException e) {

		}
		try {
			if(componeteVenta.get(1)!=null)
				data.setValue("Microprocesador", componeteVenta.get(1));

		} catch (NullPointerException |IndexOutOfBoundsException e) {

		}
		try {
			if(componeteVenta.get(2)!=null)
				data.setValue("Memoria RAM", componeteVenta.get(2));

		} catch (NullPointerException |IndexOutOfBoundsException e) {

		}
		try {
			if(componeteVenta.get(3)!=null)
				data.setValue("Tarjeta Madre", componeteVenta.get(3));

		} catch (NullPointerException |IndexOutOfBoundsException e) {

		}


		// Creando el Grafico
		JFreeChart chart = ChartFactory.createPieChart( "Ventas de componentes por tipo",  data,  true, true, false);
		panel1.setLayout(null);

		// Crear el Panel del Grafico con ChartPanel
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		chart.setBackgroundPaint( SystemColor.inactiveCaption);

		chartPanel.setBounds(0, 0, 652, 631);
		chartPanel.setPreferredSize(new java.awt.Dimension(panel1.getWidth(), panel1.getHeight()));
		panel1.add(chartPanel);
	}
	private void showGraf2() {


		// Fuente de Datos
		
		ArrayList<Persona> vendedores = Tienda.getInstance().vendedoresMasVentas();
		try {
			dataset.addValue(((Vendedor)vendedores.get(0)).getVentas(),vendedores.get(0).getNombre(),"");
		} catch (NullPointerException |IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			dataset.addValue(((Vendedor)vendedores.get(1)).getVentas(), vendedores.get(1).getNombre(), "");
		} catch (NullPointerException |IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			dataset.addValue(((Vendedor)vendedores.get(2)).getVentas(), vendedores.get(2).getNombre(),"");
		} catch (NullPointerException |IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			dataset.addValue(((Vendedor)vendedores.get(3)).getVentas(), vendedores.get(3).getNombre(),"");
		} catch (NullPointerException |IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			
			dataset.addValue(((Vendedor)vendedores.get(4)).getVentas(), vendedores.get(4).getNombre(),"" );
		} catch (NullPointerException |IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		

		// Creando el Grafico
		JFreeChart chart = ChartFactory.createBarChart3D
				("Vendedores con mas ventas","", "Nombre", 
						dataset, PlotOrientation.VERTICAL, true,true, false);
		chart.setBackgroundPaint(SystemColor.inactiveCaption);
		chart.getTitle().setPaint(Color.black); 
		CategoryPlot p = chart.getCategoryPlot(); 
		p.setRangeGridlinePaint(Color.red); 
		panel2.setLayout(null);

		// Crear el Panel del Grafico con ChartPanel
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		//  chart.getPlot().setBackgroundPaint( SystemColor.inactiveCaptionBorder );

		chartPanel.setBounds(0, 0, 652, 631);
		chartPanel.setPreferredSize(new java.awt.Dimension(panel2.getWidth(),panel2.getHeight()));
		panel2.add(chartPanel);

	}

	public void calcula() {
		Calendar calendario = new GregorianCalendar();
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);


		
			}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

		

	}





