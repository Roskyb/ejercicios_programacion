package ejers_prog.tema9.tanda3.ejer3;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ejer3 extends JFrame{

	private JButton[] arrBotones;
	private ArrayList<JLabel> arrlEtiquetas;
	private int[] fallos;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5618948073234100806L;
	private final int N = 5;

	
	public Ejer3() {
		this.fallos = new int[N];
		this.setLayout(new FlowLayout());
		dibujarPanel();
		eventos();
		
		this.setSize(600, 500);
		this.setVisible(true);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void eventos() {
		for (JButton boton : arrBotones) {
			boton.addActionListener(new EscuhadorOperMultiplicar());
		}
		this.addWindowListener(new GrabadorFallosListener(arrBotones, fallos));
	}
	
	class EscuhadorOperMultiplicar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton boton = (JButton) e.getSource();
			int n = Integer.parseInt(boton.getText());
			int x = (int) (Math.random()*10) + 1;
			int result = n * x;
		
			
			try {
				String userInput = JOptionPane.showInputDialog(n + "x" + x);
				int idx = conseguirIndice(boton);
				if(Integer.parseInt(userInput) == result) {
					boton.setEnabled(false);
					arrlEtiquetas.get(idx).setIcon(redim("img/correcto.png", 30, 30));
				}else {
					
					fallos[idx]++;
					if(fallos[idx] == 3) {
						boton.setEnabled(false);
						arrlEtiquetas.get(idx).setIcon(null);
					}
				}
			} catch (Exception e2) {
				System.err.println("El input del usuario no es un numero");
			}
			
			
			
			
		}

		private int conseguirIndice(JButton boton) {
			for (int i = 0; i < arrBotones.length; i++) {
				if(arrBotones[i].equals(boton)) {
					return i;

				}
			}
			return -1;
		}
		
	}

	private void dibujarPanel() {
		
		JLabel label = new JLabel("Practicar las tablas de multiplicar");
		label.setFont(new Font("Blackadder ITC", Font.BOLD, 35));
		this.add(label);
		
		JPanel panelColumnas = new JPanel(new GridLayout(0,2, 100, 0));
		panelColumnas.setPreferredSize(new Dimension(200, 400));
		
		arrBotones = cargarBotones();
		arrlEtiquetas = cargarEtiquetas();
		
		for (int i = 0; i < N; i++) {
			panelColumnas.add(arrBotones[i]);
			panelColumnas.add(arrlEtiquetas.get(i));
		}
		
		
		this.add(panelColumnas);
		
	}

	private ArrayList<JLabel> cargarEtiquetas() {
		ArrayList<JLabel> arrL = new ArrayList<JLabel>();
		
		for (int i = 0; i < N; i++) {
			JLabel label = new JLabel();
			label.setIcon(redim("img/fallo.png", 30, 30));
			arrL.add(label);
		}
		
		return arrL;
	}

	private JButton[] cargarBotones() {

		JButton[] botones = new JButton[N];
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		
		
		for (int i = 0; i < N; i++) {
			int num;
			
			do{
				num = (int) (Math.random() * (21 - 2) + 2);
			}
			while(numeros.contains(num)); 
			
			numeros.add(num);
			
		}
		
		numeros.sort(null);
		
		int j = 0;
		for (Integer integer : numeros) {
			JButton jButton = new JButton("" + integer);
			jButton.setPreferredSize(new Dimension(30, 30));
			botones[j] = jButton;
			j++;
		}
		
		
		
		
		return botones;
	}
	
	private static ImageIcon redim (String fichImag, int ancho, int alto)
	{
	            ImageIcon imIcon=new ImageIcon(fichImag);
	            Image im=imIcon.getImage();
	            Image im2= im.getScaledInstance(ancho, alto, 0);
	            return new ImageIcon(im2);
	}

	public static void main(String[] args) {
		new Ejer3();
	}
	
}
