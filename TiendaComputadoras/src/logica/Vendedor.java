package logica;

import java.io.Serializable;

public class Vendedor extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contrase�a;
	private float ventas;
	
	public Vendedor(String nombre, String telefono, String direccion, String codigo, String contrase�a, float ventas) {
		super(nombre, telefono, direccion, codigo);
		this.contrase�a=contrase�a;
		this.ventas=ventas;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public float getVentas() {
		return ventas;
	}

	public void setVentas(float ventas) {
		this.ventas = ventas;
	}

}