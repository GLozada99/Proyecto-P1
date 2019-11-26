package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrador;
import logica.Tienda;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream tienda=null;
				FileOutputStream tienda2=null;
				ObjectInputStream tiendaRead=null;
				ObjectOutputStream tiendaWrite=null;
				try {
					tienda = new FileInputStream("TiendaComputadoras.dat");
					tiendaRead = new ObjectInputStream(tienda);
					Tienda temp = (Tienda)tiendaRead.readObject();
					Tienda.setTienda(temp);
					tienda.close();
					tiendaRead.close();
				} catch(FileNotFoundException e) {
					try {
						tienda2 = new  FileOutputStream("TiendaComputadoras.dat");
						tiendaWrite = new ObjectOutputStream(tienda2);
						tiendaWrite.writeObject(Tienda.getInstance());
						tienda.close();
						tiendaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Iniciar sesi\u00F3n");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(210, 154, 63, 25);
		panel.add(btnLogin);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(28, 57, 63, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(28, 93, 68, 14);
		panel.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(97, 54, 86, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContra = new JTextField();
		txtContra.setBounds(97, 90, 86, 20);
		panel.add(txtContra);
		txtContra.setColumns(10);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Tienda.getInstance().confirmarLogin(txtUsuario.getText(), txtContra.getText())) {
					setVisible(false);
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}
				JOptionPane.showInternalMessageDialog(null, "El usuario o la contraseña son incorrectos");
			}
		});
		
		
	}
}
