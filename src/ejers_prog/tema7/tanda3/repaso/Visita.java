package ejers_prog.tema7.tanda3.repaso;

import java.io.Serializable;

public class Visita implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2651356418154066867L;
	private String nombre;
	private int cantPersonas;
	private int[] hora;
	
	
	
	
	public Visita(String nombre, int cantPersonas, int[] hora) {
		super();
		this.nombre = nombre;
		this.cantPersonas = cantPersonas;
		this.hora = hora;
	}




	public int[] getHora() {
		return hora;
	}
	
	
	
}
