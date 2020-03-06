package logica;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Tienda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Componente> losComponentes;
	private ArrayList<Componente> losCompTemp;
	private ArrayList <Cliente> losClientes;
	private ArrayList <Persona> losUsuarios;
	private ArrayList<Factura> lasFacturas;
	private ArrayList<Proveedor> losQueVendenTemp;
	private ArrayList<Float> preciosLosQueVendenTemp;
	private ArrayList<Float> preciosCadaCompTemp;
	private ArrayList<Combo> losCombo;
	private ArrayList<Proveedor> losProveedores;
	private ArrayList<OrdenCompra> lasOrdenes;
	private ArrayList<OrdenCompra> ordenesSinProcesar;
	private Persona usuarioActual;
	private int generadorCodigoCombo;
	private int generadorCodigoFactura;
	private int generadorCodigoComponentes;
	private int generadorCodigoOrdenCompra;
	private static Tienda tienda=null;

	private Tienda() {
		super();
		losComponentes = new ArrayList<>();
		losClientes = new ArrayList<>();
		lasFacturas = new ArrayList<>();
		losCombo = new ArrayList<>();
		losProveedores = new ArrayList<>();
		lasOrdenes = new ArrayList<>();
		losUsuarios = new ArrayList<>();
		ordenesSinProcesar = new ArrayList<>();
		losCompTemp=new ArrayList<>();
		preciosCadaCompTemp = new ArrayList<>();
		losQueVendenTemp = new ArrayList<>();
		preciosLosQueVendenTemp = new ArrayList<>();
		generadorCodigoCombo = 1;
		generadorCodigoFactura = 1;
		generadorCodigoComponentes = 1;
		generadorCodigoOrdenCompra = 1; 
	}

	public static Tienda getInstance() {
		if(tienda==null){
			tienda = new Tienda();
		}
		return tienda;
	}

	public ArrayList<Componente> getLosComponentes() {
		return losComponentes;
	}

	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		this.losComponentes = losComponentes;
	}

	public static Tienda getTienda() {
		return tienda;
	}

	public static void setTienda(Tienda tienda) {
		Tienda.tienda = tienda;
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

	public ArrayList<Proveedor> getLosQueVendenTemp() {
		return losQueVendenTemp;
	}

	public void setLosQueVendenTemp(ArrayList<Proveedor> losQueVendenTemp) {
		this.losQueVendenTemp = losQueVendenTemp;
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

	public int getGeneradorCodigoOrdenCompra() {
		return generadorCodigoOrdenCompra;
	}

	public void setGeneradorCodigoOrdenCompra(int generadorCodigoOrdenCompra) {
		this.generadorCodigoOrdenCompra = generadorCodigoOrdenCompra;
	}

	public void setLosCombo(ArrayList<Combo> losCombo) {
		this.losCombo = losCombo;
	}

	public void agregarComponente(Componente aux) {
		losComponentes.add(aux);
		generadorCodigoComponentes++;
	}

	public void agregarFactura(Factura aux) {
		lasFacturas.add(aux);
		generadorCodigoFactura++;
	}

	public void agregarOrden(OrdenCompra aux) {
		ordenesSinProcesar.add(aux);
		generadorCodigoOrdenCompra++;
	}

	public void agregarCombo(Combo aux) {
		losCombo.add(aux);
		generadorCodigoCombo++;
	}
	public void eliminarCombo(Combo aux) {
		losCombo.remove(aux);
		generadorCodigoCombo--;
	}

	public Cliente findClientebyCedula(String cedula) {
		Cliente clienteFound = null;
		boolean find = false;
		int i=0;
		while (i<losClientes.size()&&!find) {
			if(losClientes.get(i).getCodigo().equalsIgnoreCase(cedula)){
				clienteFound = losClientes.get(i);
				find = true;
			}
			i++;
		}
		return clienteFound;
	}
	public Persona usuarioByCodigo(String cedula) {
		Persona usuarioFound = null;
		boolean find = false;
		int i=0;
		while (i<losUsuarios.size()&&!find) {
			if(losUsuarios.get(i).getCodigo().equalsIgnoreCase(cedula)){
				usuarioFound = losUsuarios.get(i);
				find = true;
			}
			i++;
		}
		return usuarioFound;
	}

	public Proveedor findProveedrobyRNC(String rnc) {
		Proveedor proveedorFound = null;
		boolean find = false;
		int i=0;
		while (i<losProveedores.size()&&!find) {
			if(losProveedores.get(i).getCodigo().equalsIgnoreCase(rnc)){
				proveedorFound = losProveedores.get(i);
				find = true;
			}
			i++;
		}
		return proveedorFound;
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

	public int cantComponentes(ArrayList<Componente> aux, ArrayList<Integer> cant1) {
		int cant = 0;
		int cant2 = 0;
		if(!aux.isEmpty()) {
			for (Componente componente : aux) {
				cant2 = cant1.get(aux.lastIndexOf(componente));
				cant += cant2;
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

	public float precioTotalComponentes(ArrayList<Componente> aux,ArrayList<Integer> cantidades) {
		float precioTotal = 0;
		int i = 0;
		if(!aux.isEmpty()&&!cantidades.isEmpty()) {
			for (Componente componente : aux) {
				precioTotal += componente.getPrecioVentaActual()*cantidades.get(i);
				i++;
			}
		}
		return precioTotal;
	}
	public float precioTotalCombos(ArrayList<Combo> aux, ArrayList<Integer> cantidades ) {
		float precioTotal = 0;
		int i = 0;
		if(!aux.isEmpty()&&!cantidades.isEmpty()) {	
			for (Combo combo : aux) {
				precioTotal += combo.precioCombo()*cantidades.get(i);
				i++;
			}
		}
		return precioTotal;
	}
	public float costoFactura(ArrayList<Componente> losComponentes,ArrayList<Integer> cantidadesComponentes,ArrayList<Combo> losCombos,ArrayList<Integer> cantidadesCombos) {
		float total=precioTotalCombos(losCombos,cantidadesCombos)+precioTotalComponentes(losComponentes,cantidadesComponentes);
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

	public void restaCantiComponentes(ArrayList<Componente> componentes, ArrayList<Integer> cantiCompo) {
		int i=0;
		boolean ordenCompra=true;
		for (Componente componente : componentes) {
			componente.setCantDisponible(componente.getCantDisponible()-cantiCompo.get(i));
			i++;
			if(componente.getCantDisponible()<componente.getCantMin()) {
				for (OrdenCompra orden : Tienda.getInstance().getOrdenesSinProcesar()) {
					if(orden.getCompCompra().equals(componente)) {
						ordenCompra=false;
					}

				}
				if(ordenCompra) {
					OrdenCompra aux = new OrdenCompra("OC-"+Tienda.getInstance().getGeneradorCodigoOrdenCompra(), componente, componente.getCantMax()-componente.getCantDisponible());
					Tienda.getInstance().agregarOrden(aux);
				}

			}
		}

	}
	public void restaCantiCombos(ArrayList<Combo> combos, ArrayList<Integer> cantiCombo) {
		for (Combo combo : combos) {
			for (Componente componente : combo.getComponentes()) {
				componente.setCantDisponible(componente.getCantDisponible()-1*cantiCombo.get(combos.lastIndexOf(combo)));		
			}
		}
	}

	public boolean relacionFactura(Cliente elCliente,float precio, ArrayList<Componente> misComponentes, ArrayList<Integer> cantComponentes,ArrayList<Combo> misCombos, ArrayList<Integer> cantCombos, boolean tipo) {
		boolean cantidad=true;
		boolean facturar=false;
		boolean limite= true;
		ArrayList<Integer> guardarCantidades = new ArrayList<Integer>();

		for (Combo combo : misCombos) {
			int i=0;
			for (Componente componente : combo.getComponentes()) {
				guardarCantidades.add(componente.getCantDisponible());
				componente.setCantDisponible(componente.getCantDisponible()-1*cantCombos.get(i));
				i++;
			}	
		}

		for (Componente elComponente : misComponentes) {
			if(elComponente.getCantDisponible() < cantComponentes.get(misComponentes.lastIndexOf(elComponente))) {
				cantidad = false;
			}
		}

		if(tipo==true) {
			if(elCliente.getLimCredito() < precio+ elCliente.getCredito()) {
				limite = false;
			}
		}

		if(cantidad && limite) {
			facturar = true;
		}

		int i=0;
		for (Combo combo : misCombos) {
			for (Componente componente : combo.getComponentes()) {
				guardarCantidades.add(componente.getCantDisponible());
				componente.setCantDisponible(guardarCantidades.get(i));
				i++;	
			}
		}
		return facturar;
	}
	public void hacerCompra(OrdenCompra orden,Proveedor aux) {
		orden.getCompCompra().getPrecios().add(new Precio(orden.getCompCompra().getPrecioVentaActual(), aux.getPrecioCompo(orden.getCompCompra()),false));//getPreciosCompos().get(aux.getMisCompos().lastIndexOf(orden.getCompCompra())), false));
		aux.setDebito(orden.getCantiCompos()*aux.getPrecioCompo(orden.getCompCompra()));
		orden.setCostoTotal(orden.getCantiCompos()*aux.getPrecioCompo(orden.getCompCompra()));
		orden.setRealizada(true);
		orden.getCompCompra().setCantDisponible(orden.getCompCompra().getCantDisponible()+orden.getCantiCompos());
		lasOrdenes.add(orden);
		aux.getMisOrdenes().add(orden);
		ordenesSinProcesar.remove(orden);

	}

	/*public void comboMas(Combo combo, int cantidad) {
		combo.setCantidad(combo.getCantidad()+cantidad);
		for (Componente componente : combo.getComponentes()) {
			componente.setCantDisponible(componente.getCantDisponible()-cantidad);
			if(componente.getCantDisponible()<componente.getCantMin()) {
				OrdenCompra aux = new OrdenCompra("OC"+generadorCodigoOrdenCompra,componente, componente.getCantMax()-componente.getCantDisponible());
				Tienda.getInstance().agregarOrden(aux);
			}
		}
	}*/

	public OrdenCompra findOrdenComprabyCodigo(String codigo) {
		OrdenCompra ordenFound = null;
		boolean find = false;
		int i=0;
		while (i<ordenesSinProcesar.size()&&!find) {
			if(ordenesSinProcesar.get(i).getCodigo().equalsIgnoreCase(codigo)){
				ordenFound = ordenesSinProcesar.get(i);
				find = true;
			}
			i++;
		}
		return ordenFound;
	}

	public Combo findCombobyCodigo(String codigo) {
		Combo comboFound = null;
		boolean find = false;
		int i=0;
		while (i<losCombo.size()&&!find) {
			if(losCombo.get(i).getNombre().equalsIgnoreCase(codigo)){
				comboFound = losCombo.get(i);
				find = true;
			}
			i++;
		}
		return comboFound;
	}

	public void primeraOrdenCompra(Componente componente) {
		OrdenCompra aux = new OrdenCompra("OC-"+Tienda.getInstance().getGeneradorCodigoOrdenCompra(), componente, componente.getCantMax()-componente.getCantDisponible());
		Tienda.getInstance().agregarOrden(aux);
	}


	public ArrayList<OrdenCompra> getOrdenesSinProcesar() {
		return ordenesSinProcesar;
	}

	public void setOrdenesSinProcesar(ArrayList<OrdenCompra> ordenesSinProcesar) {
		this.ordenesSinProcesar = ordenesSinProcesar;
	}

	public ArrayList <Persona> getLosUsuarios() {
		return losUsuarios;
	}

	public void setLosUsuarios(ArrayList <Persona> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public Persona getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Persona usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	public boolean confirmarLogin(String codigo, String contrasena) {
		boolean login = false;
		Persona usuario = null;
		for (int i = 0; i < losUsuarios.size()&&!login; i++) {
			usuario = losUsuarios.get(i);
			if(usuario instanceof Administrador || usuario instanceof Vendedor) {
				try {
					if(usuario.getCodigo().equals(codigo)&&((((Administrador)usuario).getContraseña().equals(contrasena)))){
						setUsuarioActual(usuario);
						login = true;
					}
				}
				catch (ClassCastException e) {
					if(usuario.getCodigo().equals(codigo)&&((((Vendedor)usuario).getContraseña().equals(contrasena)))){
						setUsuarioActual(usuario);
						login = true;
					}

				}
			}
		}

		return login;
	}

	public ArrayList<Float> getPreciosLosQueVendenTemp() {
		return preciosLosQueVendenTemp;
	}

	public void setPreciosLosQueVendenTemp(ArrayList<Float> preciosLosQueVendenTemp) {
		this.preciosLosQueVendenTemp = preciosLosQueVendenTemp;
	}

	public ArrayList<Componente> getLosCompTemp() {
		return losCompTemp;
	}

	public void setLosCompTemp(ArrayList<Componente> losCompTemp) {
		this.losCompTemp = losCompTemp;
	}

	public ArrayList<Float> getPreciosCadaCompTemp() {
		return preciosCadaCompTemp;
	}

	public void setPreciosCadaCompTemp(ArrayList<Float> preciosCadaCompTemp) {
		this.preciosCadaCompTemp = preciosCadaCompTemp;
	}

	public ArrayList<Integer> componenteMasVendidoTipo(){
		ArrayList<Integer> masVendido= new ArrayList<>();
		int disco=0;
		int micro=0;
		int Ram=0;
		int MB=0;

		try {
			for (Factura miFactura : lasFacturas) {
				for (Componente miComponente : miFactura.getLosComponentes()) {
					if(miComponente instanceof DiscoDuro) {
						disco+=miFactura.getCantiCompo(miComponente);

					}
					if(miComponente instanceof Micro) {
						micro+=miFactura.getCantiCompo(miComponente);

					}
					if(miComponente instanceof RAM) {
						Ram+=miFactura.getCantiCompo(miComponente);

					}
					if(miComponente instanceof MotherBoard) {
						MB+=miFactura.getCantiCompo(miComponente);

					}

				}
				for (Combo miCombo : miFactura.getLosCombos()) {
					for (Componente miComponente : miCombo.getComponentes()) {
						if(miComponente instanceof DiscoDuro) {
							disco+=miFactura.getCantiUnCombo(miCombo);

						}
						if(miComponente instanceof Micro) {
							micro+=miFactura.getCantiUnCombo(miCombo);

						}
						if(miComponente instanceof RAM) {
							Ram+=miFactura.getCantiUnCombo(miCombo);

						}
						if(miComponente instanceof MotherBoard) {
							MB+=miFactura.getCantiUnCombo(miCombo);

						}

					}

				}


			}
			masVendido.add(0, disco);masVendido.add(1, micro);masVendido.add(2, Ram);masVendido.add(3, MB);


		} catch (NullPointerException e) {
			// TODO: handle exception

		}
		return (ArrayList<Integer>) masVendido.clone();
	}

	public ArrayList<Integer> componenteMasVendidoCantidad(){
		ArrayList<Integer> masVendido= new ArrayList<>();
		int compo=0;
		ArrayList<Componente> componente= new ArrayList<>();
		try {
			for (Factura miFactura : lasFacturas) {
				for (Componente miComponente : miFactura.getLosComponentes()) {
					compo = miFactura.getCantiCompo(miComponente);
					componente.add(miComponente);
					if (componente.contains(miComponente)) {
						if (true) {

						}
					}
				}


			}
			//masVendido.add(0, );
			//masVendido.add(1, micro);
			//masVendido.add(2, Ram);


		} catch (NullPointerException e) {
			// TODO: handle exception

		}
		return (ArrayList<Integer>) masVendido.clone();
	}	
	public ArrayList<Persona> vendedoresMasVentas(){
		ArrayList<Persona> losVendedores = new ArrayList<>();
		for (Persona vendedorA : losUsuarios) {
			if(vendedorA instanceof Vendedor) {
				losVendedores.add(vendedorA);
			}
		}
		ArrayList<Persona> organizados = new ArrayList<>();
		int i=0;
		for (Persona vendedor : losVendedores) {
			if(i<5) {
				organizados.add(vendedor);
				i++;
			}
			else {
				if(((Vendedor)vendedor).getVentas()>((Vendedor)organizados.get(0)).getVentas()){
					organizados.add(0, vendedor);												
				}
				else if(((Vendedor)vendedor).getVentas()>((Vendedor)organizados.get(1)).getVentas()){
					organizados.add(1, vendedor);

				}
				else if(((Vendedor)vendedor).getVentas()>((Vendedor)organizados.get(2)).getVentas()){
					organizados.add(2, vendedor);

				}
				else if(((Vendedor)vendedor).getVentas()>((Vendedor)organizados.get(3)).getVentas()){
					organizados.add(3, vendedor);

				}
				else if(((Vendedor)vendedor).getVentas()>((Vendedor)organizados.get(4)).getVentas()){
					organizados.add(4, vendedor);

				}
			}

		}
		return organizados;

	}
}
