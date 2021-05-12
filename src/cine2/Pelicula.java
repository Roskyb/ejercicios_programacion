package cine2;

public class Pelicula {
	private String titulo;
	private String genero;
	private int duracion; //duracion en segundos
	private int[] asientos;
	
	

	public Pelicula(String titulo, String genero, int duracion) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		
	}
	
	public Pelicula() {
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int[] getAsientos() {
		return asientos;
	}
	
	public void setAsientos(int[] asientos) {
		this.asientos = asientos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return titulo + " " + genero + " "+ duracionDesglosada() + ", " + (asientos != null ? asientos.length : 0) + " asientos vendidos";
	}
	
	
	private String duracionDesglosada() {
		
		int h=duracion/60/60;
		int m=(duracion - (h*60*60))/60;
		int s=duracion - (h*60*60) - (m*60);
		return  h + "h. "+ m + "m. " + s + ".seg ";
		
	}
	

}
