package ejers_prog.tema9.tanda4.ejer4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelVisorImagenes extends JPanel {

	private static final long serialVersionUID = -2834763002624007513L;
	private JCheckBox[] checkBoxs;
	private JLabel labelEleccion;
	private JPanel panelCentral;
	private DefaultComboBoxModel<Imagen> modeloImagenes;
	private File directorio;
	private JComboBox<Imagen> comboDatosImagen;
	private JLabel labelImagen;

	public PanelVisorImagenes(String directorioImagenes) {
		this.directorio = new File(directorioImagenes);
		dibujarPanel();
		eventos();

	}

	private void eventos() {
		comboDatosImagen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Imagen img = (Imagen) modeloImagenes.getSelectedItem();
				if (!(img == null)) {
					panelCentral.remove(labelEleccion);
					if (labelImagen != null) {
						panelCentral.remove(labelImagen);
					}

					labelImagen = new JLabel(redim(img.getRutaArchivo(), 100, 200));
					panelCentral.add(labelImagen);
					repaint();
					revalidate();
				}

			}

			private ImageIcon redim(String fichImag, int ancho, int alto) {
				ImageIcon imIcon = new ImageIcon(fichImag);
				Image im = imIcon.getImage();
				Image im2 = im.getScaledInstance(ancho, alto, 0);
				return new ImageIcon(im2);
			}
		});

		for (JCheckBox check : checkBoxs) {
			check.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JCheckBox check = (JCheckBox) e.getSource();
					String extension = check.getText();
					System.out.println("dd");
					if (check.isSelected()) {
						ArrayList<Imagen> imagenes = imagenseDeExtension(extension);
						modeloImagenes.addAll(imagenes);
					} else {
						ArrayList<Imagen> imagenes = imagenseDeExtension(extension);
						for (Imagen imagen : imagenes) {
							modeloImagenes.removeElement(imagen);
						}
					}

				}
			});
		}

	}

	private ArrayList<Imagen> imagenseDeExtension(String extension) {
		ArrayList<Imagen> imagenes = todasImagenes(this.directorio);
		for (Iterator<Imagen> iterator = imagenes.iterator(); iterator.hasNext();) {
			Imagen imagen = iterator.next();
			if (!imagen.getExtension().equals(extension)) {
				iterator.remove();
			} else {
			}
		}
		return imagenes;
	}

	private ArrayList<Imagen> todasImagenes(File dir) {

		ArrayList<Imagen> imagenes = new ArrayList<Imagen>();

		if (dir.isDirectory()) {

			for (String nombre : dir.list()) {
				String rutaCompleta = dir.getAbsolutePath() + "\\" + nombre;
				imagenes.add(new Imagen(new File(rutaCompleta)));
			}

		}

		return imagenes;

	}
	
	public Imagen getComboSelectedItem() {
		return (Imagen) comboDatosImagen.getSelectedItem(); 
	}

	private void dibujarPanel() {
		this.setLayout(new BorderLayout());

		this.add(dibujarPanelNorte(), BorderLayout.NORTH);
		this.add(dibujarPanelCentra(), BorderLayout.CENTER);
	}

	private JPanel dibujarPanelCentra() {

		panelCentral = new JPanel();
		panelCentral.setPreferredSize(new Dimension(100, 220));

		labelEleccion = new JLabel("Elige una imagen en el combo");

		panelCentral.add(labelEleccion);

		return panelCentral;
	}

	private JPanel dibujarPanelNorte() {
		JPanel panelNorte = new JPanel(new GridLayout(2, 1));
		panelNorte.add(crearPanelCheckBox());

		modeloImagenes = new DefaultComboBoxModel<Imagen>();
		modeloImagenes.addAll(todasImagenes(this.directorio));

		comboDatosImagen = new JComboBox<Imagen>(modeloImagenes);

		panelNorte.add(comboDatosImagen);

		return panelNorte;
	}

	private JPanel crearPanelCheckBox() {
		JPanel panelCheckBox = new JPanel();
		HashSet<String> extensiones = extensiones();
		checkBoxs = new JCheckBox[extensiones.size()];

		int i = 0;
		for (String ext : extensiones) {
			checkBoxs[i] = new JCheckBox(ext);
			checkBoxs[i].setSelected(true);

			panelCheckBox.add(checkBoxs[i]);
			i++;
		}

		return panelCheckBox;
	}

	private HashSet<String> extensiones() {
		HashSet<String> extensiones = new HashSet<String>();

		for (Imagen img : todasImagenes(this.directorio)) {
			extensiones.add(img.getExtension());
		}

		return extensiones;

	}

}
