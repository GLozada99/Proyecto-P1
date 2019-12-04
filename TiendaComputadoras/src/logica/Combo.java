package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Combo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Componente> componentes;
	private String nombre;
	private float precio;
	private int descuento;
	
	public Combo(ArrayList<Componente> componentes, String nombre, float precio, int descuento) {
		super();
		this.componentes = componentes;
		this.nombre = nombre;
		this.precio = precio;
		this.descuento = descuento;
		
		
	
		
	}

	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public String getNombre() {
		return nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public float precioCombo() {
		float precioC=0;
		precioC = ((100-(float)descuento)/100)*precio;
		
	return precioC;
		
	}

	
	
}
