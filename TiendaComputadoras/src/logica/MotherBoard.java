package logica;

public class MotherBoard extends Componente {
	private String tipoConector;
	private String tipoRAM;
	//private ArrayList<String> 
	
	public MotherBoard(String numeroSerie, String marca, String modelo, int cantMin, int cantMax,
			float precioVentaI, String tipoConector, String tipoRAM) {
		super(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI);
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
