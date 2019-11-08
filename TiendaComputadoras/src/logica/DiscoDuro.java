package logica;

public class DiscoDuro extends Componente {
	private String CapacidadAlma;
	private String tipoConexion;
	public DiscoDuro(String modelo, String numeroSerie, String marca, int cantDisponible, float cantMin, float cantMax,
			float precioVentaI, float precioCompraI, String capacidadAlma, String tipoConexion) {
		super(modelo, numeroSerie, marca, cantDisponible, cantMin, cantMax, precioVentaI, precioCompraI);
		CapacidadAlma = capacidadAlma;
		this.tipoConexion = tipoConexion;
	}
	public String getCapacidadAlma() {
		return CapacidadAlma;
	}
	public void setCapacidadAlma(String capacidadAlma) {
		CapacidadAlma = capacidadAlma;
	}
	public String getTipoConexion() {
		return tipoConexion;
	}
	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	

}
