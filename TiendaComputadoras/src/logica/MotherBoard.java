package logica;

public class MotherBoard extends Componente {
	private String tipoConector;
	private String tipoRAM;
	//private ArrayList<String> 
	
	public MotherBoard(String modelo, String numeroSerie, String marca, int cantDisponible, float cantMin,
			float cantMax, float precioVentaI, float precioCompraI, String tipoConector, String tipoRAM) {
		super(modelo, numeroSerie, marca, cantDisponible, cantMin, cantMax, precioVentaI, precioCompraI);
		this.tipoConector = tipoConector;
		this.tipoRAM = tipoRAM;
	}
	
	public String getTipoConector() {
		return tipoConector;
	}
	
	public void setTipoConector(String tipoConector) {
		this.tipoConector = tipoConector;
	}
	
	public String getTipoRAM() {
		return tipoRAM;
	}
	
	public void setTipoRAM(String tipoRAM) {
		this.tipoRAM = tipoRAM;
	}
	

}
