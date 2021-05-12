package cine2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class VentanaGeneros extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList lstGeneros;
	private GestorCine gestorCine;
	

	public VentanaGeneros() {
		
		gestorCine=new GestorCine("files/prueba.xml");
		
		dibujar();
		//eventos();
		
		pack();
		setVisible(true);
		
	}
	
	
	
	private void dibujar() {
		
		lstGeneros=new JList(gestorCine.distintosGenero());
		this.getContentPane().add(new JLabel("Elige genero"),"North");
		this.getContentPane().add(lstGeneros);
		
	}
	
	
	
	public static void main(String[] args) {
		new VentanaGeneros();
		
	}

}
