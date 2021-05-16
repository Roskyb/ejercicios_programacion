package ejers_prog.tema10.tanda2.ejer2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JuegoAhorcado extends JFrame {
	
	private JButton botonJugar;
	private JPanel panelErrores;
	private JButton[] arrayBotones;
	private JLabel labelPalabra;
	private Ahorcado ahorcado;
	private JLabel[] errores;
	
	public JuegoAhorcado() {
		this.setLayout(new GridLayout(4,1));
		dibujar();
		eventos();		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	private void dibujar() {
		this.add(dibujarPanelJugar());
		panelErrores = dibujarPanelErrores();
		this.add(panelErrores);
		this.add(dibujarPanelPalabra());
		this.add(dibujarPanelLetras());
		
	}

	private JPanel dibujarPanelErrores() {
		panelErrores = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		return panelErrores;
	}

	private JPanel dibujarPanelJugar() {
		JPanel panelJugar = new JPanel();
		botonJugar = new JButton("Jugar");
		botonJugar.setSize(new Dimension(100, 50));
		panelJugar.add(botonJugar);
		
		return panelJugar;
	}

	private JPanel dibujarPanelLetras() {
		JPanel panelPalabras = new JPanel(new GridLayout(2, 13, 5, 5));
		arrayBotones = new JButton[26];

		for (int i = 0; i < arrayBotones.length; i++) {
			arrayBotones[i] = new JButton((char)('A' + i) +"");
			panelPalabras.add(arrayBotones[i]);
		}
		
	
		return panelPalabras;
	}

	private JPanel dibujarPanelPalabra() {
		JPanel panelPalabra = new JPanel();
		panelPalabra.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		labelPalabra = new JLabel("");
		labelPalabra.setVisible(false);
		panelPalabra.add(labelPalabra);
		
		return panelPalabra;
	}


	public void estadoInicial() {
		ahorcado = null;
		botonJugar.setEnabled(true);
		labelPalabra.setText("");
		labelPalabra.setVisible(false);
		panelErrores.removeAll();
		panelErrores.repaint();
	}

	private void eventos() {
		botonJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botonJugar.setEnabled(false);
				int numLetras;
				try {
					numLetras = Integer.parseInt(JOptionPane.showInputDialog("ELEJA NÚMERO DE LETRAS"));
					System.out.println(numLetras);
				} catch (Exception e2) {
					System.out.println("No se pueden parsear a INT");
					numLetras = 0;
				}
				
				if(numLetras < 4 || numLetras == 8 || numLetras > 9 ) {
					ahorcado = new Ahorcado();
				}else {
					ahorcado = new Ahorcado(numLetras);
				}
				
				labelPalabra.setText(ahorcado.respuestaToBigString());
				labelPalabra.setVisible(true);
				
				errores = new JLabel[ahorcado.getVidasRestantes()];
				for (int i = 0; i < errores.length; i++) {
					ImageIcon imageIcon = new ImageIcon(new ImageIcon("img/fallo.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
					JLabel label = new JLabel(imageIcon);
					label.setPreferredSize(new Dimension(30,30));
					label.setVisible(false);
					errores[i] = label;
			
					panelErrores.add(label);
				}
			}
			
			
		});
		
		 ActionListener escuchadoBotones = new ActionListener() {
			
			 @Override
				public void actionPerformed(ActionEvent e) {
					if(ahorcado == null)return;
					JButton botonElegido = (JButton) e.getSource();
					char letra =  botonElegido.getText().toCharArray()[0];
					if(!ahorcado.tirar((char)(letra + 32))) {
						System.out.println(ahorcado.getFallos());
						errores[ahorcado.getFallos() - 1].setVisible(true);
						panelErrores.repaint();
						if(ahorcado.getVidasRestantes() == 0) {
							JOptionPane.showMessageDialog(null, "Se acabaron tus vidas. Palabra: " + ahorcado.getPalabra().toUpperCase(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
							estadoInicial();
						}
						
					} else {
						 labelPalabra.setText(ahorcado.respuestaToBigString());
							if(ahorcado.completo()) {
								JOptionPane.showMessageDialog(null, "Enhorabuena. Palabra: " + ahorcado.getPalabra().toUpperCase() + " acertada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
								ahorcado.guardarPalabraAcertada();
								estadoInicial();
								
							}
					}
			 }
					
		};
		for (JButton jButton : arrayBotones) {
			jButton.addActionListener(escuchadoBotones);
		}
	}

	private static final long serialVersionUID = 4077770254101815988L;
	
	public static void main(String[] args) {
		new JuegoAhorcado();
	}
}
