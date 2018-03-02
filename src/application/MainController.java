package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

	@FXML
	private Button btnMenjar;
	@FXML
	private Button btnBeure;
	@FXML
	private Button btnNext;
	@FXML
	private MenuItem mnClose;
	@FXML
	private MenuItem closeButton;

	@FXML
	void cmdMenjar(ActionEvent event) throws Exception {
		System.out.println("Showing Menjar");
		GridPane root = FXMLLoader.load(getClass().getResource("Menjar.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage) btnMenjar.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("???");
		// stage.initStyle(StageStyle.UTILITY);
		stage.show();

		if (!Main.isSplashLoaded) {
			// loadSplashScreen();
		}

	}

	@FXML
	void cmdBeure(ActionEvent event) throws Exception {
		System.out.println("Showing Beure");
		GridPane root = FXMLLoader.load(getClass().getResource("Beure.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Stage aboutStage = new Stage();
		Stage stage = (Stage) btnBeure.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("???");
		// stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

	@FXML
	void cmdNext(ActionEvent event) throws Exception {
		System.out.println("Showing Next");
		GridPane root = FXMLLoader.load(getClass().getResource("Next.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage aboutStage = new Stage();
		aboutStage.setScene(scene);
		aboutStage.setTitle("???");
		aboutStage.initStyle(StageStyle.UTILITY);
		aboutStage.show();
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

			Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
			primaryStage.setTitle("LuxyRestaurant");
			primaryStage.setScene(new Scene(parent, Util.RESOLUCIO[0], Util.RESOLUCIO[1]));
			//primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
