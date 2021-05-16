package ejers_prog.tema10.tanda2.ejer3;

public class Plato {

	private String nombre;
	private double precio;
	private String rutaImagen;
	private String tipo;
	private static final String DEFAULT_IMAGE = "img/platos/kebab.png";
	
	
	public Plato(String nombre, double precio, String tipo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.rutaImagen = DEFAULT_IMAGE;
		this.tipo = tipo;
	}
	
	public Plato(String nombre, double precio, String rutaImagen, String tipo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.rutaImagen = rutaImagen;
		this.tipo = tipo;
	}
	
	

	@Override
	public String toString() {
		return "Plato [nombre=" + nombre + ", precio=" + precio + ", rutaImagen=" + rutaImagen + ", tipo=" + tipo + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public String getRutaImagen() {
		return  rutaImagen;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
}
