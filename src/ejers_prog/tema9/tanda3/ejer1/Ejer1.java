package ejers_prog.tema9.tanda3.ejer1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Ejer1 extends JFrame implements MouseListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3893275315846088987L;
	private JPanel panelOeste;
	private JComboBox<String> comboArchivos;
	private DefaultListModel<Punto> modeloPuntos;
	final File FICHTXT = new File("files/puntos/");
	private JList<Punto> listaPuntos;
	private JPanel panelCentral;
	private JLabel etiquetaVariable;


	private JTextField textFieldDistancia;
	
	
	public Ejer1() {
		
		this.setLayout(new BorderLayout());
		
		dibujarPanelOeste();
		dibujarPanelCentral();
		eventos();
		
		this.setSize(600, 300);
		this.setVisible(true);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private ArrayList<Punto> cargarModeloPuntos(String file) {

			File f = new File(FICHTXT.getAbsolutePath() + "\\" + file);
			modeloPuntos.removeAllElements();
			ArrayList<Punto> puntos = new ArrayList<Punto>();
			if(f.isFile()){
				
				try {
					BufferedReader bf = new BufferedReader(new FileReader(f));
					String line = bf.readLine();
					
					while(line != null) {
						String[] split = line.split(",");
						Punto p = new Punto(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
						puntos.add(p);
						line = bf.readLine();
					}
					bf.close();
					System.out.println();
					return puntos;
					
				} catch (FileNotFoundException e) {
					System.err.println("No se ha encontrado el archivo");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			return null;
	}


	private void dibujarPanelCentral() {
		
		panelCentral = new JPanel(new FlowLayout());
		
		etiquetaVariable = new JLabel("Enter Distance");
		textFieldDistancia = new JTextField(10);
		textFieldDistancia.setPreferredSize(new Dimension(100, 50));
		textFieldDistancia.setEnabled(false);
		
		panelCentral.add(etiquetaVariable);
		panelCentral.add(textFieldDistancia);
		
		this.add(panelCentral, BorderLayout.CENTER);
		
		
		
	}


	private void dibujarPanelOeste() {
		panelOeste = new JPanel(new GridLayout(3,1, 1, 1));


		comboArchivos = new JComboBox<String>(FICHTXT.list());
		comboArchivos.setPreferredSize(new Dimension(100, 30));
		
		modeloPuntos = new DefaultListModel<Punto>();
//		modeloPuntos.addAll(cargarModeloPuntos((String)comboArchivos.getSelectedItem()));
		
		listaPuntos = new JList<Punto>(modeloPuntos);
		listaPuntos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPuntos.setPreferredSize(new Dimension(100, 100));
		
		JPanel panelCombo = new JPanel();
		JPanel panelLista = new JPanel();
		panelCombo.add(comboArchivos);
		panelLista.add(listaPuntos);
		panelOeste.add(panelCombo,  BorderLayout.NORTH);
		panelOeste.add(panelLista, BorderLayout.CENTER);
		this.add(panelOeste, BorderLayout.WEST);

		
		
	}


	private void eventos() {
		
		comboArchivos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedFile = comboArchivos.getSelectedItem().toString();
				modeloPuntos.addAll(cargarModeloPuntos(selectedFile));
				
			}
		});
		listaPuntos.addMouseListener(this);
		textFieldDistancia.addActionListener(new IntroListener());
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			Punto puntoSeleccionado = listaPuntos.getSelectedValue();
			etiquetaVariable.setText("Dame distancia entre " + puntoSeleccionado.toString() + " al (0,0)");
			textFieldDistancia.setEnabled(true);
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	class IntroListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			double distanciaInput = Double.parseDouble(textFieldDistancia.getText());
			double distancia =  calcularDistanciDesdeCeroCero(listaPuntos.getSelectedValue());
			System.out.println(distancia);
			if(distanciaInput > (distancia-0.2) && distanciaInput < (distancia+0.2)) {
				textFieldDistancia.setEnabled(false);
				textFieldDistancia.setText("");
				etiquetaVariable.setText("Enter distance");
				modeloPuntos.removeElementAt(listaPuntos.getSelectedIndex());
			}else {
				JOptionPane.showConfirmDialog(null, "Distancia Erronea! Prueba Otra vez", "Mensaje",JOptionPane.DEFAULT_OPTION);	
			}
			
		}

		private double calcularDistanciDesdeCeroCero(Punto punto) {
			double y1 = punto.getY();
			double y2 = 0;
			double x1 = punto.getX();
			double x2 = 0;
			
			return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		}
		
	}


	public static void main(String[] args) {
		new Ejer1();
	}
	
}
