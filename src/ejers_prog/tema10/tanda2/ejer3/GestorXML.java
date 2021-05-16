package ejers_prog.tema10.tanda2.ejer3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
	
	public HashMap<String, Plato> getPlatos() {
		HashMap<String, Plato> mapPlatos = new HashMap<String, Plato>();
		
		for (Element plato : platos) {
			mapPlatos.put(plato.getAttributeValue("nombre"), elementoAPlato(plato));
		}
		
		return mapPlatos;
	}

	public void guardarPedidos(String fecha, String primero, String principal, String[] adicionales) {
		SAXBuilder sax = new SAXBuilder();
		try {
			File file2 = new File("files/pedidos.xml");
			Document docp = sax.build(file2);
			
			List<Element> pedidosFecha = docp.getRootElement().getChildren();
			Element ePedidosFecha = null;
			
			for (Element ePedido : pedidosFecha) {
				if(ePedido.getAttributeValue("fecha").equals(fecha)) {
					ePedidosFecha = ePedido;
				}
			}
			
			if(ePedidosFecha != null) {
				Element ePedido = new Element("pedido");
				ePedidosFecha.addContent(ePedido);
				
				Element ePrimero = new Element("primero").setText(primero);
				Element ePrincipal = new Element("principal").setText(principal);
				
				ePedido.addContent(ePrimero);
				ePedido.addContent(ePrincipal);
				
				if(adicionales.length > 0 ) {
					Element eAdicionales = new Element("adicionales");
					for (String adic : adicionales) {
						Element eAdicional = new Element("adicional").setText(adic);
						eAdicionales.addContent(eAdicional);
					}
					ePedido.addContent(eAdicionales);
				}
			}else {
				
				Element ePedidos = new Element("pedidos");
				ePedidos.setAttribute("fecha", fecha);
				docp.getRootElement().addContent(ePedidos);
				
				Element ePedido = new Element("pedido");
				ePedidos.addContent(ePedido);
				
				Element ePrimero = new Element("primero").setText(primero);
				Element ePrincipal = new Element("principal").setText(principal);
				
				ePedido.addContent(ePrimero);
				ePedido.addContent(ePrincipal);
				
				if(adicionales != null) {
					Element eAdicionales = new Element("adicionales");
					for (String adic : adicionales) {
						Element eAdicional = new Element("adicional").setText(adic);
						eAdicionales.addContent(eAdicional);
					}
					ePedido.addContent(eAdicionales);
				}


			}
			
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(docp, new FileWriter(file2));
			System.out.println("guardado");
			
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
