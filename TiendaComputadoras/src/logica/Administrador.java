package logica;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contrasena;
	
	public Administrador(String nombre, String telefono, String direccion, String codigo, String contrasena) {
		super(nombre, telefono, direccion, codigo);
		this.contrasena=contrasena;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	
	

}
