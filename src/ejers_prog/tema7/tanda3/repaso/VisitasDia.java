package ejers_prog.tema7.tanda3.repaso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class VisitasDia {

	private int[] dia = new int[2];
	private ArrayList<Visita> visitas;
	
	public VisitasDia(int[] dia) {
		super();
		this.dia = dia;
		this.visitas = new ArrayList<Visita>();
	}
	
	public boolean aniadeVisita(Visita visita) {
		for (Visita v : visitas) {
			if(v.getHora()[0] == visita.getHora()[0] && v.getHora()[1] == visita.getHora()[1]) {
				return false;
			}
		}
		
		return visitas.add(visita);
		
	}
	
	public void guardarAFichero(String fichero) throws IOException {
		File f = new File("files/museo/" + fichero);
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(visitas);
		oos.close();

	}
	
	public int cargarVisitas(String fichero) throws IOException {
		File f = new File("files/museo/" + fichero);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<Visita> arr = (ArrayList<Visita>) ois.readObject();
		
		int[][] valid = cargarTiemposVisitaValidos("files/museo/horarios.txt");
		boolean valido = false;
		for (Visita visita : arr) {
			for (int i = 0; i < valid.length; i++) {
				if(visita.getHora()[0] == valid[i][0] && visita.getHora()[i] == valid[i][1]) {
					valido = true;
					break;
				}
			}
			if(valido) aniadeVisita(visita);
		}
		
		ois.close();
		
		return -1;
		
	}
	
	private int[][] cargarTiemposVisitaValidos(String fichero) throws IOException{
		File f = new File("files/museo/" + fichero);
		
		if(!f.exists()) return null;
		FileReader in = new FileReader(f);
		BufferedReader br = new BufferedReader(in);
		
		int[][] tiempos = new int[Integer.parseInt(br.lines().toString())][2];
		
		String line = br.readLine();
		int i = 0;
		while(line != null) {
			String[] split = line.split("\t");
			tiempos[i][0] = Integer.parseInt(split[0]);
			tiempos[i][1] = Integer.parseInt(split[1]);
			line = br.readLine();
			i++;
		}
		
		br.close();
		
		
		return tiempos;
		
	}
	
	
	
	public static void main(String[] args) {
		VisitasDia a = new VisitasDia(new int[]{10, 3});
		System.out.println(a.aniadeVisita(new Visita("Juan", 20, new int[]{18, 30})));
		System.out.println(a.aniadeVisita(new Visita("Juan", 20, new int[]{18, 30})));
		
	}
	
}
