package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	static Scene scene;
	public static boolean isSplashLoaded = false;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			SplashController scontroller = new SplashController();
			scontroller.initialize( primaryStage );
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
    public static Scene getMainScene() {
    	return scene;
    }
}
