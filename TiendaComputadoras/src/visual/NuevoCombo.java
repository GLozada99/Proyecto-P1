package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
import java.awt.event.ActionEvent;

public class NuevoCombo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private static DefaultTableModel tableModel;
	private static Object[] fila;
	private static JTable table;
	private JComboBox cbxDiscoDuro;
	private JComboBox cbxMicro;
	private JComboBox cbxRAM;
	private JComboBox cbxMotherBoard;



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
	}

	/**
	 * Create the dialog.
	 */
	public NuevoCombo() {
		setTitle("Nuevo Combo");
		setBounds(100, 100, 527, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Combo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 496, 85);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(10, 25, 46, 14);
		panel.add(lblCdigo);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 50, 46, 14);
		panel.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad de Combos:");
		lblCantidad.setBounds(231, 25, 140, 14);
		panel.add(lblCantidad);

		JLabel lblPorcentajeDeDescuento = new JLabel("Porcentaje de descuento: ");
		lblPorcentajeDeDescuento.setBounds(231, 50, 158, 14);
		panel.add(lblPorcentajeDeDescuento);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(399, 22, 44, 20);
		panel.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_1.setBounds(399, 47, 44, 20);
		panel.add(spinner_1);

		textField = new JTextField();
		textField.setBounds(63, 22, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(63, 47, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Componentes del Combo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 91, 541, 211);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDiscoDuro = new JLabel("Disco Duro:");
		lblDiscoDuro.setBounds(13, 34, 99, 14);
		panel_1.add(lblDiscoDuro);

		cbxDiscoDuro = new JComboBox();
		cbxDiscoDuro.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :   Cantidad    :     Capacidad   "}));
		cbxDiscoDuro.setBounds(122, 31, 371, 20);
		panel_1.add(cbxDiscoDuro);

		JLabel lblMemoriaRam = new JLabel("Memoria RAM:");
		lblMemoriaRam.setBounds(10, 121, 102, 14);
		panel_1.add(lblMemoriaRam);

		cbxMicro = new JComboBox();
		cbxMicro.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :     Cantidad   :    Velocidad"}));
		cbxMicro.setBounds(122, 77, 371, 20);
		panel_1.add(cbxMicro);

		JLabel lblMicroprocesador = new JLabel("Microprocesador:");
		lblMicroprocesador.setBounds(10, 80, 102, 14);
		panel_1.add(lblMicroprocesador);

		cbxRAM = new JComboBox();
		cbxRAM.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :     Cantidad   :    Memoria"}));
		cbxRAM.setBounds(122, 123, 371, 20);
		panel_1.add(cbxRAM);

		JLabel lblTarjetaMadre = new JLabel("Tarjeta Madre:");
		lblTarjetaMadre.setBounds(10, 172, 102, 14);
		panel_1.add(lblTarjetaMadre);

		cbxMotherBoard = new JComboBox();
		cbxMotherBoard.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo   :    Marca    :    Modelo   :     Cantidad   :    Tipo RAM"}));
		cbxMotherBoard.setBounds(122, 169, 371, 20);
		panel_1.add(cbxMotherBoard);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

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


		}
	}		
	private void DiscoDuroCbx(){
		cbxDiscoDuro.removeAllItems();
	
		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof DiscoDuro) {
				cbxDiscoDuro.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((DiscoDuro)elComponente).getCapacidadAlma()));
			}}
		//cbxDiscoDuro.insertItemAt(new String("<>"),0);
		cbxDiscoDuro.setSelectedIndex(0);
	}
	private void MicroCbx(){
		cbxMicro.removeAllItems();
		
		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof Micro) {
				cbxMicro.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((Micro)elComponente).getVelocidad()));
			}}
	//	cbxMicro.insertItemAt(new String("<Escoja una Cuenta>"),0);
		cbxMicro.setSelectedIndex(0);
	}
	private void RAMCbx(){
		cbxRAM.removeAllItems();
		
		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof RAM) {
				cbxRAM.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((RAM)elComponente).getCantMemoria()));
			}}
	//	cbxMicro.insertItemAt(new String("<Escoja una Cuenta>"),0);
		cbxRAM.setSelectedIndex(0);
	}
	private void MotherBoardCbx(){
		cbxMotherBoard.removeAllItems();
		
		for (Componente elComponente : Tienda.getInstance().getLosComponentes() ) {
			if(elComponente instanceof MotherBoard) {
				cbxMotherBoard.addItem(new String(elComponente.getNumeroSerie()+" : "+elComponente.getMarca()+" : "+elComponente.getModelo()+" : "+elComponente.getCantDisponible()+" : "+((MotherBoard)elComponente).getTipoRAM()));
			}}
	//	cbxMicro.insertItemAt(new String("<Escoja una Cuenta>"),0);
		cbxMotherBoard.setSelectedIndex(0);
	}
	

	/*public static void loadSportMans(int selection) {
	tableModel.setRowCount(0);
	fila = new Object[tableModel.getColumnCount()];
	switch (selection) {
	case 0:
		for (Componente aux : Tienda.getInstance().getLosComponentes()) {
			fila[0] = aux.getNumeroSerie();
			fila[1] = aux.getMarca();
			fila[2] = aux.getModelo();
			fila[3] = aux.getCantDisponible();
			if(aux instanceof DiscoDuro)
				fila[4] = "Disco Duro";
			if(aux instanceof  RAM)
				fila[4] = "Microprocesador";
			if(aux instanceof Micro)
				fila[4] = "Memoria RAM";
			if(aux instanceof MotherBoard)
				fila[4] = "Tarjeta Madre";

			tableModel.addRow(fila);
		}
		break;
	case 1:
		for (Componente aux : Tienda.getInstance().getLosComponentes()) {
			if(aux instanceof DiscoDuro){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = aux.getCantDisponible();
				fila[4]=((DiscoDuro) aux).getCapacidadAlma();//sera?
			fila[5] = "Disco Duro";

			tableModel.addRow(fila);
			}
		}
		break;	
	case 2:
		for (Componente aux : Tienda.getInstance().getLosComponentes()) {
			if(aux instanceof Micro){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = aux.getCantDisponible();
				fila[4]= ((Micro) aux).getVelocidad();//ojo
			fila[5] = "Microprocesador";

			tableModel.addRow(fila);
			}
		}
		break;	
	case 3:
		for (Componente aux : Tienda.getInstance().getLosComponentes()) {
			if(aux instanceof RAM){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = aux.getCantDisponible();
				fila[4]=((RAM) aux).getCantMemoria();
			fila[5] = "Memoria RAM";

			tableModel.addRow(fila);
			}
		}
		break;	

	case 4:
		for (Componente aux : Tienda.getInstance().getLosComponentes()) {
			if(aux instanceof MotherBoard){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = aux.getCantDisponible();
				fila[4]=((MotherBoard) aux).getTipoRAM();
			fila[5] = "Tarjeta Madre";

			tableModel.addRow(fila);
			}
		}
		break;
	}


	table.setModel(tableModel);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	table.getTableHeader().setReorderingAllowed(false);
	TableColumnModel columnModel = table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(60);
	columnModel.getColumn(1).setPreferredWidth(180);
	columnModel.getColumn(2).setPreferredWidth(150);
	columnModel.getColumn(3).setPreferredWidth(130);
	columnModel.getColumn(4).setPreferredWidth(81);

	}*/

}
