package ejers_prog.tema8.tanda1.ejer2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OperFicheros {

	public static void main(String[] args) {
		String nomFich = Consola.leeString();
		System.out.println(compruebaTamanio(nomFich));
		ArrayList<String> arr = null;
		try {
			arr = copiaLineas(nomFich);
			for (String string : arr) {
				System.out.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("El archivo no se ha podido encontrar");
		} catch (NullPointerException e) {
			System.out.println("No existe el arrayList");
		}
		
		
		
	}
	
	static private long compruebaTamanio(String nomFich) {
		File f = new File(nomFich);
		long size = -1;
		try {
			FileInputStream fis = new FileInputStream(f);
			
			size = fis.available();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return size;
			
	}
	
	static private ArrayList<String> copiaLineas(String nomFich) throws IOException{
		
		ArrayList<String> arr = new ArrayList<String>();
		File f = new File(nomFich);
		if(!f.exists()) return null;
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		
		String line = br.readLine();

		while(line != null) {
			
			arr.add(line);
			
			line = br.readLine();
		}
		
		br.close();
		
		return arr;
		
	}
	
	
	
}
