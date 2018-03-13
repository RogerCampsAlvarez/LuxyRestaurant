package application.client;

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

public class MainClientController {

	@FXML
	private Button btnMenjar;
	@FXML
	private Button btnBeure;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnOpinio;
	@FXML
	private MenuItem mnClose;
	@FXML
	private MenuItem closeButton;

	@FXML
	void cmdMenjar(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnMenjar.getScene().getWindow();
		new MenjarController(stage);
	}

	@FXML
	void cmdBeure(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnBeure.getScene().getWindow();
		new BeureController(stage);
	}
	
	@FXML
	void cmdOpinar(ActionEvent event) throws Exception {
		Stage stage = (Stage) btnOpinio.getScene().getWindow();
		new OpinionsController(stage);
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

	public MainClientController(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("MainClient.fxml"));
			primaryStage.setTitle("LuxyRestaurant-Client");
			primaryStage.setScene(new Scene(parent));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
