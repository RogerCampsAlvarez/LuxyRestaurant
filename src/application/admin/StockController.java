package application.admin;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class StockController {
	@FXML
	private ListView<StockItem> list;

	@FXML
    private Button btnBack;

	@FXML
	private ListView<String> list2;
	
	ObservableList<StockItem> stockList = FXCollections.observableArrayList();
	ObservableList<String> stockInfo = FXCollections.observableArrayList();

    @FXML
    void btnBack(ActionEvent event) throws IOException {
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		new MainAdminController().initialize(stage);
    }
    
    void loadList() {
		System.out.println(list);
    	if(list != null) {
	    	StockItem item1 = new StockItem(0, "Carn", 4);
	    	
	    	stockList.add(item1);
			list.setItems(stockList);
			
			stockInfo.add("ASD");
			list2.setItems(stockInfo);
    	}
    }
    
    public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Stock.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Admin-Stock");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

			loadList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
