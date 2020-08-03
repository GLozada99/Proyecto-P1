
package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Tienda;
import sql.SQLConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class ModificarCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JFormattedTextField ftxtTelefono;
	private JFormattedTextField ftxtCedula;

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
	public ModificarCliente(Cliente aux) {
		setTitle("Modificar cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarCliente.class.getResource("/Imagenes/IconCliente.png")));
		setBounds(100, 100, 297, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(234, 238, 249));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setForeground(new Color(0, 128, 128));
			panel.setBackground(new Color(234, 238, 249));
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Modificaci\u00F3n de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblCedula = new JLabel("C\u00E9dula");
				lblCedula.setBounds(12, 27, 76, 22);
				panel.add(lblCedula);
			}

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(12, 62, 76, 22);
			panel.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtNombre.setBounds(100, 62, 157, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblDireccion = new JLabel("Direcci\u00F3n");
			lblDireccion.setBounds(12, 97, 76, 22);
			panel.add(lblDireccion);

			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(100, 97, 157, 22);
			panel.add(txtDireccion);

			JLabel lblTelefono = new JLabel("Tel\u00E9fono");
			lblTelefono.setBounds(12, 132, 76, 22);
			panel.add(lblTelefono);


			{
				MaskFormatter mascaraNumero;
				MaskFormatter mascaraCedula;
				try {

					mascaraNumero = new MaskFormatter("(###) ###-####");
					mascaraNumero.setPlaceholderCharacter('_');
					ftxtTelefono = new JFormattedTextField(mascaraNumero);
					ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtTelefono.setBounds(100, 132, 157, 22);
					panel.add(ftxtTelefono);


					mascaraCedula = new MaskFormatter("###-#######-#");
					mascaraCedula.setPlaceholderCharacter('_');
					ftxtCedula = new JFormattedTextField(mascaraCedula);
					ftxtCedula.setEditable(false);
					ftxtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
					ftxtCedula.setBounds(100, 27, 157, 22);
					panel.add(ftxtCedula);

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(234, 238, 249));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean igual=false;
						for (Cliente cliente : Tienda.getInstance().getLosClientes()) {
							if (ftxtCedula.getText().equalsIgnoreCase(cliente.getCodigo())&&!ftxtCedula.getText().equalsIgnoreCase(aux.getCodigo())) {
								igual=true;
							}
						}
						if(igual==true) {
							JOptionPane.showMessageDialog(null,"Ya existe un cliente registrado con esa cedula");
						}
						else if(ftxtCedula.getText().equalsIgnoreCase("___-_______-_")||txtNombre.getText().isEmpty()||txtDireccion.getText().isEmpty()||ftxtTelefono.getText().equalsIgnoreCase("(___) ___-____")) {
							JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
						}
						else {
							aux.setCodigo(ftxtCedula.getText());
							aux.setNombre(txtNombre.getText());
							aux.setDireccion(txtDireccion.getText());
							aux.setTelefono(ftxtTelefono.getText());
							
							
							JOptionPane.showMessageDialog(null, "Modificacion Realizada","Notificación", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
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
		ftxtCedula.setText(aux.getCodigo());
		txtNombre.setText(aux.getNombre());
		txtDireccion.setText(aux.getDireccion());
		ftxtTelefono.setText(aux.getTelefono());
	}

	
}

