package logica;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Componente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String modelo;
	protected String numeroSerie;
	protected String marca;
	protected int cantDisponible;
	protected int cantMin;
	protected int cantMax;
	protected ArrayList<Precio> precios;
	protected ArrayList<Proveedor> losQueVenden;
	protected int cantidadVentas;
	protected float PVActual;
	protected float PCActual;
	

	public Componente(String numeroSerie, String marca, String modelo, int cantMin, int cantMax, float precioVentaI) {
		super();
		this.modelo = modelo;
		this.numeroSerie = numeroSerie;
		this.marca = marca;
		cantDisponible = 0;
		this.cantMin = cantMin;
		this.cantMax = cantMax;
		precios=new ArrayList<Precio>();
		precios.add(new Precio(precioVentaI, 0, false));
		PVActual = precioVentaI;
		PCActual = 0;
		losQueVenden = new ArrayList<Proveedor>();
		cantidadVentas= 0;
	}

	public float getPVActual() {
		return PVActual;
	}

	public void setPVActual(float pVActual) {
		PVActual = pVActual;
	}

	public float getPCActual() {
		return PCActual;
	}

	public void setPCActual(float pCActual) {
		PCActual = pCActual;
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
	public ArrayList<Proveedor> getLosQueVenden() {
		return losQueVenden;
	}

	public void setLosQueVenden(ArrayList<Proveedor> losQueVenden) {
		this.losQueVenden = losQueVenden;
	}

	public float getPrecioCompraActual() {
		//return precios.get(precios.size()-1).getPrecioCompra();
		return PCActual;
	}
	public float getPrecioVentaActual() {
		//return precios.get(precios.size()-1).getPrecioVenta();
		return PVActual;
	}
	public float ganancia() {
		float gain = getPrecioVentaActual()-getPrecioCompraActual();
		return gain;
	}

	public int getCantidadVentas() {
		return cantidadVentas;
	}

	public void setCantidadVentas(int cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
