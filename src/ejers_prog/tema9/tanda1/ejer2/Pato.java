package ejers_prog.tema9.tanda1.ejer2;

public class Pato {
	
	private String nombrePato;
	private int edadPato;
	
	
	public Pato(String nombrePato, int edadPato) {
		super();
		this.nombrePato = nombrePato;
		this.edadPato = edadPato;
	}


	@Override
	public String toString() {
		return nombrePato + ", " + edadPato + " años";
	}
	
	

	
}
