package com.example.testdb;

import DB.ConnectionDB;
import Model.Diagnsis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DiagnsisController implements Initializable {

    @FXML
    private TableColumn<Diagnsis, String> tableColumnIdDiagnsis;

    @FXML
    private TableColumn<Diagnsis, String> tableColumnNameDiagnsis;

    @FXML
    private TableView<Diagnsis> tableViewDiagnsis;


    private ObservableList<Diagnsis> diagnsisObservableList = FXCollections.observableArrayList();

    public void loadData() {
        String query = "SELECT * FROM Diagnsis";
        Connection con = ConnectionDB.getConnection();

        try {
            diagnsisObservableList.clear();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                diagnsisObservableList.add(new Diagnsis(resultSet.getInt("DiagnsisID"),
                        resultSet.getString("NameDiagnsis")));
                tableViewDiagnsis.setItems(diagnsisObservableList);
            }
        } catch (SQLException e) {
            System.out.println("Table View not update");
            e.printStackTrace();
        }

        tableColumnIdDiagnsis.setCellValueFactory(new PropertyValueFactory<>("DiagnsisID"));
        tableColumnNameDiagnsis.setCellValueFactory(new PropertyValueFactory<>("NameDiagnsis"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}

