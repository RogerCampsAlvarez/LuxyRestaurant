package application.admin;

import java.io.IOException;

import application.Strings;
import application.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TaulesController {
	
	@FXML
    private Button btnBack;
	
	@FXML
	private TableView<Taula> taulesTableView;
	
	ObservableList<Taula> olTaulesList = FXCollections.observableArrayList();


    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
		Scene scene = new Scene(root);
	    Stage stage = (Stage) btnBack.getScene().getWindow();
		Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
    }
    
    public void initialize() {
		//System.out.println("Hola");
    	
    	loadTaules();
		
	}

	private void loadTaules() {
		//TODO Load from DB
		Taula t1 = new Taula("Taula 1",10);
		Taula t2 = new Taula("Taula 2",6);
		
		olTaulesList.add(t1);
		olTaulesList.add(t2);
		taulesTableView.setItems(olTaulesList);

		//taulesTableView.setEditable(true);
        
		 TableColumn<Taula,String> nom = new TableColumn<Taula,String>("Numero taula");
		 nom.setCellValueFactory(new PropertyValueFactory<Taula, String>("nom"));
		 
		 TableColumn<Taula,String> capacitat = new TableColumn<Taula,String>("Capacitat taula");
		 capacitat.setCellValueFactory(new PropertyValueFactory<Taula, String>("capacitat"));
		 
		
		taulesTableView.getColumns().addAll(nom,capacitat);

	}
}
