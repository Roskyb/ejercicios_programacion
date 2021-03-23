package ejers_prog.tema8.tanda1.ejer5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Principal {

	public static void main(String[] args) {
		String nomFich = Consola.leeString();
		grabarComerciales(nomFich);
		
		ArrayList<Comercial> arrC = new ArrayList<Comercial>();
		
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("files/comerciales.obj"));
			Comercial obj = (Comercial) ois.readObject();
			while(obj != null) {
				if(!arrC.contains(obj)) arrC.add(obj);
				
				obj =  (Comercial) ois.readObject();
			}
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		for (Comercial comercial : arrC) {
			comercial.ver();
		}

		try {
			buscaMovil(generarMapaMoviles(arrC));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}
	
	private static HashMap<String, TelefonoMovil> generarMapaMoviles(ArrayList<Comercial> arr){
		
		HashMap<String, TelefonoMovil> map = new HashMap<String, TelefonoMovil>();
		
		for (Comercial comercial : arr) {
			map.put(comercial.getNombre(), comercial.getTelf());
		}
		
		return map;	
	}
	
	private static void buscaMovil (HashMap<String, TelefonoMovil> map) throws Exception {
		System.out.println("Introduce el nombre del comercial cuyo número quieres visualizar: ");
		String res = Consola.leeString();
		if(map.containsKey(res)) {
			System.out.println(res + ": " + map.get(res).getNumero());
		}else throw new Exception("Comercial no existe");
	}
	
	
	private  static void  grabarComerciales(String nomFich) {
		
		char res = 'S';
		
		String nomComercial;
		double salario;
		String numTelefono;
		double saldo;
		
		
		try {
			FileOutputStream fis = new FileOutputStream(nomFich+".obj");
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			
			while(res == 'S') {
				System.out.println("Introduce el nombre del comercial");
				nomComercial = Consola.leeString();
				
				System.out.println("Introduce el salario del comercial"	);
				salario = Consola.leeDouble();
				
				System.out.println("Indoruce el numero de telefono del comercial");
				numTelefono = Consola.leeString();
				
				System.out.println("Introduce el saldo del telefono");
				saldo = Consola.leeDouble();
				
				Comercial c = new Comercial(nomComercial, salario, new TelefonoMovil(numTelefono, saldo));

				try {
					if(evaluaComercial(c)) {
						oos.writeObject(c);
					}
				} catch (ErrorComercialException e) {
					System.out.println(e);
				}
				
				
				System.out.println("¿Desea continuar? (S/N)");
				res = Consola.leeChar();
				
			}
			oos.writeObject(null);
			oos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
			

			
		} 
	
		
	
	
	private static boolean evaluaComercial(Comercial c) throws ErrorComercialException {
		
		if(c.getSalario() < 0) throw new ErrorComercialException("El comercial tiene salario negativo");
		if(c.getTelf() == null) throw new ErrorComercialException("El teléfono del comercial no ha sido instanciado");
		

		for (char e : c.getTelf().getNumero().toCharArray()) {
			if(!Character.isDigit(e))throw new ErrorComercialException("El número de teléfono contiene caracteres diferentes a digitos");
		}

		
		return true;
		
	}
	
	
}
