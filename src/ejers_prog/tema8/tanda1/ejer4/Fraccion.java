package ejers_prog.tema8.tanda1.ejer4;

public class Fraccion {
	private int numerador;
	private int denominador;
	

	public Fraccion(int numerador, int denominador) throws FraccionException {
		super();
		this.numerador = numerador;
		if(denominador == 0) {
			throw new FraccionException(true);
		}
		this.denominador = denominador;
	}

	public Fraccion sumar(Fraccion f2) throws FraccionException {
		Fraccion aux = new Fraccion(1,1);
		aux.numerador = this.numerador * f2.denominador + this.denominador * f2.numerador;
		aux.denominador = this.denominador * f2.denominador;
		aux.simplificar();
		return aux;
	}
	
    public Fraccion restar(Fraccion f) throws FraccionException {
        Fraccion aux = new Fraccion(1,1);
        aux.numerador = numerador * f.denominador - numerador * f.denominador;
        aux.denominador = this.denominador * f.denominador;
        aux.simplificar();  
        return aux;
    }
    
    public Fraccion multiplicar(Fraccion f) throws FraccionException {
        Fraccion aux = new Fraccion(1,1);
        aux.numerador = this.numerador * f.numerador;
        aux.denominador = this.denominador * f.denominador;
        aux.simplificar(); 
        return aux;
    }
    
    public Fraccion dividir(Fraccion f) throws FraccionException {
        Fraccion aux = new Fraccion(1,1);
        aux.numerador = this.numerador * f.denominador;
        aux.denominador = this.denominador * f.numerador;
        aux.simplificar();  
        return aux;
    }
	
	@Override
	public String toString() {
		return "Fraccion [numerador=" + numerador + ", denominador=" + denominador + "]";
	}


	private void simplificar() throws FraccionException {
        int n = this.mcd();
        numerador = numerador / n;
        denominador = denominador / n;
        if(denominador == 0) {
        	throw new FraccionException();
        }
	}
	
	
    private int mcd() {
        int u = Math.abs(numerador); 
        int v = Math.abs(denominador); 
        if (v == 0) {
            return u;
        }
        int r;
        while (v != 0) {
            r = u % v;
            u = v;
            v = r;
        }
        return u;
    }

	
}
