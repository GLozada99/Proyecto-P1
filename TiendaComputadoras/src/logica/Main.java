package logica;

public class Main {

	public static void main(String[] args) {
		Persona c1=new Cliente("Juan", "00", "", "11");
		Tienda.getInstance().getLosClientes().add((Cliente) c1);
		//Componente co1=new DiscoDuro(modelo, numeroSerie, marca, cantDisponible, cantMin, cantMax, precioVentaI, precioCompraI, capacidadAlma, tipoConexion);
		System.out.println(Tienda.getInstance().findClientebyCedula("11").getNombre());

	}

}
