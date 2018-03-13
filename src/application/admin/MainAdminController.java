package application.admin;

import java.io.IOException;

import application.AboutController;
import application.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainAdminController {

	@FXML
	private Button btnStock;
	@FXML
	private Button btnTaules;
	@FXML
	private Button btnBack;
	@FXML
	private MenuItem mnClose;
	@FXML
	private MenuItem closeButton;

	@FXML
	void cmdStock(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnStock.getScene().getWindow();
		new StockController(stage);
	}

	@FXML
	void cmdTaules(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnTaules.getScene().getWindow();
		new TaulesController(stage);
	}

	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		new MainController(stage);
	}

	@FXML
	void cmdClose(ActionEvent event) {
		System.out.println("Closing " + mnClose.getText());
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void cmdAbout(ActionEvent event) throws Exception {
		new AboutController(new Stage());
	}

	public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("MainAdmin.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Admin");
			primaryStage.setScene(new Scene(parent));
			// primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
