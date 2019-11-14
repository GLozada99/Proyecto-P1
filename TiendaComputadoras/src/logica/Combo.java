package logica;

import java.util.ArrayList;

public class Combo {
	private ArrayList<Componente> componentes;
	private int cantidad;
	private String codigo;
	private float precio;
	private int descuento;
	
	public Combo(ArrayList<Componente> componentes, String codigo, float precio, int descuento) {
		super();
		this.componentes = componentes;
		this.codigo = codigo;
		this.precio = precio;
		this.descuento = descuento;
		for (Componente componente : componentes) {
			componente.setCantDisponible(componente.getCantDisponible()-cantidad);
		}
		
	}

	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public String getCodigo() {
		return codigo;
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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public float precioCombo() {
		float precioCombo=0;
		 precioCombo= ((100-descuento)/100)*precio;
		
	return precioCombo;
		
	}
	
}
