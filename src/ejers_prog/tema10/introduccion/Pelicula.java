package ejers_prog.tema10.introduccion;

public class Pelicula {

		private String titulo;
		private String genero;
		private int duracion;
		
		public Pelicula(String titulo, String genero, int duracion) {
			super();
			this.titulo = titulo;
			this.genero = genero;
			this.duracion = duracion;
		}

		public Pelicula() {
			super();
		}
	
		private String duracionDesglosada() {
			
			int h = duracion / 3600;
			int m = ( duracion % 3600) / 60;
			int s = ( duracion % 3600) % 60;
			
			return h + "h " + m + " m " + s + " segs";
			
		}
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return titulo + " " + genero +  " " + this.duracionDesglosada();
		}

		public String getTitulo() {
			return titulo;
		}

		public int getDuracion() {
			return duracion;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}
		
}
