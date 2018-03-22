package application.client;

import java.io.IOException;

import application.Strings;
import application.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private ListView lsPlats;
	
	@FXML
	private Button btnPrimerPlat;
	
	@FXML
	private Button btnSegonPlat;
	
	@FXML
	private Button btnPostres;
	
	@FXML
	private Button btnCafes;
	
	@FXML
	private Button btnBeguda;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnEnviar;
	
	@FXML
	private Button btnDemanar;
	
	private ComandaClient comClient;
	private ObservableList<String> obsListComanda = FXCollections.observableArrayList();

	/**
	 * Funcio inical
	 */
	public void initialize() {
		comClient  = new ComandaClient();
		
		btnEnviar.setVisible( false );
		
	}
	
	@FXML
	void btnBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Pane root = FXMLLoader.load(getClass().getResource("/application/client/MainClient.fxml"));
		Scene scene = new Scene(root);
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_CLIENT);
	}
	
	@FXML
	void btnPrimerPlat(ActionEvent event) throws IOException {
		
	}
	
	@FXML
	void btnSegonPlat(ActionEvent event) throws IOException {
		
	}
	
	@FXML
	void btnCafes(ActionEvent event) throws IOException {
	
	}
	
	@FXML
	void btnBeguda(ActionEvent event) throws IOException {
	
	}
	
	@FXML
	void btnPostres(ActionEvent event) throws IOException {
	
	}
	
	@FXML
	void btnEnviar(ActionEvent event) throws IOException {
	
		
	}
	
	private void enviarComanda() {
		String sPrimerPlat = comClient.getsPrimerPlat();
		String sSegonPlat = comClient.getsSegonPlat();
		String sPostres = comClient.getsPostres();
		String sCafe = comClient.getsCafe();
		String sBeguda = comClient.getsBeguda();
		
		/*
		 * Enviar la comnada a la base de dades
		 */
		
	}

	@FXML
	void btnDemanar(ActionEvent event) throws IOException {
	
		//Comprovar plat
		
		//Afegir plat a l'0bjecte
		
		//Amaga o mostra el botor Enviar
		btnEnviar.setVisible( comClient.comprovarEnvar() );
	}
}