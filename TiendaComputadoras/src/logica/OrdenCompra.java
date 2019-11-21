package logica;

public class OrdenCompra {
	private String codigo;
	private Componente compCompra;
	private int cantiCompos;
	private float costoTotal;
	private boolean realizada;//es falsa por defecto, se hace verdadera cuando se hace la compra;
	
	
	public OrdenCompra(String codigo, Componente compCompra, int cantiCompos) {
		super();
		this.codigo = codigo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(float costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	

}
