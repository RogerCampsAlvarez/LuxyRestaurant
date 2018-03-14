package application.client;

import java.io.IOException;

import application.Strings;
import application.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenjarController {

	@FXML
	private Button btnBack;

	@FXML
	void btnBack(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Pane root = FXMLLoader.load(getClass().getResource("/application/client/MainClient.fxml"));
		Scene scene = new Scene(root);
		Util.openGUI(scene, stage, Strings.MAIN_ADMIN_TITLE);
	}
	/*
	public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Menjar.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Client-Menjar");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
