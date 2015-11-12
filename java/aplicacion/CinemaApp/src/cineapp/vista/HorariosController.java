package cineapp.vista;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import cineapp.MainApp;
import cineapp.modelo.Horarios;
import cineapp.modelo.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HorariosController {

	// Referenciar aplicacion principal
	private MainApp mainApp;
	@FXML
	private GridPane gPArray;
	@FXML
	private ComboBox cBFecha;
	@FXML
	private Button btnNuevoLeon;
	@FXML
	private Button btnDF;
	@FXML
	private Button btnPuebla;
	@FXML
	private Button btnCancun;
	@FXML
	private Label lblNombre;
	private Horarios horarios;
	private ObservableList<String> fechas = FXCollections.observableArrayList();
	private int idSucursal;
	private int dias;

	/**
	 * Contructor principal de la clase
	 */
	public HorariosController() {
		horarios = new Horarios();
		fechas.add("Miércoles 11 (Hoy)");
		fechas.add("Jueves 12");
		fechas.add("Viernes 13");
		fechas.add("Sábado 14");
		fechas.add("Domingo 15");
	}

	/**
	 * Inicializa el controlador. Este metodo es llamado al terminar de cargar
	 * el fxml
	 */
	@FXML
	private void initialize() {
		cBFecha.getItems().addAll(fechas);
		cBFecha.setValue("Miércoles 11 (Hoy)");
		cBFecha.getSelectionModel().selectedIndexProperty()
				.addListener((observable, oldValue, newValue) -> handleComboBox((int) newValue));
	}

	/**
	 * Es llamado por la aplicacion principal para referenciarse a si misma
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Colocar la informacion de los horarios
	 * 
	 * @param dias
	 *            que sumar a la fecha actual para obtener la cartelera. Si es 0
	 *            muestra la cartelera del dia actual.
	 * @param idSucursal
	 *            Sucursal de los horarios
	 */
	public void setHorarios(int dias, int idSucursal) {
		this.idSucursal = idSucursal;
		this.dias = dias;
		List<Pelicula> peliculas = horarios.getHorarios(this.dias, this.idSucursal);
		lblNombre.setText(btnPuebla.getText());
		if (peliculas != null) {
			// Colocar los horarios en gPArray
			int y = 0;
			for (Pelicula pelicula : peliculas) {
				addHorarioPane(pelicula.getId(), pelicula.getTitulo(), pelicula.getClasificacion(),
						pelicula.getDuracion(), pelicula.getHorarios(), pelicula.getPosterUrl(), y);
				y++;
			}
		}
	}

	/**
	 * Agregar el panel de horarios con la informacion proporcionada
	 * 
	 * @param id
	 *            de pelicula
	 * @param titulo
	 *            de pelicula
	 * @param clasificacion
	 *            de pelicula
	 * @param duracion
	 *            de pelicula
	 * @param horarios
	 *            de pelicula
	 * @param posterUrl
	 *            de pelicula
	 */
	public void addHorarioPane(int id, String titulo, String clasificacion, int duracion, String horarios,
			String posterUrl, int y) {
		try {
			// Cargar el GridPane
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/HorarioPane.fxml"));
			GridPane horario = (GridPane) loader.load();

			// Colocar en la ubicacion deseada
			gPArray.add(horario, 0, y);

			// Colocar informacion con el controlador
			HorarioPaneController controlador = loader.getController();
			controlador.setData(id, titulo, clasificacion, duracion, horarios, posterUrl);

			// Dar acceso a la aplicacion principal
			controlador.setMainApp(mainApp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Se encarga de mostrar la cartelera correspondiente a la fecha
	 * seleccionada en el comboBox
	 * 
	 * @param dias
	 *            index de fecha seleccionada
	 */
	private void handleComboBox(int dias) {
		this.dias = dias;
		gPArray.getChildren().clear();
		setHorarios(this.dias, idSucursal);
	}

	/**
	 * Se encarga de colocar la cartelera perteneciente a la sucursal
	 * seleccionada
	 */
	@FXML
	private void setCarteleraSucursal(final ActionEvent event) {
		// Obtener el boton seleccionado
		Object source = event.getSource();
		Button btn = null;
		if (source instanceof Button) {
			btn = (Button) source;
			// Comparar el boton para saber la sucursal seleccionada
			if (btn == btnNuevoLeon) {
				this.idSucursal = 1;
			} else if (btn == btnDF) {
				this.idSucursal = 2;
			}else if (btn == btnPuebla) {
				this.idSucursal = 3;
			}else if (btn == btnCancun) {
				this.idSucursal = 4;
			}
			gPArray.getChildren().clear();
			setHorarios(this.dias, this.idSucursal);
		}
		lblNombre.setText(btn.getText());
	}

}
