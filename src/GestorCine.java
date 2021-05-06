import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorCine {

	private Document doc;
	private String nomfich;
	
	public GestorCine(String nomfich) {
		
		SAXBuilder builder = new SAXBuilder();
		this.nomfich = nomfich;
		
		try {
			doc = builder.build(nomfich);
				
		} catch (JDOMException e) {
			System.out.println("XML mal formado");
		} catch (IOException e) {
			System.out.println("el fichero no existe");
		}
		
	}
	
	
	public Pelicula peliculaMasLarga() {
		Element peliculaMax = null;
		Pelicula pMasLarga = null;
		int duracionMax = Integer.MIN_VALUE;
		
		Element eCine = doc.getRootElement();
		List<Element> lstPeliculas = eCine.getChildren("pelicula");		
		
		for (Element ePelicula : lstPeliculas) {
			Element eDuracion = ePelicula.getChild("duracion");
			int horas = Integer.parseInt(eDuracion.getChildText("horas"));
			int minutos = Integer.parseInt(eDuracion.getChildText("minutos"));
			int segundos = Integer.parseInt(eDuracion.getChildText("segundos"));
			
			int duracionTotal = segundos + minutos * 60 + horas * 60 * 60;
			
			if(duracionTotal > duracionMax) {
				duracionMax = duracionTotal;
				peliculaMax = ePelicula;
			}
			
					
		}
		
		// Crear objeto pelicula
		
		String titulo = peliculaMax.getChildText("titulo");
		String genero = peliculaMax.getChildText("genero");
		int duracion = duracionMax;
		
		pMasLarga = new Pelicula(titulo, genero, duracion);
		
		
		
		
		return pMasLarga;
		
	}
	
	private ArrayList<Pelicula> pelisGenero(String genero){
		ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
		List<Element> lstPelicula = doc.getRootElement().getChildren("pelicula");
		
		for (int i = 0; i < lstPelicula.size(); i++) {
			String generoP = lstPelicula.get(i).getChildText("genero");
			if(genero.equals(generoP)) pelis.add(crearPeli(lstPelicula.get(i)));
			
		}
		
		return pelis;
		
		
	}
	
	
	private Pelicula crearPeli(Element element) {
		String titulo = element.getChildText("titulo");
		String genero = element.getChildText("genero");
		
		int duracionSegundos = calcularDuracionSegudos(element.getChild("duracion"));
		
		Pelicula pelicula = new Pelicula(titulo, genero, duracionSegundos);
		return pelicula;
	}


	private int calcularDuracionSegudos(Element child) {
		int totalSegundos = Integer.parseInt(child.getChildText("horas")) * 3600 +Integer.parseInt(child.getChildText("minutos")) * 60  + Integer.parseInt(child.getChildText("segundos")); 
		
		return totalSegundos;
	}
	
	public boolean aniadirPeli(Pelicula peli, int numSala) {
		boolean duplicado = false;
		List<Element> eCine = doc.getRootElement().getChildren("pelicula");
		
		for (Element element : eCine) {
			if(
					element.getChildText("titulo").equals(peli.getTitulo()) 
					&&
					Integer.parseInt(element.getAttributeValue("sala")) == numSala
			) duplicado = true;
		}
		
		if(!duplicado) {
			Element ePeliNueva = peliculaAElement(peli);
			ePeliNueva.setAttribute("sala", numSala + "");
			doc.getRootElement().addContent(ePeliNueva);

			grabar();
			return true;
		}
		
		return false;
	}


private void grabar() {
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			output.output(doc, new FileOutputStream(nomfich));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private Element peliculaAElement(Pelicula peli) {
		Element ePelicula = new Element("pelicula");
		Element eTitulo = new Element("titulo").setText(peli.getTitulo());
		Element eGenero = new Element("genero").setText(peli.getGenero());
		Element eDuracion = new Element("duracion");
		
		int h = peli.getDuracion()/ 3600;
		int m = ( peli.getDuracion() % 3600) / 60;
		int s = ( peli.getDuracion() % 3600) % 60;
		
		Element eHoras = new Element("horas").setText(h+"");
		Element eMinutos = new Element("minutos").setText(m+"");
		Element eSegundos = new Element("segundos").setText(s+"");
		
		ePelicula.addContent(eTitulo);
		ePelicula.addContent(eGenero);
		eDuracion.addContent(eHoras);
		eDuracion.addContent(eMinutos);
		eDuracion.addContent(eSegundos);
		ePelicula.addContent(eDuracion);
		
		return ePelicula;
		
		
	}

	public void crearFicheroConPelisDeUnGenero(String genero, String nomFich) {
		
		Document doc2 = new Document();
		Element eGenero2 = new Element("genero").setAttribute("tipo", genero);
		doc2.setRootElement(eGenero2);
		
		List<Element> lstPelicula = doc.getRootElement().getChildren("pelicula");
		for (int i = 0; i < lstPelicula.size(); i++) {
			String generoP = lstPelicula.get(i).getChildText("genero");
			
			if(genero.equals(generoP)) {
				eGenero2.addContent(lstPelicula.get(i).clone());
			}
				
		}
		
	
		
		grabarXML(doc2, nomFich);
		
	}


	private void grabarXML(Document doc2, String nomFich) {
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
		try {
			output.output(doc2, new FileOutputStream(nomFich));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
