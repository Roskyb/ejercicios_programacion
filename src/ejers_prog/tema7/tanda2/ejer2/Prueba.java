package ejers_prog.tema7.tanda2.ejer2;

import java.io.IOException;

public class Prueba {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DuplicadorCar a = new DuplicadorCar();
		a.duplicar("files/loremCar.txt");
		DuplicadorPorBloques b = new DuplicadorPorBloques();
		b.duplicar("files/loremBloq.txt");
		DuplicadorValidado c = new DuplicadorValidado();
		c.duplicar("files/loremValidado.txt");
		
		
	}

}
