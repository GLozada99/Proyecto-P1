package logica;

import java.io.Serializable;

public class Vendedor extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contrase�a;
	
	public Vendedor(String nombre, String telefono, String direccion, String cedula, String contrase�a) {
		super(nombre, telefono, direccion, cedula);
		this.contrase�a=contrase�a;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

}