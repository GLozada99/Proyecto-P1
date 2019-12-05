package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logica.Combo;
import logica.Componente;
import logica.DiscoDuro;
import logica.Micro;
import logica.MotherBoard;
import logica.RAM;
import logica.Tienda;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class NuevoCombo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private static DefaultTableModel tableModel;
	private static Object[] fila;
	private static JTable table;
	private JComboBox<String> cbxDiscoDuro;
	private JComboBox<String> cbxMicro;
	private JComboBox<String> cbxRAM;
	private JComboBox<String> cbxMotherBoard;
	private ArrayList<Componente> comboComponente;
	private JTextField txtPrecio;
	private ArrayList<Integer> cantisUtil=new ArrayList<Integer>();




	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			NuevoCombo dialog = new NuevoCombo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public NuevoCombo(Combo aux) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoCombo.class.getResource("/Imagenes/IconAgregarCombopng.png")));
		setTitle("Nuevo Combo");
		setBounds(100, 100, 576, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(234, 238, 249));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(234, 238, 249));
		panel.setBorder(new TitledBorder(null, "Datos del Combo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 541, 85);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCdigo = new JLabel("Nombre:");
		lblCdigo.setBounds(22, 25, 65, 14);
		panel.add(lblCdigo);

		JLabel lblPorcentajeDeDescuento = new JLabel("Porcentaje de descuento: ");
		lblPorcentajeDeDescuento.setBounds(255, 25, 158, 14);
		panel.add(lblPorcentajeDeDescuento);

		JSpinner spnDescuento = new JSpinner();
		spnDescuento.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spnDescuento.setBounds(454, 22, 44, 20);
		panel.add(spnDescuento);

		txtNombre = new JTextField();
		txtNombre.setBounds(128, 22, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(22, 55, 65, 14);
		panel.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(128, 52, 86, 20);
		panel.add(txtPrecio);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(234, 238, 249));
		panel_1.setBorder(new TitledBorder(null, "Componentes del Combo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 91, 541, 211);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDiscoDuro = new JLabel("Disco Duro:");
		lblDiscoDuro.setBounds(10, 34, 99, 14);
		panel_1.add(lblDiscoDuro);

		cbxDiscoDuro = new JComboBox();
		cbxDiscoDuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> cantisUtil=new ArrayList<Integer>();
				if(cbxDiscoDuro.getSelectedIndex()!=0&&cbxMicro.getSelectedIndex()!=0&&cbxMotherBoard.getSelectedIndex()!=0&&cbxRAM.getSelectedIndex()!=0) {
					cantisUtil.add(1);cantisUtil.add(1);cantisUtil.add(1);cantisUtil.add(1);
					txtPrecio.setText(""+Tienda.getInstance().precioTotalComponentes(componenteDevolver(), cantisUtil));

				}
			}
		});
		cbxDiscoDuro.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :   Cantidad    :     Capacidad   "}));
		cbxDiscoDuro.setBounds(122, 31, 407, 20);
		panel_1.add(cbxDiscoDuro);

		JLabel lblMemoriaRam = new JLabel("Memoria RAM:");
		lblMemoriaRam.setBounds(10, 121, 102, 14);
		panel_1.add(lblMemoriaRam);

		cbxMicro = new JComboBox();
		cbxMicro.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :     Cantidad   :    Velocidad"}));
		cbxMicro.setBounds(122, 77, 407, 20);
		panel_1.add(cbxMicro);

		JLabel lblMicroprocesador = new JLabel("Microprocesador:");
		lblMicroprocesador.setBounds(10, 80, 102, 14);
		panel_1.add(lblMicroprocesador);

		cbxRAM = new JComboBox();
		cbxRAM.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :     Cantidad   :    Memoria"}));
		cbxRAM.setBounds(122, 123, 407, 20);
		panel_1.add(cbxRAM);

		JLabel lblTarjetaMadre = new JLabel("Tarjeta Madre:");
		lblTarjetaMadre.setBounds(10, 172, 102, 14);
		panel_1.add(lblTarjetaMadre);

		cbxMotherBoard = new JComboBox();
		cbxMotherBoard.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :     Cantidad   :    Tipo RAM"}));
		cbxMotherBoard.setBounds(122, 169, 407, 20);
		panel_1.add(cbxMotherBoard);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(234, 238, 249));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxDiscoDuro.getSelectedIndex()!=0&&cbxMicro.getSelectedIndex()!=0&&cbxMotherBoard.getSelectedIndex()!=0&&cbxRAM.getSelectedIndex()!=0) {
							if(aux==null&&Tienda.getInstance().findCombobyCodigo(txtNombre.getText())!=null) {
								JOptionPane.showMessageDialog(null, "Ya existe un combo con ese nombre");
							}
							else {
								Combo aux= new Combo(componenteDevolver(),txtNombre.getText(), Integer.valueOf(spnDescuento.getValue().toString()), Integer.valueOf(txtPrecio.getText()));
								Tienda.getInstance().agregarCombo(aux);
								cbxDiscoDuro.setSelectedIndex(0);cbxMicro.setSelectedIndex(0);cbxMotherBoard.setSelectedIndex(0);cbxRAM.setSelectedIndex(0);
								JOptionPane.showMessageDialog(null, "Combo creado con exito");
							}
							if(aux!=null) {
								aux.setNombre(txtNombre.getText());
								aux.setDescuento(Integer.valueOf(spnDescuento.getValue().toString()));
								aux.setPrecio(Float.valueOf(txtPrecio.getText()));
								aux.setComponentes(componenteDevolver());
								
							}
						}


					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			MotherBoardCbx();
			DiscoDuroCbx();
			MicroCbx();
			RAMCbx();
			if(aux!=null) {
				txtNombre.setText(aux.getNombre());
				txtPrecio.setText(""+aux.getPrecio());
				spnDescuento.setValue(Integer.valueOf(aux.getDescuento()));
				for (Componente componente : aux.getComponentes()) {
					for (int i = 0; i < cbxDiscoDuro.getItemCount(); i++) {
						Componente auxComp= Tienda.getInstance().findComponentebyNumeroSerie(cbxDiscoDuro.getItemAt(i).split(":")[0]);
						if(componente.equals(auxComp)) {
							cbxDiscoDuro.setSelectedIndex(i);
						}
					}
					for (int i = 0; i < cbxMicro.getItemCount(); i++) {
						Componente auxComp= Tienda.getInstance().findComponentebyNumeroSerie(cbxMicro.getItemAt(i).split(":")[0]);
						if(componente.equals(auxComp)) {
							cbxMicro.setSelectedIndex(i);
						}
					}
					for (int i = 0; i < cbxRAM.getItemCount(); i++) {
						Componente auxComp= Tienda.getInstance().findComponentebyNumeroSerie(cbxRAM.getItemAt(i).split(":")[0]);
						if(componente.equals(auxComp)) {
							cbxRAM.setSelectedIndex(i);
						}
					}
					for (int i = 0; i < cbxMotherBoard.getItemCount(); i++) {
						Componente auxComp= Tienda.getInstance().findComponentebyNumeroSerie(cbxMotherBoard.getItemAt(i).split(":")[0]);
						if(componente.equals(auxComp)) {
							cbxMotherBoard.setSelectedIndex(i);
						}
					}

				}
			}
		}

	}	
	private ArrayList<Componente> componenteDevolver() {
		int iDD=0;
		int iMicro=0;
		int iRAM=0;
		int iMB=0;
		ArrayList<Componente> miComponente= new ArrayList<Componente>();
		if(cbxDiscoDuro.getSelectedIndex()!=0&&cbxMicro.getSelectedIndex()!=0&&cbxMotherBoard.getSelectedIndex()!=0&&cbxRAM.getSelectedIndex()!=0) {
			for (Componente elComponente : Tienda.getInstance().getLosComponentes()) {
				if(elComponente instanceof DiscoDuro){
					if(cbxDiscoDuro.getSelectedIndex()==iDD) {
						miComponente.add(0,elComponente);
					}
					iDD++;
				}
				if(elComponente instanceof Micro){
					if(cbxMicro.getSelectedIndex()==iMicro) {
						miComponente.add(1,elComponente);
					}
					iMicro++;
				}
				if(elComponente instanceof RAM){
					if(cbxRAM.getSelectedIndex()==iRAM) {
						miComponente.add(2,elComponente);
					}
					iRAM++;
				}
				if(elComponente instanceof MotherBoard){
					if(cbxMotherBoard.getSelectedIndex()==iMB) {
						miComponente.add(3,elComponente);
					}
					iMB++;
				}
			}
		}
		return miComponente;

	}
	private void DiscoDuroCbx(){
		cbxDiscoDuro.removeAllItems();

		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof DiscoDuro) {
				cbxDiscoDuro.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((DiscoDuro)elComponente).getCapacidadAlma()));
			}}
		cbxDiscoDuro.insertItemAt(new String("Código   :    Marca    :    Modelo   :   Cantidad    :     Capacidad  "),0);
		cbxDiscoDuro.setSelectedIndex(0);
	}
	private void MicroCbx(){
		cbxMicro.removeAllItems();

		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof Micro) {
				cbxMicro.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((Micro)elComponente).getVelocidad()));
			}}
		cbxMicro.insertItemAt(new String("Código   :    Marca    :    Modelo   :     Cantidad   :    Velocidad"),0);
		cbxMicro.setSelectedIndex(0);
	}
	private void RAMCbx(){
		cbxRAM.removeAllItems();

		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof RAM) {
				cbxRAM.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((RAM)elComponente).getCantMemoria()));
			}}
		cbxRAM.insertItemAt(new String("Código   :    Marca    :    Modelo   :     Cantidad   :    Memoria"),0);
		cbxRAM.setSelectedIndex(0);
	}
	private void MotherBoardCbx(){
		cbxMotherBoard.removeAllItems();

		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof MotherBoard) {
				cbxMotherBoard.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((MotherBoard)elComponente).getTipoRAM()));
			}}
		cbxMotherBoard.insertItemAt(new String("Código  :    Marca    :    Modelo   :     Cantidad   :    Tipo RAM"),0);
		cbxMotherBoard.setSelectedIndex(0);
	}
}
