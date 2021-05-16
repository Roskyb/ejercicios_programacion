package ejers_prog.tema10.tanda2.ejer2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Ahorcado {

	private String palabra;
	private String respuesta;
	private int vidasRestantes;
	private int fallos;
	private Element ePalabra;
	private Document doc;

	public Ahorcado() {
		this.palabra = this.palabraAleatoria();
		this.respuesta = this.palabra.replaceAll("\\w", "-");
		this.vidasRestantes = this.palabra.length() / 2;
		this.fallos = 0;
		System.out.println(this.palabra);
	}

	public Ahorcado(int numLetras) {
		this.palabra = this.palabraAleatoria(numLetras);
		this.respuesta = this.palabra.replaceAll("\\w", "-");
		this.vidasRestantes = this.palabra.length() / 2;
		this.fallos = 0;
	}

	public String respuestaToBigString() {

		String bigString = "";
		char[] arr = respuesta.toUpperCase().toCharArray();
		for (char c : arr) {
			bigString += c + " ";
		}

		return bigString;

	}

	public boolean tirar(char c) {
		ArrayList<Integer> posicionesDeLetra = contieneLaLetra(c);
		if (posicionesDeLetra.size() > 0) {

			desvelarLetras(c, posicionesDeLetra);

			return true;
		} else {
			fallos++;
			vidasRestantes--;
			return false;
		}
	}

	private void desvelarLetras(char c, ArrayList<Integer> posicionesDeLetra) {
		for (Integer pos : posicionesDeLetra) {
			this.respuesta = respuesta.substring(0, pos) + c + respuesta.substring(pos + 1);
		}
	}

	private ArrayList<Integer> contieneLaLetra(char c) {
		ArrayList<Integer> posiciones = new ArrayList<Integer>();
		int i = 0;
		int match = palabra.indexOf(c, i);
		while (match != -1) {
			posiciones.add(match);
			i = match + 1;
			match = palabra.indexOf(c, i);
		}

		return posiciones;
	}

	private String palabraAleatoria() {
		SAXBuilder builder = new SAXBuilder();

		try {
			doc = builder.build(new File("files/palabras.xml"));
			List<Element> grupos = doc.getRootElement().getChildren();
			int random = (int) (Math.random() * grupos.size());

			List<Element> grupo = grupos.get(random).getChildren();
			random = (int) (Math.random() * grupo.size());
			return grupo.get(random).getText();

		} catch (JDOMException e) {
			System.err.println("XML mal formado");
		} catch (IOException e) {
			System.err.println("Error al acceder al archivo");
		}

		return null;
	}

	boolean completo() {
		return palabra.equals(respuesta);
	};

	private String palabraAleatoria(int numLetras) {

		SAXBuilder builder = new SAXBuilder();

		try {
			doc = builder.build(new File("files/palabras.xml"));
			List<Element> grupos = doc.getRootElement().getChildren();

			for (Element grupo : grupos) {
				if (Integer.parseInt(grupo.getAttributeValue("numletras")) == numLetras) {
					List<Element> palabras = grupo.getChildren();
					int random = (int) (Math.random() * palabras.size());
					ePalabra = palabras.get(random);
					System.out.println(ePalabra.getText());
					return ePalabra.getText();

				}
			}

		} catch (JDOMException e) {
			System.err.println("XML mal formado");
		} catch (IOException e) {
			System.err.println("Error al acceder al archivo");
		}

		return null;

	}

	public void guardarPalabraAcertada() {
		Attribute attr = ePalabra.getAttribute("aciertos");
		if (attr != null) {
			try {
				String nuevoValor = (attr.getIntValue() + 1) + "";
				ePalabra.setAttribute("aciertos", nuevoValor);
			} catch (DataConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			ePalabra.setAttribute("aciertos", 1 + "");

		}
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
		try {
			output.output(doc, new FileWriter("files/palabras.xml"));
		} catch (IOException e) {
			System.err.println("No se pudo escribir el archivo");
		}
	}

	public int getVidasRestantes() {
		return vidasRestantes;
	}

	public int getFallos() {
		return fallos;
	}

	public String getPalabra() {
		return palabra;
	}


}
