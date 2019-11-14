package logica;

public class OrdenCompra {
	private Componente compCompra;
	private int cantiCompos;
	private boolean realizada;//es falsa por defecto, se hace verdadera cuando se hace la compra;
	
	
	public OrdenCompra(Componente compCompra, int cantiCompos) {
		super();
		this.compCompra = compCompra;
		this.cantiCompos = cantiCompos;
		realizada = false;
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
	
	

}
