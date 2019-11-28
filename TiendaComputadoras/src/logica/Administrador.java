package logica;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contraseña;
	
	public Administrador(String nombre, String telefono, String direccion, String codigo, String contraseña) {
		super(nombre, telefono, direccion, codigo);
		this.contraseña=contraseña;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
	

}
