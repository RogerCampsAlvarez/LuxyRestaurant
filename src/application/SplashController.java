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
import javafx.util.Duration;

public class SplashController implements Initializable{

	Stage stage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void initialize(Stage primaryStage) {
		try {
			
			Parent parent = FXMLLoader.load( getClass().getResource( "Splash.fxml" ) );
			primaryStage.setTitle( "LuxyRestaurant" );
			primaryStage.setScene( new Scene( parent , 410 , 465 ) );
			primaryStage.show();
			
			stage = primaryStage;
			
			//Comprova si ja s'ha mostrat el SplashScreen
			if (!Main.isSplashLoaded ) {
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
			
			Main.isSplashLoaded = true; //Verifica que s'ha mostrat
			
			StackPane pane = FXMLLoader.load( getClass().getResource( ("Splash.fxml")));
			
			//Mostra Spalsh
			FadeTransition fadeIn = new FadeTransition( Duration.seconds( 1 ), pane );
			fadeIn.setFromValue( 0 );
			fadeIn.setToValue( 1 );
			fadeIn.setCycleCount( 1 );
			
			//Amaga Splash
			FadeTransition fadeOut = new FadeTransition( Duration.seconds( 1 ), pane );
			fadeOut.setFromValue( 1 );
			fadeOut.setToValue( 0 );
			fadeOut.setCycleCount( 1 );
			
			fadeIn.play();
			
			fadeIn.setOnFinished( e -> {
				fadeOut.play();
			} );
			
			fadeOut.setOnFinished( e -> {
				try {
					MainController mcontroller = new MainController();
					mcontroller.initialize( stage );
				} catch ( Exception ee ) {} 
			} );
		
		} catch( IOException e ) {}
    }

}
