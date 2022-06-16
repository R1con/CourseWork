package com.example.testdb;

import DB.ConnectionDB;
import Model.Polyclinic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PolyclinicController implements Initializable {

    @FXML
    private Button btnDoctor;

    @FXML
    private Button btnPacient;

    @FXML
    private Button btnPolyclinic;

    @FXML
    private TableColumn<Polyclinic, String> columnAddress;

    @FXML
    private TableColumn<Polyclinic, String> columnNamePolyclinic;

    @FXML
    private TableView<Polyclinic> tablePolyclinic;

    private ObservableList<Polyclinic> dataPolyclinic = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        designButton();

    }


    @FXML
    public void refreshTable() {
        String query = "SELECT * FROM Polyclinic";
        Connection con = ConnectionDB.getConnection();

        try {
            dataPolyclinic.clear();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dataPolyclinic.add(new Polyclinic(resultSet.getString("NamePolyclinic"),
                        resultSet.getString("adress")));
            }
            tablePolyclinic.setItems(dataPolyclinic);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadData() {
        Connection con = ConnectionDB.getConnection();
        refreshTable();

        columnNamePolyclinic.setCellValueFactory(new PropertyValueFactory<>("NamePolyclinic"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
    }

    @FXML
    void handleToDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Doctor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnDoctor.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleToPacient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Pacient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnPacient.getScene().getWindow();
        stage.close();
        stage.setTitle("Pacient");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleToPolyclinic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Polyclinic.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnPacient.getScene().getWindow();
        stage.close();
        stage.setTitle("Polyclinic");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    private void designButton() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/Pacient.jpg");
            FileInputStream input1 = new FileInputStream("src/main/resources/com/example/testdb/assets/doctor.jpg");
            FileInputStream input2 = new FileInputStream("src/main/resources/assets/polyclinic.jpg");

            Image img = new Image(input);
            Image img1 = new Image(input1);
            Image img2 = new Image(input2);

            ImageView imgv = new ImageView(img);
            ImageView imgv1 = new ImageView(img1);
            ImageView imgv2 = new ImageView(img2);

            btnPacient.setGraphic(imgv);
            btnDoctor.setGraphic(imgv1);
            btnPolyclinic.setGraphic(imgv2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
