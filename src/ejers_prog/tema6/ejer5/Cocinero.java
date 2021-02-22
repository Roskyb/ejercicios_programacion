package ejers_prog.tema6.ejer5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cocinero {

	private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	
	private Map<String, Plato> platos = new HashMap<String, Plato>();
	
	
	public void aniadirIngrediente(Ingrediente ing) {
		
		boolean repetido = false;
		
		for (Ingrediente ingrediente : ingredientes) {
			if(ingrediente.getNombre().equals(ing.getNombre())){
				System.out.println("Ya existe un ingrediente con ese nombre, deseas cambiar el valor calorico?");
				char r = Consola.leeChar();
				
				if(r == 'S' || r == 's') {
					ingrediente.setCalorias(ing.getCalorias());
				}
				
				repetido = true;
				break;
			}
		}
		
		if(!repetido) {
			ingredientes.add(ing);
		}
		
		
	}
	
	
	public void aniadirIngrediente(Ingrediente[] ings) {
		for (Ingrediente ingrediente : ings) {
			aniadirIngrediente(ingrediente);
		}
	}
	
	public boolean aniadirPlato(Plato plato) {
		
		if(platos.get(plato.getNombre()) == null) {
			System.out.println("No se ha podido añadir ya que el platos esta repetido!");
			return false;
		} else {
			platos.put(plato.getNombre(), plato);
			System.out.println("El plato se a añdido correctamente");
			return true;
		}
		
	}
	
	
	
}
