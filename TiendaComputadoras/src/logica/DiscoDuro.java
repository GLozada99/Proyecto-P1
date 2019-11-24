package logica;

import java.io.Serializable;

public class DiscoDuro extends Componente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String capacidadAlma;
	private String tipoConexion;

	public DiscoDuro(String numeroSerie, String marca, String modelo, int cantMin, int cantMax,
			float precioVentaI, String capacidadAlma, String tipoConexion) {
		super(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI);
		this.capacidadAlma = capacidadAlma;
		this.tipoConexion = tipoConexion;
	}

	public String getCapacidadAlma() {
		return capacidadAlma;
	}
	
	public void setCapacidadAlma(String capacidadAlma) {
		this.capacidadAlma = capacidadAlma;
	}
	
	public String getTipoConexion() {
		return tipoConexion;
	}
	
	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	

}
