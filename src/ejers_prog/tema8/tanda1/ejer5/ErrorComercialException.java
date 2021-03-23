package ejers_prog.tema8.tanda1.ejer5;

public class ErrorComercialException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3909882449989913444L;
	private String detalle;

	

	public ErrorComercialException(String detalle) {
		super(detalle);
		this.detalle = detalle;
	}



	public String toString() {
		return "Error: " + detalle;
	}

}
