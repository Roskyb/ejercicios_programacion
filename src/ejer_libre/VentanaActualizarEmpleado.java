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

public class VentanaActualizarEmpleado extends JDialog {


	private static final long serialVersionUID = 2041934391857833806L;
	private GestorConsultas gestor;
	private Empleado empleado;
	private JButton botonActualizar;
	private JTextField inputComision;
	private JTextField inputSalario;



	public VentanaActualizarEmpleado(Frame frame, GestorConsultas gestor, Empleado empleado) {
		super(frame);
		this.gestor = gestor;
		this.empleado = empleado;
		this.setLayout(new BorderLayout());
		
		dibujar();
		eventos();
		
		this.pack();
		this.setModal(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}



	private void eventos() {
		botonActualizar.addActionListener(e -> {
			boolean cambiarSalario = gestor.cambiarSalario(inputSalario.getText(), inputComision.getText(), empleado.getNumEmp());
			System.out.println(cambiarSalario);
			if(cambiarSalario) {
				
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Error al actualizar");
			};
		});
	}



	private void dibujar() {
		this.add(dibujarPanelNorte(), BorderLayout.NORTH);
		this.add(dibujarPanelCentral());
		this.add(dibujarPanelSur(), BorderLayout.SOUTH);
	}



	private Component dibujarPanelSur() {
		JPanel panelSur = new JPanel();
		
		botonActualizar = new JButton("Actualizar Datos");
		panelSur.add(botonActualizar);
		return panelSur;
	}



	private Component dibujarPanelCentral() {
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(2,2,10,10));
		

		
		JLabel salario = new JLabel("Salario");
		inputSalario = new JTextField(10);
		inputSalario.setText(empleado.getSalario() + "");
		
		panelCentral.add(salario);
		panelCentral.add(inputSalario);
		
		
		JLabel comision = new JLabel("Comision");
		inputComision = new JTextField(10);
		inputComision.setText(empleado.getComision() +"");
		
		panelCentral.add(comision);
		panelCentral.add(inputComision);
		
		return panelCentral;
	}



	private Component dibujarPanelNorte() {
		JPanel panelNorte = new JPanel();
		JLabel title = new JLabel("Datos del empleado: " + empleado.getNombre());
		title.setFont(new Font("Roboto", Font.BOLD, 35));
		panelNorte.add(title);
		return panelNorte;
	}
	
	

}
