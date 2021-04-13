package ejers_prog.tema9.tanda2.ejer2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Ejer2 extends JFrame {
	


	private static final int N_ETIQUETAS = 10;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6365702989836118135L;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JButton botonClickMe;
	private JTextArea textArea;
	private JButton botonOk;


	public Ejer2() {
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(500, 500);
		panelSuperior = panelSuperior();
		panelInferior = panelInferior();
		
		this.getContentPane().add(panelSuperior);
		this.getContentPane().add(panelInferior);
		botonOk = new JButton("OK");
		this.getContentPane().add(botonOk);

		


		this.setVisible(true);
		this.setTitle("Paneles anidados en BoxLayout");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel panelSuperior() {
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		Border title = BorderFactory.createTitledBorder("BorderLayout");
		panelSuperior.setBorder(title);
		panelSuperior.setVisible(true);
		
		JPanel gridPanel1 = new JPanel(new GridLayout(0,1));
		title = BorderFactory.createTitledBorder("GridLayout");
		gridPanel1.setBorder(title);
		gridPanel1.setVisible(true);
		
		for (int i = 1; i <= N_ETIQUETAS; i++) {
			JLabel j = new JLabel("Label " + i);
			j.setPreferredSize(new Dimension(this.getSize().width / 4 , 10));
			gridPanel1.add(j);
			
		}

		panelSuperior.add(gridPanel1, BorderLayout.WEST);
		
		
		
		JPanel gridPanel2 = new JPanel(new GridLayout(0,1));
		gridPanel2.setBorder(title);
		gridPanel2.setVisible(true);
		
		gridPanel2.setLocation(210, 20);
		gridPanel2.setSize(200, 250);
		gridPanel2.setBorder(title);
		gridPanel2.setVisible(true);
		
		for (int i = 1; i <= N_ETIQUETAS; i++) {
			JTextField tf = new JTextField();
			gridPanel2.add(tf);
		}
		
		panelSuperior.add(gridPanel2, BorderLayout.CENTER);
		
		
		return panelSuperior;
		
	}
	
	private JPanel panelInferior() {
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		Border title = BorderFactory.createTitledBorder("BorderLayout");
		panelInferior.setBorder(title);
		panelInferior.setVisible(true);
		
		
		JPanel borderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		borderPanel.setBorder(BorderFactory.createTitledBorder("FlowLayout"));
		
		
		panelInferior.add(borderPanel, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		botonClickMe = new JButton("Click Me");
		borderPanel.add(botonClickMe);
		panelInferior.add(textArea, BorderLayout.CENTER);
		

		
		return panelInferior;
		
	}
	
	
	public static void main(String[] args) {
		new Ejer2();
	}
	
}
