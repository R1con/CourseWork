package com.example.testdb;

import DB.ConnectionDB;
import Model.Polyclinic;
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

public class PolyclinicController implements Initializable {

    @FXML
    private TableColumn<Polyclinic, String> columnAddress;

    @FXML
    private TableColumn<Polyclinic, String> columnNamePolyclinic;

    @FXML
    private TableView<Polyclinic> tablePolyclinic;

    private ObservableList<Polyclinic> dataPolyclinic = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;


    public void loadData() {
        String query = "SELECT * FROM Polyclinic";
        Connection con = ConnectionDB.getConnection();

        try {
            dataPolyclinic.clear();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                dataPolyclinic.add(new Polyclinic(rs.getString("NamePolyclinic"),
                        rs.getString("adress")));
            }

            tablePolyclinic.setItems(dataPolyclinic);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        columnNamePolyclinic.setCellValueFactory(new PropertyValueFactory<>("NamePolyclinic"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
