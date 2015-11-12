package cineapp;

import java.io.IOException;

import cineapp.vista.BarraLateralController;
import cineapp.vista.CarteleraController;
import cineapp.vista.DetallesPeliculaController;
import cineapp.vista.HorariosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Comienza la App
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Cinemas PandaPlus");
		
		// Colocar icono
		this.primaryStage.getIcons().add(new Image("file:res/img/iconos/App.png"));

		// Inicializar el Layout
		initRootLayout();

		// Mostrar scenes
		showCartelera();
		showBarraLateral();
		// showBannerAnuncios();
	}

	/**
	 * Inicializa el root layout.
	 */
	public void initRootLayout() {
		try {
			// Cargar layout de archivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Mostrar la Scene con el layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna el Stage principal
	 * 
	 * @return PrimaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Retorna el root layout.
	 * 
	 * @return Main RootLayout
	 */
	public BorderPane getRootLayout() {
		System.out.println("Debug: RootLayout obtained");
		return rootLayout;
	}

	/**
	 * Muestra la barra lateral dentro del RooLayout
	 */
	public void showBarraLateral() {
		try {
			// Cargar BarraLateral
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/BarraLateral.fxml"));
			AnchorPane BarraLateral = (AnchorPane) loader.load();

			// Colocar la barra a la izquierda del Layout
			rootLayout.setLeft(BarraLateral);

			// Dar al controlador acceso a la app principal
			BarraLateralController controlador = loader.getController();
			controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar en la ventana principal Cartelera.fxml
	 */
	public void showCartelera() {
		try {
			// Cargar Cartelera
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Cartelera.fxml"));
			AnchorPane cartelera = (AnchorPane) loader.load();

			// Colocar la cartelera en el centro del Layout
			rootLayout.setCenter(cartelera);

			// Dar al controlador acceso a la app principal
			CarteleraController controlador = loader.getController();
			controlador.setMainApp(this);

			// Cargar la cartelera
			controlador.cargarCartelera();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar en la ventana principal Horarios.fxml
	 */
	public void showHorarios() {
		try {
			// Cargar Horarios
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Horarios.fxml"));
			AnchorPane horarios = (AnchorPane) loader.load();

			// Colocar los horarios en el centro del Layout
			rootLayout.setCenter(horarios);

			// Dar al controlador acceso a la app principal
			HorariosController controlador = loader.getController();
			controlador.setMainApp(this);
			
			// Cargar los horarios
			// TODO: Obtener fecha y sucursal especificada
			controlador.setHorarios(0, 3);
		} catch (IOException e) {

		}
	}

	/**
	 * Mostrar en la ventana principal Proximamente.fxml
	 */
	public void showProximamente() {
		try {
			// Cargar Proximamente
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Proximamente.fxml"));
			AnchorPane proximamente = (AnchorPane) loader.load();

			// Colocar la cartelera en el centro del Layout
			rootLayout.setCenter(proximamente);

			// Dar al controlador acceso a la app principal
			// TODO: Definir la clase ProximamenteControler.java
			// ProximamenteController controlador = loader.getController();
			// controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar en la ventana principal Ofertas.fxml
	 */
	public void showOfertas() {
		try {
			// Cargar Proximamente
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Ofertas.fxml"));
			AnchorPane ofertas = (AnchorPane) loader.load();

			// Colocar la cartelera en el centro del Layout
			rootLayout.setCenter(ofertas);

			// Quitar el panel de ofertas
			rootLayout.setBottom(null);

			// Dar al controlador acceso a la app principal
			// TODO: Definir la clase OfertasControler.java
			// OfertasController controlador = loader.getController();
			// controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar en la ventana principal Comentarios.fxml
	 */
	public void showComentarios() {
		try {
			// Cargar Proximamente
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Comentarios.fxml"));
			AnchorPane comentarios = (AnchorPane) loader.load();

			// Colocar la cartelera en el centro del Layout
			rootLayout.setCenter(comentarios);

			// Dar al controlador acceso a la app principal
			// TODO: Definir la clase ComentariosControler.java
			// ComentariosController controlador = loader.getController();
			// controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Es llamado al seleccionar un poster en la vista de cartelera
	 */
	public void showDetallesPelicula(int id) {
		try {
			// Cargar Pelicula
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/DetallesPelicula.fxml"));
			AnchorPane pelicula = (AnchorPane) loader.load();

			// Colocar detalles de la pelicula en RooTLayout
			rootLayout.setCenter(pelicula);

			// Dar al controlador acceso a la app principal
			DetallesPeliculaController controlador = loader.getController();
			controlador.setMainApp(this);

			// Cargar informacion de pelicula
			controlador.setMovieData(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
