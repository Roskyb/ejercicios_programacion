package ejers_prog.tema9.tanda4.ejer4;

import java.io.File;
import java.io.Serializable;

public class Imagen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4819562631240894923L;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extension == null) ? 0 : extension.hashCode());
		result = prime * result + ((nombreArchivo == null) ? 0 : nombreArchivo.hashCode());
		result = prime * result + ((rutaArchivo == null) ? 0 : rutaArchivo.hashCode());
		result = prime * result + (int) (tamanioBytes ^ (tamanioBytes >>> 32));
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
		if (nombreArchivo == null) {
			if (other.nombreArchivo != null)
				return false;
		} else if (!nombreArchivo.equals(other.nombreArchivo))
			return false;
		if (rutaArchivo == null) {
			if (other.rutaArchivo != null)
				return false;
		} else if (!rutaArchivo.equals(other.rutaArchivo))
			return false;
		if (tamanioBytes != other.tamanioBytes)
			return false;
		return true;
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




	
	
	
}
