package application.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.ConnexioBD;
import application.Strings;
import application.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TaulesController {

	@FXML
	private TextField tfNom;
	@FXML
	private TextField tfQtat;
	@FXML
	private Button btnBack;
	@FXML
	private TableView<Taula> taulesTableView;
	@FXML
	private TableColumn<Taula, String> tcNom;
	@FXML
	private TableColumn<Taula, String> tcCapacitat;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnDelete;
	
	private String nomSeleccionat;
	private ConnexioBD con;
	ObservableList<Taula> olTaulesList = FXCollections.observableArrayList();

	@FXML
	void btnAdd(ActionEvent event) throws IOException {
		String nom = tfNom.getText();
		String cap = tfQtat.getText();
		con.execDB(" insert into taules values (default,'"+nom+"',"+cap+",1) ");
		loadTaules();
	}
	
	@FXML
	void btnBack(ActionEvent event) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
	}
	
	@FXML
	void btnDelete(ActionEvent event) throws IOException {
		con.execDB("DELETE FROM taules WHERE nom = '"+nomSeleccionat+"'");
		loadTaules();
	}
	
	@FXML
	public void clickItem(MouseEvent event) throws ClassNotFoundException, SQLException {
		if(taulesTableView.getSelectionModel().getSelectedItem() != null) {
			nomSeleccionat = String.valueOf(taulesTableView.getSelectionModel().getSelectedItem().getNom());
		}
	}
	
	public void initialize() {
		con = new ConnexioBD();
		loadTaules();
	}
	
	private void loadTaules() {
		try {
			ResultSet rs = con.queryDB("select * from taules order by id asc");

			olTaulesList.clear();
			while (rs.next()) { // Carrega els productes per categoria
				olTaulesList.add(new Taula(/* rs.getInt("id")+ */ rs.getString("nom"), rs.getInt("persones")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Linies necessaries per poder inserir valors a les cel·les
		tcNom.setCellValueFactory(new PropertyValueFactory<Taula, String>("nom"));
		tcCapacitat.setCellValueFactory(new PropertyValueFactory<Taula, String>("capacitat"));

		taulesTableView.getItems().setAll(olTaulesList);

	}
}
