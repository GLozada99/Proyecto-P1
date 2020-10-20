package logica;

import java.io.Serializable;

public class Vendedor extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contrasena;
	private float ventas;
	
	public Vendedor(String nombre, String telefono, String direccion, String codigo, String contrasena, float ventas) {
		super(nombre, telefono, direccion, codigo);
		this.contrasena=contrasena;
		this.ventas=ventas;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public float getVentas() {
		return ventas;
	}

	public void setVentas(float ventas) {
		this.ventas = ventas;
	}

}