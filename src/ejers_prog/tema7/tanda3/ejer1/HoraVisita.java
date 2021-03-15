package ejers_prog.tema7.tanda3.ejer1;

import java.io.Serializable;

public class HoraVisita implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516643828798515347L;


	@Override
	public String toString() {
		return "HoraVisita [hora=" + hora + ", minutos=" + minutos + "]";
	}


	private int hora;
	private int minutos;
	
	
	public HoraVisita(int hora, int minutos) {
		super();
		this.hora = hora;
		this.minutos = minutos;
	}


	public int getHora() {
		return hora;
	}


	public int getMinutos() {
		return minutos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hora;
		result = prime * result + minutos;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoraVisita other = (HoraVisita) obj;
		if (hora != other.hora)
			return false;
		if (minutos != other.minutos)
			return false;
		return true;
	}
	
	
	
	
}
