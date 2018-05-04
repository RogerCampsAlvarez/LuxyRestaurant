package application.admin;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import application.Strings;
import application.Util;
import application.ConnexioBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cuina {		
		@FXML
		private Button btnBack;
		ArrayList<Comanda> al = new ArrayList<Comanda>();
		@FXML
		private TableView<Comanda> taulesTableView;
		@FXML
		private TableColumn<Comanda, String> tcId;
		@FXML
		private TableColumn<Comanda, String> tcTaula;
		@FXML
		private TableColumn<Comanda, String> tcPrimer;
		@FXML
		private TableColumn<Comanda, String> tcSegon;
		@FXML
		private TableColumn<Comanda, String> tcPostres;
		private String idSeleccionada;
		
		@FXML
		void btnBack(ActionEvent event) throws IOException {
			Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) btnBack.getScene().getWindow();
			Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
		}
		
		@FXML
		public void clickItem(MouseEvent event) throws ClassNotFoundException, SQLException{
		    if (event.getClickCount() < 2) //Checking double click
		    {
		        idSeleccionada = String.valueOf((taulesTableView.getSelectionModel().getSelectedItem().getId()));		       
		    }else {
		    	acabarComanda(idSeleccionada);
		    }
		}
		
		public void initialize() throws ClassNotFoundException, SQLException {
			load();
		}
		
		public void Update() throws ClassNotFoundException, SQLException {
			load();
		}
		
		@FXML
		public void btnRecuperarUltim(ActionEvent event) throws SQLException, ClassNotFoundException {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/restaurant", "postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE comandes SET acavat=false WHERE id="+idSeleccionada+";");
			al.clear();
			Update();
		}
		
		@FXML
		public void btnAcabarComanda(ActionEvent event) throws ClassNotFoundException, SQLException {
			acabarComanda(idSeleccionada);
		}
		
		private void acabarComanda(String id) throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/restaurant", "postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE comandes SET acavat=true WHERE id="+id+";");
			al.clear();
			Update();
		}
		
		private void load() throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/restaurant", "postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM comandes WHERE acavat=false ORDER BY id;");
			
			//Afegir al arraylist tots els resultats del query
			while(rs.next()) {
				Comanda x = new Comanda(connection,rs.getInt("id"),rs.getInt("taula"),rs.getInt("primer"),rs.getInt("segon"),rs.getInt("postre"));
				al.add(x);
			}
			
			
			ObservableList<Comanda> olTaulesList = FXCollections.observableArrayList();
			//Comanda i = new Comanda(stmt,1,0,0,3,9);
			//Comanda ii = new Comanda(stmt,0,0,1,2,8);
			
			Iterator<Comanda> iterador = al.iterator();
			while(iterador.hasNext()) {
				olTaulesList.add(iterador.next());
			}
			
			
			tcId.setCellValueFactory(new PropertyValueFactory<Comanda, String>("id"));
			tcTaula.setCellValueFactory(new PropertyValueFactory<Comanda, String>("taula"));
			tcPrimer.setCellValueFactory(new PropertyValueFactory<Comanda, String>("primer"));
			tcSegon.setCellValueFactory(new PropertyValueFactory<Comanda, String>("segon"));
			tcPostres.setCellValueFactory(new PropertyValueFactory<Comanda, String>("postre"));
			taulesTableView.getItems().setAll(olTaulesList);
			
			
			
		}
}

