package ejers_prog.tema9.tanda4.ejer3;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ejers_prog.tema9.tanda4.ejer2.DialogTipoMayuscula;

public class Ejer3 extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6382710087292095027L;

	public Ejer3() {

		this.setLayout(new BorderLayout());

		PanelVisorImagenes panelVisor = new PanelVisorImagenes("img/lugares_famosos");
		this.add(panelVisor);
		pack();
		this.setVisible(true);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new Ejer3();
	}
	
}
