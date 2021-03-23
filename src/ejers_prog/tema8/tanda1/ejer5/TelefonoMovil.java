package ejers_prog.tema8.tanda1.ejer5;

import java.io.Serializable;

public class TelefonoMovil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3270355021355242317L;
	private String numero;
	private double saldo;

	public TelefonoMovil(String numero, double saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}
	

	@Override
	public String toString() {
		return "TelefonoMovil [numero=" + numero + ", saldo=" + saldo + "]";
	}
}
