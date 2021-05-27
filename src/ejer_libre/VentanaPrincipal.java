package ejer_libre;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {


	private static final long serialVersionUID = 1460733405084413412L;
	private JButton botonListar;
	private JButton botonBorrarDepartamento;
	private JButton botonAniadirDepartamento;
	private JButton botonGenerarXML;

	public VentanaPrincipal() {
		
		
		dibujar();
		eventos();
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void eventos() {
		GestorConsultas gc = new GestorConsultas();
		botonListar.addActionListener(e -> {
			new DialogoListado(this, gc);
		});
		
		botonBorrarDepartamento.addActionListener(e -> {
			new DialogoBorrarDepartamento(this, gc);
		});
		
		botonAniadirDepartamento.addActionListener(e -> {
			new DialogoAniadirDepartamento(this, gc);
		});
		
		botonGenerarXML.addActionListener(e -> {
			String localidad = JOptionPane.showInputDialog(null, "Introduce el nombre de la localidad de la que quieres generar el XML");
			GestorXML gestorXML = new GestorXML(localidad+"_departamentos");
			ArrayList<String> departamentos = gc.getDepartamentos(localidad);
			for (String depar : departamentos) {
				gestorXML.aniadirDepartamento(depar, localidad);				
			}
			gestorXML.guardarDocumento();
			
		});
		
	}

	private void dibujar() {
		this.add(dibujarPanelNorte(), BorderLayout.NORTH);
		this.add(dibujarPanelCentral());
			
	}

	private Component dibujarPanelNorte() {
		JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel titulo = new JLabel("GESTION DE EMPLEADOS");
		titulo.setFont(new Font("Roboto", Font.BOLD, 35));
		panelNorte.add(titulo);
		return panelNorte;
	}

	private Component dibujarPanelCentral() {
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(3, 1, 5, 5));
		
		botonListar = new JButton("Listar empleados y editar");
		botonBorrarDepartamento = new JButton("Borrar departamento");
		botonAniadirDepartamento = new JButton("Añadir departamento");
		botonGenerarXML = new JButton("Generar XML Localidades");
		
		panelCentral.add(botonListar);
		panelCentral.add(botonAniadirDepartamento);
		panelCentral.add(botonBorrarDepartamento);
		panelCentral.add(botonGenerarXML);
		
		
		return panelCentral;
	}

	public static void main(String[] args) {
		new VentanaPrincipal();
	}
	
}


