package explicacion_eventos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaPedido extends JFrame implements WindowListener {

	private JComboBox<String> comboTipos;
	private JList<String> listaPlatos;
	private JButton botonAniadir;
	private JButton botonGuardar;
	private DefaultListModel<String> modeloPlatos;
	private DefaultListModel<ItemPedido> modeloPedido;
	private JList<ItemPedido> listaPedido;
	private int cantPedidos;
	
	public VentanaPedido() {
		cantPedidos = 0;
		dibujar();
		eventos();
		
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void eventos() {
		// TODO Auto-generated method stub
		comboTipos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] platos = mapaMenu().get(comboTipos.getSelectedItem().toString());
				modeloPlatos.removeAllElements();
				for (String string : platos) {
					modeloPlatos.addElement(string);
				}
			}
		});
		
		botonAniadir.addActionListener(new AniadirPlatoListener());
		botonGuardar.addActionListener(new GuardarPedidoListener(this));
		this.addWindowListener(this);
		
		
	}
	
	class AniadirPlatoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String platoSeleccionado = listaPlatos.getSelectedValue();
			if(platoSeleccionado == null) {
				JOptionPane.showMessageDialog(null, "Selecciona un plato!");
			}else {
				int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cuantas unidades deseas?"));
				ItemPedido item = new ItemPedido(platoSeleccionado, cantidad);
				
				if(!modeloPedido.contains(item)) {
					modeloPedido.addElement(item);
				}else {
					int res = JOptionPane.showConfirmDialog(null, "Ya has pedido eso, deseas añadir " +  cantidad + " unidades más?");
					if(res == JOptionPane.YES_OPTION) {
						int iAntiguo = modeloPedido.indexOf(item);
						ItemPedido itemViejo = modeloPedido.getElementAt(iAntiguo);
						itemViejo.setCantidad(itemViejo.getCantidad() + item.getCantidad());
						repaint();revalidate();
					}
				}
				
				
				
				
			}
			
		}
		
	}
	
	public void unPedidoMas() {
		this.cantPedidos++;
	}

	private void dibujar() {
		// Defecto Border Layout
		
		// norte combo con tipos de platos
		
		String[] tipos = new String[mapaMenu().size()];
		int i = 0;
		for (String string : mapaMenu().keySet()) {
			tipos[i] = string;
			i++;
		}
		
		comboTipos = new JComboBox<String>(tipos);
		this.add(comboTipos, BorderLayout.NORTH);
		
		// zona centro lista + boton + lista
		
		JPanel panelCentral = new JPanel(new GridLayout(1,3));
		modeloPlatos = new DefaultListModel<String>();
		modeloPedido = new DefaultListModel<ItemPedido>();
		
		listaPlatos = new JList<String>(modeloPlatos);
		
		botonAniadir = new JButton(">>");
		listaPedido = new JList<ItemPedido>(modeloPedido);
		
		
		panelCentral.add(listaPlatos);
		panelCentral.add(botonAniadir);
		panelCentral.add(listaPedido);
		
		this.add(panelCentral, BorderLayout.CENTER);
		
		// SUR 
		botonGuardar = new JButton("GUARDAR");
		this.add(botonGuardar, BorderLayout.SOUTH);
		
		
		
		
	}
	
	public ArrayList<ItemPedido> platosPedido(){
		
		if(modeloPedido.size() == 0) {
			return null;
		}
		
		ArrayList<ItemPedido> arr = new ArrayList<ItemPedido>();
		
		for (int i = 0; i < modeloPedido.size(); i++) {
			arr.add(modeloPedido.getElementAt(i));
			
		}
		
		return arr;
	}
	
	private LinkedHashMap<String, String[]> mapaMenu(){
		
		LinkedHashMap<String, String[]> mapa = new LinkedHashMap<String, String[]>();
		
		mapa.put("Primero", new String[] {"Sopa" , "Gaspacho", "Pasta"});
		mapa.put("Segundo", new String[] {"Menestra" , "Bocata de mandarinas", "Pizza"});
		mapa.put("Postre", new String[] {"Lentejas" , "Solomillo", "Pito de ave"});
		
		return mapa;
	}

	public static void main(String[] args) {
		new VentanaPedido();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Gracias por tus " + cantPedidos + " pedidos");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
