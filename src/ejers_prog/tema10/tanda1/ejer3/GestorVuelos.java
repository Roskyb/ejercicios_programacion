package ejers_prog.tema10.tanda1.ejer3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorVuelos {
	
	private Document doc;

	public GestorVuelos(String nomFich) {
		File f = new File("files/gestorVuelos/" + nomFich);
		cargarDocumento(f);
		
	
	}

	public ArrayList<String> destinosDirectosDesde(String origen){
		ArrayList<String> arr = new ArrayList<String>();
		  List<Element> vuelos = doc.getRootElement().getChildren();

		  for (Element vuelo : vuelos) {
			if(vuelo.getChild("escala") == null && vuelo.getChildText("origen").equals(origen)) {
				arr.add(vuelo.getChildText("destino"));
			}
			
		  }
		
		
		return arr;
	}
	
	public void vuelosPosterioresA(String hora) {
		List<Element> vuelos = doc.getRootElement().getChildren();	
		for (Element vuelo : vuelos) {
			if(horaPosterior(hora, vuelo.getChildText("hora"))) verVuelo(vuelo);
		}
		
		
	}
	
	private boolean horaPosterior(String hora, String hora2) {
		
		int tm1 = calcularMarcaDeTiempor(hora);
		int tm2 = calcularMarcaDeTiempor(hora2);
		
		return tm2 > tm1;
	}

	private int calcularMarcaDeTiempor(String hora) {
		
		String[] split = hora.split(":");
		
		
		return (Integer.parseInt(split[0]) * 60) + Integer.parseInt(split[1]);
	}

	private void verVuelo(Element vuelo) {
		System.out.println("-----------------------------");
		System.out.println("Origen: " + vuelo.getChildText("origen"));
		System.out.println("Destino:"  + vuelo.getChildText("destino"));
		System.out.println("Hora: " +  vuelo.getChildText("hora"));
		System.out.println("Escala: " + (vuelo.getChild("escala") == null ? "Vuelo sin escala" : "Vuelo con escala" ));
		System.out.println("-----------------------------");
	}

	private void cargarDocumento(File f) {
		SAXBuilder builder = new SAXBuilder();
		
		try {
			doc = builder.build(f);
				
		} catch (JDOMException e) {
			System.out.println("XML mal formado");
		} catch (IOException e) {
			System.out.println("el fichero no existe");
		}
	}
	
	private boolean nuevoVuelo(String id, String origen,String destino, int[] hora, boolean escala) {
		
		if(checkVueloDuplicado(id)) return false;
		Element eNuevoVuelo = new Element("vuelo");
		eNuevoVuelo.setAttribute("id", id);
		eNuevoVuelo.addContent(new Element("origen").setText(origen));
		eNuevoVuelo.addContent(new Element("destino").setText(destino));
		eNuevoVuelo.addContent(new Element("hora").setText(hora[0] + ":" + hora[1]));
		if(escala) eNuevoVuelo.addContent(new Element("Escala"));
		
		doc.getRootElement().addContent(eNuevoVuelo);
				
		return true;
	}
	
	public boolean borrarVuelo(String id) {
		
		List<Element> vuelos = doc.getRootElement().getChildren();
		
		for (Element vuelo : vuelos) {
			if(vuelo.getAttributeValue("id").equals(id)) {
				vuelos.remove(vuelo);
				return true;
			}
		}
		
		
		return false;
		
	}
	
	private boolean checkVueloDuplicado(String id) {
		List<Element> vuelos = doc.getRootElement().getChildren();
		for (Element vuelo : vuelos) {
			if(vuelo.getAttributeValue("id").equals(id)) return true;
		}
		return false;
	}

	private void vuelosConDestino(String destino) {
		File f = new File("files/gestorVuelos/vuelos_" + destino + ".xml" );
		Document vuelosDoc = new Document();
		Element eVuelos = new Element("vuelos").setAttribute("destino", destino);
		vuelosDoc.setRootElement(eVuelos);
		
		List<Element> vuelos = doc.getRootElement().getChildren();
		for (Element vuelo : vuelos) {
			if(vuelo.getChildText("destino").equals(destino)) {
				Element eVuelo = vuelo.clone();
				eVuelo.removeChild("destino");
				eVuelos.addContent(eVuelo);
			}
			
		}
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			outputter.output(vuelosDoc, new FileWriter(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void crearFicheroCompanias() {
		Document docComp = new Document();
		
		Element eComps = new Element("companias");
		docComp.setRootElement(eComps);
		
		HashMap<String, Element> vuelosPorCompania = new HashMap<String, Element>();
		
		
		HashSet<String> comps = getCompanias();
		for (String comp : comps) {
			if(comp != null) {
				Element eCompania = new Element("compania").setAttribute("id", comp);
				vuelosPorCompania.put(comp, eCompania);
			}
		}
		
		
		List<Element> vuelos = doc.getRootElement().getChildren();
		
		
		for (Element vuelo : vuelos) {
			Element eVuelo = new Element("Vuelo");
			String id = vuelo.getAttributeValue("id");
			eVuelo.setAttribute("id", id).setContent(vuelo.getChild("destino").clone());
			eVuelo.setAttribute("hora", vuelo.getChildText("hora"));
			vuelosPorCompania.put(getCompania(id), vuelosPorCompania.get(getCompania(id)).addContent(eVuelo));
		}
		
		eComps.addContent(vuelosPorCompania.values());

		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			outputter.output(docComp, new FileWriter("files/gestorVuelos/companias.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	private HashSet<String> getCompanias() {
		HashSet<String> comps = new HashSet<String>();
	List<Element> vuelos = doc.getRootElement().getChildren();
		
		for (Element vuelo : vuelos) {
			String id = vuelo.getAttributeValue("id");
			id = getCompania(id);
			comps.add(id);
		}
		
		return comps;
	}

	private String getCompania(String id) {
		return id.substring(0, 2) ;
	}

	private void cambiarEstructura() {
		List<Element> vuelos = doc.getRootElement().getChildren();
		
		for (Element vuelo : vuelos) {
			vuelo.setAttribute("hora", vuelo.getChildText("hora"));
			vuelo.removeChild("hora");
			if(vuelo.getChild("escala") != null) vuelo.removeChild("escala");
			
		}
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			outputter.output(doc, new FileWriter("files/gestorVuelos/vuelos.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		GestorVuelos a = new GestorVuelos("vuelos.xml");
		a.destinosDirectosDesde("Madrid").forEach(e -> System.out.println(e));
		

		a.nuevoVuelo("ib7777", "Vitoria", "Swaziland", new int[] {19, 30}, false);
		
		System.out.println(a.borrarVuelo("ib7777"));
		
		a.vuelosPosterioresA("18:00");
		a.vuelosConDestino("Frankfurt");
		a.crearFicheroCompanias();
		a.cambiarEstructura();
	}
	


}
