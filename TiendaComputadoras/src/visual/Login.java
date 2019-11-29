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
import logica.Persona;
import logica.Tienda;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.TextField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField txtUsuario;
	private TextField txtContra;
	
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
						Persona admin = new Administrador("Admin1", "000-000-0000", "PUCMM", "Admin", "Admin");
						Tienda.getInstance().getLosUsuarios().add(0,admin);
						tiendaWrite.writeObject(Tienda.getInstance());
						tienda2.close();
						tiendaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
	
				} catch (ClassNotFoundException e) {
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Imagenes/IconPrincipal.png")));
		setTitle("Iniciar sesi\u00F3n");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnLogin = new JButton("Iniciar Sesion");
		getRootPane().setDefaultButton(btnLogin);;
		btnLogin.setBounds(68, 100, 135, 25);
		panel.add(btnLogin);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(12, 16, 103, 14);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(12, 60, 103, 14);
		panel.add(lblContrasea);

		txtUsuario = new TextField();
		txtUsuario.setBounds(117, 13, 147, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContra = new TextField();
		txtContra.setBounds(117, 57, 147, 20);
		txtContra.setEchoChar('*');
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
				else {
					JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos");
				}
			}
		});


	}
}
