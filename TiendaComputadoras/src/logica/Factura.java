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
	private ArrayList<Integer> cantiComponentes;
	private ArrayList<Integer> cantiCombos;
	private boolean tipo; //si es false, la factura es pagada, si es true, la factura es a credito;

	public Factura(String codigo,float costo, Date fecha, Cliente elCliente, ArrayList<Componente> losComponentes,
			ArrayList<Combo> losCombos,ArrayList<Integer> cantiComponentes, ArrayList<Integer> cantiCombos, boolean tipo) {
		super();
		this.codigo = codigo;
		this.costo = costo;
		this.fecha = fecha;
		this.elCliente = elCliente;
		this.losComponentes = losComponentes;
		this.losCombos = losCombos;
		this.cantiComponentes=cantiComponentes;
		this.cantiCombos=cantiCombos;
		this.tipo = tipo;

		/*if(this.tipo == true) {
			if(elCliente.comprobarLimCredito(costo) == false) {
				elCliente.setCredito(elCliente.getCredito+costo);
			}
			if (elCliente.comprobarLimCredito(costo) == true) {
				System.out.println("El costo excede el límite de credito del cliente");
			}
		}para lo visual*/ 

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


	public int getCantiCompo(Componente aux) {
		return cantiComponentes.get(losComponentes.lastIndexOf(aux));
	}

	public float costoTotal() {
		float total=Tienda.getInstance().precioTotalCombos(losCombos)+Tienda.getInstance().precioTotalComponentes(losComponentes);
		return total;
	}
	/*public void insertComponenteyCant(Componente aux, int aux1) {
		losComponentes.add(aux);
		cantiComponentes.add(aux1);
	}*/

	/*public void deleteComponente(Componente aux) {
		losComponentes.remove(aux);
		cantiComponentes.remove(losComponentes.lastIndexOf(aux));
	}*/

	public ArrayList<Integer> getCantiComponentes() {
		return cantiComponentes;
	}

	public void setCantiComponentes(ArrayList<Integer> cantiComponentes) {
		this.cantiComponentes = cantiComponentes;
	}

	public ArrayList<Integer> getCantiCombos() {
		return cantiCombos;
	}

	public void setCantiCombos(ArrayList<Integer> cantiCombos) {
		this.cantiCombos = cantiCombos;
	}

	/*for (Componente componente1 : losComponentes) {
				componente1.setCantDisponible(orden.getCantiCompos()+componente1.getCantDisponible());
			}*/




}
