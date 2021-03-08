package ejers_prog.tema7.tanda1.ejer3;

import java.io.Serializable;

public class TelefonoMovil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6349962043417122245L;
	/**
	 * 
	 */
	
	private String numero;
	private double saldo;
	
	
	
	public TelefonoMovil(String numero, double saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}

	public void ver() {
		System.out.println("Numero: " + numero + " Saldo:" + saldo);
	}
	
	public void cargar(int s) {
		saldo+=s;
	}
	
	public void llamar(int minutos) {
		saldo -= minutos * 2;
	}

	
	@Override
	public String toString() {
		return "TelefonoMovil [numero=" + numero + ", saldo=" + saldo + "]";
	}
	
}
