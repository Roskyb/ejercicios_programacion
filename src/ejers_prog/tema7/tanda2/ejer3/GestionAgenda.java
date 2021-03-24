package ejers_prog.tema7.tanda2.ejer3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
			System.out.println("--------------------------------------");
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			
			String linea = bf.readLine();
			
			while(linea != null) {
				System.out.println(linea);
				linea = bf.readLine();
			}
			
			bf.close();
			System.out.println("--------------------------------------");
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
				
				if(nombreEnLinea(linea, nom)) {
					bf.close();
					return transforma(linea);
				}
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
		if(splitted.length == 4) return new Persona(splitted[2], Integer.parseInt(splitted[1]) , splitted[0], splitted[3]);
		return null;
	}
	
	public boolean eliminaPersona(Persona p) throws IOException {
		
		File f = new File("files/" + nomFich);
		boolean borrado = false;
		if(!f.exists()) System.out.println("La agenda no existe!");
		else {
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);

			ArrayList<Persona> arr = new ArrayList<Persona>();
			String linea = bf.readLine();
			while(linea != null) {
				Persona per = transforma(linea);
				if(per != null) {
					if(!per.equals(p)) arr.add(per);
				}
				
				linea = bf.readLine();
			}
			bf.close();
			
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < arr.size(); i++) {
				bw.write(arr.get(i).toString());
				if(i < arr.size() - 1) {
					bw.newLine();
				}
				
			}
			
			bw.close();
		}

		
		
		return borrado;
	}
	
	
	public void edadMasFrecuente() throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("files/" + nomFich));
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		
		String line = bf.readLine();
		Persona per;
		while(line != null) {
			per = transforma(line);
			if(map.containsKey(per.getEdad())) {
				int cont = map.get(per.getEdad());
				map.put(per.getEdad(), cont+1);
			}else {
				map.put(per.getEdad(), 1);
			}
			
			line = bf.readLine();
		}
		
		bf.close();
		
		int max = Integer.MIN_VALUE;
		int num = -1;
		for (Integer i : map.keySet()) {
			if(map.get(i) > max) {
				max = map.get(i);
				num = i;
			}
		}
		
		System.out.println("La edad más frecuente es: " + num);
			
	}
	
	
	public static void main(String[] args) throws IOException {
		GestionAgenda a = new GestionAgenda("personas.txt");
		a.ver();
		a.aniadirPersona(new Persona("Jorge", 20, "66211212", "Keoland"));
		a.aniadirPersona(new Persona("Iker", 20, "66211212", "Keoland"));
		System.out.println(a.buscaPersona("Juan"));
		a.ver();
//		a.eliminaPersona(new Persona("Jorge", 20, "66211212", "Keoland"));
//		a.eliminaPersona(new Persona("Iker", 20, "66211212", "Keoland"));
		a.ver();
		a.aniadirPersona(new Persona("Jorge", 20, "66211212", "Keoland"));
		a.ver();
		a.eliminaPersona(new Persona("Juan", 25, "666555777", "Vitoria-Gasteiz"));
		a.ver();
		a.edadMasFrecuente();
		
		
	}
	
}
