package logica;

import java.io.Serializable;

public class Precio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float precioVenta;
	private float precioCompra;
	private boolean cancelado;
	
	public Precio(float precioVenta, float precioCompra, boolean cancelado) {
		super();
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.cancelado = cancelado;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	
	
}
