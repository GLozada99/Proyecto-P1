package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Administrador;
import logica.Cliente;
import logica.Persona;
import logica.Tienda;
import logica.Vendedor;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JRadioButton;
import java.awt.Label;

public class NuevoUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtContra;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JFormattedTextField ftxtTelefono;
	private JFormattedTextField ftxtCedula;
	private JRadioButton rdnAdministrador;
	private JRadioButton rdnVendedor;



	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			NuevoUsuario dialog = new NuevoUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoUsuario(Persona aux) {
		setBounds(100, 100, 285, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 245, 222);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 23, 74, 14);
		panel.add(lblNombre);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 60, 74, 14);
		panel.add(lblCodigo);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 97, 74, 14);
		panel.add(lblTelfono);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 134, 74, 14);
		panel.add(lblDireccin);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 171, 74, 14);
		panel.add(lblContrasea);

		txtContra = new JTextField();
		txtContra.setBounds(83, 168, 142, 20);
		panel.add(txtContra);
		txtContra.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(83, 20, 142, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(83, 131, 142, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		{
			MaskFormatter mascaraNumero;
			MaskFormatter mascaraCedula;
			try {

				mascaraNumero = new MaskFormatter("(###) ###-####");
				mascaraNumero.setPlaceholderCharacter('_');
				ftxtTelefono = new JFormattedTextField(mascaraNumero);
				ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtTelefono.setBounds(83, 93, 142, 22);
				panel.add(ftxtTelefono);


				mascaraCedula = new MaskFormatter("######");
				mascaraCedula.setPlaceholderCharacter('_');
				ftxtCedula = new JFormattedTextField(mascaraCedula);
				ftxtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtCedula.setBounds(83, 56, 142, 22);
				panel.add(ftxtCedula);

				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Tipo de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 239, 245, 57);
				contentPanel.add(panel_1);
				panel_1.setLayout(null);

				rdnAdministrador = new JRadioButton("Administrador");
				rdnAdministrador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdnAdministrador.setSelected(true);
						rdnVendedor.setSelected(false);

					}
				});
				rdnAdministrador.setBounds(6, 16, 128, 23);
				panel_1.add(rdnAdministrador);

				rdnVendedor = new JRadioButton("Vendedor");
				rdnVendedor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdnAdministrador.setSelected(false);
						rdnVendedor.setSelected(true);

					}
				});
				rdnVendedor.setBounds(138, 16, 91, 23);
				panel_1.add(rdnVendedor);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Persona usuario1=null;
						if(Tienda.getInstance().getLosUsuarios().contains(Tienda.getInstance().findClientebyCedula(ftxtCedula.getText()))) {
							JOptionPane.showMessageDialog(null, "El usuario ingresado ya está registrado");
						}
						else {
							if(!rdnAdministrador.isSelected() && !rdnVendedor.isSelected()) {
								JOptionPane.showMessageDialog(null, "Seleccione su tipo de usuario");
							}
							else if(rdnAdministrador.isSelected()) {
								usuario1= new Administrador(txtNombre.getText(), ftxtTelefono.getText(), txtDireccion.getText(), ftxtCedula.getText(), txtContra.getText());
							}
							else if(rdnVendedor.isSelected()) {
								usuario1= new Vendedor(txtNombre.getText(), ftxtTelefono.getText(), txtDireccion.getText() , ftxtCedula.getText(), txtContra.getText());
							}
							if(aux==null) {
								Tienda.getInstance().getLosUsuarios().add(usuario1);
								JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
							}
							else {
								JOptionPane.showMessageDialog(null, "Usuario modificado con exito");

							}
						}
					}
				});
				if(aux!=null) {
					ftxtCedula.setText(aux.getCodigo());
					txtNombre.setText(aux.getNombre());
					txtDireccion.setText(aux.getDireccion());
					ftxtTelefono.setText(aux.getTelefono());
					try {
						txtContra.setText(((Administrador)aux).getContraseña());	
					} catch (ClassCastException e) {
						txtContra.setText(((Vendedor)aux).getContraseña());	
					}
				}

				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
