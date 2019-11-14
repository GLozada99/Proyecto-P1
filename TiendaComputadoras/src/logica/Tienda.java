package logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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

/*	public void removeComponente(Componente componenteElim) {
		for (Componente componente : losComponentes) {
			if(componente == componenteElim) {
				losComponentes.remove(componenteElim);
			}
		}
	}*/

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

	public int[]  cantComponentesTipo(ArrayList<Componente> aux, ArrayList<Integer> cant1){
		int[] cant = new int[4];
		int cant2 = 0; 
		for (int i = 0; i < 4; i++) {
			cant[i]=0;
		}
		for (Componente componente : aux) {
			cant2 = cant1.get(aux.lastIndexOf(componente));
			if(componente instanceof DiscoDuro){
				cant[0] += cant2;
			}
			if(componente instanceof Micro){
				cant[1] += cant2;
			}
			if(componente instanceof MotherBoard){
				cant[2] += cant2;
			}
			if(componente instanceof RAM){
				cant[3] += cant2;
			}
		}
		return cant;
	}
	
	public int cantCombos(ArrayList<Combo> aux, ArrayList<Integer> cant1) {
		int cant = 0;
		int cant2 = 0;
		if(!aux.isEmpty()) {
			for (Combo combo : aux) {
			cant2 = cant1.get(aux.lastIndexOf(combo));
			cant += cant2;
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
		float total=precioTotalCombos(losCombos)+precioTotalComponentes(losComponentes);
		return total;
	}

	public void pagoDeuda(String cedulaCliente, float monto) {
		Cliente cliente1=findClientebyCedula(cedulaCliente);
		if (cliente1!=null) {
		cliente1.setCredito(cliente1.getCredito()-monto);
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Este cliente no existe");
		}
	}
	
	public void restaCantiComponentes(ArrayList<Componente> componentes, ArrayList<Combo> combos,ArrayList<Integer> cantiCompo,ArrayList<Integer> cantiCombo) {
		int i=0;
		for (Componente componente : componentes) {
			componente.setCantDisponible(componente.getCantDisponible()-cantiCompo.get(i));
			i++;
		}
		i=0;
		for (Combo combo : combos) {
			for (int j = 0; j < cantiCombo.get(j); j++) {
				for (int k = 0; k < combo.getComponentes().size(); k++) {
					combo.getComponentes().get(k).setCantDisponible(combo.getComponentes().get(k).getCantDisponible()-1);;	
				}
				
			}
		}
	}
	//public void confirmarLogin(String nombre, String cedula);


}
