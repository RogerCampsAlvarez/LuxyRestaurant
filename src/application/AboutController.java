package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AboutController {
	@FXML
	Button btnCloseAbout;

	@FXML
	Label noms;

	@FXML
	void cmdCloseAbout(ActionEvent event) {
		Stage stage = (Stage) btnCloseAbout.getScene().getWindow();
		stage.close();
	}
}
