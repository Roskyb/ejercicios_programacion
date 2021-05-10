package ejers_prog.tema10.tanda1.ejer1;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Ejer1{



	
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		
		Document doc = new Document();
		
		Element eTabla = new Element("tabla");
		
		Attribute attNum = new Attribute("num", num+"");
		eTabla.setAttribute(attNum);
		doc.setRootElement(eTabla);
		
		
		for (int i = 1; i <= 10; i++) {
			int resultado = i*num;
			Element eFactor = new Element("factor")
					.setAttribute(new Attribute("f", i+""))
					.setContent(new Element("resultado").setText(resultado + ""));
			eTabla.addContent(eFactor);
		}
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		
		try {
			
			outputter.output(doc, new FileWriter("files/tabla_del_" + num + ".xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
