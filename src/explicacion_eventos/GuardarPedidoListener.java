package explicacion_eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GuardarPedidoListener implements ActionListener {

	private VentanaPedido owner;

	public GuardarPedidoListener(VentanaPedido ventanaPedido) {
		this.owner = ventanaPedido;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<ItemPedido> arr = owner.platosPedido();
		if(arr == null) {
			JOptionPane.showMessageDialog(null, "No se ha pedido nada");
		}else {
			try {
				PrintWriter pw = new PrintWriter("files/pedido.txt");
				
				for (ItemPedido itemPedido : arr) {
					pw.println(itemPedido.toString());
				}
				
				pw.close();
				
				JOptionPane.showMessageDialog(null, "Fichero creado");
				owner.unPedidoMas();
			} catch (FileNotFoundException e1) {
				System.out.println("No se ha podido encontrar el archivo");
			}	
		}
		
	}

}
