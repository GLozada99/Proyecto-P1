package logica;

import java.io.Serializable;

public class Vendedor extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contraseña;
	
	public Vendedor(String nombre, String telefono, String direccion, String cedula, String contraseña) {
		super(nombre, telefono, direccion, cedula);
		this.contraseña=contraseña;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}