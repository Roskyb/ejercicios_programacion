package ejers_prog.tema10.tanda2.ejer1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GestionConsultas extends JFrame {

	private GestorXML gestor;
	private File f;
	private JList<HoraConsulta> listaHorarios;
	private static final HoraConsulta HORA_INICIO = new HoraConsulta(9, 10);
	private static final HoraConsulta HORA_FIN = new HoraConsulta(20, 45);
	private JComboBox<String> comboMedicos;
	private JTextField inputNombrePaciente;
	private JButton botonAniadir;

	public GestionConsultas() {

		this.f = new File("files/consultas.xml");
		this.gestor = new GestorXML(f);
		operacionAperturaXML();
		dibujar();
		eventos();

	}

	private void eventos() {
		botonAniadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<HoraConsulta> horaCitaSolicitadas = listaHorarios.getSelectedValuesList();
				String medico = (String) comboMedicos.getSelectedItem();
				String nomPaciente = inputNombrePaciente.getText();
				if(inputNombrePaciente.getText().equals("")) {
					inputNombrePaciente.requestFocus();
					return;
				};
				ArrayList<HoraConsulta> citasOcupadas = gestor.citasMedico(medico);
				String textoAsignadas = "CONSULTAS ASIGNADAS: " + nomPaciente + " - " + medico + "\n";
				String textoFallidas = "CONSULTAS NO ASIGNADAS: " + medico  + " YA TIENE ASIGNASDAS:" + "\n";
				boolean fallidas = false;
				boolean aniadidas = false;
				
				for (HoraConsulta horaConsulta : horaCitaSolicitadas) {
					if(!citasOcupadas.contains(horaConsulta)) {
						gestor.crearCitaEnMedico(medico, horaConsulta, nomPaciente);
						textoAsignadas += horaConsulta.toString() + "\n";
						aniadidas = true;
					}else {
						textoFallidas += horaConsulta.toString() + "\n";
						fallidas = true;
					}
				}
				
				if(fallidas) JOptionPane.showMessageDialog(null, textoFallidas, "Asignación errónea", JOptionPane.DEFAULT_OPTION);
				if(aniadidas)JOptionPane.showMessageDialog(null, textoAsignadas, "Asignación Correcta", JOptionPane.INFORMATION_MESSAGE);
				gestor.escribirXML();
				inputNombrePaciente.setText("");
				gestor.verXML();
			}


		});
	}

	private void dibujar() {
		this.setVisible(true);

		this.add(dibujarPanelOeste(), BorderLayout.WEST);
		this.add(dibujarPanelCentral());
		this.add(dibujarPanelSur(), BorderLayout.SOUTH);

		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JPanel dibujarPanelSur() {
		JPanel panelSur = new JPanel();
		botonAniadir = new JButton("AÑADIR");
		panelSur.add(botonAniadir);
		return panelSur;
	}

	private JPanel dibujarPanelCentral() {

		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// panel medico
		JPanel panelMedico = new JPanel();
		panelMedico.setLayout(new BoxLayout(panelMedico, BoxLayout.Y_AXIS));
		JLabel labelMedico = new JLabel("Elija medico");
		
		comboMedicos = new JComboBox<String>(gestor.nombresMedicos());
		comboMedicos.setPreferredSize(new Dimension(150, 30));
		panelMedico.add(labelMedico);
		panelMedico.add(comboMedicos);
		// panel Paciente

		JPanel panelPaciente = new JPanel();
		panelPaciente.setLayout(new BoxLayout(panelPaciente, BoxLayout.Y_AXIS));
		JLabel labelPaciente = new JLabel("Nombre paciente: ");
		inputNombrePaciente = new JTextField();
		inputNombrePaciente.setPreferredSize(new Dimension(100, 30));
		panelPaciente.add(labelPaciente);
		panelPaciente.add(inputNombrePaciente);

		panelCentral.add(panelMedico);
		panelCentral.add(panelPaciente);

		return panelCentral;
	}

	private JPanel dibujarPanelOeste() {
		JPanel panelOeste = new JPanel();
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
		JLabel titulo = new JLabel("Elija una o más horas");

		
		
		listaHorarios = new JList<HoraConsulta>(generarArrayHoraConsulta());
		JScrollPane scrollPanelHorarios = new JScrollPane(listaHorarios);
		scrollPanelHorarios.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanelHorarios.setPreferredSize(new Dimension(30, 100));

		panelOeste.add(titulo);
		panelOeste.add(scrollPanelHorarios);

		return panelOeste;
	}

	private HoraConsulta[] generarArrayHoraConsulta() {

		int totalHorasConsultas = (HORA_FIN.getHora() - HORA_INICIO.getHora()) * 2 + 2;

		HoraConsulta[] horarios = new HoraConsulta[totalHorasConsultas];

		
		horarios[0] = HORA_INICIO;
		horarios[totalHorasConsultas-1] = HORA_FIN;
		for (int i = 1; i < horarios.length-1; i++) {
			HoraConsulta consultaAnterior = horarios[i-1];
			if(consultaAnterior.getMinutos() + 30 >= 60) {
				horarios[i] = new HoraConsulta(consultaAnterior.getHora() + 1, + (consultaAnterior.getMinutos() +30) - 60);
			}else {
				horarios[i] = new HoraConsulta(consultaAnterior.getHora(), consultaAnterior.getMinutos() + 30);
			}
		}
		
		

		return horarios;
	}

	
	private void operacionAperturaXML() {
		if (this.f.exists()) {
			int res = JOptionPane.showConfirmDialog(null, "¿Deseas resetear el fichero de medicos", "Resetear",
					JOptionPane.YES_NO_OPTION);
			this.gestor.parsearXML();
			if (res == JOptionPane.YES_OPTION) {
				gestor.resetearMedicos();
				
			}
		} else {
			this.gestor.crearXMLconInfomacionBasica();
			this.gestor.parsearXML();
		}
	}

	public static void main(String[] args) {
		new GestionConsultas();
	}

}
