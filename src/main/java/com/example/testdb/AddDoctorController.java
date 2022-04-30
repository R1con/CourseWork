package com.example.testdb;

import DB.ConnectionDB;
import Model.Doctor;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDoctorController implements Initializable {

    @FXML
    private Button btnBackToSceneDoctor;

    @FXML
    private Button btnResetData;

    @FXML
    private Button btnSaveData;

    @FXML
    private TextField middlenameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberOfficeField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField postField;

    private String query = "INSERT Doctor VALUES (?, ?, ?, ?, ?, ?)";
    private boolean update;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Doctor doctor;
    int doctorId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        designButton();
    }

    @FXML
    void handleBackToSceneDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Doctor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnBackToSceneDoctor.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleResetData(ActionEvent event) {
        clearData();
    }

    @FXML
    void handleSaveData(ActionEvent event) throws SQLException {
        connection = ConnectionDB.getConnection();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String middleName = middlenameField.getText();
        String numberOffice = numberOfficeField.getText();
        String phoneNumber = phoneNumberField.getText();

        if (name.isEmpty() || surname.isEmpty() || middleName.isEmpty() || numberOffice.isEmpty() || phoneNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");
            alert.showAndWait();
        } else {
            getQuery();
            insert();
            clearData();
        }
    }

    private void getQuery() {
        if (!update) {
            query = "INSERT Doctor VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            query = "UPDATE `Doctor` SET "
                    + "`name`=?,"
                    + "`birth`=?,"
                    + "`adress`=?,"
                    + "`email`= ? WHERE id = '" + doctorId + "'";
        }
    }

    private void insert() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, surnameField.getText());
            preparedStatement.setString(3, middlenameField.getText());
            preparedStatement.setInt(4, Integer.parseInt(phoneNumberField.getText()));
            preparedStatement.setInt(5, Integer.parseInt(numberOfficeField.getText()));
            preparedStatement.setString(6, postField.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearData() {
        nameField.setText(null);
        surnameField.setText(null);
        middlenameField.setText(null);
        numberOfficeField.setText(null);
        phoneNumberField.setText(null);
        postField.setText(null);
    }

    private void designButton() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/backArrow.jpg");
            Image img = new Image(input);
            ImageView imgv = new ImageView(img);
            btnBackToSceneDoctor.setGraphic(imgv);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
