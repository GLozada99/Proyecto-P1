package logica;

public class Vendedor extends Persona {
	private String contrase�a;
	
	public Vendedor(String nombre, String telefono, String direccion, String cedula, String contrase�a) {
		super(nombre, telefono, direccion, cedula);
		this.contrase�a=contrase�a;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

}