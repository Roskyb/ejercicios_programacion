package ejer_libre;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoAniadirDepartamento extends JDialog{

	private static final long serialVersionUID = 1945617867712480527L;
	private GestorConsultas gestor;
	private JTextField inputNombre;
	private JTextField inputLocalidad;
	private JButton botonAniadir;

	public DialogoAniadirDepartamento(Frame frame, GestorConsultas gc) {
		super(frame);
		this.gestor = gc;
		
		this.setLayout(new BorderLayout());
		
		dibujar();
		eventos();
		
		
		
		this.setModal(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void eventos() {
		botonAniadir.addActionListener(e -> {
			if(gestor.anidirDepartamento(inputNombre.getText(), inputLocalidad.getText())) {
				JOptionPane.showMessageDialog(null, "Departamento añadido correctamente");
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Fallo al añadir el departamento");
			};
		});
	}

	private void dibujar() {
		JLabel title = new JLabel("EMPLEADOS");
		title.setFont(new Font("Roboto", Font.BOLD, 20));
		JPanel panelNorte = new JPanel();
		panelNorte.add(title);
		this.add(panelNorte, BorderLayout.NORTH);
		
		this.add(dibujarPanelFormulario());
		botonAniadir = new JButton("Añadir");
		this.add(botonAniadir, BorderLayout.SOUTH);
		this.pack();
	}

	private Component dibujarPanelFormulario() {
		JPanel panelCentral = new JPanel(new GridLayout(3,2));
		panelCentral.add(new JLabel("Nombre"));
		inputNombre = new JTextField(10);
		panelCentral.add(inputNombre);
		panelCentral.add(new JLabel("Localidad"));
		inputLocalidad = new JTextField(10);
		panelCentral.add(inputLocalidad);
		return panelCentral;
	}

}

