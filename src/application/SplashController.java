package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashController implements Initializable {

	Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {}

	public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Splash.fxml"));
			stage = primaryStage;
			stage.setTitle("LuxyRestaurant");
			stage.setScene(new Scene(parent, 400, 300));
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			stage.show();

			// Comprova si ja s'ha mostrat el SplashScreen
			if (!Main.isSplashLoaded) {
				loadSplashScreen();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * MOSTRAR / AMAGAR SLPASHSCREEN
	 */
	private void loadSplashScreen() {
		try {

			Main.isSplashLoaded = true; // Verifica que s'ha mostrat

			StackPane pane = FXMLLoader.load(getClass().getResource(("Splash.fxml")));

			// Mostra Spalsh
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);

			// Amaga Splash
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			fadeIn.play();

			fadeIn.setOnFinished(e -> {
				fadeOut.play();
			});

			fadeOut.setOnFinished(e -> {
				try {
					MainController mcontroller = new MainController();
					//stage.initStyle(StageStyle.DECORATED);
					mcontroller.initialize(stage);
				} catch (Exception ex) {
				}
			});

		} catch (IOException e) {
		}
	}

}
