package application.client;

import java.io.IOException;

import application.AboutController;
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

public class MainClientController {

	@FXML private Button btnMenjar;
	@FXML private Button btnBeure;
	@FXML private Button btnBack;
	@FXML private Button btnOpinio;
	@FXML private MenuItem mnClose;
	@FXML private MenuItem closeButton;

	public static ComandaClient comClient = new ComandaClient();
	public static ComandaClient comClientExtra = new ComandaClient();
	
	@FXML
	void cmdMenjar(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnMenjar.getScene().getWindow();
		Pane root = FXMLLoader.load(getClass().getResource("/application/client/Menu.fxml"));
		Scene scene = new Scene(root);
		Util.openGUI(scene, stage, Strings.TITLE_CLIENT_MENU);	
	}

	@FXML
	void cmdBeure(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnBeure.getScene().getWindow();
		Pane root = FXMLLoader.load(getClass().getResource("/application/client/Beure.fxml"));
		Scene scene = new Scene(root);
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);	
	}
	
	@FXML
	void cmdOpinar(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnOpinio.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/application/client/Opinions.fxml"));
		Scene scene = new Scene(root);
		Util.openGUI(scene, stage, Strings.TITLE_CLIENT_OPINAR);
	}

	@FXML
	void cmdBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		Pane root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene scene = new Scene(root);
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
