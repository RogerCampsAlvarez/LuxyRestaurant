package application.admin;

import java.io.IOException;

import application.Strings;
import application.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TaulesController {
	
	@FXML
    private Button btnBack;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.MAIN_TITLE);
    }
    
    public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Taules.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Admin-Taules");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
