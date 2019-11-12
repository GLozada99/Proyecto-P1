package logica;

import java.util.ArrayList;

public class Tienda {
	private ArrayList<Componente> losComponentes;
	private ArrayList <Cliente> losClientes;
	private ArrayList<Factura> lasFacturas;
	private ArrayList<Combo> losCombo;
	private ArrayList<Proveedor> losProveedores;
	private ArrayList<OrdenCompra> lasOrdenes;
	private int generadorCodigoCombo;
	private int generadorCodigoFactura;
	private int generadorCodigoComponentes;
	private static Tienda tienda=null;

	private Tienda() {
		super();
		losComponentes = new ArrayList<>();
		losClientes = new ArrayList<>();
		lasFacturas = new ArrayList<>();
		losCombo = new ArrayList<>();
		losProveedores = new ArrayList<>();
		lasOrdenes = new ArrayList<>();
		generadorCodigoCombo = 1;
		generadorCodigoFactura = 1;
		generadorCodigoComponentes = 1;
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

	public ArrayList<Combo> getLosCombo() {
		return losCombo;
	}

	public void setElCombo(ArrayList<Combo> elCombo) {
		this.losCombo = elCombo;
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

	public Cliente findClientebyCedula(String cedula) {
		Cliente clienteFound = null;
		boolean find = false;
		int i=0;
		while (i<losClientes.size()&&!find) {
			if(losClientes.get(i).getCedula().equalsIgnoreCase(cedula)){
				clienteFound = losClientes.get(i);
				find = true;
			}
			i++;
		}
		return clienteFound;
	}

	public void removeComponente(Componente componenteElim) {
		for (Componente componente : losComponentes) {
			if(componente == componenteElim) {
				losComponentes.remove(componenteElim);
			}
		}
	}

	public Componente findComponentebyNumeroSerie(String NumeroSerie) {
		Componente componenteFound = null;
		boolean find = false;
		int i=0;
		while (i<losComponentes.size()&&!find) {
			if(losComponentes.get(i).getNumeroSerie().equalsIgnoreCase(NumeroSerie)){
				componenteFound = losComponentes.get(i);
				find = true;
			}
			i++;
		}
		return componenteFound;
	}

	public int[]  cantComponentesTipo(){
		int[] cant = new int[4];
		for (int i = 0; i < 4; i++) {
			cant[i]=0;
		}
		for (Componente componente : losComponentes) {
			if(componente instanceof DiscoDuro){
				cant[0]++;
			}
			if(componente instanceof Micro){
				cant[1]++;
			}
			if(componente instanceof MotherBoard){
				cant[2]++;
			}
			if(componente instanceof RAM){
				cant[3]++;
			}
		}
		return cant;
	}

	public float precioTotalComponentes(ArrayList<Componente> aux ) {
		float precioTotal = 0;
		if(!aux.isEmpty()) {
			for (Componente componente : aux) {
				precioTotal += componente.getPrecioVentaActual();
			}
		}
		return precioTotal;
	}
	public float precioTotalCombos(ArrayList<Combo> aux ) {
		float precioTotal = 0;
		if(!aux.isEmpty()) {	
			for (Combo combo : aux) {
				precioTotal += combo.precioCombo();
			}
		}
		return precioTotal;
	}
	public float costoFactura(ArrayList<Componente> losComponentes,ArrayList<Combo> losCombos) {
		float total=Tienda.getInstance().precioTotalCombos(losCombos)+Tienda.getInstance().precioTotalComponentes(losComponentes);
		return total;
	}

	public void pagoDeuda(String cedulaCliente, float monto) {
		//validar si el cliente no existe
		Cliente cliente1=null;
		cliente1=findClientebyCedula(cedulaCliente);
		cliente1.setCredito(cliente1.getCredito()-monto);

	}


}
