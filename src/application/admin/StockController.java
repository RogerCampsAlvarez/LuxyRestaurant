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
    private Button btnBack;

	@FXML
	private ListView<StockCategory> categoryListView;
	
	@FXML
	private ListView<StockItem> stockListView;
	
	@FXML
	private TextField tfQtat;
	
	ObservableList<StockCategory> olCategory = FXCollections.observableArrayList();
	ObservableList<StockItem> olStock = FXCollections.observableArrayList();
	
    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
    }
    
    void loadData() {
    	//TODO load from DB
    	ConnexioBD con = new ConnexioBD();
    	
    	ArrayList<StockCategory> categories = new ArrayList<>();
    	//StockCategory[] categories = new StockCategory[100];
    	ResultSet rs = con.queryDB("select * from categories");
    	try {
			while (rs.next()) {
				categories.add(new StockCategory(rs.getString("cat")));
			}    	
			rs = con.queryDB("select * from plats");
		
			while (rs.next()) {
				categories.get(rs.getInt("id_cat")-1).productesList.add(new StockItem(rs.getInt("id"), rs.getString("nom"), rs.getInt("quantitat")));
			}
		
			for(int i=0;i<categories.size();i++) {
		    	olCategory.add(categories.get(i));
			}
			
	    	categoryListView.setItems(olCategory);
	    	stockListView.setItems(olStock);
	    	con.desconnectarDB();
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
		new Thread() {
			@Override
			public void run() {
				loadData();
			}
		}.start();
		
		tfQtat.setVisible(false);

		//Mostrar segona listview depenent de categoria
		categoryListView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
			cat = nv;
			loadCategory(cat);
        });

		//Mostrar quadre de quantitat depenent de producte
		stockListView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
			if(nv != null) {
				System.out.println("\n"+nv);				
				idSelected = nv.id;
				tfQtat.setText(String.valueOf(nv.quantitat));
			}
			tfQtat.setVisible(true);
			
        });
		
    	ConnexioBD con = new ConnexioBD();

		tfQtat.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				int id = idSelected;
				int qtat = Integer.parseInt(tfQtat.getText());
				con.execDB("update plats set quantitat = "+qtat+" where id = "+id);
				updateCategory(cat);
				loadCategory(cat);
			}
		});
			
	}

	private void updateCategory(StockCategory cat) {
    	ConnexioBD con = new ConnexioBD();
    	ResultSet rs = con.queryDB("select * from plats p inner join categories c on id_cat = c.id and c.cat = '"+cat.nom+"'");
    	try {
    		cat.productesList.clear();
			while(rs.next()) {
				cat.productesList.add(new StockItem(rs.getInt("id"), rs.getString("nom"), rs.getInt("quantitat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadCategory(StockCategory cat) {
		System.out.println("\n"+cat);
		List<StockItem> productes = ((StockCategory)cat).productesList;
		
		olStock.clear();
		for(StockItem item : productes) {
			System.out.println(item);
			olStock.add(item);
		}
		tfQtat.setVisible(false);
	}
}
