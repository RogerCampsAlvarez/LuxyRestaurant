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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.net.util.IPAddressUtil;
/**
 * 
 * @author Marc Vila
 *
 */
public class OpinionsController {

	@FXML private Button btnBack;
	@FXML private Button btnEnviar;
	@FXML private ListView lvPlats;
	@FXML private ListView lvComentaris;
	@FXML private ChoiceBox<Integer> cbPuntuacio;
	@FXML private TextArea tAreaComentari;
	
	private ConnexioBD conDB;
	private ObservableList<String> obsListComanda = FXCollections.observableArrayList();
	private ObservableList<String> obsListValoracions = FXCollections.observableArrayList();
	
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
		generarChoiceBox();
		
	}
	
	private void generarChoiceBox()  {
		cbPuntuacio.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
	    cbPuntuacio.getSelectionModel().select(10);
	}

	/**
	 * Ompla la llista de plats de la comanda dels clients
	 */
	private void omplirLlistaPlats() {
		List<String> lsPlats = new ArrayList<>();
		lsPlats.add( MainClientController.comClient.getsPrimerPlat() );
		lsPlats.add( MainClientController.comClient.getsSegonPlat() );
		lsPlats.add( MainClientController.comClient.getsPostres() );
		lsPlats.add( MainClientController.comClientExtra.getsPrimerPlat() );
		lsPlats.add( MainClientController.comClientExtra.getsSegonPlat() );
		lsPlats.add( MainClientController.comClientExtra.getsPostres() );
		
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
	
	/**
	 * Cerca les valoracions del plat
	 */
	private void cercarValoracions() {
		obsListValoracions.clear();
		
		String sQuery = "SELECT opinio, valoracio FROM opinio_plat WHERE id_plat = (SELECT id FROM plats WHERE nom = '" + sPlat + "');";

		try {
			conDB = new ConnexioBD();
			ResultSet rs = conDB.queryDB(sQuery);

			while (rs.next()) {
				obsListValoracions.add(rs.getString("opinio") + "\n\t -> " + rs.getInt("valoracio") );
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			conDB.desconnectarDB();
		}
		
		lvComentaris.setItems(obsListValoracions);
		
	}
	
	/**
	 * Envia l'opinio del client.
	 * @param ae
	 */
	@FXML
	void onClickEnviar( ActionEvent ae ) {
		String sOpinio = tAreaComentari.getText();
		int iPuntuacio = cbPuntuacio.getValue();
		
		String sQuery = "INSERT INTO public.opinio_plat( id_plat, opinio, valoracio) "
				+ "VALUES ( ( SELECT id FROM plats WHERE nom = '" + sPlat + "'), '" + sOpinio + "', " + iPuntuacio + ");";
		
		try {
			
			conDB = new ConnexioBD();
			conDB.execDB(sQuery);
			
			suprimirPlatOpinat();
			tAreaComentari.setText("");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			conDB.desconnectarDB();
		}
	}
	
	/**
	 * Elimina de la llista de plats per opinar el plat opinat
	 */
	private void suprimirPlatOpinat() {
		
		boolean bEliminar = true;
		
		for( int i = 0; i < obsListComanda.size(); i++ ) {
			
			if (bEliminar && obsListComanda.get( i ).equals( sPlat )) {
				obsListComanda.remove( i );
			}
			
		}
		
		lvComentaris.setItems(obsListValoracions);
		
	}
	
}
