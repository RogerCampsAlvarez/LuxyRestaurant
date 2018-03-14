package application.admin;

import java.io.IOException;

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
	private ListView<StockItem> list;
	
	@FXML
	private ListView<String> list2;
	
	ObservableList<StockItem> stockList = FXCollections.observableArrayList();
	ObservableList<String> stockInfo = FXCollections.observableArrayList();
	
    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.MAIN_ADMIN_TITLE);
    }
    
    void loadList() {
    	StockItem item1 = new StockItem(0, "Carn", 4);
    	
    	stockList.add(item1);
    	list.setItems(stockList);
		
		stockInfo.add("ASD");
		list2.setItems(stockInfo);
    }
    
	public void initialize() {
		loadList();
	}
}
