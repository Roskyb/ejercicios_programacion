package ejers_prog.tema9.tanda4.ejer3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.DirectoryNotEmptyException;
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
					System.out.println(img.equals(modeloImagenes.getElementAt(2)));
					labelImagen.setText("");
					labelImagen.setIcon(redim(img.getRutaArchivo(), 100, 200));
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

					if (check.isSelected()) {
						ArrayList<Imagen> imagenes = imagenseDeExtension(extension);
						modeloImagenes.addAll(imagenes);
					} else {
						ArrayList<Imagen> imagenes = imagenseDeExtension(extension);
						for (Imagen imagen : imagenes) {
							modeloImagenes.removeElement(imagen);
						}
						
						
						Imagen img = (Imagen) comboDatosImagen.getSelectedItem();
						if(img != null) {
							if(img.getExtension().equals(check.getText())) {
								labelImagen.setIcon(null);
								labelImagen.setText("Elige una imagen en el combo");
								comboDatosImagen.setSelectedItem(null);
							}							
						}else {
							labelImagen.setIcon(null);
							labelImagen.setText("Elige una imagen en el combo");
							comboDatosImagen.setSelectedItem(null);
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

	private void dibujarPanel() {
		this.setLayout(new BorderLayout());

		this.add(dibujarPanelNorte(), BorderLayout.NORTH);
		this.add(dibujarPanelCentral(), BorderLayout.CENTER);
	}

	private JLabel dibujarPanelCentral() {

		labelImagen = new JLabel("Elige una imagen en el combo");
		labelImagen.setPreferredSize(new Dimension( 100 ,150));
		return labelImagen;
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
