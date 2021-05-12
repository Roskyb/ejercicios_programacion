package ejers_prog.tema10.introduccion;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JRadioButton;


public class VentanaGeneros extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5735436120621136696L;

	private GestorCine gestor;
	private JList listaGeneros;

	public VentanaGeneros() {

		this.gestor = new GestorCine("files/prueba.xml");
		dibujar();

		eventos();

		this.setSize(200, 300);
		this.setVisible(true);
		this.setTitle("Ventana generos");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private void eventos() {
		this.listaGeneros.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String genero = listaGeneros.getSelectedValue().toString();
				dibujarDialogo(genero);
				
			}
				
			

			private void dibujarDialogo(String genero) {
				JDialog dialogo = new JDialog();
				dialogo.setLayout(new BoxLayout(dialogo.getContentPane(), BoxLayout.Y_AXIS));
				
				JRadioButton JRadioButton = new JRadioButton("opcion1");
				
				ArrayList<Pelicula> pelis = gestor.pelisGenero(genero);
				
				for (Pelicula pelicula : pelis) {
				
				}
				
				JButton botonComprar = new JButton("Comprar");
				
				dialogo.add(JRadioButton);
				dialogo.add(botonComprar);
				dialogo.setVisible(true);
				dialogo.pack();
			}
			
		});
	}

	private void dibujar() {

		listaGeneros = new JList<String>(gestor.getGeneros());
		this.getContentPane().add(listaGeneros, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new VentanaGeneros();
	}

}
