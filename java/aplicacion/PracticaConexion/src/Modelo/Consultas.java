package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Consultas {

	
	 public boolean Registro(String nombre ,String Matricula,String Direccion,String Edad,String  Sexo, Connection con )
	 {
		 System.out.println(""+nombre+Matricula+Direccion+Edad+Sexo);
		 boolean status =false;
		 String query="INSERT INTO alumno (nombre,Matricula,Direccion,Edad,Sexo) VALUES (?,?,?,?,?)";
		 PreparedStatement ps=null;
		 try{
			 ps=con.prepareStatement(query);
			
			 ps.setString(1,nombre);
			 ps.setString(2,Matricula);
			 ps.setString(3,Direccion);
			 ps.setString(4,Edad);
			 ps.setString(5,Sexo);
			
			
			 
			
			int registrosEfectados= ps.executeUpdate();
			status= registrosEfectados > 0 ? true:false;
		 }catch (Exception e){
			 System.err.println("Error " +e.getMessage());
			 
		 }
		 finally
		 {
			 ps=null;
			 return status;
		
	 }	
	}
	
	
	
}
