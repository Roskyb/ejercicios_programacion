import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Ventana_Ejemplo extends JFrame {

	private static final long serialVersionUID = -6670625111540513712L;


	public Ventana_Ejemplo() {
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		
		this.add(panelIzquierda());
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	private JPanel panelIzquierda() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,0));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		
		panel.add(new JTextArea(10,20));
		
		JPanel subPanel = new JPanel(new GridLayout(0,3, 10, 10));
		subPanel.add(new JButton("Boton 1"));
		subPanel.add(new JButton("Boton 2"));
		subPanel.add(new JButton("Boton 3"));
		
		panel.add(subPanel);
		
		JComboBox<String> combo = new JComboBox<String>();
		combo.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		panel.add(combo);
		
		JButton button = new JButton("otro boton");
		button.setHorizontalAlignment(SwingConstants.LEADING);
		
		panel.add(button);
		
		return panel;
	}
	
	
	public static void main(String[] args) {
		new Ventana_Ejemplo();
	}
	
	
}
