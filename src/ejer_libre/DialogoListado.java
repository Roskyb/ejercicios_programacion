package ejer_libre;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class DialogoListado extends JDialog {

	private static final long serialVersionUID = 2001658270747444727L;

	private final class EscuchadorSelecionadoLista extends MouseAdapter {
		private Frame frame;

		public EscuchadorSelecionadoLista(Frame frame) {
			this.frame = frame;
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				new VentanaActualizarEmpleado(this.frame, gestor, listaEmpleados.getSelectedValue());
				reCargarLista();
				
			}
		}
	}

	private GestorConsultas gestor;
	private JList<Empleado> listaEmpleados;
	private Frame frame;
	private DefaultListModel<Empleado> modelo;

	public DialogoListado(Frame frame, GestorConsultas gc) {
		super(frame);
		this.frame = frame;
		this.gestor = gc;
		this.setLayout(new BorderLayout());
		
		dibujar();
		eventos();
		
		
		this.setModal(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void reCargarLista() {
		modelo.removeAllElements();
		modelo.addAll(gestor.getEmpleados());
	}

	private void eventos() {
		listaEmpleados.addMouseListener(new EscuchadorSelecionadoLista(frame));
		
	}

	private void dibujar() {
		JLabel title = new JLabel("EMPLEADOS");
		title.setFont(new Font("Roboto", Font.BOLD, 35));
		this.add(title, BorderLayout.NORTH);

		
		JScrollPane scrollLista = cargarLista();
		this.add(scrollLista);
		this.pack();

	}

	private JScrollPane cargarLista() {
		modelo = new DefaultListModel<Empleado>();
		
		modelo.addAll(gestor.getEmpleados());
		listaEmpleados = new JList<Empleado>(modelo);
		listaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollLista = new JScrollPane(listaEmpleados);
		return scrollLista;
	}
	
}
