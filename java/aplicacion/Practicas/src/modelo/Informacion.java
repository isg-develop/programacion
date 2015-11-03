package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Informacion {
	
	
	 public boolean insertarRegistro(String Nombre,String Apellido_paterno,String Apellido_materno,String Matricula,String Carrera, Connection con )
	 {
		 System.out.println(""+Nombre+Apellido_paterno+Apellido_materno+Matricula+Carrera);
		 boolean status =false;
		 String query="INSERT INTO alumno (Nombre,Apellido_paterno,Apellido_materno,Matricula,Carrera) VALUES (?,?,?,?,?)";
		 PreparedStatement ps=null;
		 try{
			 ps=con.prepareStatement(query);
			
			 ps.setString(1,Nombre);
			 ps.setString(2,Apellido_paterno);
			 ps.setString(3, Apellido_materno);
			 ps.setString(4,Matricula);
			 ps.setString(5,Carrera);
			
			
			 
			
			
			
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
