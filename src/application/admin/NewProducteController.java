package application.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewProducteController {

	@FXML
	private Button btnBack, btnAddProd;
	
	@FXML
	private TextField tfNom, tfQtat, tfPreu;
	@FXML
	private TextArea taDesc;
	@FXML
	private ComboBox<StockCategory> cbCategoria;
	@FXML
	private ComboBox<String> cbTipus;
	
	ConnexioBD con;
	
	@FXML
	void btnBack(ActionEvent event) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
	}
	
	@FXML
	void btnAddProd(ActionEvent event) {
		String nom = tfNom.getText();
		int qtat = Integer.parseInt(tfQtat.getText());
		float preu = Float.parseFloat(tfPreu.getText());
		String tipus = cbTipus.getValue();
		String desc = taDesc.getText();
		StockCategory categoria = cbCategoria.getValue();
		int idCat = categoria.id;
		
		String sql = "insert into plats values(DEFAULT,'"+nom+"',"+0+","+0+","+0+","+0+","+0+","+preu+",'"+desc+"','"+tipus+"',"+qtat+","+idCat+")";
		System.out.println(sql);
		con.execDB(sql);
	}

	/**
	 * Carrega les dades de la DB a les llistes
	 */
	void loadData() {
		cbCategoria.setItems(StockController.olCategory);
		cbTipus.setItems(FXCollections.observableArrayList("Primer","Segon","Postres"));
	}

	/**
	 * Funció que es crida al iniciar la escena
	 */
	public void initialize() {
		// Carreguem les dades amb un thread per que no s'aturi el fil principal mentres es consulta
		new Thread() {
			@Override
			public void run() {
				loadData();
			}
		}.start();

		con = new ConnexioBD();

	}
}
