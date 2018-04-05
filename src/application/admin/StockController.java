package application.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StockController {
	
	@FXML
    private Button btnBack;

	@FXML
	private ListView<StockCategory> categoryListView;
	
	@FXML
	private ListView<StockItem> stockListView;
	
	ObservableList<StockCategory> categoryList = FXCollections.observableArrayList();
	ObservableList<StockItem> stockList = FXCollections.observableArrayList();
	
    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
    }
    
    void loadList() {
    	//TODO load from DB
    	ConnexioBD con = new ConnexioBD();
    	ResultSet rs = con.queryDB("select * from plats");
    	
    	StockCategory catCarnics = new StockCategory("Productes Càrnics");
			try {
				int index=0;
				while (rs.next()) {
					catCarnics.productesList.add(new StockItem(index++, rs.getString("nom"), 4));
				}
			
		    	/*catCarnics.productesList.add(new StockItem(1, "Filet de Porc", 4));
		    	catCarnics.productesList.add(new StockItem(2, "Filet de Vedella", 3));
		    	catCarnics.productesList.add(new StockItem(3, "Hamburguesa", 5));
		    	catCarnics.productesList.add(new StockItem(4, "Bistec", 6));*/
	    	categoryList.add(catCarnics);
	    	
	    	StockCategory catMarisc = new StockCategory("Marisc");
		    	catMarisc.productesList.add(new StockItem(1, "Gambetes de Palamós", 3));
		    	catMarisc.productesList.add(new StockItem(2, "Llangosta", 30));
		    	catMarisc.productesList.add(new StockItem(3, "Calamar", 8));
	    	categoryList.add(catMarisc);
	    	
	    	categoryListView.setItems(categoryList);
			stockListView.setItems(stockList);
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    				
			
    }
    
    /**
     * Funció que es crida al iniciar la escena
     */
	public void initialize() {
		loadList();
		
		categoryListView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
			System.out.println("\n"+nv);
			List<StockItem> productes = ((StockCategory)nv).productesList;
			stockList.clear();
			for(StockItem item : productes) {
				System.out.println(item);
				stockList.add(item);
			}
        });
			
	}
}
