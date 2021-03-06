package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private float costo;
	private Date fecha;
	private Cliente elCliente;
	private ArrayList<Componente> losComponentes = null;
	private ArrayList<Combo> losCombos = null;
	private ArrayList<Integer> cantiComponentes = null;
	private ArrayList<Integer> cantiCombos = null;
	private boolean tipo; //si es false, la factura es pagada, si es true, la factura es a credito;

	public Factura(String codigo,float costo, Cliente elCliente, ArrayList<Componente> losComponentes,
			ArrayList<Combo> losCombos,ArrayList<Integer> cantiComponentes, ArrayList<Integer> cantiCombos, boolean tipo) {
		super();
		this.codigo = codigo;
		this.costo = costo;
		fecha= new Date();
		this.elCliente = elCliente;
		this.losComponentes = losComponentes;
		this.losCombos = losCombos;
		this.cantiComponentes=cantiComponentes;
		this.cantiCombos=cantiCombos;
		this.tipo = tipo;
		
		/*if(this.losComponentes == null) {
			this.losComponentes = new ArrayList<Componente>();
		}
		if(this.losCombos == null) {
			this.losCombos = new ArrayList<Combo>();
		}
		if(this.cantiComponentes == null) {
			this.cantiComponentes = new ArrayList<Integer>();
		}
		if(this.cantiComponentes == null) {
			this.cantiCombos = new ArrayList<Integer>();
		}*/
		

		Tienda.getInstance().restaCantiComponentes(losComponentes, cantiComponentes);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Date getFecha() {
		return fecha;
	}

	@SuppressWarnings("deprecation")
	public void setFecha(String fecha) {
		//("yyyy-MM-dd HH:mm:ss");
		String yearMonthDay = fecha.split(" ")[0];
		String hourMinuteSecond = fecha.split(" ")[1];
		Integer year = Integer.valueOf(yearMonthDay.split("-")[0]);
		Integer month = Integer.valueOf(yearMonthDay.split("-")[1]);
		Integer day = Integer.valueOf(yearMonthDay.split("-")[2]);
		
		Integer hour = Integer.valueOf(hourMinuteSecond.split(":")[0]);
		Integer minute = Integer.valueOf(hourMinuteSecond.split(":")[1]);
		Float secondF = Float.valueOf(hourMinuteSecond.split(":")[2]);
		Integer second = secondF.intValue();
		Date auxfecha = new Date(year, month, day, hour, minute, second);
		
		this.fecha = auxfecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getElCliente() {
		return elCliente;
	}

	public void setElCliente(Cliente elCliente) {
		this.elCliente = elCliente;
	}

	public ArrayList<Componente> getLosComponentes() {
		return losComponentes;
	}

	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		this.losComponentes = losComponentes;
	}

	public ArrayList<Combo> getLosCombos() {
		return losCombos;
	}

	public void setLosCombos(ArrayList<Combo> losCombos) {
		this.losCombos = losCombos;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}


	public int getCantiCompo(Componente aux) {
		return cantiComponentes.get(losComponentes.lastIndexOf(aux));
	}

	public ArrayList<Integer> getCantiComponentes() {
		return cantiComponentes;
	}

	public void setCantiComponentes(ArrayList<Integer> cantiComponentes) {
		this.cantiComponentes = cantiComponentes;
	}

	public ArrayList<Integer> getCantiCombos() {
		return cantiCombos;
	}
	public int getCantiUnCombo(Combo aux) {
		return cantiCombos.get(losCombos.lastIndexOf(aux));
	}

	public void setCantiCombos(ArrayList<Integer> cantiCombos) {
		this.cantiCombos = cantiCombos;
		
	}
	
	




}
