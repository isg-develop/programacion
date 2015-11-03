package controlador;

import java.sql.Connection;

import modelo.sedesol.mx.Informacion;


public class Controlador {
	
	Informacion obj = new Informacion();

	public void insertarRegistro(String Nombre, String Apellido_paterno, String Apellido_materno,
			String Matricula, String Carrera, Connection con) {
		
		obj.insertarRegistro(Nombre, Apellido_paterno, Apellido_materno,Matricula,Carrera, con);
	}
		
	}
	
	

	

