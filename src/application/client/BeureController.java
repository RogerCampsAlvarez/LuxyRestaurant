package application.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BeureController {
	
	@FXML
    private Button btnBack;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		new MainClientController().initialize(stage);
    }
    
    public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Beure.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Client-Beure");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
