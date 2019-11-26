package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PagarCuentasCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JFormattedTextField ftxtCedula;
	private JTextField txtCredito;
	private JTextField txtLimCredito;
	private Cliente aux;
	private JSpinner spnMonto;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			AgregarCliente dialog = new AgregarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagarCuentasCliente() {
		setBounds(100, 100, 503, 291);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(12, 5, 461, 104);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setLayout(null);
			JButton btnBuscar = new JButton("");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!Tienda.getInstance().getLosClientes().isEmpty()) {
						aux = Tienda.getInstance().findClientebyCedula(ftxtCedula.getText());
						
						if(aux!=null) {
							txtNombre.setText(aux.getNombre()); txtNombre.setEditable(false);
							txtCredito.setText(""+aux.getCredito()); txtCredito.setEditable(false);
							txtLimCredito.setText(""+aux.getLimCredito()); txtLimCredito.setEditable(false);
						}
						else {
							JOptionPane.showMessageDialog(null, "El cliente no esta registrado","Notificación", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "No hay clientes registrados","Notificación", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnBuscar.setIcon(new ImageIcon(PagarCuentasCliente.class.getResource("/Imagenes/Lupa.png")));
			btnBuscar.setActionCommand("Buscar");
			btnBuscar.setBounds(190, 27, 45, 22);
			panel.add(btnBuscar);
			{
				JLabel lblCedula = new JLabel("Cedula:");
				lblCedula.setBounds(12, 27, 76, 22);
				panel.add(lblCedula);
			}

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 62, 76, 22);
			panel.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtNombre.setBounds(78, 62, 157, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);


			{
				MaskFormatter mascaraNumero;
				MaskFormatter mascaraCedula;
				try {

					mascaraNumero = new MaskFormatter("(###) ###-####");
					mascaraNumero.setPlaceholderCharacter('_');


					mascaraCedula = new MaskFormatter("###-#######-#");
					mascaraCedula.setPlaceholderCharacter('_');
					ftxtCedula = new JFormattedTextField(mascaraCedula);
					ftxtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtCedula.setBounds(78, 27, 100, 22);
					panel.add(ftxtCedula);
					
					JLabel lblCredito = new JLabel("Credito:\r\n");
					lblCredito.setBounds(264, 27, 57, 22);
					panel.add(lblCredito);
					
					txtCredito = new JTextField();
					txtCredito.setEditable(false);
					txtCredito.setText((String) null);
					txtCredito.setFont(new Font("Calibri", Font.PLAIN, 18));
					txtCredito.setColumns(10);
					txtCredito.setBounds(350, 27, 100, 22);
					panel.add(txtCredito);
					
					JLabel lblLimCredito = new JLabel("Lim. Credito:");
					lblLimCredito.setBounds(264, 62, 76, 22);
					panel.add(lblLimCredito);
					
					txtLimCredito = new JTextField();
					txtLimCredito.setEditable(false);
					txtLimCredito.setText((String) null);
					txtLimCredito.setFont(new Font("Calibri", Font.PLAIN, 18));
					txtLimCredito.setColumns(10);
					txtLimCredito.setBounds(350, 62, 100, 22);
					panel.add(txtLimCredito);

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar\r\n");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(Float.valueOf(txtCredito.getText().toString())>=Float.valueOf(spnMonto.getValue().toString())) {
							aux.setCredito(aux.getCredito()-Float.valueOf(spnMonto.getValue().toString()));
							txtNombre.setText(aux.getNombre()); txtNombre.setEditable(false);
							txtCredito.setText(""+aux.getCredito()); txtCredito.setEditable(false);
							txtLimCredito.setText(""+aux.getLimCredito()); txtLimCredito.setEditable(false);
							JOptionPane.showMessageDialog(null, "Pago Realizado con Exito","Notificación", JOptionPane.INFORMATION_MESSAGE);			
						}
						else {
							JOptionPane.showMessageDialog(null, "La cantidad ingresada supera al credito","Notificación", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 122, 461, 74);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblMontoAPagar = new JLabel("Monto a pagar:");
		lblMontoAPagar.setBounds(77, 25, 114, 22);
		panel.add(lblMontoAPagar);
		
		spnMonto = new JSpinner();
		spnMonto.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnMonto.setBounds(268, 25, 114, 22);
		panel.add(spnMonto);
	}
}