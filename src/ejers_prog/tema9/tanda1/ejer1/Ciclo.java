package ejers_prog.tema9.tanda1.ejer1;

public class Ciclo {

	private String nombreCiclo;
	private String tipo;
	
	
	public Ciclo(String nombreCiclo, String tipo) {
		super();
		this.nombreCiclo = nombreCiclo;
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return nombreCiclo + "(" + tipo + ")";
	}
	
	
	
	
	
	
	
	
}
