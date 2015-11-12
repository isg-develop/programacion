package cineapp.vista;

import cineapp.MainApp;
import javafx.fxml.FXML;

public class OfertasController {

	// Referenciar aplicacion principal
	private MainApp mainApp;

	/**
	 * Contructor principal de la clase
	 */
	public OfertasController() {

	}

	/**
	 * Inicializa el controlador. Este metodo es llamado al terminar de cargar
	 * el fxml
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Es llamado por la aplicacion principal para referenciarse a si misma
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
