package ejers_prog.tema6.ejer4;

import java.util.HashMap;
import java.util.Iterator;

public class Agenda {

	private HashMap<String, Integer[]> entradas;
	
	private final int MAX_ENTRADAS;

	public Agenda(int entradas) {
		super();
		MAX_ENTRADAS = entradas;
		this.entradas = new HashMap<String, Integer[]>();
		
		
	}
	
	
	public void aniadeEntrada(String nombre, Integer[] numeros) {
		
		char r = 'S';
		
		if(entradas.size() >= MAX_ENTRADAS) {
			System.out.println("La agenda esta completa");
		} else if(entradas.containsKey(nombre) ) {
			
			System.out.print("Su agenda ya dispone de los siguientes teléfonos para " + nombre + ": ");
			
			Integer[] nums = entradas.get(nombre);
			
			for (int i = 0; i < nums.length; i++) {
				System.out.print(nums[i] + ", ");
			}
			
			System.out.println("¿Desea sustituirlos por los nuevos? (S/N)");
			r = Consola.leeChar();
			
			if(r == 'S') {
				entradas.put(nombre, numeros);
			}

		}else {
			entradas.put(nombre, numeros);
		}
	
		
	}

	
	public void ver() {
		
		Iterator<String> it = entradas.keySet().iterator();
		
		while(it.hasNext()) {
			String nombre = it.next();
			System.out.print(nombre + ": ");
			for (Integer n : entradas.get(nombre)) {
				System.out.print(n + ", ");
			}
			
			System.out.println();
			
		}
		
	}
	
	public String buscaNombre(String nombre) {
		
		Integer[] nums = entradas.get(nombre);
		
		if(nums != null) {
			String msg = "";
			
			for (Integer i : nums) {
				msg += i + ", ";
			}
			
			System.out.println(msg);
			
			return msg;
			
			
			
		}else {
			System.out.println(nombre + " no se encuentra en la agenda");
			
			return nombre + " no se encuentra en la agenda";
		}
		
	}
	
	
	public int cuantosTelefonos(int telf) {
		
		int cont = 0;
		Iterator<String> it = entradas.keySet().iterator();
		
		
		while(it.hasNext()) {
			String nombre = it.next();
			boolean repetido = false;
			for (Integer n : entradas.get(nombre)) {
				if(telf == n && !repetido) {
					++cont;
					repetido = true;
				}
			}
			
			
		}
		
		
		return cont;
	}
	
	public static void main(String[] args) {
		Agenda a = new Agenda(5);
		
		a.aniadeEntrada("Jorge", new Integer[]{5646546, 5555555});
		a.aniadeEntrada("Manolo", new Integer[]{5555555, 5555555});
		a.aniadeEntrada("Manolina", new Integer[]{5555555, 5555555});
		a.aniadeEntrada("Manolon", new Integer[]{5555555, 5555555});
		a.aniadeEntrada("Manolon", new Integer[]{111111, 8888888});
		a.aniadeEntrada("Homer", new Integer[]{111111, 8888888});
		
		a.ver();
		
		
		a.buscaNombre("Jodrge");
		a.buscaNombre("Jorge");
		System.out.println(a.cuantosTelefonos(5555555));
		
		
	}
	
	
	
	
}
