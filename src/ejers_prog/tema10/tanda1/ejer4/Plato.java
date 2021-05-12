package ejers_prog.tema10.tanda1.ejer4;

public class Plato {

	private String nombre;
	private double precio;
	private String descripcion;
	private Double calorias;
	
	
	public Plato(String nombre, double precio, String descripcion, Double calorias) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.calorias = calorias;
	}


	@Override
	public String toString() {
		return "Plato [nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + ", calorias="
				+ calorias + "]";
	}


	public Double getCalorias() {
		return calorias;
	}


	public String getNombre() {
		return nombre;
	}


	public double getPrecio() {
		return precio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calorias == null) ? 0 : calorias.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Plato other = (Plato) obj;
		if (calorias == null) {
			if (other.calorias != null)
				return false;
		} else if (!calorias.equals(other.calorias))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}
	
	
	
}
