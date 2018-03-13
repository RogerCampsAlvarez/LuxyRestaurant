package application;

import java.io.IOException;

import application.admin.MainAdminController;
import application.client.MainClientController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private Button btnClient;
	@FXML
	private Button btnAdmin;
	@FXML
	private MenuItem mnClose;
	@FXML
	private MenuItem closeButton;

	@FXML
	void cmdClient(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnClient.getScene().getWindow();
		new MainClientController(stage);
	}

	@FXML
	void cmdAdmin(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnAdmin.getScene().getWindow();
		new MainAdminController().initialize(stage);
	}

	@FXML
	void cmdClose(ActionEvent event) {
		System.out.println("Closing " + mnClose.getText());
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void cmdAbout(ActionEvent event) throws Exception {
		System.out.println("Showing About");
		new AboutController(new Stage());
	}

	public MainController(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
			primaryStage.setTitle("LuxyRestaurant");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}