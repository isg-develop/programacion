package Controlador;

import java.sql.Connection;





import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Modelo.Consultas;

public class Controlador {
	
	
	Consultas obj = new Consultas ();
	

	public void Registro(String Nombre, String matricula, String direccion, String edad,String sexo, Connection con) {
		obj.Registro(Nombre, matricula,direccion,edad,sexo, con);
	
	}
}

