package logica;

import java.util.ArrayList;

public class Tienda {
	private ArrayList<Componente> losComponentes;
	private ArrayList <Cliente> losClientes;
	private ArrayList<Factura> lasFacturas;
	private ArrayList<Combo> elCombo;
	private ArrayList<Proveedor> losProveedores;
	private ArrayList<OrdenCompra> lasOrdenes;
	private int generadorCodigoCombo;
	private int generadorCodigoFactura;
	private int generadorCodigoComponentes;
	private static Tienda tienda;
	
	public Tienda() {
		super();
		this.losComponentes = losComponentes;
		this.losClientes = losClientes;
		this.lasFacturas = lasFacturas;
		this.elCombo = elCombo;
		this.losProveedores = losProveedores;
		this.lasOrdenes = lasOrdenes;
		this.generadorCodigoCombo = generadorCodigoCombo;
		this.generadorCodigoFactura = generadorCodigoFactura;
		this.generadorCodigoComponentes = generadorCodigoComponentes;
		this.tienda = tienda;
	}
	public static Tienda getInstance() {
		if(tienda==null){
			tienda= new Tienda();
		}
		return tienda;
	}
	public ArrayList<Componente> getLosComponentes() {
		return losComponentes;
	}
	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		this.losComponentes = losComponentes;
	}
	public ArrayList<Cliente> getLosClientes() {
		return losClientes;
	}
	public void setLosClientes(ArrayList<Cliente> losClientes) {
		this.losClientes = losClientes;
	}
	public ArrayList<Factura> getLasFacturas() {
		return lasFacturas;
	}
	public void setLasFacturas(ArrayList<Factura> lasFacturas) {
		this.lasFacturas = lasFacturas;
	}
	public ArrayList<Combo> getElCombo() {
		return elCombo;
	}
	public void setElCombo(ArrayList<Combo> elCombo) {
		this.elCombo = elCombo;
	}
	public ArrayList<Proveedor> getLosProveedores() {
		return losProveedores;
	}
	public void setLosProveedores(ArrayList<Proveedor> losProveedores) {
		this.losProveedores = losProveedores;
	}
	public ArrayList<OrdenCompra> getLasOrdenes() {
		return lasOrdenes;
	}
	public void setLasOrdenes(ArrayList<OrdenCompra> lasOrdenes) {
		this.lasOrdenes = lasOrdenes;
	}
	public int getGeneradorCodigoCombo() {
		return generadorCodigoCombo;
	}
	public void setGeneradorCodigoCombo(int generadorCodigoCombo) {
		this.generadorCodigoCombo = generadorCodigoCombo;
	}
	public int getGeneradorCodigoFactura() {
		return generadorCodigoFactura;
	}
	public void setGeneradorCodigoFactura(int generadorCodigoFactura) {
		this.generadorCodigoFactura = generadorCodigoFactura;
	}
	public int getGeneradorCodigoComponentes() {
		return generadorCodigoComponentes;
	}
	public void setGeneradorCodigoComponentes(int generadorCodigoComponentes) {
		this.generadorCodigoComponentes = generadorCodigoComponentes;
	}
	public Tienda getTienda() {
		return tienda;
	}
	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	

}
