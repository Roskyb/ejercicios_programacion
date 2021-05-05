import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ExplicacionJDOM {

	
	public ExplicacionJDOM() {

	
	}
	
	
	public static void main(String[] args) {
		final String[] DEPARTAMENTOS = new String[] {"Finanzas", "Calidad", "Informática"};
		final int[] PRESUSPUESTOS = new int[] {1000, 2000, 3000};
		
		
		
		Document doc = new Document();
		Element ePresupuestos = new Element("Presupuestos");	
		doc.setRootElement(ePresupuestos);
		
		for (int i = 0; i < DEPARTAMENTOS.length; i++) {
			String j = DEPARTAMENTOS[i];
			Element eDepartamentos = new Element("Departamento");
			Attribute aNombre = new Attribute("nombre", j);
			eDepartamentos.setAttribute(aNombre);
			eDepartamentos.setText(PRESUSPUESTOS[i] + "");	
			ePresupuestos.addContent(eDepartamentos);
			
			
			
		}
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			
			outputter.output(doc, new FileWriter("files/presupuestos.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
	}
	
}
