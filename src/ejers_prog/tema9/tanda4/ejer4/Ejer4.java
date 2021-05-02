package ejers_prog.tema9.tanda4.ejer4;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class Ejer4 extends JFrame{

	private final class BotonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton key = (JButton) e.getSource();
			
			switch (key.getText()) {
			case "VACIAR ALBUM":
				modeloListaImagenes.removeAllElements();
				botonVaciar.setEnabled(false);
				break;
			case "ELIMINAR":
				modeloListaImagenes.removeElementAt(listaImagenes.getSelectedIndex());
				botonEliminar.setEnabled(false);
				if(modeloListaImagenes.size() == 0) botonVaciar.setEnabled(false);
				break;
				
			case ">>>":
				Imagen img = panelOeste.getComboSelectedItem();
				
				if(img != null && !modeloListaImagenes.contains(img)) {
					modeloListaImagenes.addElement(img);
				}
				
				if(modeloListaImagenes.size() > 0) {
					botonVaciar.setEnabled(true);
				}
				break;
				
			default:
				break;
			}
			

		}
	}



	private JButton botonPasar;
	private JList<Imagen> listaImagenes;
	private JButton botonEliminar;
	private JButton botonVaciar;
	private PanelVisorImagenes panelOeste;
	private DefaultListModel<Imagen> modeloListaImagenes;



	public Ejer4() {
		this.setLayout(new BorderLayout());

		dibujar();
		eventos();
		pack();
		this.setVisible(true);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	private void eventos() {
		botonPasar.addActionListener(new BotonListener());
		
		listaImagenes.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				botonEliminar.setEnabled(true);
			}
		});
		
		botonVaciar.addActionListener(new BotonListener());
		
		botonEliminar.addActionListener(new BotonListener());
		
	}



	private void dibujar() {
		this.add(crearPanelOeste(), BorderLayout.WEST);
		this.add(crearPanelCentral());
		this.add(crearPanelEste(), BorderLayout.EAST);
	}



	private JPanel crearPanelEste() {
		JPanel panelEste = new JPanel(new GridLayout(2, 1));
		
		botonEliminar = new JButton("ELIMINAR");
		botonEliminar.setEnabled(false);
		botonVaciar = new JButton("VACIAR ALBUM");
		botonVaciar.setEnabled(false);
		
		
		panelEste.add(botonEliminar);
		panelEste.add(botonVaciar);
		
		
		return panelEste;
	}



	private JPanel crearPanelCentral() {
		JPanel panelCentral = new JPanel(new BorderLayout());
		botonPasar = new JButton(">>>");
		modeloListaImagenes = new DefaultListModel<Imagen>();
		listaImagenes = new JList<Imagen>(modeloListaImagenes);
		JScrollPane scrollLista = new JScrollPane(listaImagenes);
		scrollLista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollLista.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelCentral.add(scrollLista, BorderLayout.CENTER);
		
		panelCentral.add(botonPasar, BorderLayout.WEST);
		return panelCentral;
	}



	private PanelVisorImagenes crearPanelOeste() {
		panelOeste = new PanelVisorImagenes("img/lugares_famosos");
		return panelOeste;
	}



	public static void main(String[] args) {
		new Ejer4();
	}
	
}
