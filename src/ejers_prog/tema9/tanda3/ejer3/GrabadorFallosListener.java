package ejers_prog.tema9.tanda3.ejer3;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GrabadorFallosListener implements WindowListener {

	private int[] fallos;
	private JButton[] arrBotones;

	public GrabadorFallosListener(JButton[] arrBotones, int[] fallos) {
		// TODO Auto-generated constructor stub
		this.fallos = fallos;
		this.arrBotones = arrBotones;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {

		String input = JOptionPane.showInputDialog("Introduce tu nombre, se generara un fichero con los números que tienes que practicar");
		
		PrintWriter bf;
		try {
			bf = new PrintWriter(new FileWriter("files/"+ input + ".txt"));
			bf.print("Debes practicar tablas de estos numeros");
			for (int i = 0; i < this.fallos.length; i++) {
				if(fallos[i] == 3) {
					int numero = Integer.parseInt(this.arrBotones[i].getText());
					bf.println(numero);
				}
			}
			
			bf.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("No se puede acceder al recurso");
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {

		

	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
