package logica;

import java.util.ArrayList;

public abstract class Componente {
	protected String modelo;
	protected String numeroSerie;
	protected String marca;
	protected int cantDisponible;
	protected int cantMin;
	protected int cantMax;
	protected ArrayList<Precio> precios;
	
	public Componente(String modelo, String numeroSerie, String marca, int cantDisponible, int cantMin, int cantMax, float precioVentaI, float precioCompraI) {
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

	public int getCantMin() {
		return cantMin;
	}

	public int getCantMax() {
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

	public void setCantMin(int cantMin) {
		this.cantMin = cantMin;
	}

	public void setCantMax(int cantMax) {
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
