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
	public static void main(String[] args) {
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
	public NuevoUsuario() {
		setBounds(100, 100, 265, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 0, 235, 209);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 47, 46, 14);
		panel.add(lblNombre);
		
		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 75, 46, 14);
		panel.add(lblCdula);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 107, 46, 14);
		panel.add(lblTelfono);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 135, 74, 14);
		panel.add(lblDireccin);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 162, 74, 14);
		panel.add(lblContrasea);
		
		txtContra = new JTextField();
		txtContra.setBounds(83, 159, 97, 20);
		panel.add(txtContra);
		txtContra.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(83, 44, 97, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(83, 132, 97, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		{
			MaskFormatter mascaraNumero;
			MaskFormatter mascaraCedula;
			try {

				mascaraNumero = new MaskFormatter("(###) ###-####");
				mascaraNumero.setPlaceholderCharacter('_');
				JFormattedTextField ftxtTelefono = new JFormattedTextField(mascaraNumero);
				ftxtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtTelefono.setBounds(83, 102, 97, 22);
				panel.add(ftxtTelefono);


				mascaraCedula = new MaskFormatter("######");
				mascaraCedula.setPlaceholderCharacter('_');
				ftxtCedula = new JFormattedTextField(mascaraCedula);
				ftxtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
				ftxtCedula.setBounds(83, 70, 97, 22);
				panel.add(ftxtCedula);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Tipo de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 220, 235, 95);
				contentPanel.add(panel_1);
				panel_1.setLayout(null);
				
				 rdnAdministrador = new JRadioButton("Administrador");
				rdnAdministrador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdnAdministrador.setSelected(true);
						rdnVendedor.setSelected(false);
						
					}
				});
				rdnAdministrador.setBounds(6, 29, 91, 23);
				panel_1.add(rdnAdministrador);
				
				 rdnVendedor = new JRadioButton("Vendedor");
				rdnVendedor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdnAdministrador.setSelected(false);
						rdnVendedor.setSelected(true);
						
					}
				});
				rdnVendedor.setBounds(6, 66, 71, 23);
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
						if(rdnAdministrador.isSelected()) {
							usuario1= new Administrador(txtNombre.getText(), ftxtTelefono.getText(), txtDireccion.getText(), ftxtCedula.getText(), txtContra.getText());    
						}
						if(rdnVendedor.isSelected()) {
							usuario1= new Vendedor(txtNombre.getText(), ftxtTelefono.getText(), txtDireccion.getText() , ftxtCedula.getText(), txtContra.getText());
						}
						Tienda.getInstance().getLosUsuarios().add(usuario1);
						}
					}
				});
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
