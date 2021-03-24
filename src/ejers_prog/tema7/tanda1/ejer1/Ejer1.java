package ejers_prog.tema7.tanda1.ejer1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejer1 {
	
	public static void copiarByteAByte(String nombre) throws IOException {
		String path = "img/" + nombre + ".jpg";
		String copyPath = "img/" + nombre + "_CPY" + ".jpg";
		File f = new File(path);
	
		
		if(f.exists()) {
			FileInputStream fis = new FileInputStream(path);
			FileOutputStream fos = new FileOutputStream(copyPath);
			
			
			while(fis.available() > 0) fos.write(fis.read());
			
			fis.close();
			fos.close();
			
			System.out.println("Copia realizada con exito!");
			
			
		}else {
			System.out.println("La ruta indicada no es correcta");
		}
	}
	
	public static void copiarNbytes(String nombre) throws IOException {
		String path = "img/" + nombre + ".jpg";
		String copyPath = "img/" + nombre + "_CPY" + ".jpg";
		File f = new File(path);
		final int N = 512;
		byte[] buffer = new byte[N];
		
		if(f.exists()) {
			FileInputStream fis = new FileInputStream(path);
			FileOutputStream fos = new FileOutputStream(copyPath);
			
			
			while(fis.available() > 0) {
				fis.read(buffer);
				fos.write(buffer);
			}
			
			fis.close();
			fos.close();
			
			System.out.println("Copia realizada con exito!");
			
			
		}else {
			System.out.println("La ruta indicada no es correcta");
		}
	}
	
	public static void copiarDeGolpe(String nombre) throws IOException{
		String path = "img/" + nombre + ".jpg";
		String copyPath = "img/" + nombre + "_CPY" + ".jpg";
		File f = new File(path);
		
		
		if(f.exists()) {
			FileInputStream fis = new FileInputStream(path);
			FileOutputStream fos = new FileOutputStream(copyPath);
			
			byte[] copyData = new byte[(int) f.length()];
			
			for (int i = 0; i < copyData.length; i++) {
				copyData[i] = (byte) fis.read();
			}
			
			fis.close();
			
			fos.write(copyData);
			fos.close();
			
			System.out.println("Copia realizada con exito!");
			
			
		}else {
			System.out.println("La ruta indicada no es correcta");
		}
	}
	
	public static void main(String[] args) throws IOException {
		
//		copiarByteAByte(args[0]);
//		copiarNbytes(args[0]);
//		copiarDeGolpe(args[0]);
		
	}
	
}
