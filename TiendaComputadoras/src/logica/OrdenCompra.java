package logica;

public class OrdenCompra {
	private Componente compCompra;
	int cantiCompos;
	boolean realizada;
	public OrdenCompra(Componente compCompra, int cantiCompos, boolean realizada) {
		super();
		this.compCompra = compCompra;
		this.cantiCompos = cantiCompos;
		this.realizada = realizada;
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
