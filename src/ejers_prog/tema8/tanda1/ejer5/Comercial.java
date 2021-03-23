package ejers_prog.tema8.tanda1.ejer5;

import java.io.Serializable;

public class Comercial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441248997523072778L;
	private String nombre;
	private double salario;
	private TelefonoMovil telf;
	
	public Comercial(String nombre, double salario, TelefonoMovil telefonoMovil) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.telf = telefonoMovil;
	}
	
	public void ver() {
		String telf = this.telf.toString();
		System.out.println(this.toString() + " " + telf);
	}

	public double getSalario() {
		return salario;
	}

	@Override
	public String toString() {
		return "Comercial [nombre=" + nombre + ", salario=" + salario + ", telf=" + telf + "]";
	}

	public TelefonoMovil getTelf() {
		return telf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comercial other = (Comercial) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	
	
	
}
