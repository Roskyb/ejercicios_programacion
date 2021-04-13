package ejers_prog.tema9.tanda1.ejer1;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ejer1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6878577283399113759L;

	private final Ciclo[] ciclos = new Ciclo[] { new Ciclo("Desarrollo de Aplicaciones Web", "GS"),
			new Ciclo("Auxiliar Administrativo", "GM") };

	private final String[] extras = new String[] { "Permiso B1", "Ingles", "Euskera", "Experiencia" };

	private JTextField campoNombre;
	private JLabel imgContainer;
	private JComboBox<Ciclo> comboGrados;
	private ButtonGroup radioJornadas;
	private JButton botonGuardar;
	private JButton botonBorrar;

	public Ejer1() {


		// IMAGEN EMPLEADO
		imgContainer = new JLabel(redim("img/empleado.png", 200, 200));
		imgContainer.setSize(100, 200);
		imgContainer.setLocation(10, 10);
		this.add(imgContainer);

//		this.add(imgContainer);


		// CAMPO NOMBRE
		campoNombre = new JTextField(100);
		campoNombre.setText("Escribe aquí tu nombre");
		campoNombre.setSize(300, 40);
		campoNombre.setLocation(150 , 10);
		this.add(campoNombre);

		// RADIO BUTTONS
		radioJornadas = new ButtonGroup();
		JRadioButton radioMediaJornada = new JRadioButton("Media Jornada");
		radioJornadas.add(radioMediaJornada);
		JRadioButton radioJornadaCompleta = new JRadioButton("Jornada Completa");
		radioJornadas.add(radioJornadaCompleta);
		radioMediaJornada.setLocation(150 , 60);
		radioMediaJornada.setSize(150, 20);
		radioJornadaCompleta.setLocation(300 , 60);
		radioJornadaCompleta.setSize(150, 20);
		this.add(radioMediaJornada);
		this.add(radioJornadaCompleta);

		// COMBO
		comboGrados = new JComboBox<Ciclo>();
		for (int i = 0; i < ciclos.length; i++) {
			comboGrados.addItem(ciclos[i]);
		}

		comboGrados.setSize(300, 40);
		comboGrados.setLocation(150, 100);
		this.add(comboGrados);

		// CHECKBOXES
		Checkbox[] checkboxes = new Checkbox[extras.length];
		int x = 150;
		int i = 0;
		for (String extra : extras) {

			Checkbox a = new Checkbox(extra);
			a.setLocation(x, 150);
			a.setSize(50, 20);
			a.setSize(100, 20);
			a.setState(true);
			checkboxes[i] = a;
			this.add(a);
			i++;
			x += 100;
		}


		// BOTONES

		Dimension tamañoBotones = new Dimension(100, 30);
		botonGuardar = new JButton("Guardar");
		botonGuardar.setSize(tamañoBotones);
		botonGuardar.setLocation(150, 200);

		botonBorrar = new JButton("Borrar");
		botonBorrar.setSize(tamañoBotones);
		botonBorrar.setLocation(300 , 200);
		botonBorrar.setEnabled(false);
		;

		this.add(botonGuardar);
		this.add(botonBorrar);

		// Configuracion general de la ventana
		this.setTitle("Instancia Empleado");
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize(600, 300);
		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static ImageIcon redim(String fichImag, int ancho, int alto) {
		ImageIcon imIcon = new ImageIcon(fichImag);
		Image im = imIcon.getImage();
		Image im2 = im.getScaledInstance(ancho, alto, 0);
		return new ImageIcon(im2);
	}

	public static void main(String[] args) {
		new Ejer1();
	}

}
