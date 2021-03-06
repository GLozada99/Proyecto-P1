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
import sql.SQLConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.TextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
				/*try {
					SQLConnection.setConnectionURL("LAPTOP-TPPBSJFQ", "Proyecto");
					SQLConnection.getData();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
				Persona admin = new Administrador("Admin1", "000-000-0000", "PUCMM", "Admin", "Admin");
				Tienda.getInstance().getLosUsuarios().add(0,admin);
				
				
				/*
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
				}*/

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
	 * @throws ClassNotFoundException 
	 */
	public Login(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Imagenes/IconPrincipal.png")));
		setTitle("Iniciar sesi\u00F3n");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 196);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 238, 249));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(234, 238, 249));
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnLogin = new JButton("Iniciar Sesi�n");
		getRootPane().setDefaultButton(btnLogin);;
		btnLogin.setBounds(68, 100, 135, 25);
		panel.add(btnLogin);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/user.png")));
		lblUsuario.setBounds(10, 20, 103, 20);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/IconContrase\u00F1a.png")));
		lblContrasea.setBounds(10, 60, 103, 20);
		panel.add(lblContrasea);

		txtUsuario = new TextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtUsuario.getText().length() == 6) { 
					e.consume(); 
				}
			} 
		});
		txtUsuario.setBounds(115, 20, 147, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContra = new TextField();
		txtContra.setBounds(115, 60, 147, 20);
		txtContra.setEchoChar('�');
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
					JOptionPane.showMessageDialog(null, "El usuario o la contrase�a son incorrectos");
				}
			}
		});


	}
}
