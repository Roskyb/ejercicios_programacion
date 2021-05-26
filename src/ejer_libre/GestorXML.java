package ejer_libre;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorXML {
	private String nomFich;
	private Document doc;
	
	GestorXML(String nomFich) {
		this.nomFich = nomFich;
		cargarDocumento();
		
	}
	private void cargarDocumento() {
			doc = new Document();
			doc.setRootElement(new Element("departamentos"));
	}
	
	
	public void guardarDocumento() {
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		try {
			out.output(this.doc, new FileWriter("files/" + nomFich + ".xml"));
			out.output(this.doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void aniadirDepartamento(String nombre, String localidad) {
		doc.getRootElement().addContent(new Element("departamento").setText(nombre).setAttribute("localidad", localidad));
	}

}
