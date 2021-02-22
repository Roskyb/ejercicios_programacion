package ejers_prog.tema6.ejer5;

import java.util.HashSet;

public class Plato {

	private String nombre;
	private HashSet<Ingrediente> ingredientes;
	
	
	
	
	public Plato(String nombre, HashSet<Ingrediente> ingredientes) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
	}




	@Override
	public String toString() {
		return "Plato [nombre=" + nombre + ", ingredientes=" + ingredientes + "]";
	}




	public String getNombre() {
		return nombre;
	}
	
	
	
	
}
