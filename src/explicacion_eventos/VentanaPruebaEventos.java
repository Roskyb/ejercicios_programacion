package explicacion_eventos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaPruebaEventos extends JFrame implements ActionListener, KeyListener{

	private JTextField txt;
	private JButton boton;
	private JComboBox<?> combo;
	private JList lista;
	private DefaultListModel<String> modeloLista;
	
	public VentanaPruebaEventos() {
		
		dibujar();
		
		
		eventos();
		
		
		setSize(200, 400);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void eventos() {
		
		boton.addActionListener(this);
		txt.addKeyListener(this);
		txt.addActionListener(new TxTIntroListener());
		combo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String comboSelectedItem = combo.getSelectedItem().toString();
				txt.setText(comboSelectedItem);
				modeloLista.addElement(comboSelectedItem);
			}
		} );
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				modeloLista.removeAllElements();
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
	class TxTIntroListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			txt.setBackground(Color.RED);
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Boton pulsado");
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 'a') JOptionPane.showMessageDialog(null, "Has escrito una a");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void dibujar() {
		this.setLayout(new FlowLayout());
		this.add(txt=new JTextField(10));
		this.add(boton=new JButton("Aceptar"));
		this.add(combo = new JComboBox(new String[]	{"Uno", "Dos", "Tres"}));
		modeloLista = new DefaultListModel<String>();
		lista = new JList<String>(modeloLista);
		lista.setPreferredSize(new Dimension(100, 100));
		this.add(lista);
	}
	
	
	public static void main(String[] args) {
//		String strInput = JOptionPane.showInputDialog("Dame tu nombre");
//		if(strInput.equals("admin"))
			new VentanaPruebaEventos();
	}




	
}
