package ejers_prog.tema8.tanda1.ejer1;

import java.io.IOException;

public class DividePorCero {

	public static void main(String[] args) {
		
		
		System.out.println("Introduce n1 y n2 para dividirlos");
		int n1, n2;
		try {
			n1 = Consola.leeInt();
			n2 = Consola.leeInt();
			System.out.println(n1/n2);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			System.out.println("No se ha introducido un número");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("Error: no se puede dividor entre 0");
		}
		
		
	}
	
}
