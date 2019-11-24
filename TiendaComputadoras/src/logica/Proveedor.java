package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Proveedor extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Componente> misCompos;
	private ArrayList<OrdenCompra> misOrdenes;
	private float debito;
	private ArrayList<Float> preciosCompos;
	
	public Proveedor(String nombre, String telefono, String direccion, String cedula, ArrayList<Componente> misCompos,ArrayList<Float> preciosCompos) {
		super(nombre, telefono, direccion, cedula);
		this.misCompos = misCompos;
		this.misOrdenes = new ArrayList<>();
		this.setPreciosCompos(preciosCompos);
		debito = 0;
	}
		
	public ArrayList<Componente> getMisCompos() {
		return misCompos;
	}
	
	public void setMisCompos(ArrayList<Componente> misCompos) {
		this.misCompos = misCompos;
	}
	
	public ArrayList<OrdenCompra> getMisOrdenes() {
		return misOrdenes;
	}
	
	public void setMisOrdenes(ArrayList<OrdenCompra> misOrdenes) {
		this.misOrdenes = misOrdenes;
	}
	
	public float getDebito() {
		return debito;
	}
	
	public void setDebito(float debito) {
		this.debito = debito;
	}
	
	public ArrayList<Float> getPreciosCompos() {
		return preciosCompos;
	}
	
	public void setPreciosCompos(ArrayList<Float> preciosCompos) {
		this.preciosCompos = preciosCompos;
	}
	
	public float getPrecioCompo(Componente aux) {
		return preciosCompos.get(misCompos.lastIndexOf(aux));
	}
	
	public void insertComponenteyPrecio(Componente aux, float aux1) {
		misCompos.add(aux);
		preciosCompos.add(aux1);
	}
	
	public void deleteComponente(Componente aux) {
		misCompos.remove(aux);
		preciosCompos.remove(misCompos.lastIndexOf(aux));
	}
	
	
	
}
