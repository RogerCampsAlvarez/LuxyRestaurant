package application.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CuinaController {
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

	private ConnexioBD conDB;

	@FXML
	void btnBack(ActionEvent event) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
	}

	@FXML
	public void clickItem(MouseEvent event) throws ClassNotFoundException, SQLException {
		if (event.getClickCount() < 2) { // Checking double click
			if(taulesTableView.getSelectionModel().getSelectedItem() != null)
				idSeleccionada = String.valueOf(taulesTableView.getSelectionModel().getSelectedItem().getId());
		} else {
			acabarComanda(idSeleccionada);
		}
	}

	public void initialize() throws ClassNotFoundException, SQLException {
		load();
	}

	@FXML
	public void btnRecuperarUltim(ActionEvent event) throws SQLException, ClassNotFoundException {
		conDB = new ConnexioBD();
		conDB.execDB("UPDATE comandes SET acabat_menjar=false WHERE id=" + idSeleccionada + ";");
		al.clear();
		load();
	}

	@FXML
	public void btnAcabarComanda(ActionEvent event) throws ClassNotFoundException, SQLException {
		acabarComanda(idSeleccionada);
	}

	private void acabarComanda(String id) throws ClassNotFoundException, SQLException {
		conDB = new ConnexioBD();

		conDB.execDB("UPDATE comandes SET acabat_menjar=true WHERE id=" + id + ";");
		al.clear();
		load();

		conDB.desconnectarDB();
	}

	private void load() throws ClassNotFoundException, SQLException {

		new Thread() {
			@Override
			public void run() {
				try {
					conDB = new ConnexioBD();
					ResultSet rs = conDB.queryDB("SELECT * FROM comandes WHERE acabat_menjar=false ORDER BY id;");

					// Afegir al arraylist tots els resultats del query
					while (rs.next()) {
						al.add(new Comanda(rs.getInt("id"), rs.getInt("taula"), rs.getInt("primer"), rs.getInt("segon"),
								rs.getInt("postre")));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				carregarComandes();
			}
		}.start();
	}

	/**
	 * Un cop les comandes estan registrades dins l'arraylist les carreguem totes a
	 * l'observablelist i la vista.
	 */
	private void carregarComandes() {
		ObservableList<Comanda> olTaulesList = FXCollections.observableArrayList();
		// Comanda i = new Comanda(stmt,1,0,0,3,9);
		// Comanda ii = new Comanda(stmt,0,0,1,2,8);

		Iterator<Comanda> iterador = al.iterator();
		while (iterador.hasNext()) {
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