package logica;

public class Micro extends Componente {
	private String tipoConexion;
	private String velocidad;
	
	public Micro(String numeroSerie, String marca, String modelo, int cantDisponible, int cantMin, int cantMax,
			float precioVentaI, float precioCompraI, String tipoConexion, String velocidad) {
		super(numeroSerie, marca, modelo, cantDisponible, cantMin, cantMax, precioVentaI, precioCompraI);
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
