package ejers_prog.tema8.tanda1.ejer3;

public class SumaNumeros {

	public static void main(String[] args) {
		int suma = 0;
		int cont = 0;
		for (String num : args) {
			
			try {
				suma += Integer.parseInt(num);
			}catch(NumberFormatException e) {
				System.out.println(num + " no se puede sumar");
				cont++;
			}
		}
		
		System.out.println("La suma total es " + suma);
		System.out.println("No se han podido sumar" + cont + " números");
		
	}
	
}
