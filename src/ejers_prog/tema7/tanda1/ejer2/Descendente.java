package ejers_prog.tema7.tanda1.ejer2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Descendente {

	private float[] arr;
	private int util;

	public Descendente(int arrSize) {
		super();
		this.arr = new float[arrSize];

		this.util = 0;
	}

	public void aniadirNum(float n) {
		if (util == arr.length) {
			System.out.println("No se pueden añadir más números");
		} else if (util == 0) {
			arr[0] = n;
			util++;
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > n && arr[i + 1] == 0) {
					arr[i + 1] = n;
					util++;
					break;
				} else if (arr[i] < n) {
					moverDerecha(i);
					arr[i] = n;
					util++;
					break;
				}
			}

		}

	}

	private void moverDerecha(int i) {
		for (int j = util; j > i; j--) {
			arr[j] = arr[j - 1];
		}
	}

	private void moverIzquierda(int i) {
		for (int j = i; j < util; j++) {
			arr[j] = arr[j + 1];
		}
	}

	public void borrarDuplicados() {

		Set<Float> set = new HashSet<Float>();
		for (int i = 0; i < util; i++) {
			if (set.add(arr[i]) == false) {
				arr[i] = 0;
				moverIzquierda(i);
				util--;
			}
		}
	}

	public void aniadeNumsArray(String nombre) throws IOException {
		File f = new File("files/" + nombre);
		escribirFichero(f);
	}

	private void escribirFichero(File f) throws IOException {
		FileOutputStream fos = new FileOutputStream(f);
		DataOutputStream dos = new DataOutputStream(fos);
		borrarDuplicados();
		for (int i = 0; i < util; i++) {
			dos.writeFloat(arr[i]);
		}
		fos.close();
		dos.close();
	}

	public void verFichero(String nombreFichero) throws IOException {
		File f = new File("files/" + nombreFichero);

		if (!f.exists())
			System.out.println("El fichero no existe");
		else {
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);

			while (fis.available() > 0) {
				System.out.print(dis.readFloat() + ", ");
			}

			fis.close();
			dis.close();

		}
	}

	public void aniadeNumsRandom(int limiteI, int limiteS, int cant, String nomFichero) throws IOException {
		File f = new File("files/" + nomFichero);

		if (f.exists()) {
			FileInputStream fis = new FileInputStream(f);
			float[] existentes = new float[fis.available()];
			int idx = 0;
			while (fis.available() > 0) {

				existentes[idx] = fis.read();

				idx++;
			}

			fis.close();

			int i = 0;
			FileOutputStream fos = new FileOutputStream(f, true);
			DataOutputStream dos = new DataOutputStream(fos);
			while (i < cant) {

				boolean repetido = false;
				float r = 0;
				do {
					r = (float) (Math.random() * (limiteI - limiteS + 1) + limiteS);
					for (int j = 0; j < existentes.length; j++) {
						if (existentes[i] == r)
							repetido = true;
					}

				} while (repetido);

				dos.writeFloat(r);

				i++;
			}

			fos.close();
			dos.close();

		}

	}

	public float buscarEnFichero(String nomFich, int pos) throws IOException {
		File f = new File("files/" + nomFich);

		if (!f.exists())
			System.out.println("El fichero no existe");
		else {
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);

			int i = 0;
			while (fis.available() > 0) {
				float readed = dis.readFloat();
				if (i == pos - 1) {
					dis.close();
					return readed;
				}
				i++;
			}
			
			dis.close();

		}
		return -1;

	}

	public void ver() {
		for (float f : arr) {
			if (f != 0)
				System.out.print(f + ", ");
		}
		System.out.println();
	}

	public void copiaFichero(File ficheroOrigen, File ficheroDestino) throws IOException {
		if (!ficheroOrigen.exists()) {
			System.out.println("El fichero origen" + ficheroOrigen.getName() + " no existe");
		} else if (!ficheroOrigen.exists()) {
			System.out.println("El fichero destino" + ficheroDestino.getName() + " no existe");
		} else {
			if (ficheroDestino.exists()) {
				System.out.println("El fichero destino ya existe, que quiers hacer:");
				System.out.println("1) Sobreescribir");
				System.out.println("2) Añadir al final del archivo");
				System.out.println("3) Nada");
				int res = Consola.leeInt();
				FileOutputStream fos = new FileOutputStream(ficheroDestino, res == 1 ? false : true);
				DataOutputStream dos = new DataOutputStream(fos);

				if (res == 1 || res == 2) {
					FileInputStream fis = new FileInputStream(ficheroOrigen);
					DataInputStream dis = new DataInputStream(fis);

					while (dis.available() > 0) {

						int leido = Math.round(dis.readFloat());
						System.out.println(leido);

						if (leido % 2 != 0) {
							dos.writeInt(leido);
						}

					}

					fis.close();
					dis.close();
					fos.close();
					dos.close();

				}
			}
		}

	}

	public static void crearFicheroEnteros(String nombre) throws IOException {
		File f = new File("files/" + nombre);

		if (f.exists())
			System.out.println("El fichero ya existe!");
		else {
			FileOutputStream fos = new FileOutputStream(f);
			DataOutputStream dos = new DataOutputStream(fos);
			for (int i = 0; i < 10; i++) {
				dos.writeInt(i);
			}
			fos.close();
			fos.close();
		}

	}

	public static void main(String[] args) throws IOException {
		Descendente a = new Descendente(10);

		a.aniadirNum(9);
		a.aniadirNum(10);
		a.aniadirNum(9);
		a.aniadirNum(7);
		a.aniadirNum(7);
		a.aniadirNum(8);
		a.aniadirNum(6);

		a.verFichero("ficheroArray");

//		a.aniadeNumsRandom(1, 20, 3, "ficheroarray");
//		a.aniadeNumsArray("ficheroarray");
//		a.aniadeNumsRandom(1, 100, 3, "ficheroarray");
//		a.verFichero("ficheroarray");
//		System.out.println();
//		System.out.println(a.buscarEnFichero("ficheroarray", 2));
		File f1 = new File("files/ficheroarray");
		File f2 = new File("files/copiafichero");
		Descendente.crearFicheroEnteros("copiafichero");

//		a.copiaFichero(f1, f2);

		a.copiaFichero(f1, f2);

		FileInputStream fis = new FileInputStream("files/copiafichero");
		DataInputStream dis = new DataInputStream(fis);

		while (fis.available() > 0) {
			System.out.print(dis.readInt() + ", ");
		}

		fis.close();
		dis.close();

	}

}
