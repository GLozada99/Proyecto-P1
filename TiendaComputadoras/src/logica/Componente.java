package logica;

import java.util.ArrayList;

public abstract class Componente {
	protected String modelo;
	protected String numeroSerie;
	protected String marca;
	protected int cantDisponible;
	protected float cantMin;
	protected float cantMax;
	protected ArrayList<Precio> precios;
	
	public Componente(String modelo, String numeroSerie, String marca, int cantDisponible, float cantMin, float cantMax, float precioVentaI, float precioCompraI) {
		super();
		this.modelo = modelo;
		this.numeroSerie = numeroSerie;
		this.marca = marca;
		this.cantDisponible = cantDisponible;
		this.cantMin = cantMin;
		this.cantMax = cantMax;
		precios=new ArrayList<Precio>();
		precios.add(new Precio(precioVentaI, precioCompraI, false));
	
	}

	public String getModelo() {
		return modelo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public String getMarca() {
		return marca;
	}

	public int getCantDisponible() {
		return cantDisponible;
	}

	public float getCantMin() {
		return cantMin;
	}

	public float getCantMax() {
		return cantMax;
	}

	public ArrayList<Precio> getPrecios() {
		return precios;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setCantDisponible(int cantDisponible) {
		this.cantDisponible = cantDisponible;
	}

	public void setCantMin(float cantMin) {
		this.cantMin = cantMin;
	}

	public void setCantMax(float cantMax) {
		this.cantMax = cantMax;
	}

	public void setPrecios(ArrayList<Precio> precios) {
		this.precios = precios;
	}
	public float getPrecioCompraActual() {
		return precios.get(precios.size()).getPrecioCompra();
	}
	public float getPrecioVentaActual() {
		return precios.get(precios.size()).getPrecioVenta();
	}
	
	
	
}
