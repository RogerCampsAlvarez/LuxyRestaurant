package application.admin;

import application.Strings;
import application.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BarraController {
    @FXML
    private Label nom;
    @FXML
    private Label taula;
    @FXML
    private Label hora;
    @FXML
    public TableView<Beguda> taula_begudes;
    @FXML
    public Button btnBack;

    List<Beguda> llista = new ArrayList<Beguda>();
    ObservableList<Beguda> llistaBegudes = FXCollections.observableList(llista);
    Parent root;


    public void initialize(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/sample/Barra.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        Date date = new Date();
        System.out.println(date.getMinutes());
    }

    @FXML
    public void crearEntrada(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        buscarBD();
    }

    @FXML
    public void completarBeguda(ActionEvent actionEvent) {
        taula_begudes.getItems().remove(taula_begudes.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void clickItem(MouseEvent event) {
        nom.setText(taula_begudes.getSelectionModel().getSelectedItem().getNom());
        taula.setText(taula_begudes.getSelectionModel().getSelectedItem().getTaula());
        hora.setText(taula_begudes.getSelectionModel().getSelectedItem().getHora());
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/application/admin/MainAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Util.openGUI(scene, stage, Strings.TITLE_MAIN_ADMIN);
    }


    public Statement connexio() {
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://144.217.11.3:5432/luxyrestaurant", "luxy", "LuxyRestaurant");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stmt;
    }


    public void buscarBD() throws ClassNotFoundException, SQLException {
        Statement stmt = connexio();
        int id;
        int id_taula;
        int id_beguda;
        String taula = "";
        String beguda = "";
        Date date;


        System.out.println("Buscant a la base de dades...");
        try {
            ResultSet rs = stmt.executeQuery("Select * from comandes");
            while (rs.next()) {
                date = new Date();
                id = rs.getInt("id");
                id_taula = rs.getInt("taula");
                id_beguda = rs.getInt("beguda");
                beguda = buscarBeguda(id_beguda);
                taula = buscarTaula(id_taula);
                //borrarLinea(id);

                pujarATableview(taula, beguda, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ha acavat de buscar a la base de dades");
    }


    private void borrarLinea(int id) throws SQLException {
        Statement stmt = connexio();

        try {
            stmt.executeUpdate("delete from comandes where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }


    private void pujarATableview(String taula, String beguda, Date Dhora) {
        String time = modificarHora(Dhora);
        llistaBegudes.add(new Beguda(beguda, taula, time));
        taula_begudes.setItems(llistaBegudes);
    }


    private String modificarHora(Date Dhora) {
        String hora = Integer.toString(Dhora.getHours());
        String minuts;
        String time;

        if (Dhora.getMinutes() < 10) {
            minuts = "0" + Dhora.getMinutes();
        } else {
            minuts = Integer.toString(Dhora.getMinutes());
        }

        time = hora + ":" + minuts;


        return time;
    }


    public String buscarBeguda(int id) throws SQLException {
        String nom = "";
        ResultSet rs2 = null;
        Statement stmt = connexio();

        try {
            rs2 = stmt.executeQuery("select nom from begudes where id = " + id);
            while (rs2.next()) {
                nom = rs2.getString("nom");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs2.close();
            stmt.close();
        }

        return nom;
    }


    public String buscarTaula(int id) throws SQLException {
        String nom = "";
        ResultSet rs2 = null;
        Statement stmt = connexio();

        try {
            rs2 = stmt.executeQuery("select nom from taules where id = " + id);
            while (rs2.next()) {
                nom = rs2.getString("nom");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs2.close();
            stmt.close();
        }

        return nom;
    }
}