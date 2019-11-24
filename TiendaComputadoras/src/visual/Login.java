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

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
						tienda2 = new  FileOutputStream("empresa.dat");
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(178, 109, 63, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Tienda.getInstance().setUsuarioActual(Tienda.getInstance().getLosUsuarios().get(0));
				Principal frame = new Principal(Tienda.getInstance().getUsuarioActual());
				dispose();
				frame.setVisible(true);
				
			}
		});
		
		
	}

}
