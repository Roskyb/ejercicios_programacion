package ejer_libre;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DialogoBorrarDepartamento extends JDialog {

	private static final long serialVersionUID = 7133643737993997462L;
	private GestorConsultas gestor;
	private JTextField inputDepar;

	public DialogoBorrarDepartamento(Frame frame, GestorConsultas gestor) {
		super(frame);
		this.gestor = gestor;
		this.setLayout(new FlowLayout());
		
		dibujar();
		eventos();
		
		
		
		this.setModal(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void eventos() {
		inputDepar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					String nombreDepar = inputDepar.getText();
					if(gestor.buscarDepartamento(nombreDepar)) {
						int res = JOptionPane.showConfirmDialog(null, "Deseas borrar el departamento: " + nombreDepar);
						
						if(res == JOptionPane.OK_OPTION) {
							if(gestor.borrarDepartamento(nombreDepar)) {
								JOptionPane.showMessageDialog(null, "Departamento borrado exitosamente");
							};
							
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "No existe un departamento con el identificador: " + inputDepar.getText());
					};
					
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}

	private void dibujar() {
		JLabel labelDepar = new JLabel("Introduce el nombre del departamento que deseas borrar");
		inputDepar = new JTextField(10);
		
		this.add(labelDepar);
		this.add(inputDepar);
		
		this.pack();
	}
	
}
