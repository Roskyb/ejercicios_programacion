package ejer_libre;

import java.sql.Date;

public class Empleado {
	private String numEmp;
	private String nombre;
	private String puesto;
	private String num_sup;
	private Date fecha_alta;
	private double salario;
	private double comision;
	private int num_dep;
	
	public Empleado(String numEmp, String nombre, String puesto, String num_sup, Date fecha_alta, double salario,
			double comision, int num_dep) {
		super();
		this.numEmp = numEmp;
		this.nombre = nombre;
		this.puesto = puesto;
		this.num_sup = num_sup;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
		this.num_dep = num_dep;
	}
	

	public String getNumEmp() {
		return numEmp;
	}


	public void setNumEmp(String numEmp) {
		this.numEmp = numEmp;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPuesto() {
		return puesto;
	}


	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}


	public String getNum_sup() {
		return num_sup;
	}


	public void setNum_sup(String num_sup) {
		this.num_sup = num_sup;
	}


	public Date getFecha_alta() {
		return fecha_alta;
	}


	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}


	public double getSalario() {
		return salario;
	}


	public void setSalario(double salario) {
		this.salario = salario;
	}


	public double getComision() {
		return comision;
	}


	public void setComision(double comision) {
		this.comision = comision;
	}


	public int getNum_dep() {
		return num_dep;
	}


	public void setNum_dep(int num_dep) {
		this.num_dep = num_dep;
	}


	@Override
	public String toString() {
		return "Empleado [numEmp=" + numEmp + ", nombre=" + nombre + ", puesto=" + puesto + ", num_sup=" + num_sup
				+ ", fecha_alta=" + fecha_alta + ", salario=" + salario + ", comision=" + comision + ", num_dep="
				+ num_dep + "]";
	}
	
	
	
}
