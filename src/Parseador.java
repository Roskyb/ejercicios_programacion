import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Parseador {

	
	public static void main(String[] args) {
		SAXBuilder builder = new SAXBuilder();
		
		try {
			Document doc = builder.build("files/prueba.xml");
			Element eVuelos = doc.getRootElement();
			
			List<Element> listaVuelos = eVuelos.getChildren();
			
			
			
			System.out.println(listaVuelos.size());
			
			
			for (Element element : listaVuelos) {
				System.out.println("Vuelo de " + element.getChild("origen").getText());
			}
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
