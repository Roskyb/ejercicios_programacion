package ejers_prog.tema7.tanda2.ejer2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicadorValidado extends DuplicadorDeArchivo{
	
	
	
	@Override
	public boolean duplicar(String rutaDestino) throws IOException {
		
		char[] prohibidos = {'o', 'l'};
		
		
		File fo = new File(this.getNomRuta());
		File fd = new File(rutaDestino);
		
		if(!fo.equals(fd) && fo.exists()) {
			FileReader fr  = new FileReader(fo);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(fd);
			char[] buffer = new char[1];
			int n = br.read(buffer);
			boolean accept = true;
			while(n > 0) {
				accept = true;
				for (int i = 0; i < prohibidos.length; i++) {
					if(prohibidos[i] == buffer[0]) accept = false;
				}
				if(accept) fw.write(buffer, 0, n);
				n = br.read(buffer);
			}
			
			br.close();
			fw.close();	
			System.out.println("Copia realizada con exito!");
			return true;
		}else {
			System.out.println("Error al duplicar");
			return false;
		}
		
	}
	
}
