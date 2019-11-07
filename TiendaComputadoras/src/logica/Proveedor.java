package logica;

import java.util.ArrayList;

public class Proveedor extends Persona {
	private ArrayList<Componente> misCompos;
	private ArrayList<OrdenCompra> misOrdenes;
	private float debito;
	private ArrayList<Float> preciosCompos;
	public Proveedor(String nombre, String telefono, String direccion, ArrayList<Componente> misCompos,ArrayList<Float> preciosCompos) {
		super(nombre, telefono, direccion);
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
	public float getPrecioCompo(int i) {
		return preciosCompos.lastIndexOf(misCompos.get(i));
	}

}
