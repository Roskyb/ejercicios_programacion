package ejers_prog.tema8.tanda1.ejer3;

public class SumaNumeros {

	public static void main(String[] args) {
		int suma = 0;
		for (String num : args) {
			
			try {
				suma += Integer.parseInt(num);
			}catch(NumberFormatException e) {
				System.out.println(num + " no se puede sumar");
			}
		}
		
		System.out.println(suma);
		
	}
	
}
