package ejers_prog.tema10.tanda2.ejer3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class GestorXML {
	
	private Document doc;
	private File file;
	private List<Element> platos;
	
	public GestorXML(File file) {
		this.file = file;
		if(this.file.exists()) {
			parsearXML();
		}
	}

	private void parsearXML() {
		SAXBuilder builder = new SAXBuilder();
		
		try {
			this.doc = builder.build(this.file);
			platos = this.doc.getRootElement().getChildren();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Plato[] damePlatos(String tipo) {
		ArrayList<Plato> arrL = new ArrayList<Plato>();
		for (Element plato : platos) {
			String atTipo = plato.getAttributeValue("tipo");
			if(tipo.equals(atTipo)) {
				arrL.add(elementoAPlato(plato));
			}
		}
		
		
		Plato[] arr = new Plato[arrL.size()];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = arrL.get(i);
		}
		
		return arr;
	}
	
	

	private Plato elementoAPlato(Element plato) {
		String nombre = plato.getAttributeValue("nombre");
		double precio = Double.parseDouble(plato.getChildText("precio"));
		String rutaImagen = plato.getChildText("imagen");
		String tipo = plato.getAttributeValue("tipo");
		
		if(rutaImagen != null) return new Plato(nombre, precio, rutaImagen ,tipo);
		else return new Plato(nombre, precio, tipo);
	}

	public HashMap<String, Double> getPrecios() {
		HashMap<String, Double> precios = new HashMap<String, Double>();
		
		for (Element plato : platos) {
			precios.put(plato.getAttributeValue("nombre"), Double.parseDouble(plato.getChildText("precio")));
		}
		
		return precios;
	}
	
	
	
	
	
	
	

}
