package tareas;
import java.sql.*;

public class ConexionjOdbc {
	
	    private Connection conexion;
	    private String basedatos = "BaseDemo";
	    private String url = "jdbc:postgresql://localhost:5432/"+basedatos;
	    private String driver = "org.postgresql.Driver";
	    private String user = "postgres";
	    private String pass = "tucontraseña";
	    private Statement stmt = null;

	    public ConexionjOdbc() {
	    }
	    
	    public Connection conectar() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	        conexion = null;
	        try {
	            Class.forName(driver).newInstance();
	            conexion = (DriverManager.getConnection(url, user, pass));
	            if (conexion != null) {
	              //System.out.println("SE HA ESTABLECIDO UNA CONEXION A LA BASE DE DATOS: " );
	              //System.out.println("CON "+ url.toUpperCase());
	            }
	        } catch (SQLException e) {
	            System.out.println(" Falla de conectividad:  " + " " + conexion);
	            e.printStackTrace();
	        }
	        return conexion;
	    }
	    
	    public Connection getConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	        conexion = this.conectar();
	        return conexion;
	    }
	    
	    public void printConexion(){
	    	try {
	           if (conexion != null) {
	              System.out.println("SE HA ESTABLECIDO UNA CONEXION A LA BASE DE DATOS: " );
	              System.out.println("driver: "+ conexion.toString());
	            }
	        } catch (Exception e) {;}	    	
	    }
	     
	    public Statement getStmt() {
	        return stmt;
	    }

	    public void closeConexion() throws SQLException {
	        conexion.close();
	        conexion = null;
	    }

	    public void setStmt(Statement stmt) {
	        this.stmt = stmt;
	    }
}
