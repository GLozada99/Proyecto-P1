package logica;

import java.io.Serializable;

public class RAM extends Componente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cantMemoria;
	private String tipoMemoria;
	
	public RAM(String numeroSerie, String marca, String modelo, int cantMin, int cantMax,
			float precioVentaI, String cantMemoria, String tipoMemoria) {
		super(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI);
		this.cantMemoria = cantMemoria;
		this.tipoMemoria = tipoMemoria;
	}
	
	public String getCantMemoria() {
		return cantMemoria;
	}
	
	public void setCantMemoria(String cantMemoria) {
		this.cantMemoria = cantMemoria;
	}
	
	public String getTipoMemoria() {
		return tipoMemoria;
	}
	
	public void setTipoMemoria(String tipoMemoria) {
		this.tipoMemoria = tipoMemoria;
	}
	

}
