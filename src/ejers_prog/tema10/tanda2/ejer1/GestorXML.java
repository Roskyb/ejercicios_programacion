package ejers_prog.tema10.tanda2.ejer1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorXML {

	
	
	private Document doc;
	private File file;
	private final String[] MEDICOS = new String[] {"Dr Saez", "Dra Artea", "Dr Cabeza", "Dra Kholn"};


	public GestorXML(File f) {
		this.file = f;
	}

	public void parsearXML() {
		try {
			SAXBuilder builder = new SAXBuilder();
			this.doc = builder.build(this.file);
		} catch (JDOMException e) {
			System.out.println("xml mal formado");
		} catch (IOException e) {
			System.out.println("Error al abriel el archivo");
		}
	}
	
	public void escribirXML() {
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		try {
			out.output(this.doc, new FileWriter(file));
		} catch (IOException e) {
			System.out.println("No ha sido posilbe escribir el archivo");
		}
	}

	public void resetearMedicos() {
		List<Element> medicos = doc.getRootElement().getChildren();
		
		for (Element medico : medicos) {
			medico.removeContent();
		}
		
		
		
	}

	public void crearXMLconInfomacionBasica() {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		Document documentoBasico = new Document();
		Element eConsultas = new Element("consultas");
		documentoBasico.setRootElement(eConsultas);
		for (String medico : MEDICOS) {
			Element eMedico = new Element("medico")
					.setAttribute(new Attribute("nombre", medico));
			eConsultas.addContent(eMedico);
		}
		
		try {
			outputter.output(documentoBasico, new FileWriter(file));
		} catch (IOException e) {
			System.out.println("Error al crear el archivo");
		}
		
	}

	public String[] nombresMedicos() {
		
		List<Element> medicos = doc.getRootElement().getChildren();
		String[] nombresMedicos = new String[medicos.size()];
		int i = 0;
		for (Element medico : medicos) {
			nombresMedicos[i] = medico.getAttributeValue("nombre");
			i++;
		}
		
		return nombresMedicos;
	}

	public ArrayList<HoraConsulta> citasMedico(String nomMedico) {
		List<Element> medicos = doc.getRootElement().getChildren();
		for (Element medico : medicos) {
			if(medico.getAttributeValue("nombre").equals(nomMedico)) {
				List<Element> consultas = medico.getChildren("consulta");
				ArrayList<HoraConsulta> arrConsultas = new ArrayList<HoraConsulta>();
				int i = 0;
				for (Element consulta : consultas) {
					String[] horario = consulta.getChildText("hora").split(":");
					int hora = Integer.parseInt(horario[0]);
					int minutos = Integer.parseInt(horario[1]);
					arrConsultas.add(new HoraConsulta(hora, minutos));
					
					i++;
				}
				return arrConsultas;
			}	
		}
		return null;
		
		
	}
	
	public void verXML() {
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		try {
			out.output(this.doc, System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearCitaEnMedico(String medico, HoraConsulta citaLibre, String paciente) {
		List<Element> medicos = doc.getRootElement().getChildren();
		for (Element med : medicos) {
			if(med.getAttributeValue("nombre").equals(medico)) {
				med.addContent(new Element("consulta")
						.addContent(new Element("hora")
								.setText(citaLibre.toString()))
						.addContent(new Element("paciente")
								.setText(paciente))
						);
				break;
			}
		}
	}
	
	

	
	
}
