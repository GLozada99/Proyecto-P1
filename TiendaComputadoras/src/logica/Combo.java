package logica;

import java.util.ArrayList;

public class Combo {
	private ArrayList<Componente> componentes;
	private int cantidad;
	private String codigo;
	private float precio;
	private int descuento;
	
	public Combo(ArrayList<Componente> componentes, String codigo, float precio, int descuento, int cantidad) {
		super();
		this.componentes = componentes;
		this.codigo = codigo;
		this.precio = precio;
		this.descuento = descuento;
		this.cantidad = cantidad;
		
		for (Componente componente : componentes) {
			componente.setCantDisponible(componente.getCantDisponible()-cantidad);
			if(componente.getCantDisponible()<componente.getCantMin()) {
				OrdenCompra aux = new OrdenCompra(componente, componente.getCantMax()-componente.getCantDisponible());
				Tienda.getInstance().getOrdenesSinProcesar().add(aux);
			}
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
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public float precioCombo() {
		float precioCombo=0;
		 precioCombo= ((100-descuento)/100)*precio;
		
	return precioCombo;
		
	}

	
	
}
