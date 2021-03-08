package ejers_prog.tema7.tanda1.ejer3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionComercial {

	private String nomFich;

	public GestionComercial(String nomFich) {
		super();
		this.nomFich = nomFich;
	}

	public void guardarComerciales(ArrayList<Comercial> arr) throws IOException {
		File f = new File("files/" + nomFich);
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		for (Comercial comercial : arr) {
			oos.writeObject(comercial);
		}

		oos.writeObject(null);
		oos.close();

	}

	public void verComerciales() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);

		boolean cont = true;
		while (cont) {
			Comercial obj = (Comercial) ois.readObject();
			if (obj != null)
				obj.ver();
			else
				cont = false;
		}

		ois.close();
	}

	public Comercial buscaComercial(String nomComer) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		boolean available = true;
		while(available) {
			Comercial obj = (Comercial) ois.readObject();
			if(obj != null) {
				 if( obj.getNombre().equals(nomComer)) return obj;
			}
			else available = false;
		}
		
		ois.close();
		return null;
		
		
	}
	
	public void generaFichMoviles(String nomFich) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("files/" + nomFich);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		FileInputStream fis = new FileInputStream("files/" + this.nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		boolean available = true;
		while(available) {
			Comercial obj = (Comercial) ois.readObject();
			if(obj != null) {
				if(obj.getTelf() != null) {
					obj.getTelf().cargar(10);
					oos.writeObject(obj.getTelf());
				}
			} else available = false;
		}
		
		oos.writeObject(null);
		ois.close();
		oos.close();
		
	}
	
	public void trabajarTodos() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<Comercial> arr = new ArrayList<Comercial>();
		
		boolean available = true;
		while(available) {
			Comercial obj = (Comercial) ois.readObject();
			if(obj != null) {
				obj.trabajar();
				arr.add(obj);
			} else available = false;
		}
		
		FileOutputStream fos = new FileOutputStream("files/" + nomFich);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for (Comercial comercial : arr) {
			oos.writeObject(comercial);
		}
		oos.writeObject(null);
		oos.close();
		
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		GestionComercial a = new GestionComercial("comerciales");
		ArrayList<Comercial> arr = new ArrayList<Comercial>();

		for (int i = 0; i < 4; i++) {
			arr.add(new Comercial("R2D" + i, 1000 + i, new TelefonoMovil("11111" + i, 100 + i)));
		}

//		a.guardarComerciales(arr);
//		a.verComerciales();
//		a.generaFichMoviles("moviles");
		a.verComerciales();
		a.trabajarTodos();
		a.verComerciales();
	}

}
