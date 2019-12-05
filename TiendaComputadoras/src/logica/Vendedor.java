package logica;

import java.io.Serializable;

public class Vendedor extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contraseña;
	private float ventas;
	
	public Vendedor(String nombre, String telefono, String direccion, String codigo, String contraseña, float ventas) {
		super(nombre, telefono, direccion, codigo);
		this.contraseña=contraseña;
		this.ventas=ventas;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public float getVentas() {
		return ventas;
	}

	public void setVentas(float ventas) {
		this.ventas = ventas;
	}

}