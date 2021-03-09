package ejers_prog.tema7.tanda2.ejer3;

public class Persona {

	private String nombre;
	private int edad;
	private String telf;
	private String lugar;
	public Persona(String nombre, int edad, String telf, String lugar) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.telf = telf;
		this.lugar = lugar;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public String getTelf() {
		return telf;
	}
	public String getLugar() {
		return lugar;
	}
	@Override
	public String toString() {
		return this.telf + "\t" + this.edad + "\t" + this.nombre + "\t" + this.lugar;
	}
	
	
	
	
}
