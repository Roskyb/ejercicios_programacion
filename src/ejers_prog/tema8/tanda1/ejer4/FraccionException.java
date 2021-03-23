package ejers_prog.tema8.tanda1.ejer4;

public class FraccionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4703176105786568029L;

	
	private boolean detalle;
	FraccionException(boolean detalle) {
		super();
		this.detalle = detalle;
	}
	
	public FraccionException() {
		super();
	}
	public String toString() {
		String error = "\"El resultado de esta operaci�n es una fracci�n inv�lida\"";
		if(detalle) error = "No se puede instanciar fracci�n con denominador 0";
		return error;
	}

	
}
