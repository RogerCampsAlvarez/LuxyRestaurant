package application.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenjarController {

    @FXML
    private Button btnBack;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	Parent root = (Parent)FXMLLoader.load(getClass().getResource("MainClient.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
	    Stage stage = (Stage) btnBack.getScene().getWindow();
	    stage.setScene(scene);
	    stage.setTitle("???");
	   // stage.initStyle(StageStyle.UTILITY);
	    stage.show();
    }

}
