package ejers_prog.tema9.tanda4.ejer1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaEcuacion extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1863845854114670752L;
	private JTextField input1;
	private JTextField input2;
	private JTextField input3;
	private JButton botonResolver;
	private JLabel solucion1;
	private JLabel solucion2;
	private final Font DEFAULT_FONT = new Font("Apple Casual", Font.BOLD, 15);

	public VentanaEcuacion() {
		this.setLayout(new BorderLayout());

		dibujar();
		eventos();
		this.setSize(600, 150);
		this.setVisible(true);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void eventos() {
		botonResolver.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String a = input1.getText();
		String b = input2.getText();
		String c = input3.getText();
		
		int error = -1;
		if (esNumero(a) && esNumero(b) && esNumero(c)) {
			try {
				SolucionCuadratica soluciones = solucion(Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c));
				
				solucion1.setText(soluciones.getS1() + "");
				solucion2.setText(soluciones.getS2() + "");
				
			} catch (RaizExcepcion e1) {
				System.err.println(e1.getMessage());
				
				error = JOptionPane.showConfirmDialog(null, e1.getMessage(), "Mensaje",
						JOptionPane.DEFAULT_OPTION);
			}
			
		} else {
			error = JOptionPane.showConfirmDialog(null, "Los coeficientes deben ser numéricos!", "Mensaje",
					JOptionPane.DEFAULT_OPTION);
		}
		
		if(error == 0) limpiarInputs();
	}
	


	private void limpiarInputs() {
		input1.setText("");
		input2.setText("");
		input3.setText("");
		solucion1.setText("                ");
		solucion2.setText("                ");
		
	}

	private SolucionCuadratica solucion(int a , int b, int c) throws RaizExcepcion {
		
		if(a == 0) throw new RaizExcepcion("Solución no cuadratica");
		if((4*a*c) < 0) throw new RaizExcepcion("Solución imaginaria");
		
        double x1 = (-b + Math.sqrt((b*b)-(4*a*c)))/(2*a);
        double x2 = (-b - Math.sqrt((b*b)-(4*a*c)))/(2*a);
        
        return new SolucionCuadratica(x1, x2);
		
	}

	private boolean esNumero(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Input String cannot be parsed to Integer.");
		}
		return false;
	}

	private void dibujar() {

		dibujarPanelNorte();
		dibujarPanelCentral();
		dibujarPanelSur();

	}

	private void dibujarPanelSur() {
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout());
		JLabel labelSolucion1 = new JLabel("Solución 1: ");
		JLabel labelSolucion2 = new JLabel("Solución 2: ");

		labelSolucion1.setFont(DEFAULT_FONT);
		labelSolucion2.setFont(DEFAULT_FONT);

		solucion1 = new JLabel("                ");
		solucion1.setBackground(Color.YELLOW);
		solucion1.setOpaque(true);
		solucion2 = new JLabel("                ");
		solucion2.setBackground(Color.YELLOW);
		solucion2.setOpaque(true);

		solucion1.setFont(DEFAULT_FONT);
		solucion2.setFont(DEFAULT_FONT);

		panelSur.add(labelSolucion1);
		panelSur.add(solucion1);
		panelSur.add(labelSolucion2);
		panelSur.add(solucion2);

		this.add(panelSur, BorderLayout.SOUTH);

	}

	private void dibujarPanelCentral() {
		botonResolver = new JButton("RESOLVER");
		botonResolver.setFont(DEFAULT_FONT);
		JPanel panelCentral = new JPanel();
		panelCentral.add(botonResolver);
		this.add(panelCentral);
	}

	private void dibujarPanelNorte() {

		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout());

		input1 = new JTextField(10);
		JLabel label1 = new JLabel("x2 + ");
		label1.setFont(DEFAULT_FONT);

		input2 = new JTextField(10);
		JLabel label2 = new JLabel("x + ");
		label2.setFont(DEFAULT_FONT);

		input3 = new JTextField(10);
		JLabel label3 = new JLabel(" = 0 ");
		label3.setFont(DEFAULT_FONT);

		panelNorte.add(input1);
		panelNorte.add(label1);
		panelNorte.add(input2);
		panelNorte.add(label2);
		panelNorte.add(input3);
		panelNorte.add(label3);

		this.add(panelNorte, BorderLayout.NORTH);

	}

	public static void main(String[] args) {
		new VentanaEcuacion();
	}

}
