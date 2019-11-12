package logica;

public class Cliente extends Persona {
	private float credito;
	private float limCredito;
	
	public Cliente(String nombre, String telefono, String direccion, String cedula) {
		super(nombre, telefono, direccion, cedula);
		credito = 0;
		limCredito = 1000;
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
	
	public boolean comprobarLimCredito(float credito) {
		boolean type = false;
		if(credito+getCredito() > limCredito) {
			type = true;
		}
		return type;
	}
	
}
