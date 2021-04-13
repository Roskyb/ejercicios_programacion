package ejers_prog.tema9.tanda2.ejer1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ejer1 extends JFrame{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7467665408889113450L;
	private JPanel resultadoPanel;
	private JPanel panelNumeros;
	private JPanel panelOperaciones;
	private JPanel panelOpciones;
	@SuppressWarnings("unused")
	private final String[] OPCIONES_CALCULADORA = new String[] {"Decimal", "Binario", "Hexadecimal", "Octal"};
	private ArrayList<JRadioButton> arrayJRadiosButtons;

	public Ejer1() {
		this.setLayout(new BorderLayout());
		
		// panel resultado - zona norte
		resultadoPanel = new JPanel();
		resultadoPanel.setVisible(true);
		resultadoPanel.setBackground(Color.ORANGE);
		resultadoPanel.setLayout(new BorderLayout());
		resultadoPanel.setPreferredSize(new Dimension(585, 100));
		
		JTextField cajaDeTexto = new JTextField();
		cajaDeTexto.setEditable(false);
		cajaDeTexto.setText("0");
		cajaDeTexto.setBackground(Color.ORANGE);
		cajaDeTexto.setBorder(null);
		cajaDeTexto.setFont(new Font("Arial", Font.BOLD, 32));
	
		resultadoPanel.add(cajaDeTexto, BorderLayout.EAST);
		
		this.add(resultadoPanel, BorderLayout.NORTH);
		this.pack();
		
		// panel numeros - zona centro
		
		panelNumeros = new JPanel();
		panelNumeros.setVisible(true);
		panelNumeros.setLayout(new GridLayout(0, 3));
		
		for (int i = 0; i < 10; i++) {
			JButton b = new JButton(Integer.toString(i));
			panelNumeros.add(b);
		}
		
		this.add(panelNumeros, BorderLayout.CENTER);
	
		// panel radios - zona oeste
		panelOpciones = new JPanel();
		panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));

		arrayJRadiosButtons = new ArrayList<JRadioButton>();
		
		ButtonGroup grupoOpciones = new ButtonGroup();

		for (int i = 0; i < OPCIONES_CALCULADORA.length; i++) {
			JRadioButton b = new JRadioButton(OPCIONES_CALCULADORA[i]);
			if(i == 0) b.setSelected(true);
			arrayJRadiosButtons.add(b);
			grupoOpciones.add(b);
			panelOpciones.add(b);
		}
		
		
		
		this.add(panelOpciones, BorderLayout.WEST);
		
		// panel botones operacion - zona sur
		
		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelOperaciones.setVisible(true);
		panelOperaciones.setLocation(0, 0);
		panelOperaciones.setSize(600, 100);
		
		JButton b1 = new JButton("+");
		JButton b2 = new JButton("-");
		JButton b3 = new JButton("*");
		JButton b4 = new JButton("/");
		JButton b5 = new JButton("=");
		
		Dimension d4 = new Dimension(60, 50);
		b1.setPreferredSize(d4);
		b2.setPreferredSize(d4);
		b3.setPreferredSize(d4);
		b4.setPreferredSize(d4);
		b5.setPreferredSize(d4);
		
		panelOperaciones.add(b1);
		panelOperaciones.add(b2);
		panelOperaciones.add(b3);
		panelOperaciones.add(b4);
		panelOperaciones.add(b5);
		
		this.add(panelOperaciones, BorderLayout.SOUTH);
		
		

		
		this.setTitle("Calculadora");
		
		this.setLocation(0, 0);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Ejer1();
	}
	
}
