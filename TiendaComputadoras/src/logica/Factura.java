package logica;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private String codigo;
	private float costo;
	private Date fecha;
	private Cliente elCliente;
	private ArrayList<Componente> losComponentes;
	private ArrayList<Combo> losCombos;
	private ArrayList<Integer> enteros;
	private boolean tipo; //si es false, la factura es pagada, si es true, la factura es a credito;
	
	public Factura(String codigo, float costo, Date fecha, Cliente elCliente, ArrayList<Componente> losComponentes,
			ArrayList<Combo> losCombos, boolean tipo) {
		super();
		this.codigo = codigo;
		this.costo = costo;
		this.fecha = fecha;
		this.elCliente = elCliente;
		this.losComponentes = losComponentes;
		this.losCombos = losCombos;
		this.tipo = tipo;
		
		if(this.tipo == true) {
			if(elCliente.limCredito(costo) == false) {
				elCliente.setCredito(+costo);
			}
			if (elCliente.limCredito(costo) == true) {
				System.out.println("El costo excede el límite de credito del cliente");
			}
		}
		
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
	
	
		public int getPrecioCompo(Componente aux) {
			return enteros.get(losComponentes.lastIndexOf(aux));
		}
		
		public void insertComponente(Componente aux, int aux1) {
			losComponentes.add(aux);
			enteros.add(aux1);
		}
		
		public void deleteComponente(Componente aux) {
			losComponentes.remove(aux);
			enteros.remove(losComponentes.lastIndexOf(aux));
		}
	
			/*for (Componente componente1 : losComponentes) {
				componente1.setCantDisponible(orden.getCantiCompos()+componente1.getCantDisponible());
			}*/
		
	
	
	
}
