package ejers_prog.tema10.tanda2.ejer3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VentanaRestaurante extends JFrame {

	private JPanel panelPrimerPlato;
	private JPanel panelPrimeros;
	private JPanel panelAdicionales;

	private JButton botonPedir;
	private GestorXML gestor;
	private JRadioButton[] arrPrincipales;
	private JRadioButton[] arrPrimeros;
	private JCheckBox[] arrAdicional;
	private HashMap<String, Plato> mapaPlatos;
	private Plato platoPrimero;
	private Plato platoPrincipal;
	private ArrayList<Plato> platosAdicionales;

	public VentanaRestaurante() {

		gestor = new GestorXML(new File("files/platos.xml"));
		mapaPlatos = gestor.getPlatos();
		dibujar();
		eventos();

		this.setVisible(true);
		this.setSize(600, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
			radio.addActionListener(new EscuchadorPlatoAdicional(labelPrecioAdicionales));
		}

		return panelAdicionales;
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

	private JPanel dibujarPanelSur() {
		JPanel panelCentral = new JPanel(new FlowLayout());
		panelCentral.setPreferredSize(new Dimension(this.getWidth(), 35));
		botonPedir = new JButton("ORDER");
		panelCentral.add(botonPedir);
		return panelCentral;
	}

	private void eventos() {
		botonPedir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (platoPrimero == null || platoPrincipal == null) {
					JOptionPane.showMessageDialog(null, "Debes elegir al menos un primer plato y un plato principal");
				} else {
					JDialog dialogoPedido = new JDialog();
					dialogoPedido.setModal(true);
					dialogoPedido.setTitle("Your order");
					dialogoPedido.setLayout(new BoxLayout(dialogoPedido.getContentPane(), BoxLayout.Y_AXIS));
					JPanel panel1 = new JPanel();
					panel1.setLayout(new GridLayout(2, 2, 10, 10));

					JLabel nombrePlato = new JLabel(platoPrimero.getNombre() + " " + platoPrimero.getPrecio() + "€");
					JLabel imagenPlato = new JLabel(redim(platoPrimero.getRutaImagen(), 100, 100));

					panel1.add(nombrePlato);
					panel1.add(imagenPlato);

					nombrePlato = new JLabel(platoPrincipal.getNombre() + " " + platoPrincipal.getPrecio() + "€");
					imagenPlato = new JLabel(redim(platoPrincipal.getRutaImagen(), 100, 100));

					panel1.add(nombrePlato);
					panel1.add(imagenPlato);

					dialogoPedido.add(panel1);
					JLabel labelAdicionales = new JLabel();
					String texto = "";
					double total = 0;
					if (platosAdicionales != null && platosAdicionales.size() > 0) {
						Iterator<Plato> it = platosAdicionales.iterator();

						while (it.hasNext()) {
							Plato extra = it.next();
							texto += extra.getNombre() + "(" + extra.getPrecio() + "€)";
							total += extra.getPrecio();
							if (it.hasNext())
								texto += ", ";
						}
						texto += ": " + total + "€";
						labelAdicionales.setText(texto);
						JPanel panel2 = new JPanel();
						panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
						panel2.add(labelAdicionales);
						dialogoPedido.add(panel2);
					}

					JLabel labelPrecioTotal = new JLabel();
					labelPrecioTotal.setFont(new Font("Arial", Font.BOLD, 25));
					labelPrecioTotal.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.black));
					double totalTodos = platoPrincipal.getPrecio() + platoPrimero.getPrecio() + total;

					labelPrecioTotal.setText("YOUR TOTAL PRICE: " + totalTodos + " €");

					JPanel panel3 = new JPanel();
					panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
					panel3.add(labelPrecioTotal);

					dialogoPedido.add(panel3);

					JPanel panel4 = new JPanel();
					panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

					JButton botonAceptar = new JButton("ACCEPT");
					JButton botonDescartar = new JButton("DISCARD");

					botonDescartar.addActionListener(e1 -> {
						dialogoPedido.dispose();
					});

					botonAceptar.addActionListener(e1 -> {
						GregorianCalendar calendar = new GregorianCalendar();
						String fecha = calendar.get(GregorianCalendar.DAY_OF_MONTH) + "-"
								+ (calendar.get(GregorianCalendar.MONTH) + 1);
						int largo = 0;
						if (platoPrimero != null) {
							largo = platosAdicionales.size();
						}
						String[] adicionales = new String[largo];
						for (int i = 0; i < adicionales.length; i++) {
							adicionales[i] = platosAdicionales.get(i).getNombre();
						}
						gestor.guardarPedidos(fecha, platoPrimero.getNombre(), platoPrincipal.getNombre(), adicionales);
						dialogoPedido.dispose();
					});

					panel4.add(botonAceptar);
					panel4.add(botonDescartar);
					dialogoPedido.add(panel4);

					dialogoPedido.pack();
					dialogoPedido.setVisible(true);

				}

			}

			private ImageIcon redim(String fichImag, int ancho, int alto) {
				ImageIcon imIcon = new ImageIcon(fichImag);
				Image im = imIcon.getImage();
				Image im2 = im.getScaledInstance(ancho, alto, 0);
				return new ImageIcon(im2);
			}

		});
	}

	private final class EscuchadorPlatoAdicional implements ActionListener {
		private JLabel labelPrecio;

		public EscuchadorPlatoAdicional(JLabel labelPrecio) {
			this.labelPrecio = labelPrecio;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			labelPrecio.setVisible(true);
			String textoPrecio = "";
			platosAdicionales = new ArrayList<Plato>();
			for (JCheckBox check : arrAdicional) {
				if (check.isSelected()) {
					textoPrecio += mapaPlatos.get(check.getText()).getPrecio() + "€ + ";
					platosAdicionales.add(mapaPlatos.get(check.getText()));
				}
			}
			if (textoPrecio.length() > 2) {
				textoPrecio = textoPrecio.substring(0, textoPrecio.length() - 2);
				labelPrecio.setText(textoPrecio);

			}

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
			labelPrecio.setVisible(true);
			platoPrincipal = mapaPlatos.get(nombrePlato);

			labelPrecio.setText(platoPrincipal.getPrecio() + "€");

		}
	}

	private final class EscuchadorPrimerPlato implements ActionListener {
		private String nombrePlato;
		private JLabel labelPrecio;

		public EscuchadorPrimerPlato(String text, JLabel labelPrecio) {
			nombrePlato = text;
			this.labelPrecio = labelPrecio;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			labelPrecio.setVisible(true);
			platoPrimero = mapaPlatos.get(nombrePlato);

			labelPrecio.setText("PRICE: " + platoPrimero.getPrecio() + "€");

		}
	}

	private static final long serialVersionUID = 6793477893011238559L;

	public static void main(String[] args) {
		new VentanaRestaurante();
	}

}
