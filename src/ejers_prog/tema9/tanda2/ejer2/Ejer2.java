package ejers_prog.tema9.tanda2.ejer2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Ejer2 extends JFrame {
	


	private JPanel panelSuperior;
	private JPanel panelInferior;


	public Ejer2() {
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		panelSuperior = panelSuperior();
		panelInferior = panelInferior();
		
		this.getContentPane().add(panelSuperior);
		this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
		this.getContentPane().add(panelInferior);
		this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
		this.getContentPane().add(new JButton("OK"));

		

		this.setSize(600, 600);
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
		gridPanel1.setLocation(5, 20);
		gridPanel1.setSize(200, 250);
		gridPanel1.setBorder(title);
		gridPanel1.setVisible(true);
		
		for (int i = 1; i < 4; i++) {
			JLabel j = new JLabel("Label " + i);
			gridPanel1.add(j);
			gridPanel1.add(Box.createRigidArea(new Dimension(200, 0)));
		}

		panelSuperior.add(gridPanel1, BorderLayout.WEST);
		
		
		
		
		
		JPanel gridPanel2 = new JPanel(new GridLayout(0,1));
		gridPanel2.setBorder(title);
		gridPanel2.setVisible(true);
		
		gridPanel2.setLocation(210, 20);
		gridPanel2.setSize(200, 250);
		gridPanel2.setBorder(title);
		gridPanel2.setVisible(true);
		
		for (int i = 1; i < 4; i++) {
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
		borderPanel.add(new JButton("Click Me"));
		
		panelInferior.add(borderPanel, BorderLayout.NORTH);
		
		JTextArea ta = new JTextArea();
		
		panelInferior.add(ta, BorderLayout.CENTER);
		
		
		
		

		
		return panelInferior;
		
	}
	
	
	public static void main(String[] args) {
		Ejer2 a = new Ejer2();
	}
	
}
