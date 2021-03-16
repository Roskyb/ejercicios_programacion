import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestorPuntos {
	
	
	private static void pasarFichArray(String nomFich, Punto[] puntos) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("files/" + nomFich));
		
		String linea = br.readLine();
		int i  = 0;
		
		try {
			while(linea != null) {
				
				
				try {
					String[] coords = linea.split(",");
					float x = Float.parseFloat(coords[0]);
					float y = Float.parseFloat(coords[1]);
					puntos[i] = new Punto(x, y); 
					i++;
				} catch (NumberFormatException e) {
					System.out.println("Punto ilegible");
				}

				linea = br.readLine();
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("El tamaño del array es menor al número de puntos");
		}
		br.close();
	}
	
	private static void verArrayDePuntos(Punto[] puntos) {
		int i = 1;
		for (Punto punto : puntos) {
			try {
				System.out.println("Punto nº" + i + ": " + punto.toString());
				i++;
			}catch(NullPointerException e) {
				System.out.println("Punto sin definir");
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		Punto[] puntos = new Punto[10];
		
		try {
			pasarFichArray("puntos.txt", puntos);
			verArrayDePuntos(puntos);
		}catch(FileNotFoundException e) {
			System.out.println("El fichero de puntos no existe");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de lectura de fichero");
		}
	}
}
