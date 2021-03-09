package ejers_prog.tema7.tanda2.ejer3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionAgenda {

	private String nomFich;

	public GestionAgenda(String nomFich) {
		super();
		this.nomFich = nomFich;
	}
	
	public void ver() throws IOException {
		File f = new File("files/" + nomFich);
		
		if(!f.exists()) System.out.println("La agenda no existe!");
		else {
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			
			String linea = bf.readLine();
			
			while(linea != null) {
				System.out.println(linea);
				linea = bf.readLine();
			}
			
			bf.close();
			
		}
		
		
	}
	
	public void aniadirPersona(Persona p) throws IOException {
		File f = new File("files/" + nomFich);
		if(!f.exists()) System.out.println("La agenda no existe!");
		else {
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			String entrada = p.toString();
			
			bw.newLine();
			bw.write(entrada);
			bw.close();
		}
	}
	
	public Persona buscaPersona(String nom) throws IOException {
		File f = new File("files/" + nomFich);
		
		if(!f.exists()) System.out.println("La agenda no existe!");
		else {
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			
			String linea = bf.readLine();
			
			while(linea != null) {
				
				if(nombreEnLinea(linea, nom)) return transforma(linea);
				linea = bf.readLine();
			}
			
			bf.close();
		}
		return null;
	}
	
	public boolean nombreEnLinea(String linea, String nombre) {
		String[] splitted = linea.split("\t");
		return splitted[2].equals(nombre);
	}
	
	public Persona transforma(String linea) {
		
		String[] splitted = linea.split("\t");
		return new Persona(splitted[2], Integer.parseInt(splitted[1]) , splitted[0], splitted[3]);
	}
	
	
	public static void main(String[] args) throws IOException {
		GestionAgenda a = new GestionAgenda("personas.txt");
		a.ver();
		a.aniadirPersona(new Persona("Jorge", 20, "66211212", "Keoland"));
		a.ver();
		System.out.println(a.buscaPersona("Jorge"));
		
		
		
	}
	
}
