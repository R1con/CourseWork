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


    private ObservableList<Diagnsis> data = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    public void loadData() {
        String query = "SELECT * FROM Diagnsis";
        Connection con = ConnectionDB.ConnectionMyDB();

        try {
            data.clear();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new Diagnsis(rs.getInt("DiagnsisID"),
                        rs.getString("NameDiagnsis")));
                tableViewDiagnsis.setItems(data);
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

