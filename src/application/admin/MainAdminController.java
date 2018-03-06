package application.admin;

import java.io.IOException;

import application.Main;
import application.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		new StockController().initialize(stage);
	}

	@FXML
	void cmdTaules(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnTaules.getScene().getWindow();
		new TaulesController().initialize(stage);
	}

	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		new MainController().initialize(stage);
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
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("About.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage aboutStage = new Stage();
		aboutStage.setScene(scene);
		aboutStage.setTitle("About");
		aboutStage.initModality(Modality.WINDOW_MODAL);
		aboutStage.initOwner(Main.getMainScene().getWindow());
		aboutStage.setResizable(false);
		aboutStage.initStyle(StageStyle.UTILITY);
		aboutStage.show();
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
