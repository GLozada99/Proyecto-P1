package logica;

public class Main {

	public static void main(String[] args) {
		Persona c1=new Cliente("Juan", "00", "", "11");
		Tienda.getInstance().getLosClientes().add((Cliente) c1);
		
		System.out.println(Tienda.getInstance().findClientebyCedula("11").getNombre());

	}

}
