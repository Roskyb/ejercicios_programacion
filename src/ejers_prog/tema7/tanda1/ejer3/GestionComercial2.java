package ejers_prog.tema7.tanda1.ejer3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionComercial2 {
	private String nomFich;

	public GestionComercial2(String nomFich) {
		super();
		this.nomFich = nomFich;
	}

	public void guardarComerciales(ArrayList<Comercial> arr) throws IOException {
		File f = new File("files/" + nomFich);
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(arr);
		oos.close();

	}

	public void verComerciales() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);

		ArrayList<Comercial> arr = (ArrayList<Comercial>) ois.readObject();

		ois.close();
		for (Comercial comercial : arr) {
			comercial.ver();
		}

	}

	public Comercial buscaComercial(String nomComer) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);

		ArrayList<Comercial> arr = (ArrayList<Comercial>) ois.readObject();

		ois.close();

		for (Comercial comercial : arr) {
			if (comercial.getNombre() == nomComer)
				return comercial;
		}

		return null;

	}

	public void generaFichMoviles(String nomFich) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("files/" + nomFich);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		FileInputStream fis = new FileInputStream("files/" + this.nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);

		@SuppressWarnings("unchecked")
		ArrayList<Comercial> arr = (ArrayList<Comercial>) ois.readObject();

		ois.close();

		for (Comercial comercial : arr) {
			if (comercial != null) {
				if (comercial.getTelf() != null) {
					comercial.getTelf().cargar(10);
					oos.writeObject(comercial.getTelf());
				}
			}
		}

		oos.close();

	}

	public void trabajarTodos() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);

		@SuppressWarnings("unchecked")
		ArrayList<Comercial> arr =  (ArrayList<Comercial>) ois.readObject();
	
		for (Comercial comercial : arr) {
			comercial.trabajar();
		}
		
		ois.close();


		FileOutputStream fos = new FileOutputStream("files/" + nomFich);
		ObjectOutputStream oos = new ObjectOutputStream(fos);


		oos.writeObject(arr);
		oos.close();

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		GestionComercial a = new GestionComercial("comerciales.obj");
		ArrayList<Comercial> arr = new ArrayList<Comercial>();

		for (int i = 0; i < 5; i++) {
			arr.add(new Comercial("R2D" + i, 1000 + i, new TelefonoMovil("11111" + i, 100 + i)));
		}

		a.guardarComerciales(arr);
		a.verComerciales();
		a.buscaComercial("R2D2").ver();
		System.out.println(a.buscaComercial("ddddd"));
		a.generaFichMoviles("moviles.obj");

		FileInputStream fis = new FileInputStream("files/" + "moviles.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);

		boolean cont = true;
		while (cont) {
			TelefonoMovil obj = (TelefonoMovil) ois.readObject();

			if (obj != null)
				obj.ver();
			else
				cont = false;
		}

		ois.close();

		a.trabajarTodos();
		a.verComerciales();
	}
}
