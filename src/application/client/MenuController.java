package application.client;

import java.io.IOException;
import java.util.List;

import application.BaseDades;
import application.Strings;
import application.Util;
import application.VariablesBaseDades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

	@FXML
	private ListView lvPlats;
	
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
	
	@FXML
	private Text txtPlat;
	
	@FXML
	private Text txtDescripcio;
	
	private ComandaClient comandaClient;
	private ObservableList<String> obsListComanda = FXCollections.observableArrayList();
	
	private String sPlat;
	private String sBeguda;
	private String sCafe;
	private int iPlat = 0;

	/**
	 * Funcio inical
	 */
	public void initialize() {
		comandaClient  = new ComandaClient();
		
		btnEnviar.setVisible( false );
		btnDemanar.setVisible( false );
		
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
		
		obternirPlats( "primer" );
		
		lvPlats.setItems( obsListComanda );
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
        btnDemanar.setVisible( false );
		iPlat = 1;

		obternirPlats( "segon" );
		
		lvPlats.setItems( obsListComanda );
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
        btnDemanar.setVisible( false );
		iPlat = 4;
		
		obternirPlats("postres");
		
		lvPlats.setItems( obsListComanda );
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	sPlat = (String) lvPlats.getSelectionModel().getSelectedItem();
	        	platSeleccionat();
	        }
	    });
	}
	
	
	private void obternirPlats( String sTipus ) {
		VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "SELECT nom FROM plats WHERE en_estoc = true AND tipus = '" + sTipus + "';";
		
		try  {
			BaseDades.ConnectarDB( vBD );
			BaseDades.QueryDB( sQuery , vBD);
			
			while ( vBD.rs.next() ) {
				obsListComanda.add( vBD.rs.getString( "nom") );
			}
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			BaseDades.DesconnectarDB( vBD );
		}
	}
	
	@FXML
	void btnCafes(ActionEvent event) throws IOException {
		obsListComanda.clear();
        btnDemanar.setVisible( false );
		iPlat = 2;
		
		obtenirBeguda(true);
		
		lvPlats.setItems( obsListComanda );
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
        btnDemanar.setVisible( false );
		iPlat = 3;
		
		obtenirBeguda(false);
		
		lvPlats.setItems( obsListComanda );
		lvPlats.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	sBeguda = (String) lvPlats.getSelectionModel().getSelectedItem();
	        	platSeleccionat();
	        }
	    });
	}
	
	private void obtenirBeguda( boolean bCafe ) {
		VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "SELECT nom FROM begudes WHERE en_estoc = true AND cafe = " + bCafe + ";";
		
		try  {
			BaseDades.ConnectarDB( vBD );
			BaseDades.QueryDB( sQuery , vBD);
			
			while ( vBD.rs.next() ) {
				obsListComanda.add( vBD.rs.getString( "nom") );
			}
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			BaseDades.DesconnectarDB( vBD );
		}
	}
	
	@FXML
	void btnEnviar(ActionEvent event) throws IOException {
		System.out.println( "Enviant..." );
		
		VariablesBaseDades vBD = new VariablesBaseDades();
		String sQuery = "INSERT INTO public.comandes(" + 
				"            taula, primer, segon, postre, beguda, acavat)" + 
				"    VALUES (0, '" + comandaClient.getsPrimerPlat()+ "','" 
				+ comandaClient.getsSegonPlat()+ "', '" + comandaClient.getsPostres()+ "', '" 
				+ comandaClient.getsBeguda()+ "', FALSE);";
		
		try  {
			BaseDades.ConnectarDB( vBD );
			BaseDades.UpdateDB( sQuery , vBD);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			BaseDades.DesconnectarDB( vBD );
		}
		
	}
	
	private void enviarComanda() {
		String sPrimerPlat = comandaClient.getsPrimerPlat();
		String sSegonPlat = comandaClient.getsSegonPlat();
		String sPostres = comandaClient.getsPostres();
		String sCafe = comandaClient.getsCafe();
		String sBeguda = comandaClient.getsBeguda();
		
		/*
		 * Enviar la comnada a la base de dades
		 */
		
	}
	
	private void platSeleccionat() {

        txtPlat.setText( sPlat );
        VariablesBaseDades vBD = new VariablesBaseDades();
        String sQuery = "SELECT descripcio FROM plats WHERE nom = '" + sPlat + "';";
        
        try {
        	BaseDades.ConnectarDB( vBD );
        	BaseDades.QueryDB( sQuery, vBD );
        	
        	if ( vBD.rs.next() ) {
        		txtDescripcio.setText( vBD.rs.getString( "descripcio" ) );
        	}
        } catch ( Exception e) {
			// TODO: handle exception
		} finally {
			BaseDades.DesconnectarDB( vBD );
		}
        
        btnDemanar.setVisible( true );
	}

	@FXML
	void btnDemanar(ActionEvent event) throws IOException {
	
		//Comprova el plat seleccionat
		switch ( iPlat ) {
		case 0://Primr plat
			comandaClient.setsPrimerPlat( sPlat );
			break;
		case 1://Segon Plat
			comandaClient.setsSegonPlat( sPlat );
			break;
		case 2://Postres
			comandaClient.setsPostres( sPlat );	
			break;
		case 3://Beguda
			comandaClient.setsBeguda( sBeguda );		
			break;
		case 4://Cafes
			comandaClient.setsCafe( sCafe );			
			break;
		default:
			break;
		}
		
		//Amaga o mostra el botor Enviar
		btnEnviar.setVisible( comandaClient.comprovarPlatsNull() );
	}
}