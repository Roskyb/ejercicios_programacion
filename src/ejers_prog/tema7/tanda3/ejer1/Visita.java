package ejers_prog.tema7.tanda3.ejer1;

import java.io.Serializable;

public class Visita implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7509715306878410692L;
	private String nombre;
	private int cantPersonas;
	HoraVisita horaVisita;
	
	public Visita(String nombre, int cantPersonas, int hora, int  minutos) {
		super();
		this.nombre = nombre;
		this.cantPersonas = cantPersonas;
		this.horaVisita = new HoraVisita(hora, minutos);
	}

	public String getNombre() {
		return nombre;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public HoraVisita getHoraVisita() {
		return horaVisita;
	}

	public void setHoraVisita(HoraVisita horaVisita) {
		this.horaVisita = horaVisita;
	}

	public int getCantPersonas() {
		return cantPersonas;
	}

	@Override
	public String toString() {
		String h = horaVisita.toString();
		return "Visita [nombre=" + nombre + ", cantPersonas=" + cantPersonas + ", horaVisita=" + h + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantPersonas;
		result = prime * result + ((horaVisita == null) ? 0 : horaVisita.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Visita other = (Visita) obj;
		if (cantPersonas != other.cantPersonas)
			return false;
		if (horaVisita == null) {
			if (other.horaVisita != null)
				return false;
		} else if (!horaVisita.equals(other.horaVisita))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
	
	
}
