package logica;

public class Micro extends Componente {
	private String tipoConexion;
	private String velocidad;
	public Micro(String modelo, String numeroSerie, String marca, int cantDisponible, float cantMin, float cantMax,
			float precioVentaI, float precioCompraI, String tipoConexion, String velocidad) {
		super(modelo, numeroSerie, marca, cantDisponible, cantMin, cantMax, precioVentaI, precioCompraI);
		this.tipoConexion = tipoConexion;
		this.velocidad = velocidad;
	}
	public String getTipoConexion() {
		return tipoConexion;
	}
	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}
	public String getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}
	

}
