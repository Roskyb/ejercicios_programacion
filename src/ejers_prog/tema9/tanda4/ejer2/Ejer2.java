package ejers_prog.tema9.tanda4.ejer2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Ejer2 extends JFrame  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118263870518817319L;
	private JTextArea textArea;
	private JButton botonVaciar;
	private JButton botonMayusculas;
	private JButton botonInvertir;
	private DialogTipoMayuscula modal;

	public Ejer2() {
		this.setLayout(new BorderLayout());
		
		dibujar();
		eventos();
		modal = new DialogTipoMayuscula(textArea);
		this.setSize(400, 290);
		this.setVisible(true);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void eventos() {
		botonVaciar.addActionListener(this);
		botonInvertir.addActionListener(this);
		botonMayusculas.addActionListener(this);
		
	}

	private void dibujar() {
		dibujarPanelNorte();
		dibujarPanelCentral();
		dibujarPanelSur();
	}

	private void dibujarPanelSur() {
		JPanel panelSur = new JPanel(new GridLayout(1, 3, 5, 5));
		panelSur.setBorder(BorderFactory.createEmptyBorder(5,  10, 10, 10));
		
		botonVaciar = new JButton("VACIAR");
		botonMayusculas = new JButton("MAYUSCULAS");
		botonInvertir = new JButton("INVERTIR");
		
		panelSur.add(botonVaciar);
		panelSur.add(botonMayusculas);
		panelSur.add(botonInvertir);
		
		this.add(panelSur, BorderLayout.SOUTH);
		
	}

	private void dibujarPanelCentral() {
		JPanel panelCentral = new JPanel();
		textArea = new JTextArea(10, 20);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panelCentral.add(scroll);
		
		this.add(panelCentral);
		
		
	}

	private void dibujarPanelNorte() {
		JPanel panelNorte = new JPanel(new FlowLayout());
		JLabel tituloNorte = new JLabel("Escriba aquí el texto: ");
		
		panelNorte.add(tituloNorte);
		this.add(panelNorte, BorderLayout.NORTH);
		
	}

	public static void main(String[] args) {
		new Ejer2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPulsado = (JButton) e.getSource();
		if(textArea.getText().length() == 0) return;
		switch (botonPulsado.getText()) {
		case "VACIAR":
			textArea.setText("");
			break;
			
		case "INVERTIR":
			String texto = textArea.getText();
			String textoInvertido = "";
			
			for (int i = texto.length() - 1; i >= 0; i--) {
				textoInvertido = textoInvertido + texto.charAt(i);
			}

			textArea.setText(textoInvertido);
			
			break;
			
		case "MAYUSCULAS":
				modal.setVisible(true);
			break;


		default:
			break;
		}
		

		
		
	}
	
}
