package ejers_prog.tema7.tanda2.ejer1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Ejer1 {
	
	public static void main(String[] args) throws IOException {

		File f1 = Ejer1.crearFicheroNumsBin();
		File f2 = new File("files/nums.txt");
		
		generarFicheroNumsTxt(f1, f2);
		
		
	}
	
	
	public static File crearFicheroNumsBin() throws IOException {
		File f = new File("files/nums.bin");
		FileOutputStream fis = new FileOutputStream(f);
		
		int resp = -1;
		
		while(resp != 0) {
			
			System.out.println("Introduce un núnero: ");
			resp = Consola.leeInt();
			
			fis.write((byte) resp);
			
		}
		
		fis.close();
		
		return f;
	}
	
	public static void generarFicheroNumsTxt(File f1, File f2) throws IOException {
		FileInputStream fis = new FileInputStream(f1);
		
		FileWriter fw = new FileWriter(f2);
		
		while(fis.available() > 0) {
			fw.write(Integer.toString(fis.read()) + "\n");
		}
		
		fis.close();
		fw.close();
		
		
	}
	
	
}
