package cineapp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Horarios {

	private final Connection conn = JDBC.getConnection();
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * Obtiene los horarios de peliculas
	 * @param dias que sumar a la fecha actual para obtener la cartelera.
	 * 				Si es 0 muestra la cartelera del dia actual.
	 * @param idSucursal sucursal
	 * @return
	 */
	public List<Pelicula> getHorarios(int dias, int idSucursal) {
		List<Pelicula> peliculas = new ArrayList<>();
		try {
			// Generar consilta
			sql = "";		// Consultar cartelera
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dias);
			ps.setInt(2, idSucursal);
			rs = ps.executeQuery();
			
			// Procesar ResultSet
			if (rs.first()) {
				do {
					// Obtener los valores
					Pelicula pelicula = new Pelicula();
					pelicula.setId(rs.getInt(1));
					pelicula.setTitulo(rs.getString(2));
					pelicula.setClasificacion(rs.getString(3));
					pelicula.setDuracion(rs.getInt(4));
					pelicula.setPosterUrl(rs.getString(5));
					
					// Agregar las horas de proyeccion
					pelicula.setHorarios(getHorasPelicula(pelicula.getId()));
					
					// Agregar la pelicula a la lista
					peliculas.add(pelicula);
				} while (rs.next());
			} else {
				peliculas = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculas;
	}
	
	/**
	 * Retorna las diferentes horas de proyeccion de una misma pelicula
	 * 
	 * @param idPelicula
	 * @return
	 */
	private String getHorasPelicula(int idPelicula) {
		String horas = "";
		try {
			// Generar consilta
			sql = "";		// Obtener horas de proyeccion
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idPelicula);
			ResultSet rs = ps.executeQuery();
			
			// Procesar ResultSet
			if (rs.first()) {
				do {
					horas += rs.getString(1) + " | ";
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horas;
	}
}
