package ejers_prog.tema7.tanda1.ejer3;

import java.io.File;
import java.io.FileInputStream;
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
		Comercial obj;
		
		while (( obj = (Comercial) ois.readObject()) != null) {
			obj.ver();
		}

		ois.close();
	}

	public Comercial buscaComercial(String nomComer) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Comercial obj;
		while(( obj = (Comercial) ois.readObject()) != null) {
			if( obj.getNombre().equals(nomComer)) {
				ois.close();
				return obj;
			}
		}
		
		ois.close();
		return null;
		
		
	}
	
	public void generaFichMoviles(String nomFich) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("files/" + nomFich);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		FileInputStream fis = new FileInputStream("files/" + this.nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Comercial obj;
		while(( obj = (Comercial) ois.readObject()) != null) {
			if(obj.getTelf() != null) obj.getTelf().cargar(10);
		}
		
		oos.writeObject(null);
		ois.close();
		oos.close();
		
	}
	
	public void trabajarTodos() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("files/" + nomFich);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<Comercial> arr = new ArrayList<Comercial>();
		
		Comercial obj;
		while(( obj = (Comercial) ois.readObject()) != null) {
				obj.trabajar();
				arr.add(obj);
		}
		
		FileOutputStream fos = new FileOutputStream("files/" + nomFich);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for (Comercial comercial : arr) {
			oos.writeObject(comercial);
		}
		oos.writeObject(null);
		oos.close();
		ois.close();
		
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
		
		TelefonoMovil obj;
		while ((obj = (TelefonoMovil) ois.readObject()) != null) {
			obj.ver();
		}

		ois.close();
		
		a.trabajarTodos();
		a.verComerciales();
	}

}
