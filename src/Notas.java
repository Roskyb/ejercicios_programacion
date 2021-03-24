import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Notas {

	private String nomFich;
	final static int N_POR_ALUM  = 7;
	
	public Notas(String nomFich) throws FileNotFoundException {
		super();
		this.nomFich = nomFich;
		
	}
	
	
	public void aniadirNotas(byte[] notas) throws IOException {
		if(notas.length != N_POR_ALUM) System.out.println("DEBE HABER " + N_POR_ALUM + " NOTAS POR ALUMNO");
		else{
			FileOutputStream fos = new FileOutputStream(nomFich, true);
			fos.write(notas);
			fos.close();
		}
	}
	
	public byte[] notasAlumno(int pos) throws IOException {
		
		if(pos < 0 || pos > alumonsFichero()) return null;
		else {
			FileInputStream fis = new FileInputStream(this.nomFich);
			
			
			
			byte[] notasAlumno = new byte[N_POR_ALUM];
			
			for (int i = 0; i < pos; i++) {
				fis.read(notasAlumno);
			}
			
			fis.close();
			
			return notasAlumno;
			
		}
		
		
	}
	
	
	private long alumonsFichero() {
		
		File f = new File(this.nomFich);
		long bytesFichero = f.length();
		
		return bytesFichero / N_POR_ALUM;
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		var a = new Notas("files/notas.bin");
		a.aniadirNotas(new byte[] {1,2,3,4,5,6,7});
		a.aniadirNotas(new byte[] {1,2,3,4,5,6,7});
		a.aniadirNotas(new byte[] {1,2,3,2,2,2,7});
		
		for (byte b : a.notasAlumno(9)) {
			System.out.println(b);
		}
		
		
	}
	
	
	
}
