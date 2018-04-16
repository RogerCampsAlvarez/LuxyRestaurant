package application;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashController {

	Stage splashStage;
	Stage loadStage = new Stage();
	Stage nextStage = new Stage();

	public void initialize(Stage primaryStage) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Splash.fxml"));
			splashStage = primaryStage;
			splashStage.setTitle("LuxyRestaurant");
			splashStage.setScene(new Scene(parent, 400, 300));
			splashStage.initStyle(StageStyle.UNDECORATED);
			splashStage.show();

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
			FadeTransition fadeIn = new FadeTransition(Duration.millis(500), pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);

			// Amaga Splash
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			fadeIn.play();
			//new Thread() {
				//@Override
			//	public void run() {
					loadProgressBar();
			//	}
			//}.start();
			
			fadeIn.setOnFinished(e -> {
				fadeOut.play();
			});

			fadeOut.setOnFinished(e -> {
				try {
					loadStage.close();
					splashStage.close();
					Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));

					nextStage.setTitle("LuxyRestaurant");
					nextStage.setScene(new Scene(parent));
					nextStage.initStyle(StageStyle.DECORATED);
					nextStage.show();

					/*MainController mcontroller = new MainController();
					mcontroller.initialize(splashStage);*/
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

		} catch (IOException e) {
		}
	}

	protected void loadProgressBar() {
		int w=255,h=20;
		Pane root = new Pane();
		Canvas canvas = new Canvas(w, h);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		progres(gc,w,h);

		root.getChildren().add(canvas);
		Scene scene = new Scene(root, w, h, Color.WHITESMOKE);
		loadStage.initStyle(StageStyle.UNDECORATED);
		loadStage.setScene(scene);
		loadStage.show();
	}
	
	private void progres(GraphicsContext gc, int w, int h) {
		new Thread() { //Thread per poder fer que es visualitzi mentres es carrega
		//Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
				for (int x = 0; x <= w; x++) { //Green
					gc.setFill(Color.rgb(0,255,0));
					gc.fillRect(x, 1, 1, h);
					
					//Espera 5ms entre cada columna
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
