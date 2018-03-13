package application.client;

import java.io.IOException;

import application.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OpinionsController {

	@FXML
	private Button btnBack;
	
	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		new MainClientController().initialize(stage);	
	}
	
	public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Opinions.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Client-Opinions");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
