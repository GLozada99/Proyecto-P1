package logica;

public class OrdenCompra {
	private Componente compCompra;
	private int cantiCompos;
	private boolean realizada;//es falsa por defecto, se hace verdadera cuando se hace la compra;
	private Precio nuevoPrecioCompra;
	public OrdenCompra(Componente compCompra, int cantiCompos, float precioCompra) {
		super();
		this.compCompra = compCompra;
		this.cantiCompos = cantiCompos;
		realizada = false;
		nuevoPrecioCompra=new Precio(compCompra.getPrecioVentaActual(), precioCompra, false);
		
	}
	public Componente getCompCompra() {
		return compCompra;
	}
	public void setCompCompra(Componente compCompra) {
		this.compCompra = compCompra;
	}
	public int getCantiCompos() {
		return cantiCompos;
	}
	public void setCantiCompos(int cantiCompos) {
		this.cantiCompos = cantiCompos;
	}
	public boolean isRealizada() {
		return realizada;
	}
	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}
	public void hacerCompra(Proveedor aux) {
		compCompra.getPrecios().add(nuevoPrecioCompra);
		aux.setDebito(cantiCompos*nuevoPrecioCompra.getPrecioCompra());
		realizada=true;
		compCompra.setCantDisponible(compCompra.getCantDisponible()+cantiCompos);
		
	}

}
