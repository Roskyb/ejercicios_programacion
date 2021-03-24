import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PruebaFile {

	public static void crearFicherosNums(String nomFich) throws IOException {
		// Crear nomfich con N numeros aleatorios
		// cada uno ocupa un byte 
		
		FileOutputStream fos = new FileOutputStream(nomFich, true);
		
		final int N = 10;
		
		for (int i = 0; i < N ; i++) {
			int n = 1 + (int) (Math.random() * 100);
			fos.write(n);
		
		}
		
		fos.close();
		
	}
	
	public static void verFichNums(String nomFich) throws IOException {
		File f = new File(nomFich);
		
		if(!f.exists()) {
			System.out.println("El fichero no existe!");
		}else {
			FileInputStream fis = new FileInputStream(nomFich);
			
			int leido=fis.read();
		
			while(fis.available() > 0) {
				System.out.println(leido);
				leido = fis.read();
			}
			
			fis.close();
//			ANOTHER WAY
//			while(leido != -1) {
//				System.out.println(leido);
//				leido = fis.read();
//			}
			
			
			
			
		}
		
		
	}
	
	public static boolean copiar(String imgPath, String CopyDest) throws IOException {
		
		if(!new File(imgPath).exists()) return false;

		FileInputStream fis = new FileInputStream(imgPath);
		FileOutputStream fos = new FileOutputStream(CopyDest);
		
		while(fis.available() > 0) {
			fos.write(fis.read());
		}
		
		
		fis.close();
		fos.close();
		
		return true;
		
	}
	
	
	public static void main(String[] args) throws IOException {
	
//		crearFicherosNums("numsAleatorios.bin");
//
		
//		verFichNums("numsAleatorios.bin");		
		
//		System.out.println(copiar("img/img1.jpg", "img/img1copia.jpg"));
		
	}
	
}
