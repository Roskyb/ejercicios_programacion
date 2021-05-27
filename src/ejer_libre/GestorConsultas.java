package ejer_libre;

import java.sql.*;
import java.util.ArrayList;



public class GestorConsultas {

	private Connection con;

	GestorConsultas() {
		conectarBaseDeDatos();
	}

	private void conectarBaseDeDatos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio_prog", "root", "");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public ArrayList<Empleado> getEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from empleado;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String numEmp = rs.getString(1);
				String nombre = rs.getString(2);
				String puesto = rs.getString(3);
				String num_sup = rs.getString(4);
				Date fecha_alta = rs.getDate(5);
				double salario = rs.getDouble(6);
				double comision = rs.getDouble(7);
				int num_dep = rs.getInt(8);
				
				Empleado emp = new Empleado(numEmp, nombre, puesto, num_sup, fecha_alta, salario, comision, num_dep);
				
				empleados.add(emp);
				
				
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleados;
	}

	public boolean cambiarSalario(String salario, String comision, String numEmp) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("update empleado set salario=?, comision=? where num_emp=?");
			ps.setDouble(1, Double.parseDouble(salario));
			ps.setDouble(2, Double.parseDouble(comision));
			ps.setString(3, numEmp);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Error de sql");
			
		} catch (NumberFormatException e1) {
			System.out.println("formato erroneo");
		}
		return false;
	}

	public boolean buscarDepartamento(String depar) {
			PreparedStatement ps;

			try {
				ps = con.prepareStatement("select count(*) from departamento where nombre=? or num_depto=?;");
				ps.setString(1, depar);
				ps.setString(2, depar);
				
				ResultSet rs = ps.executeQuery();
				
				if(!rs.next()) return false;
				else return true;
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
			
	}

	public boolean borrarDepartamento(String nombreDepar) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("delete from departamento where nombre=?");
			ps.setString(1, nombreDepar);
			
			return ps.executeUpdate() > 0;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean anidirDepartamento(String nombre, String localidad) {
		int num_depto = nuevoIdentificadorDepartamento();
		if(num_depto != -1) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement("INSERT INTO departamento(NUM_DEPTO, NOMBRE, LOCALIDAD) VALUES (?,?,?)");
				ps.setInt(1, num_depto);
				ps.setString(2, nombre);
				ps.setString(3, localidad);
				
				return ps.executeUpdate() > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return false;
	}

	private int nuevoIdentificadorDepartamento() {
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT MAX(NUM_DEPTO) FROM departamento");
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) + 1;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public ArrayList<String> getDepartamentos(String localidad) {
		ArrayList<String> arr = new ArrayList<String>();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT nombre FROM departamento WHERE localidad=?");
			ps.setString(1, localidad);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
			
			rs.close();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arr;
		
	}
	
	

}
