package ejers_prog.tema7.ejer_clase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class GestorConciertos {

	private HashMap<Concierto, Integer> ventasConciertos;

	public GestorConciertos(HashMap<Concierto, Integer> ventasConciertos) {
		super();
		this.ventasConciertos = ventasConciertos;
	}
	
	public void fichConciertosLlenos(String nomFich) throws IOException {
		
	
		
		Set<Concierto> it = ventasConciertos.keySet();
			
		for (Concierto concierto : it) {
			if(concierto.getAforo() == ventasConciertos.get(concierto)) {
				
			}
		}
		
		
	}
	
		
}
