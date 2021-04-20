package ejers_prog.tema9.tanda3.ejer1;

public class Punto {

	public double getX() {
		return x;
	}



	public double getY() {
		return y;
	}



	private double x;
	private double y;
	
	public Punto(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	
	
}
