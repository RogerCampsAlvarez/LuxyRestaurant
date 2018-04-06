package application.client;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
import javafx.stage.Stage;

public class OpinionsController {

	@FXML private Button btnBack;
	
	@FXML private ListView lvPlats;
	
	@FXML private ListView lvComentaris;
	
	private ConnexioBD conDB;
	private ObservableList<ValoracioPlats> obsListComanda = FXCollections.observableArrayList();
	
	private String sPlat;
	
	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Pane root = FXMLLoader.load(getClass().getResource("/application/client/MainClient.fxml"));
		Scene scene = new Scene(root);
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_CLIENT);
	}
	
	/**
	 * Funcio inical
	 */
	public void initialize() {
		
		omplirLlistaPlats();
		
	}

	/**
	 * Ompla la llista de plats de la comanda dels clients
	 */
	private void omplirLlistaPlats() {
		List<String> lsPlats = new ArrayList<>();
		lsPlats.add( MainClientController.comClient.getsPrimerPlat() );
		lsPlats.add( MainClientController.comClient.getsSegonPlat() );
		lsPlats.add( MainClientController.comClient.getsPostres() );
		lsPlats.add( MainClientController.comClient.getsBeguda() );
		lsPlats.add( MainClientController.comClient.getsCafe() );
		lsPlats.add( MainClientController.comClientExtra.getsPrimerPlat() );
		lsPlats.add( MainClientController.comClientExtra.getsSegonPlat() );
		lsPlats.add( MainClientController.comClientExtra.getsPostres() );
		lsPlats.add( MainClientController.comClientExtra.getsBeguda() );
		lsPlats.add( MainClientController.comClientExtra.getsCafe() );
		
		for( int i = 0; i < lsPlats.size(); i++ ) {
			if ( !lsPlats.get(i).equals( "") ) {
				obsListComanda.add( lsPlats.get( i ));
			}
		}
		
		lvPlats.setItems( obsListComanda );
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sPlat = (String) lvPlats.getSelectionModel().getSelectedItem();
				cercarValoracions();
			}

		});
		
	}
	
	private void cercarValoracions() {
		
		String sQuery = "SELECT opinio, valoracio FROM opinio_plat WHERE id_plat = SELECT id FROM plats WHERE nom = '" + sPlat + "';";

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
	
}
