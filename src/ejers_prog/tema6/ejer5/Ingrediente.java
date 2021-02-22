package ejers_prog.tema6.ejer5;

public class Ingrediente {
	private String nombre;
	private int calorias;
	
	
	public Ingrediente(String nombre, int calorias) {
		this.nombre = nombre;
		this.calorias = calorias;
	}


	@Override
	public String toString() {
		return "Ingrediente [nombre=" + nombre + ", calorias=" + calorias + "]";
	}


	public int getCalorias() {
		return calorias;
	}


	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}


	public String getNombre() {
		return nombre;
	}
	
	
	
	

	
	
	
	
}
