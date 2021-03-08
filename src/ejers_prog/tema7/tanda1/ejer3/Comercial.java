package ejers_prog.tema7.tanda1.ejer3;

import java.io.Serializable;

public class Comercial implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5304229521776738386L;

	private String nombre;
	private double salario;
	private TelefonoMovil telf;
	
	public Comercial(String nombre, double salario) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.telf = null;
	}
	
	
	public Comercial(String nombre, double salario, TelefonoMovil telf) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.telf = telf;
	}
	
	public void ver() {
		System.out.print(this.toString());
		this.telf.ver();
	}
	
	public void trabajar() {
		salario+=10;
		if(telf != null) {
			telf.llamar(15);
		}
	}
	
	public void trabajar(int g, int m) {
		salario+=g;
		if(telf != null) {
			telf.llamar(m);
		}
	}


	public String getNombre() {
		return nombre;
	}


	public TelefonoMovil getTelf() {
		return telf;
	}


	public void setTelf(TelefonoMovil telf) {
		this.telf = telf;
	}


	@Override
	public String toString() {
		return "Comercial [nombre=" + nombre + ", salario=" + salario + "]";
	}
	
	
	
	
	
}
