package logica;

public class Cliente extends Persona {
	private String cedula;
	private float credito;
	private float limCredito;
	
	public Cliente(String nombre, String telefono, String direccion, String cedula, float limCredito) {
		super(nombre, telefono, direccion);
		this.cedula = cedula;
		this.credito = 0;
		this.limCredito = limCredito;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public float getCredito() {
		return credito;
	}
	
	public void setCredito(float credito) {
		this.credito = credito;
	}
	
	public float getLimCredito() {
		return limCredito;
	}
	
	public void setLimCredito(float limCredito) {
		this.limCredito = limCredito;
	}

}
