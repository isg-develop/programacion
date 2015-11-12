package cineapp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cartelera {

	private final Connection conn = JDBC.getConnection();
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Obtiene las peliculas listadas como en cartelera de
	 * la Base de Datos
	 * @return Peliculas en cartelera
	 */
	public List<Pelicula> getCartelera() {
		List<Pelicula> peliculas = new ArrayList<>();
		try {
			// Generar consulta
			sql = "";		// Obtener lista de peliculas
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			// Procesar ResultSet
			if (rs.first()) {
				do {
					// Obtener los valores
					Pelicula pelicula = new Pelicula();
					pelicula.setId(rs.getInt(1));
					pelicula.setTitulo(rs.getString(2));
					pelicula.setPosterUrl(rs.getString(3));
					
					// Agregar la pelicula a la lista
					peliculas.add(pelicula);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculas;
	}
	
	public Pelicula getPelicula(int id) {
		Pelicula pelicula = new Pelicula();
		try {
			// Generar consulta
			sql = "";		// Obtener informacion de la pelicula
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			//Procesar ResultSet
			if (rs.first()) {
				pelicula.setTitulo(rs.getString(1));
				pelicula.setDirector(rs.getString(2));
				pelicula.setReparto(rs.getString(3));
				pelicula.setSinopsis(rs.getString(4));
				pelicula.setClasificacion(rs.getString(5));
				pelicula.setDuracion(rs.getInt(6));
				pelicula.setGenero(rs.getString(7));
				pelicula.setPosterUrl(rs.getString(8));
				pelicula.setTrailerUrl(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pelicula;
	}

}
