package ejers_prog.tema7.tanda2.ejer3;

public class Persona {

	private String nombre;
	private int edad;
	private String telf;
	private String lugar;
	public Persona(String nombre, int edad, String telf, String lugar) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.telf = telf;
		this.lugar = lugar;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edad;
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telf == null) ? 0 : telf.hashCode());
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
		Persona other = (Persona) obj;
		if (edad != other.edad)
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telf == null) {
			if (other.telf != null)
				return false;
		} else if (!telf.equals(other.telf))
			return false;
		return true;
	}


	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public String getTelf() {
		return telf;
	}
	public String getLugar() {
		return lugar;
	}
	@Override
	public String toString() {
		return this.telf + "\t" + this.edad + "\t" + this.nombre + "\t" + this.lugar ;
	}
	
	
	
	
}
