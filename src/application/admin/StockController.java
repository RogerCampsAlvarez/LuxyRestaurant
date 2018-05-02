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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StockController {

	@FXML
	private Button btnBack, btnAddProd;

	@FXML
	private ListView<StockCategory> categoryListView;

	@FXML
	private ListView<StockItem> stockListView;

	@FXML
	private TextField tfQtat, tfAddCat;

	static ObservableList<StockCategory> olCategory = FXCollections.observableArrayList();
	ObservableList<StockItem> olStock = FXCollections.observableArrayList();

	ConnexioBD con;
	@FXML
	void btnBack(ActionEvent event) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
	}

	@FXML
	void btnAddProd(ActionEvent event) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/NewProducte.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnAddProd.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
	}

	/**
	 * Carrega les dades de la DB a les llistes
	 */
	void loadData() {
		con = new ConnexioBD();
		olCategory.clear();
		olStock.clear();

		loadBegudes(con);
		loadPlats(con);
		//con.desconnectarDB();
	}

	private void loadBegudes(ConnexioBD con) {
		try {
			StockCategory category = new StockCategory(100, "Begudes");
			olCategory.add(category);

			ResultSet rs = con.queryDB("select * from begudes order by id asc");
			
			olCategory.get(0).productesList.clear();
			while (rs.next()) { // Carrega els productes per categoria
				olCategory.get(0).productesList.add(new StockItem(rs.getInt("id"), rs.getString("nom"), rs.getInt("quantitat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadPlats(ConnexioBD con) {
		ResultSet rs = con.queryDB("select * from categories order by id asc");
		try {
			while (rs.next()) { // Carrega les categories
				StockCategory category = new StockCategory(rs.getInt("id"), rs.getString("cat"));
				olCategory.add(category);
			}
			rs = con.queryDB("select * from plats");

			while (rs.next()) { // Carrega els productes per categoria
				olCategory.get(rs.getInt("id_cat")).productesList.add(new StockItem(rs.getInt("id"), rs.getString("nom"), rs.getInt("quantitat")));
			}

			// Carreguem les ListView
			categoryListView.setItems(olCategory);
			stockListView.setItems(olStock);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	int idSelected;
	int qtatSelected;
	StockCategory cat;

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

		tfQtat.setVisible(false);

		// Mostrar segona listview depenent de categoria
		categoryListView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			cat = nv;
			if(cat != null)
				loadCategory(cat);
		});

		// Mostrar quadre de quantitat depenent de producte
		stockListView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				System.out.println("\n" + nv);
				idSelected = nv.id;
				tfQtat.setText(String.valueOf(nv.quantitat));
			}
			tfQtat.setVisible(true);

		});

		ConnexioBD con = new ConnexioBD();

		// Quan fem enter actualitzem la BD amb la quantitat
		tfQtat.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				int id = idSelected;
				int qtat = Integer.parseInt(tfQtat.getText());
				if(cat.id == 100) {//Si es la categoria de begudes
					con.execDB("update begudes set quantitat = " + qtat + " where id = " + id);
				} else {
					con.execDB("update plats set quantitat = " + qtat + " where id = " + id);
				}
				/*loadBegudes(con);
				loadCategory(cat);*/
				loadData();
			}
		});

		tfAddCat.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (!tfAddCat.getText().equals(""))
					con.execDB("insert into categories values(DEFAULT,'" + tfAddCat.getText() + "')");
				loadData();
			}
		});
	}

	/**
	 * Carrega la categoria
	 * 
	 * @param cat
	 */
	private void loadCategory(StockCategory cat) {
		System.out.println("\n" + cat);

		olStock.clear();
		System.out.println("cat: "+cat);
		for (StockItem item : cat.productesList) {
			System.out.println("Item: "+item+" cat: "+cat+" "+cat.productesList);
			olStock.add(item);
		}
		tfQtat.setVisible(false);
	}
	
	/*
	 * Actualitza la categoria
	 * 
	 * @param cat
	private void updateCategory(StockCategory cat) {
		ConnexioBD con = new ConnexioBD();
		// Fa un select dels productes de la categoria i els fica a la seva llista
		ResultSet rs = con.queryDB(
				"select * from plats p inner join categories c on id_cat = c.id and c.cat = '" + cat.nom + "'");
		try {
			cat.productesList.clear();
			while (rs.next()) {
				cat.productesList.add(new StockItem(rs.getInt("id"), rs.getString("nom"), rs.getInt("quantitat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
}
