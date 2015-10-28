package tareas;
import java.sql.*;
import java.util.Scanner;
public class Modulo2Tarea2 {
    private Scanner teclado;
	public Modulo2Tarea2(){
		// constructor		
	}
	
	public void imprmirNumero(){
		teclado = new Scanner(System.in);
		System.out.print("Ingrese primer valor: ");
		int numero = teclado.nextInt();
		System.out.println("El numero es: " + numero);		
	}
	
	public void GetEstudiantes(){
		ConexionjOdbc baseDatos = new ConexionjOdbc();
		Connection conexion = null;
		ResultSet rs = null;
		try {
			conexion = baseDatos.getConexion();
		
			baseDatos.printConexion();
            String sentencia ="SELECT id, nombre, matricula  FROM public.estudiantes; "; //"SELECT nombre,pass,rol FROM ct_empleados  WHERE (usuario = ?) AND (pass=?)";
            PreparedStatement s = conexion.prepareStatement(sentencia);
            //s.setString(1, nombreColumna); //parametro
            rs = s.executeQuery();//ejecuta la consulta
            if (rs != null) {
                while (rs.next()) {
                	System.out.println("-----------------------");
                	System.out.println("id: "+rs.getInt("id") );
                	System.out.println("nombre: "+rs.getString("nombre") );
                	System.out.println("matricula: "+rs.getString("matricula") );
                }
            }
        } catch (Exception e) {
            System.out.println(" Error by: " + e.getMessage());
        }
		finally {
			try {
       	        conexion.close(); //Cierra la conexion
			} catch (Exception e) {  }
		}
	}
	
	public static void main(String[] ar){
		Modulo2Tarea2 objeto = new Modulo2Tarea2();
		objeto.GetEstudiantes();		
	}
}
