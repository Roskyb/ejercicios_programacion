package ejers_prog.tema9.tanda4.ejer4;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Ejer4 extends JFrame {

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
				if (modeloListaImagenes.size() == 0)
					botonVaciar.setEnabled(false);
				break;

			case ">>>":
				Imagen img = panelOeste.getComboSelectedItem();

				if (img != null && !modeloListaImagenes.contains(img)) {
					modeloListaImagenes.addElement(img);
				}

				if (modeloListaImagenes.size() > 0) {
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
				botonEliminar.setEnabled(true);
			}
		});

		botonVaciar.addActionListener(new BotonListener());

		botonEliminar.addActionListener(new BotonListener());

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				String nombre = "files/albumnes/" + JOptionPane.showInputDialog("Introduce tu nombre: ") + "_album.obj";
				File f = new File(nombre);
				if (f.exists()) {
					try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

						Imagen img = (Imagen) ois.readObject();
						while (img != null) {
							System.out.println("jheljfdsa");
							modeloListaImagenes.addElement(img);
							img = (Imagen) ois.readObject();
						}

						ois.close();

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				if (modeloListaImagenes.getSize() > 0) {
					String nombre = JOptionPane.showInputDialog("Si quieres guardar el album introduce tu nombre");
					File f = new File("files/albumnes/" + nombre + "_album.obj");
					if (f.exists()) {
						int res = JOptionPane.showConfirmDialog(null, "El album ya existe, quieres sobreescribirlo?");
						if (res == 0) {
							crearArchivoAlbum(f);
						}
					} else {
						crearArchivoAlbum(f);
					}

				}

			}

			private void crearArchivoAlbum(File f) {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

					for (int i = 0; i < modeloListaImagenes.size(); i++) {
						oos.writeObject(modeloListaImagenes.getElementAt(i));
					}

					oos.writeObject(null);
					oos.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
