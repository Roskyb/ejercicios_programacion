package ejers_prog.tema9.tanda4.ejer3;

import java.io.File;

public class Imagen {

	private String nombreArchivo;
	private long tamanioBytes;
	private String extension;
	private String rutaArchivo;
	
	public Imagen(File archivoImagen) {
		this.rutaArchivo = archivoImagen.getAbsolutePath();
		this.nombreArchivo = archivoImagen.getName();
		this.tamanioBytes = archivoImagen.length();
		this.extension = this.nombreArchivo.substring(this.nombreArchivo.lastIndexOf(".") + 1);
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	@Override
	public String toString() {
		double kb = tamanioBytes / 1000;
		return this.nombreArchivo + " (" + kb + "Kb, " + this.tamanioBytes + "bytes.)";
	}


	public String getRutaArchivo() {
		return rutaArchivo;
	}


	public String getExtension() {
		return extension;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extension == null) ? 0 : extension.hashCode());
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
		Imagen other = (Imagen) obj;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		return !true;
	}
	

	
	
	
}
