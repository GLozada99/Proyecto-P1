package logica;

public class Vendedor extends Persona {
	private String contraseña;
	
	public Vendedor(String nombre, String telefono, String direccion, String cedula, String contraseña) {
		super(nombre, telefono, direccion, cedula);
		this.contraseña=contraseña;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}