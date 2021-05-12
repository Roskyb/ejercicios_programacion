package cine2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



public class GestorCine {
	
	private Document doc;
	private String nomFich;
	
	
	public GestorCine(String nomFich) {
		this.nomFich=nomFich;		
		SAXBuilder builder=new SAXBuilder();
		try {
			doc=builder.build(nomFich);
		} catch (JDOMException e) {
			System.out.println("XML mal formado");
		} catch (IOException e) {
			System.out.println("fichero no existe");			
		}	
		
	}
	
	
	public Pelicula peliculaMasLarga() {
		
		Element peliMax=null;
		int duracionMax=0;
		
		
		Element eCine=doc.getRootElement();
		List<Element>lstPeliculas=eCine.getChildren("pelicula");
		
		for (Element ePelicula: lstPeliculas) {			
			Element eDuracion=ePelicula.getChild("duracion");
			int horas=Integer.parseInt(eDuracion.getChildText("horas"));
			int minutos=Integer.parseInt(eDuracion.getChildText("minutos"));
			int segundos=Integer.parseInt(eDuracion.getChildText("segundos"));
			
			int total=segundos + minutos*60 + horas*60*60;
			if (total>duracionMax)
			{
				duracionMax=total;
				peliMax=ePelicula;  //Guardar referencia a este Element				
			}
		}
		
		//Crear objeto Pelicula
		if (peliMax!=null) {
			return elementAPelicula(peliMax);
		}
		return null;
		
	}
	
	
	private static Pelicula elementAPelicula (Element ePelicula) {
		
		Pelicula p=new Pelicula();
		p.setTitulo(ePelicula.getChildText("titulo"));
		p.setGenero(ePelicula.getChildText("genero"));
		
		Element eDuracion=ePelicula.getChild("duracion");
		int horas=Integer.parseInt(eDuracion.getChildText("horas"));
		int minutos=Integer.parseInt(eDuracion.getChildText("minutos"));
		int segundos=Integer.parseInt(eDuracion.getChildText("segundos"));
		int total=segundos + minutos*60 + horas*60*60;
		p.setDuracion(total);				
		
		
		if(ePelicula.getChild("entradas") != null) {
			List<Element> lstEntradas = ePelicula.getChild("entradas").getChildren();			
			int[] asientos = new int[lstEntradas.size()];
			
			for (int i = 0; i < asientos.length; i++) {
				asientos[i] = Integer.parseInt(lstEntradas.get(i).getText());
			}
			
			p.setAsientos(asientos);
		
		}else {
			p.setAsientos(null);
		}
		
			
		return p;		
	}
	
	
	
	public ArrayList<Pelicula> todasLasPelis(){
		
		ArrayList<Pelicula> pelis=new ArrayList<Pelicula>();		
		Element eCine=doc.getRootElement();
		
		List<Element> lstPeliculas=eCine.getChildren("pelicula");
		for (int i=0; i<lstPeliculas.size(); i++) {
			Element ePelicula=lstPeliculas.get(i);
			pelis.add(elementAPelicula(ePelicula));			
		}				
		return pelis;
	}
	
	
	
	private Element peliculaAElement(Pelicula p) {
		
		Element ePelicula=new Element("pelicula");		
		Element eTitulo=new Element("titulo");
		eTitulo.setText(p.getTitulo());		
		Element eGenero=new Element("genero");
		eGenero.setText(p.getGenero());
		
		int h=p.getDuracion()/60/60;
		int m=(p.getDuracion() - (h*60*60))/60;
		int s=p.getDuracion() - (h*60*60) - (m*60);
		
		Element eDuracion=new Element("duracion");		
		Element eHoras=new Element("horas");
		eHoras.setText(h+"");
		Element eMinutos=new Element("minutos");
		eMinutos.setText(m+"");		
		Element eSegundos=new Element("segundos");
		eSegundos.setText(s+"");
		
		eDuracion.addContent(eHoras);
		eDuracion.addContent(eMinutos);
		eDuracion.addContent(eSegundos);
		
		ePelicula.addContent(eTitulo);
		ePelicula.addContent(eGenero);
		ePelicula.addContent(eDuracion);
		
		return ePelicula;
		
	
	}
	
	
	public String[] distintosGenero() {
		
		HashSet<String> setGeneros=new HashSet<String>();		
		Element eCine=doc.getRootElement();
		
		List<Element> lstPeliculas=eCine.getChildren("pelicula");
		for (int i=0; i<lstPeliculas.size(); i++) {
			Element ePelicula=lstPeliculas.get(i);			
			setGeneros.add(ePelicula.getChildText("genero"));
		}
		
		
		String[] generos=new String[setGeneros.size()];
		int i=0;
		for (String genero: setGeneros) {
			generos[i]=genero;
			i++;
		}
		
		return generos;
	}
	
	
	
	public boolean aniadirPeli (Pelicula pNueva, int nSala) {
		
		//Añadir pNueva al final de <cine>, si no existe otra peli de igual titulo en la
		//misma sala
		
		
		//Verificar si duplicada
		
		boolean duplicada=false;
		Element eCine=doc.getRootElement();
		
		List<Element> lstPeliculas=eCine.getChildren("pelicula");
		for (int i=0; i<lstPeliculas.size() ; i++) {
			Element ePelicula=lstPeliculas.get(i);
			if
			   ((ePelicula.getChildText("titulo").equals(pNueva.getTitulo())  )
				&&
				(Integer.parseInt(ePelicula.getAttributeValue("sala"))==nSala))
			{
				duplicada=true;
				break;
			}
				
			
		}
		if (!duplicada) {
			Element ePeliNueva=peliculaAElement(pNueva);
			ePeliNueva.setAttribute("sala", nSala + "");
			eCine.addContent(ePeliNueva);
			grabar();
			return true;
			
		}
		
		
		
		
		return false;
	}
	
	
	private void grabarOtro(Document doc, String nomFich) {
		
		XMLOutputter outputter=new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc,new FileWriter(nomFich));			
		} catch (IOException e) {
			System.out.println("No se puede grabar fichero nuevo");
		}
		
		
	}
	
	
	private void grabar() {
		
		XMLOutputter outputter=new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc,new FileWriter(nomFich));
			//outputter.output(doc,System.out);
		} catch (IOException e) {
			System.out.println("No se puede grabar fichero");
		}
	}
	
	
	public void crearFichPelisGenero(String genero, String nomFichNuevo) {
		/*
		<genero nombre="Drama">
			<pelicula>
				<titulo>adsf</titulo>
				<duracion>
				
				</duracion>
			</pelicula>
			
			<pelicula>
				<titulo>adsf</titulo>
				<duracion>
				
				</duracion>
			</pelicula>
		
		    .______
		</genero>
		*/
		
		//Documento nuevo con su etiqueta raiz
		Element eGenero2=new Element("genero");
		eGenero2.setAttribute("nombre",genero);
		Document doc2=new Document(eGenero2);
		
		
		Element eCine=doc.getRootElement();
		List<Element> lstPeliculas=eCine.getChildren("pelicula");
		for (int i=0; i<lstPeliculas.size() ; i++) {
			Element ePelicula=lstPeliculas.get(i);		
			
			
			if (ePelicula.getChildText("genero").equals(genero))
			{
				//No podemos añadir ePelicula a otro arbol, pues ya tiene padre
				Element ePelicula2=ePelicula.clone();
				Element eGenero22=ePelicula2.getChild("genero");
				ePelicula2.removeContent(eGenero22);
				ePelicula2.removeAttribute("sala");	
				
				eGenero2.addContent(ePelicula2);			
			}
		}
		
		grabarOtro(doc2, nomFichNuevo);
		
	}
	
	

	public static void main(String[] args) {
		
		GestorCine gc=new GestorCine("cine.xml");
		/*
		Pelicula pMasLarga=gc.peliculaMasLarga();
		if (pMasLarga!=null)
			System.out.println(pMasLarga.toString());
		
		ArrayList<Pelicula> lista=gc.pelisGenero("Comedia");
		
		System.out.println(lista);
		*/
		
		/*if (!gc.aniadirPeli(new Pelicula("Los simpaticos","Genero nuevo",7300), 55))
			System.out.println("Ya existe. No se ha añadido");
		*/
		
//		gc.crearFichPelisGenero("Comedia","l");
	}

}
