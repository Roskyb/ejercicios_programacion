package ejers_prog.tema9.tanda2.ejer3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ejer3 extends JFrame{
	
	
	
	private JOptionPane panelInicio;
	private JPanel panelOpciones;
	private JPanel panelImagenes;
	private File directorioImagenes;


	public Ejer3() {
	
		panelInicio = new JOptionPane(null);
		String inputValue = JOptionPane.showInputDialog("Introduce la ruta");
		directorioImagenes = new File(inputValue);
		
		if(inputValue != null && directorioImagenes.exists() && directorioImagenes.isDirectory() && directorioImagenes.list().length > 0) {
			dibujarVentanaPrincipal();
		}else {
			System.out.println("Operacion Cancelada");
		}
		
		
		
	}
	
	
	private void dibujarVentanaPrincipal() {
		this.setLayout(new BorderLayout(10, 10));
		
		dibujarPanelOpciones();
		dibujarPanelImagenes();
		
		this.setSize(800, 600);
		this.setVisible(true);
		this.setTitle("");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private void dibujarPanelImagenes() {
		panelImagenes = new JPanel();
		panelImagenes.setVisible(true);
		panelImagenes.setLayout(new BorderLayout());

		JLabel tituloImagenes = new JLabel("Conoces estos lugares");
		panelImagenes.add(tituloImagenes, BorderLayout.NORTH);
		JPanel contenedorImagenes = new JPanel();
		contenedorImagenes.setSize(500, 600);
		contenedorImagenes.setLayout(new GridLayout(0,3, 2, 2));
		contenedorImagenes.setVisible(true);
		
		ArrayList<JLabel> imagenes = cargarImagenes();
		
		for (JLabel jLabel : imagenes) {
			contenedorImagenes.add(jLabel);
		}
		
		panelImagenes.add(contenedorImagenes, BorderLayout.CENTER);
		
		
		
		
		this.add(panelImagenes);
		
		
		
		
		
	}


	private ArrayList<JLabel> cargarImagenes() {
		ArrayList<JLabel> imagenes = new ArrayList<JLabel>();
		
		for (String str : directorioImagenes.list()) {
			System.out.println(directorioImagenes.getPath() + "\\" + str);
			JLabel jl = new JLabel();
			jl.setIcon(redim(directorioImagenes.getPath() + "/" + str, 120, 100));
			imagenes.add(jl);
			
		}
		
		return imagenes;
	}


	private void dibujarPanelOpciones() {

		panelOpciones = new JPanel();
		
		panelOpciones.setVisible(true);
		panelOpciones.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setVisible(true);
		panelSeleccion.setLayout(new FlowLayout());
		JLabel etiquetaSelecion = new JLabel("Imagen seleccionada");
		DefaultComboBoxModel<String> a = new DefaultComboBoxModel<String>(directorioImagenes.list());
		
		JComboBox<String> opciones = new JComboBox<String>(a);
		
		panelSeleccion.add(etiquetaSelecion);
		panelSeleccion.add(opciones);
		
		// opcion 2 
		JPanel panelArea = new JPanel();
		panelArea.setVisible(true);
		panelArea.setLayout(new FlowLayout());
		
		JLabel etiquetaTextArea = new JLabel("Escribe tu opinion");
		JTextArea textAreaOpciones = new JTextArea(5, 15);
		
		panelArea.add(etiquetaTextArea);
		panelArea.add(textAreaOpciones);
		
		
		panelOpciones.add(panelSeleccion);
		panelOpciones.add(panelArea);
		panelOpciones.add(new JButton("Guardar"));
		
		panelOpciones.setPreferredSize(new Dimension(350, 300));
		
		this.add(panelOpciones, BorderLayout.WEST);
		this.pack();
	}
	
	private static ImageIcon redim(String fichImag, int ancho, int alto) {
		ImageIcon imIcon = new ImageIcon(fichImag);
		Image im = imIcon.getImage();
//		Image im2 = im.getScaledInstance(ancho, alto, 0);
		return new ImageIcon(im);
	}


	public static void main(String[] args) {
		Ejer3 a = new Ejer3();
	}

}
