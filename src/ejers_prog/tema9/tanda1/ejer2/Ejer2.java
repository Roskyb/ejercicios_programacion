package ejers_prog.tema9.tanda1.ejer2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Ejer2 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -276096341781405824L;

	private Pato[] patitos = new Pato[] {
		new Pato("DonaldDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD", 80),
		new Pato("Roger", 180),
		new Pato("Daysi", 70)
	};
	
	private JList<Pato> listaPatos;
	private JScrollPane panelPatos;
	private JButton botonGuardar;
	private JPanel panelCheckBox;
	private JCheckBox[] checksAlimentos;

	public Ejer2() {
		
		
		// lista

		
		listaPatos = new JList<Pato>(patitos);
		listaPatos.setSelectionMode(0);
		listaPatos.setBackground(Color.PINK);
		panelPatos = new JScrollPane(listaPatos);
		panelPatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelPatos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panelPatos.setLocation(50, 50);
		panelPatos.setSize(200, 200);
		panelPatos.setVisible(true);

		
		this.add(panelPatos);
		
		
		// BOTON
		
		botonGuardar = new JButton("Guardar");
		
		botonGuardar.setSize(200, 30);
		botonGuardar.setLocation(50, 275);
		
		this.add(botonGuardar);
		
		// COMIDAS
		
		panelCheckBox = new JPanel();
		
		String[] comidas  = alimentos("files/comidas.txt");
		checksAlimentos = new JCheckBox[comidas.length];
		
		int y = 0;
		for (int i = 0; i < comidas.length; i++) {
			JCheckBox c = new JCheckBox(comidas[i]);
			c.setSize(150, 20);
			c.setLocation(20, y);
			checksAlimentos[i]  = c;
			panelCheckBox.add(c);
			y+= 30;
		}
		
		panelCheckBox.setSize(150, comidas.length * 40 );
		panelCheckBox.setLocation(300, 50);
		panelCheckBox.setLayout(null);
		this.add(panelCheckBox);
		
		
		// general
		this.setTitle("ALIMENTA A TUS PATOS");
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize(600, 450);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private String[] alimentos(String nomFich) {
		
		File f  = new File(nomFich);
		if(!f.exists() || f.length() == 0) return null;
		ArrayList<String> comidas = new ArrayList<String>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			
			String line = bf.readLine();
			while(line != null) {
				
				comidas.add(line);
				
				line = bf.readLine();
			}
			
			bf.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no se ha podido encontrar");
		} catch (IOException e) {
			System.out.println("Error de lectura");
		}
		
		
		return comidas.toArray(new String[0]); 
		
		
		
	}
	
	public static void main(String[] args) {
		new Ejer2();
	}
	
}
