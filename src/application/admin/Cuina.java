package application.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import application.Strings;
import application.Util;
import application.Objectes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cuina {
	@FXML
	TableView tv_plats;
	@FXML
	TableColumn tc_un;
	@FXML
	TableColumn tc_dos;
	
			
	Plat p = new Plat(stmt,1);
	
	public void initialize() {
		
	}
	
}
