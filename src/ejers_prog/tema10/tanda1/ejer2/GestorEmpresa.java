package ejers_prog.tema10.tanda1.ejer2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorEmpresa {

	final private String[] DEPARTAMENTOS = new String[] { "ventas", "facturacion", "informatica" };
	final private int[] FACTURACIONES = new int[] { 100000, 20000, 30000 };

	public GestorEmpresa(String nomFichero) {

		File f = new File("files/" + nomFichero);

		Document doc = new Document();
		Element eDepartamentos = new Element("departamentos");
		doc.setRootElement(eDepartamentos);
		int i = 0;
		for (String string : DEPARTAMENTOS) {
			eDepartamentos.addContent(new Element("Departamento").setAttribute("nombre", string)
					.setContent(new Element("facturacion").setText(FACTURACIONES[i] + "")));
			
			i++;
		}
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			
			outputter.output(doc, new FileWriter(f, false));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new GestorEmpresa("empresa.xml");
	}

}
