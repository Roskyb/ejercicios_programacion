package ejers_prog.tema6.ejer5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Cocinero {

	private ArrayList<Ingrediente> ingredientes;

	private Map<String, Plato> platos;
	
	
	
	

	public Cocinero() {
		super();
		this.ingredientes = new ArrayList<Ingrediente>() ;
		this.platos = new HashMap<String, Plato>();
	}

	public void aniadirIngrediente(Ingrediente ing) {

		boolean repetido = false;

		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getNombre().equals(ing.getNombre())) {
				System.out.println("Ya existe un ingrediente con ese nombre, deseas cambiar el valor calorico?");
				char r = Consola.leeChar();

				if (r == 'S' || r == 's') {
					ingrediente.setCalorias(ing.getCalorias());
				}

				repetido = true;
				break;
			}
		}

		if (!repetido) {
			ingredientes.add(ing);
		}

	}

	public void aniadirIngrediente(Ingrediente[] ings) {
		for (Ingrediente ingrediente : ings) {
			aniadirIngrediente(ingrediente);
		}
	}

	public void aniadirPlato(Plato plato) {

		if (platos.get(plato.getNombre()) != null) {
			System.out.println("No se ha podido añadir ya que el platos esta repetido!");

		} else {
			platos.put(plato.getNombre(), plato);
			System.out.println("El plato se a añadido correctamente");
		}

	}

	public void aniadirPlatoRandom(String nombre, int n) {

		if (n > ingredientes.size()) {
			System.out.println("No se puede añadir el plato random, demasiados ingredientes");
		} else if (platos.get(nombre) != null) {
			System.out.println("No se puede añadir el plato random, ya existe uno con ese nombre");
		} else {
			HashSet<Ingrediente> ings = new HashSet<Ingrediente>();
			Ingrediente ing;

			for (int i = 0; i < n; i++) {

				do {

					ing = ingredientes.get((int) (Math.random() * ingredientes.size()));

				} while (ings.contains(ing));

				ings.add(ing);

			}
			aniadirPlato(new Plato(nombre, ings));

		}

	}

	public void verMapa(int n) {
		Iterator<String> it = platos.keySet().iterator();
		int sumacalorias;

		while (it.hasNext()) {

			sumacalorias = 0;
			Plato plato = platos.get(it.next());
			for (Ingrediente ingrediente : plato.getIngredientes()) {
				sumacalorias += ingrediente.getCalorias();
			}

			if (sumacalorias <= n) {
				System.out.println(plato.toString());
			}

		}

	}
	
	public void eliminarCalorias() {

		final int N = 100;
		Iterator<String> it = platos.keySet().iterator();

		while (it.hasNext()) {
			Plato plato = platos.get(it.next());
			
			Iterator<Ingrediente> it2 = plato.getIngredientes().iterator();
			
			while(it2.hasNext()) {
				Ingrediente ing = it2.next();
			
				if(ing.getCalorias() > N) it2.remove();
				
			}
			
		}

	}

	public static void main(String[] args) {
		Cocinero a = new Cocinero();
		a.aniadirIngrediente(new Ingrediente[] { new Ingrediente("Lenteja", 10), new Ingrediente("Brocoli", 10),
				new Ingrediente("Tomate", 10), new Ingrediente("Chocolate", 101), new Ingrediente("Lechuga", 10) });
		a.aniadirPlatoRandom("Bocata de lentejas", 3);
		a.aniadirPlatoRandom("Tarantulas Fritas", 1);

		a.verMapa(1000);
		
		a.eliminarCalorias();
		
		a.verMapa(50);

	}

}
