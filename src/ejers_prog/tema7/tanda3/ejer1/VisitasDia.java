package ejers_prog.tema7.tanda3.ejer1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class VisitasDia {

	private int[] diaVisitas;
	private ArrayList<Visita> visitas;

	public VisitasDia(int[] diaVisitas) {
		super();
		this.diaVisitas = diaVisitas;
		this.visitas = new ArrayList<Visita>();
	}

	public boolean aniadeVisita(Visita v) throws IOException {

		if (!horarioValido(v.getHoraVisita()))
			return false;
		if (!maxPersonasHora(v))
			return false;

		if (visitas.contains(v))
			return false;

		visitas.add(v);
		return true;
	}

	public int cargarVisitas(String fichero) throws IOException, ClassNotFoundException {
		int total = 0;

		File f = new File("files/" + fichero);
		if (!f.exists())
			return -1;

		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);

		@SuppressWarnings("unchecked")
		ArrayList<Visita> arr = (ArrayList<Visita>) ois.readObject();

		ois.close();

		if (arr == null)
			return -1;

		for (Visita visita : arr) {
			if (aniadeVisita(visita))
				total++;
		}

		return total;
	}

	public void guardarFichero(String fichero) throws IOException {
		FileOutputStream fos = new FileOutputStream("files/" + fichero);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(this.visitas);
		oos.close();

	}

	public void verFichero(String fichero) throws IOException, ClassNotFoundException {
		File f = new File("files/" + fichero);
		if (!f.exists())
			System.out.println("El fichero no existe");
		else {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);

			@SuppressWarnings("unchecked")
			ArrayList<Visita> arr = (ArrayList<Visita>) ois.readObject();
			ois.close();
			for (Visita visita : arr) {
				System.out.println(visita.toString());
			}

		}
	}

	public boolean actualizaVisita(String nombre) throws IOException {

		Visita match = null;

		Iterator<Visita> it = visitas.iterator();

		while (it.hasNext()) {
			Visita v = it.next();
			if (v.getNombre().equals(nombre)) {
				match = v;
				it.remove();
				break;
			}

		}

		if (match == null)
			return false;

		System.out.println("Que quieres hacer: \n 1) Cambio de tiempo \n 2) Cambio cantidad personas");
		int res = Consola.leeInt();

		if (res == 1) {
			System.out.println("Introduce las horas: ");
			int horas = Consola.leeInt();
			System.out.println("Intruduce los minutos: ");
			int minutos = Consola.leeInt();
			if (!horarioValido(new HoraVisita(horas, minutos)))
				return false;
			match.setHoraVisita(new HoraVisita(horas, minutos));
			visitas.add(match);
			return true;
		} else if (res == 2) {
			System.out.println("Introduce la nueva cantidad de personas: ");
			int personas = Consola.leeInt();
			if (personasHora(match.getHoraVisita()) + personas < 50)
				return false;
			match.setCantPersonas(personas);
			visitas.add(match);
			return true;
		} else
			return false;

	}

	public void crearInforme() throws FileNotFoundException {
		String nomFich = "files/report_" + this.diaVisitas[1] + "_" + this.diaVisitas[0] + ".txt";
		PrintWriter pw = new PrintWriter(nomFich);

		for (Visita v : visitas) {
			String nombre = v.getNombre();
			String horas = v.getHoraVisita().getHora() + ":" + v.getHoraVisita().getMinutos();
			String personas = v.getCantPersonas() + "personas";
			pw.println(nombre + "\t" + horas + "\t" + personas);

		}

		pw.close();
	}

	public HashMap<HoraVisita, Integer> mapasLibre() throws IOException {

		HashMap<HoraVisita, Integer> map = new HashMap<HoraVisita, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader("files/horarios.txt"));
		String line = br.readLine();
		while(line != null){
			String[] split = line.split("\t");
			HoraVisita hv = new HoraVisita(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			map.put(hv, 50 - personasHora(hv));
			line = br.readLine();
		}
		
		br.close();

		

		return map;

	}

	public void verMapasLibre() throws IOException {
		HashMap<HoraVisita, Integer> maps = mapasLibre();
		for (HoraVisita key : maps.keySet()) {
			System.out.println(key.toString() + " -> " + (maps.get(key) == null ? "0" : maps.get(key)));
		}
	}

	public void verVisitas() {
		for (Visita v : visitas) {
			System.out.println(v.toString());
		}
	}

	private boolean horarioValido(HoraVisita hora) throws IOException {

		String fichero = "files/horarios.txt";
		FileReader in = new FileReader(fichero);
		BufferedReader bf = new BufferedReader(in);
		String line = bf.readLine();
		while (line != null) {

			String[] split = line.split("\t");

			if (Integer.parseInt(split[0]) == hora.getHora() && Integer.parseInt(split[1]) == hora.getMinutos()) {
				bf.close();
				return true;
			}

			line = bf.readLine();
		}

		bf.close();

		return false;
	}

	public HoraVisita tiempoVisitaMasCercano(int horas, int minutos) throws IOException {
		HoraVisita horaLibre = null;

		BufferedReader bf = new BufferedReader(new FileReader("files/horarios.txt"));
		String line = bf.readLine();
		int minMt = Integer.MAX_VALUE;
		String[] hora = new String[2];
		while (line != null) {
			String[] split = line.split("\t");
			HoraVisita hv = new HoraVisita(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			if (personasHora(hv) < 50) {
				int mt1 = 60 * horas + minutos;
				int mt2 = 60 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);

				System.out.println(
						horas + ":" + minutos + " / " + Integer.parseInt(split[0]) + ":" + Integer.parseInt(split[1]));
				System.out.println(mt1 + " - " + mt2 + " = " + (mt1 - mt2));
				int dif = mt1 - mt2;

				if (Math.abs(dif) <= Math.abs(minMt)) {
					if (Math.abs(dif) == Math.abs(minMt)) {
						if (dif > minMt) {
							minMt = dif;
							hora = split;
						}
					} else {
						minMt = dif;
						hora = split;
					}

				}
			}

			line = bf.readLine();
		}

		bf.close();

		horaLibre = new HoraVisita(Integer.parseInt(hora[0]), Integer.parseInt(hora[1]));
		return horaLibre;

	}

	public int borrarVisitasPasadas() throws IOException {
		int total = 0;
		Date d = new Date();
		@SuppressWarnings("deprecation")
		HoraVisita horaActual = new HoraVisita(d.getHours(), d.getMinutes());
		String nomFich = "files/visitasPasadas_" + horaActual.getHora() + "_" + horaActual.getMinutos() + "_" + total
				+ ".bin";
		;
		Iterator<Visita> it = visitas.iterator();
		FileOutputStream fos = new FileOutputStream(nomFich);
		DataOutputStream dos = new DataOutputStream(fos);
		while (it.hasNext()) {
			Visita next = it.next();
			HoraVisita hora = next.getHoraVisita();

			if (horaAnterior(horaActual, hora)) {
				total++;
				short cantPersonas = (short) next.getCantPersonas();
				dos.writeShort(cantPersonas);
				it.remove();
			}

		}

		dos.close();

		return total;
	}

	private boolean horaAnterior(HoraVisita h1, HoraVisita h2) {

		return (24 * 60) - ((h2.getHora()) * 60 + (24 - h2.getMinutos())) > (24 * 60)
				- ((h1.getHora()) * 60 + (24 - h1.getMinutos()));
	}

	private boolean maxPersonasHora(Visita v) {
		int total = v.getCantPersonas();
		for (Visita visita : visitas) {
			if (visita.getHoraVisita().equals(v.getHoraVisita()))
				total += visita.getCantPersonas();
		}

		return total <= 50;

	}

	static private void checkearFicheroBorrados(String nomFich) throws IOException {
		FileInputStream fis = new FileInputStream(nomFich);
		DataInputStream dis = new DataInputStream(fis);

		System.out.println(dis.readShort());

		dis.close();

	}

	private int personasHora(HoraVisita v) {
		int total = 0;
		for (Visita visita : visitas) {
			if (visita.getHoraVisita().equals(v))
				total += visita.getCantPersonas();
		}

		return total;

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		VisitasDia a = new VisitasDia(new int[] { 3, 4 });
		System.out.println(a.aniadeVisita(new Visita("Antonio", 13, 13, 30)));
		System.out.println(a.aniadeVisita(new Visita("Juan", 51, 10, 30)));
		System.out.println(a.aniadeVisita(new Visita("Juan", 25, 11, 00)));
		System.out.println(a.aniadeVisita(new Visita("Maria", 25, 11, 00)));
		System.out.println(a.aniadeVisita(new Visita("Francis", 12, 11, 00)));
		System.out.println(a.aniadeVisita(new Visita("Francis", 7, 12, 30)));
		System.out.println(a.aniadeVisita(new Visita("Francis", 7, 12, 30)));
		System.out.println(a.aniadeVisita(new Visita("Francis", 7, 7, 30)));
		System.out.println(a.aniadeVisita(new Visita("Francis", 26, 6, 30)));
		System.out.println(a.aniadeVisita(new Visita("Francis", 49, 10, 10)));

//		a.guardarFichero("visitasDia.obj");
//		a.verFichero("visitasDia.obj");
//		a.verVisitas();
//		a.cargarVisitas("visitasDia.obj");
//		a.verVisitas();
//
//		a.crearInforme();
//		
		a.verMapasLibre();
//		System.out.println(a.tiempoVisitaMasCercano(10, 40));

//		a.verVisitas();
//		
//		a.borrarVisitasPasadas();
//		System.out.println("_____________________");
//		a.verVisitas();
//		checkearFicheroBorrados("files/visitasPasadas_8_5_2.bin");
//		System.out.println(a.tiempoVisitaMasCercano(10, 30).toString());
		

	}

}
