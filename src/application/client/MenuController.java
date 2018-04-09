package application.client;

import java.io.IOException;
import java.sql.ResultSet;

import application.ConnexioBD;
import application.Strings;
import application.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {

	@FXML private ListView lvPlats;
	@FXML private Button btnPrimerPlat;
	@FXML private Button btnSegonPlat;
	@FXML private Button btnPostres;
	@FXML private Button btnCafes;
	@FXML private Button btnBeguda;
	@FXML private Button btnBack;
	@FXML private Button btnEnviar;
	@FXML private Button btnDemanar;
	@FXML private Text txtPlat;
	@FXML private Text txtDescripcio;

	private ObservableList<String> obsListComanda = FXCollections.observableArrayList();

	private String sPlat;
	private String sBeguda;
	private String sCafe;
	private int iPlat = 0;
	
	ConnexioBD conDB;


	/**
	 * Funcio inical
	 */
	public void initialize() {

		btnEnviar.setVisible(false);
		btnDemanar.setVisible(false);

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
		obsListComanda.clear();
		iPlat = 0;

		obternirPlats("primer");

		lvPlats.setItems(obsListComanda);
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sPlat = (String) lvPlats.getSelectionModel().getSelectedItem();
				platSeleccionat();
			}
		});
	}

	@FXML
	void btnSegonPlat(ActionEvent event) throws IOException {
		obsListComanda.clear();
		btnDemanar.setVisible(false);
		iPlat = 1;

		obternirPlats("segon");

		lvPlats.setItems(obsListComanda);
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sPlat = (String) lvPlats.getSelectionModel().getSelectedItem();
				platSeleccionat();
			}
		});
	}

	@FXML
	void btnPostres(ActionEvent event) throws IOException {
		obsListComanda.clear();
		btnDemanar.setVisible(false);
		iPlat = 2;

		obternirPlats("postres");

		lvPlats.setItems(obsListComanda);
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sPlat = (String) lvPlats.getSelectionModel().getSelectedItem();
				platSeleccionat();
			}
		});
	}

	private void obternirPlats(String sTipus) {
		//VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "SELECT nom FROM plats WHERE  quantitat > 0 AND tipus = '" + sTipus + "';";
		try {
			conDB = new ConnexioBD();
			ResultSet rs = conDB.queryDB(sQuery);

			while (rs.next()) {
				obsListComanda.add(rs.getString("nom"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			conDB.desconnectarDB();
		}
	}

	@FXML
	void btnCafes(ActionEvent event) throws IOException {
		obsListComanda.clear();
		btnDemanar.setVisible(false);
		iPlat = 4;

		obtenirBeguda(true);

		lvPlats.setItems(obsListComanda);
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sCafe = (String) lvPlats.getSelectionModel().getSelectedItem();
				platSeleccionat();
			}
		});
	}

	@FXML
	void btnBeguda(ActionEvent event) throws IOException {
		obsListComanda.clear();
		btnDemanar.setVisible(false);
		iPlat = 3;

		obtenirBeguda(false);

		lvPlats.setItems(obsListComanda);
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sBeguda = (String) lvPlats.getSelectionModel().getSelectedItem();
				platSeleccionat();
			}
		});
	}

	private void obtenirBeguda(boolean bCafe) {
		//VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "SELECT nom FROM begudes WHERE quantitat > 0 AND cafe = " + bCafe + ";";

		try {
			conDB = new ConnexioBD();
			ResultSet rs = conDB.queryDB(sQuery);

			while (rs.next()) {
				obsListComanda.add(rs.getString("nom"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			conDB.desconnectarDB();
		}
	}

	@FXML
	void btnEnviar(ActionEvent event) throws IOException {
		
		enviarComanda();

	}

	private void enviarComanda() {
		int iPrimerPlat = obtenirID(MainClientController.comClient.getsPrimerPlat());
		int iSegonPlat = obtenirID(MainClientController.comClient.getsSegonPlat());
		int iPostres = obtenirID(MainClientController.comClient.getsPostres());
		int iCafe = obtenirID(MainClientController.comClient.getsCafe());
		int iBeguda = obtenirID(MainClientController.comClient.getsBeguda());

		//VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "INSERT INTO comandes(taula, primer, segon, postre, beguda, acavat)" + "VALUES (0, '"
				+ iPrimerPlat + "','" + iSegonPlat + "', '" + iPostres + "', '" + iBeguda + "', FALSE);";

		try {
			conDB = new ConnexioBD();
			conDB.execDB(sQuery);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			conDB.desconnectarDB();
		}

	}

	private int obtenirID(String sPlat) {
		//VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "SELECT id FROM plats WHERE nom = '" + sPlat + "';";

		try {
			conDB = new ConnexioBD();
			ResultSet rs = conDB.queryDB(sQuery);
			
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			conDB.desconnectarDB();
		}
		return iPlat;
	}

	private void platSeleccionat() {

		txtPlat.setText(sPlat);
		//VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "SELECT descripcio FROM plats WHERE nom = '" + sPlat + "';";

		try {
			conDB = new ConnexioBD();
			ResultSet rs = conDB.queryDB(sQuery);
			if (rs.next()) {
				txtDescripcio.setText(rs.getString("descripcio"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			conDB.desconnectarDB();
		}

		btnDemanar.setVisible(true);
	}

	@FXML
	void btnDemanar(ActionEvent event) throws IOException {

		// Comprova el plat seleccionat
		switch (iPlat) {
			case 0:// Primr plat
				MainClientController.comClient.setsPrimerPlat(sPlat);
				break;
			case 1:// Segon Plat
				MainClientController.comClient.setsSegonPlat(sPlat);
				break;
			case 2:// Postres
				MainClientController.comClient.setsPostres(sPlat);
				break;
			case 3:// Beguda
				MainClientController.comClient.setsBeguda(sBeguda);
				break;
			case 4:// Cafes
				MainClientController.comClient.setsCafe(sCafe);
				break;
			default:
				break;
		}

		// Amaga o mostra el botor Enviar
		btnEnviar.setVisible(MainClientController.comClient.comprovarPlatsNull());
	}
}