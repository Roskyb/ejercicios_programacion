package ejers_prog.tema7.tanda1.ejer2;

public class Descendente {

	private float[] arr;

	public Descendente(int arrSize) {
		super();
		this.arr = new float[arrSize];
	}
	
	
	
	public void aniadirNum(float n) {
		if(tamanioUtil() == 0) {
			System.out.println("No se pueden añadir más números");
		}else {
			
			for (int i = 0; i < arr.length; i++) {
				
				if(arr[i] < n ) {
					for (int j = i; j < arr.length; j++) {
						arr[i + 1] = arr[i];
					}
					
					arr[i] = n;
					break;
				}else if(arr[i] == 0) {
					arr[i] = n;
					break;
				}
				
				
			}
			
			
		}
	}
	
	public void ver() {
		for (float f : arr) {
			System.out.print(f + ", ");
		}
	}
	
	private int tamanioUtil() {
		int cont = 0;
		
		for (float f : arr) {
			if(f == 0) cont++;
		}
		
		return cont;
	}
	
	public static void main(String[] args) {
		Descendente a = new Descendente(10);
		
		a.aniadirNum(1);
		a.aniadirNum(2);
		a.aniadirNum(3);
		
		a.ver();
		
	}
	
	
}
