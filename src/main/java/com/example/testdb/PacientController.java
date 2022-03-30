package com.example.testdb;

import DB.ConnectionDB;
import Model.Pacient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PacientController implements Initializable {

    @FXML
    private TableColumn<Pacient, String> columnAdress;

    @FXML
    private TableColumn<Pacient, String> columnBirthday;

    @FXML
    private TableColumn<Pacient, String> columnGender;

    @FXML
    private TableColumn<Pacient, Integer> columnInsurancepPolicy;

    @FXML
    private TableColumn<Pacient, String> columnMiddleName;

    @FXML
    private TableColumn<Pacient, String> columnName;

    @FXML
    private TableColumn<Pacient, Integer> columnPhoneNumber;

    @FXML
    private TableColumn<Pacient, String> columnSurName;

    @FXML
    private TableView<Pacient> tablePacient;

    private ObservableList<Pacient> data = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    public void loadData() {
        String query = "SELECT * FROM Pacient";
        Connection con = ConnectionDB.getConnection();

        try {
            data.clear();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new Pacient(rs.getString("name"), rs.getString("surname"),
                        rs.getString("middleName"), rs.getString("Gender"),
                        rs.getString("birthday"), rs.getString("phoneNumber"),
                        rs.getString("adress"), rs.getInt("insurancepPolicy")));
                tablePacient.setItems(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurName.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnMiddleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        columnBirthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        columnAdress.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        columnInsurancepPolicy.setCellValueFactory(new PropertyValueFactory<>("InsurancepPolicy"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
