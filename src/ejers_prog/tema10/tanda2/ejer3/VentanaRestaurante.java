package ejers_prog.tema10.tanda2.ejer3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VentanaRestaurante extends JFrame {


	private final class EscuchadorPrimerPlato implements ActionListener {
		private String nombrePlato;
		private JLabel labelPrecio;

		public EscuchadorPrimerPlato(String text, JLabel labelPrecio) {
			nombrePlato = text;
			this.labelPrecio = labelPrecio;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(nombrePlato);
			labelPrecio.setVisible(true);
			precioPrimeros = precios.get(nombrePlato);
			labelPrecio.setText("PRICE: " + precioPrimeros + "€");

		}
	}
	
	private final class EscuchadorPlatoPrincipal implements ActionListener {
		private String nombrePlato;
		private JLabel labelPrecio;

		public EscuchadorPlatoPrincipal(String text, JLabel labelPrecio) {
			nombrePlato = text;
			this.labelPrecio = labelPrecio;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(nombrePlato);
			labelPrecio.setVisible(true);
			precioPrincipales = precios.get(nombrePlato);
			labelPrecio.setText("PRICE: " + precioPrincipales + "€");

		}
	}
	
	private final class EscuchadorPlatoAdicional implements ActionListener {
		private String nombrePlato;
		private JLabel labelPrecio;

		public EscuchadorPlatoAdicional(String text, JLabel labelPrecio) {
			nombrePlato = text;
			this.labelPrecio = labelPrecio;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			precioAdicionales = 0;
			System.out.println(nombrePlato);
			labelPrecio.setVisible(true);
			String textoPrecio = "";
			for (JCheckBox check : arrAdicional) {
				if(check.isSelected()) {
					textoPrecio += precios.get(check.getText()) + "€ + ";
					precioAdicionales += precios.get(check.getText());
				}
			}
			labelPrecio.setText( textoPrecio );
			System.out.println(precioAdicionales);

		}
	}
	



	private JPanel panelPrimerPlato;
	private static final long serialVersionUID = 6793477893011238559L;
	private JPanel panelPrimeros;
	private JPanel panelAdicionales;
	private JButton botonPedir;
	private GestorXML gestor;
	private JRadioButton[] arrPrincipales;
	private JRadioButton[] arrPrimeros;
	private JCheckBox[] arrAdicional;
	private HashMap<String, Double>  precios;
	private double precioTotal;
	private double precioPrincipales;
	private double precioPrimeros;
	private double precioAdicionales;

	public VentanaRestaurante() {
		precioTotal = 0;
		gestor = new GestorXML(new File("files/platos.xml"));
		precios = gestor.getPrecios();
		dibujar();
		eventos();
	

		this.setVisible(true);
		this.setSize(600, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	private void eventos() {
		botonPedir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(precioPrimeros == 0.0 || precioPrincipales ==  0.0) {
					JOptionPane.showMessageDialog(null, "Debes elegir al menos un primer plato y un plato principal");
				}else {
					JDialog dialogoPedido = new JDialog();
					JPanel panel1 = new JPanel();
					
				}
			
			}
		});
	}


	private void dibujar() {

		panelPrimerPlato = dibujarPanelPrimerPlato();
		panelPrimeros = dibujarPanelPlatoPrincipal();
		panelAdicionales = dibujarPanelAdicionales();
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout());
		panelCentral.add(panelPrimerPlato);
		panelCentral.add(panelPrimeros);
		panelCentral.add(panelAdicionales);

		this.add(dibujarPanelSur(), BorderLayout.SOUTH);

		this.add(panelCentral);

	}

	private JPanel dibujarPanelSur() {
		JPanel panelCentral = new JPanel(new FlowLayout());
		panelCentral.setPreferredSize(new Dimension(this.getWidth(), 35));
		botonPedir = new JButton("ORDER");
		panelCentral.add(botonPedir);
		return panelCentral;
	}

	private JPanel dibujarPanelPrimerPlato() {
		JPanel panelPrimerPlato = new JPanel();
		panelPrimerPlato.setLayout(new BoxLayout(panelPrimerPlato, BoxLayout.Y_AXIS));
	
		JLabel labelEntrantes = new JLabel("CHOOSE FIRST COURSE");
		labelEntrantes.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));
	
		panelPrimerPlato.add(labelEntrantes);
		
		cargarPlatos("primero", panelPrimerPlato);
		
		JLabel labelPrecioPrimero = new JLabel("Price: ");
		labelPrecioPrimero.setVisible(false);
		panelPrimerPlato.add(labelPrecioPrimero);
		

		for (JRadioButton radio : arrPrimeros) {
			radio.addActionListener(new EscuchadorPrimerPlato(radio.getText(), labelPrecioPrimero));
		}
		
		return panelPrimerPlato;
	}


	private JPanel dibujarPanelPlatoPrincipal() {
		JPanel panelPlatoPrincipal = new JPanel();
		panelPlatoPrincipal.setLayout(new BoxLayout(panelPlatoPrincipal, BoxLayout.Y_AXIS));
		JLabel labelPrimeros = new JLabel("CHOOSE MAIN COURSE");
		labelPrimeros.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));
	
		panelPlatoPrincipal.add(labelPrimeros);
	
		cargarPlatos("principal", panelPlatoPrincipal);
		
		JLabel labelPrecioPrimeros = new JLabel("");
		labelPrecioPrimeros.setVisible(false);
		panelPlatoPrincipal.add(labelPrecioPrimeros);
		
		for (JRadioButton radio : arrPrincipales) {
			radio.addActionListener(new EscuchadorPlatoPrincipal(radio.getText(), labelPrecioPrimeros));
		}
		
		return panelPlatoPrincipal;
	}


	private JPanel dibujarPanelAdicionales() {
		JPanel panelAdicionales = new JPanel();
		panelAdicionales.setLayout(new BoxLayout(panelAdicionales, BoxLayout.Y_AXIS));

		JLabel labelAdicionales = new JLabel("SIDE DISHES (extra price)");
		labelAdicionales.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));

		panelAdicionales.add(labelAdicionales);
		
		cargarPlatos("adicional", panelAdicionales);
		
		JLabel labelPrecioAdicionales = new JLabel();
		labelPrecioAdicionales.setVisible(false);
		panelAdicionales.add(labelPrecioAdicionales);

		for (JCheckBox radio : arrAdicional) {
			radio.addActionListener(new EscuchadorPlatoAdicional(radio.getText(), labelPrecioAdicionales));
		}
		
		
		return panelAdicionales;
	}

	private void cargarPlatos(String tipo, JPanel panel) {
		Plato[] platosTipo = gestor.damePlatos(tipo);

		switch (tipo) {

		case ("principal"):
			arrPrincipales = new JRadioButton[platosTipo.length];
			ButtonGroup bgPrincipales = new ButtonGroup();
			for (int i = 0; i < platosTipo.length; i++) {
				JRadioButton radio = new JRadioButton(platosTipo[i].getNombre());
				bgPrincipales.add(radio);
				arrPrincipales[i] = (radio);
				panel.add(radio);
			}
			break;

		case ("primero"):
			arrPrimeros = new JRadioButton[platosTipo.length];
			ButtonGroup bgPrimeros = new ButtonGroup();
			for (int i = 0; i < platosTipo.length; i++) {
				JRadioButton radio = new JRadioButton(platosTipo[i].getNombre());
				bgPrimeros.add(radio);
				arrPrimeros[i] = radio;
				panel.add(radio);
			}
			break;

		case ("adicional"):
			arrAdicional = new JCheckBox[platosTipo.length];
			for (int i = 0; i < platosTipo.length; i++) {
				JCheckBox check = new JCheckBox(platosTipo[i].getNombre());
				arrAdicional[i] = check;
				panel.add(check);
			}
			break;
		}



	}

	public static void main(String[] args) {
		new VentanaRestaurante();
	}

}

 