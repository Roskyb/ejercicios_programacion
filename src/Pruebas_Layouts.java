import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Pruebas_Layouts extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817618845122144203L;

	public Pruebas_Layouts() {
		
		this.setLayout(new BorderLayout());
		
		dibujar();
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void dibujar() {
		this.add(dibujarPanelNorte(), BorderLayout.NORTH);
		this.add(dibujarPanelCentral());
	}

	private JPanel dibujarPanelCentral() {
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(2,2));
		for (int i = 0; i < 4; i++) {
			
			JPanel container = new JPanel();
			container.setBorder(BorderFactory.createTitledBorder("Container" + (i +1)));
			container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
			JLabel labelText = new JLabel("Input: ");
			labelText.setAlignmentY(CENTER_ALIGNMENT);
			JTextField textField = new JTextField(10);
		
			
			container.add(labelText);
			container.add(textField);
			container.setBackground(new Color(100 * i /3, 100 * i/3, 100* i/3));
			
			panelCentral.add(container);
			
		}

		
		return panelCentral;
	}

	private JPanel dibujarPanelNorte() {
		JPanel panelNorte = new  JPanel();
		panelNorte.setLayout(null);
		panelNorte.setPreferredSize(new Dimension(300, 100));
		panelNorte.setBackground(Color.pink);
		
		JLabel titulo = new JLabel("PRUEBA POSICION");
		titulo.setFont(new Font("ROBOTO", Font.BOLD, 35));
		titulo.setLocation(panelNorte.getPreferredSize().width /2,panelNorte.getPreferredSize().height /3 );
		titulo.setSize(titulo.getPreferredSize());
		panelNorte.add(titulo);
		
		return panelNorte;
	}

	public static void main(String[] args) {
		new Pruebas_Layouts();
	}
	
}
