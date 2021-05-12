package cine2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaAnularVentas extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010094575702690389L;
	private JComboBox<Pelicula> comboPelis;
	private JCheckBox[] checksAsientos;
	private GestorCine gXML;
	private JPanel panelAsientos;
	private DefaultListModel<Integer> modeloAsientos;
	private JList lstSeleccionados;
	
	public VentanaAnularVentas() {
		
		gXML = new GestorCine("files/peliculas.xml");
		ArrayList<Pelicula> arrayPelis = gXML.todasLasPelis();
		
		Pelicula[] pelis = new Pelicula[arrayPelis.size()];
		
		for (int i = 0; i < arrayPelis.size(); i++) {
			pelis[i] = arrayPelis.get(i);
		}
		
		comboPelis = new JComboBox<Pelicula>(pelis);
		panelAsientos = new JPanel();
		panelAsientos.setLayout(new BoxLayout(panelAsientos, BoxLayout.Y_AXIS));
		this.add(comboPelis, BorderLayout.NORTH);
		this.add(panelAsientos);
		
		lstSeleccionados = new JList(modeloAsientos = new DefaultListModel<Integer>());
		lstSeleccionados.setPreferredSize(new Dimension(0, 150));
		lstSeleccionados.setBackground(Color.pink);
		
		this.add(lstSeleccionados, BorderLayout.SOUTH);
		
		eventos();
		
		this.setVisible(true);
		this.setSize(500, 500);
	}
	
	
	private void eventos() {
		comboPelis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloAsientos.removeAllElements();
				Pelicula pelSel = (Pelicula) comboPelis.getSelectedItem();
				int[] asienos = pelSel.getAsientos();
				
				if(asienos == null) {
					JOptionPane.showConfirmDialog(null, "Sin asientos vendidos!");
				}else {
					checksAsientos = new JCheckBox[asienos.length];
					panelAsientos.removeAll();
					for (int i = 0; i < asienos.length; i++) {
						checksAsientos[i] = new JCheckBox(asienos[i] + "");
						panelAsientos.add(checksAsientos[i]);
					}
					panelAsientos.revalidate();
					panelAsientos.repaint();
					
					eventosChecks();
				}
			}
		});
	}


	private void eventosChecks() {

		for (int i = 0; i < checksAsientos.length; i++) {
			checksAsientos[i].addActionListener(this);;
			
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox checkSel = (JCheckBox) e.getSource();
		if(checkSel.isSelected()) {
			modeloAsientos.addElement(Integer.parseInt(checkSel.getText()));
		} else {
			modeloAsientos.removeElement(Integer.parseInt(checkSel.getText()));
		}
	}


	public static void main(String[] args) {
		new VentanaAnularVentas();
	}
	
}
