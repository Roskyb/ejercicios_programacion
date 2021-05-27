package ejers_prog.tema10.tanda1.ejer4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestionMenu {

	private String nomFich;
	private Document doc;
	
	public GestionMenu(String nomFich) {
		super();
		this.nomFich = nomFich;
		parsear();
		
	}




	private void parsear() {
		SAXBuilder builder = new SAXBuilder();
		try {
			this.doc = builder.build(new File("files/menus/" +  this.nomFich));
		} catch (JDOMException | IOException e) {
			System.out.println("XML mal formado");
		}
	}
	
	public void ver() {
		String root = doc.getRootElement().toString();
		root = root.substring(root.indexOf("<"), root.indexOf(">") + 1);
		String iRoot = root.replace("/", "");
		System.out.println(iRoot);
		
		for (Element element : doc.getRootElement().getChildren()) {
			String eStr = element.toString();
			eStr = eStr.substring(eStr.indexOf("<"), eStr.indexOf(">") + 1);
			System.out.println("\t" + eStr);
		}
		
		
		System.out.println(root);
		
//		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
//		try {
//			out.output(doc, System.out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	
	
	private Plato consultaPlato(String nomPlato) {
		Plato plato = null;
		
		List<Element> platos = doc.getRootElement().getChildren();
		
		for (Element p : platos) {
			if(p.getChildText("nombre").equals(nomPlato)) {
				plato = transformarPlato(p);
			}
		}
		
		return plato;
	}




	private Plato transformarPlato(Element p) {
		Plato plato;
		String nombre = p.getChildText("nombre");
		double precio = Double.parseDouble(p.getChildText("precio"));
		String descripcion = p.getChildText("descripcion");
		double calorias = Double.parseDouble(p.getChildText("calorias"));
		plato = new Plato(nombre, precio, descripcion, calorias);
		return plato;
	}




	private ArrayList<Plato> platosPorCalorias(double i, double j) {
		ArrayList<Plato> arr = new ArrayList<Plato>();
		List<Element> platos = doc.getRootElement().getChildren();
		for (Element p : platos) {
			Double calorias = Double.parseDouble(p.getChildText("calorias"));

			if(calorias >= i && calorias <= j) {
				arr.add(transformarPlato(p));
			}
		}
		
		return arr;
	}




	private boolean nuevaComida(Plato plato) {
		if(plato == null) return false;
		if(consultaPlato(plato.getNombre()) != null) return false;
		
		Element eNuevoPlato = transformarEnElemento(plato);
		this.doc.getRootElement().addContent(eNuevoPlato);
		
		
		return true;
	}




	private Element transformarEnElemento(Plato plato) {
		
		Element ePlato = new Element("plato");
		ePlato.addContent(new Element("nombre").setText(plato.getNombre()));
		ePlato.addContent(new Element("precio").setText(plato.getPrecio()+""));
		ePlato.addContent(new Element("descripcion").setText(plato.getDescripcion()));
		ePlato.addContent(new Element("calorias").setText(plato.getCalorias() + ""));
		
		
		return ePlato;
	}




	private void eliminarPlatoMasCaro() {
		List<Element> platos = doc.getRootElement().getChildren();
		double precio_max = Double.MIN_VALUE;
		int idx = -1;
		int i = 0;
		for (Element p : platos) {
			Double precio = Double.parseDouble(p.getChildText("precio"));
			if(precio > precio_max) {
				precio_max = precio;
				idx = i; 
			}
			i++;
		}

		platos.remove(idx);
	}




	private HashMap<Double, HashSet<Plato>> mapaPlatosPrecios() {
		HashMap<Double, HashSet<Plato>> mapa = new HashMap<Double, HashSet<Plato>>();
		
		List<Element> platos = doc.getRootElement().getChildren();
		for (Element p : platos) {
			Plato plato = transformarPlato(p);
			if(mapa.get(plato.getPrecio()) ==  null) {
				HashSet<Plato> set = new HashSet<Plato>();
				set.add(plato);
				mapa.put(plato.getPrecio(), set);
			}else {
				HashSet<Plato> set = mapa.get(plato.getPrecio());
				set.add(plato);	
			}
		}
	
		return mapa;
	}




	public static void main(String[] args) {
		GestionMenu a = new GestionMenu("menus.xml");
		a.nuevaComida(new Plato("Bocata de mandarinas", 56.5, "Exquisito bocata de mandarinas", (double) 100));
		
		for (Plato p : a.platosPorCalorias(100, 500)) {
			System.out.println(p.toString());
		}

		
		HashMap<Double, HashSet<Plato>> mapa = a.mapaPlatosPrecios();
		for (Double k : mapa.keySet()) {
			System.out.println(k);
			for (Plato plato : mapa.get(k)) {
				System.out.println(plato.toString());
			}
		}
		a.ver();
	}
	

	

	
	
	
}

