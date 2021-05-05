package ejers_prog.tema9.tanda4.ejer2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class DialogTipoMayuscula extends JDialog implements ActionListener{



	/**
	 * 
	 */
	private static final long serialVersionUID = -2907918231196460573L;
	private JRadioButton[] opciones;
	private JPanel panel;
	private ButtonGroup bg;
	private JTextArea panelMayusculas; 

	public DialogTipoMayuscula(JTextArea panelMayusculas) {
		this.dibujarPanel();
		this.add(panel);
		this.pack();
		this.setVisible(false);
		this.panelMayusculas = panelMayusculas;
	}

	private void eventos() {
		for (JRadioButton jRadioButton : opciones) {
			jRadioButton.addActionListener(this);
			
		}
	}

	private void dibujarPanel() {

		panel = new JPanel();
		getPanel().setLayout(new BoxLayout(getPanel(), BoxLayout.Y_AXIS));
		Border title = BorderFactory.createTitledBorder("Elija el tipo de aliniamiento");
		
		getPanel().setBorder(title);
		
		opciones = new JRadioButton[] {
				new JRadioButton("CAMBIAR A MAYUSCULAS"),
				new JRadioButton("Cambiar a mayusculas"), 
				new JRadioButton("Cambiar A Mayusculas")
		};
		
		
		bg = new ButtonGroup();
		opciones[0].setSelected(true);
		
		for (JRadioButton jRadioButton : opciones) {
			bg.add(jRadioButton);
			getPanel().add(jRadioButton);
		}
		

		
		eventos();
			
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton selectedRadios = (JRadioButton) e.getSource();
		String texto = panelMayusculas.getText();
		
		switch (selectedRadios.getText()) {
		case "CAMBIAR A MAYUSCULAS":
			panelMayusculas.setText(texto.toUpperCase());
			break;
			
		case "Cambiar a mayusculas":
			
			String[] frases = texto.split("\\. ");
			System.out.println(frases.length);
			
			texto = "";
			for (int i = 0; i < frases.length; i++) {
				String frase = frases[i];
				texto += frase.substring(0, 1).toUpperCase() + frase.substring(1) + "";
				if(!frase.endsWith(".")) {
					if(i < frases.length - 1) texto += ". ";
					else texto+= ".";					
				}
				
			}

			panelMayusculas.setText(texto);
			
			break;
			
		case "Cambiar A Mayusculas":
			
			String[] palabras = texto.split("\\s");
			String nuevoTexto = "";
			
			for (String string : palabras) {
				nuevoTexto += string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
			}
			
			panelMayusculas.setText(nuevoTexto);
			break;

		}
		
		this.setVisible(false);
	}
	
	
}
