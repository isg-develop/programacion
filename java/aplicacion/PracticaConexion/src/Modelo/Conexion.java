package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	public static Connection getConnection()
	 {
		Connection con=null;
	 String servidor= "jdbc:mysql://localhost/ejercicioconexion";
	 String Nombre= "root";
	 String Contraseña = "";
		
	
	try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con= DriverManager.getConnection(servidor, Nombre, Contraseña);

		
		
			
     }
	       catch (Exception e) 
		{
		
			System.out.println("la conexion no se realizo "+e.getMessage());
		
      }
		finally
		{
			return con;
		}
   }
	
	public static void close(Connection con){
		if (con != null){
			try{
				con.close();
				con = null;
			}catch (Exception e){
				
			}
			
		}
}
	



}
