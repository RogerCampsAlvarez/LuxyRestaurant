package application.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
		// System.out.println("Showing Menjar");
		GridPane root = FXMLLoader.load(getClass().getResource("Stock.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		Stage stage = (Stage) btnStock.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("???");
		// stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

	@FXML
	void cmdTaules(ActionEvent event) throws Exception {
		// System.out.println("Showing Beure");
		GridPane root = FXMLLoader.load(getClass().getResource("Taules.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		// Stage aboutStage = new Stage();
		Stage stage = (Stage) btnTaules.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("???");
		// stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("../Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		Stage stage = (Stage) btnBack.getScene().getWindow();

		MainController mcontroller = new MainController();
		// stage.initStyle(StageStyle.DECORATED);
		mcontroller.initialize(stage);
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
