package ejers_prog.tema10.tanda2.ejer1;

public class HoraConsulta {
	private int hora;
	private int minutos;
	
	
	public HoraConsulta(int hora, int minutos) {
		super();
		this.hora = hora;
		this.minutos = minutos;
	}
	
	

	public int getHora() {
		return hora;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hora;
		result = prime * result + minutos;
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
		HoraConsulta other = (HoraConsulta) obj;
		if (hora != other.hora)
			return false;
		if (minutos != other.minutos)
			return false;
		return true;
	}



	public int getMinutos() {
		return minutos;
	}

	public HoraConsulta siguienteHoraConsulta() {
		
		if(this.minutos == 0){
			HoraConsulta siguienteHoraConsulta = new HoraConsulta(this.hora, minutos +30);
			return siguienteHoraConsulta;
		}else {
			HoraConsulta siguienteHoraConsulta = new HoraConsulta(this.hora + 1, 00);
			return siguienteHoraConsulta;
		}
	}


	@Override
	public String toString() {
		return this.hora + ":" + (this.minutos == 0 ? "00" : this.minutos);
	}
	
	
	
}
