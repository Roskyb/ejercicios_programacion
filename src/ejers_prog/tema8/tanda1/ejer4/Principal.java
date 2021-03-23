package ejers_prog.tema8.tanda1.ejer4;

public class Principal {

	public static void main(String[] args) {
		try {
			Fraccion a = new Fraccion(4,8);
			
			System.out.println(a.sumar(new Fraccion(6, 0)));
		} catch (FraccionException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		
	}
}
