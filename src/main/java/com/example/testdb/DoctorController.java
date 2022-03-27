package com.example.testdb;

import DB.ConnectionDB;
import Model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class DoctorController implements Initializable {

    @FXML
    private TableColumn<Doctor, String> colMiddleName;

    @FXML
    private TableColumn<Doctor, String> colName;

    @FXML
    private TableColumn<Doctor, Integer> colOffice;

    @FXML
    private TableColumn<Doctor, String> colPhoneNumber;

    @FXML
    private TableColumn<Doctor, String> colSurname;

    @FXML
    private TableColumn<Doctor, Integer> idDoctor;

    @FXML
    private TableView<Doctor> tableDoctor;


    private ObservableList<Doctor> data = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    public void loadData() {
        String query = "SELECT * FROM Doctor";
        Connection con = ConnectionDB.ConnectionMyDB();

        try {
            data.clear();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new Doctor(rs.getInt("DoctorID"),
                        rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("MiddleName"), rs.getString("PhoneNumber"),
                        rs.getInt("Office")));
                tableDoctor.setItems(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idDoctor.setCellValueFactory(new PropertyValueFactory<>("DoctorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        colMiddleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
        colOffice.setCellValueFactory(new PropertyValueFactory<>("Office"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

}