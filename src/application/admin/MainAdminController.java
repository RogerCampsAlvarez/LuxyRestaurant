package application.admin;

import java.io.IOException;

import application.AboutController;
import application.MainController;
import application.Strings;
import application.Util;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/Stock.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnStock.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_ADMIN_STOCK);
	}

	@FXML
	void cmdTaules(ActionEvent event) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource("/application/admin/Taules.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnTaules.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_ADMIN_TAULES);
	}

	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN);
	}

	@FXML
	void cmdClose(ActionEvent event) {
		System.out.println("Closing " + mnClose.getText());
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void cmdAbout(ActionEvent event) throws Exception {
		new AboutController().initialize(new Stage());
	}
}
