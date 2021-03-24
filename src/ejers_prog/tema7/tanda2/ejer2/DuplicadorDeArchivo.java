package ejers_prog.tema7.tanda2.ejer2;

import java.io.IOException;

abstract public class DuplicadorDeArchivo {

	private String nomRuta;
	private final String RUTA_DEFAULT = "files/lorem.txt";
	
	
	public DuplicadorDeArchivo() {
		super();
		this.nomRuta = RUTA_DEFAULT;
	}
	
	public DuplicadorDeArchivo(String nomRuta) {
		super();
		this.nomRuta = nomRuta;
	}
	
	public boolean duplicar(String rutaDestino) throws IOException {
		return false;
		
	}

	public String getNomRuta() {
		return nomRuta;
	}
	
	
	
}
