package com.example.testdb;

import DB.ConnectionDB;
import Model.Pacient;
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

public class AddPacientController implements Initializable {

    @FXML
    private TextField adressFIeld;

    @FXML
    private TextField birthdayField;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSaveData;

    @FXML
    private TextField genderField;

    @FXML
    private TextField insurancepPolicyField;

    @FXML
    private TextField middlenameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField surnameField;

    private String query = "INSERT Pacient VALUES (?, ?, ?, ?, ?, ?)";
    private boolean update;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    private int pacientId;
    private Pacient pacient;

    @FXML
    void handleBackScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Pacient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleSaveData(ActionEvent event) {
        connection = ConnectionDB.getConnection();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String middleName = middlenameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String gender = genderField.getText();
        String address = adressFIeld.getText();
        String insurancepPolicy = insurancepPolicyField.getText();

        if (name.isEmpty() || surname.isEmpty() || middleName.isEmpty() || phoneNumber.isEmpty() || gender.isEmpty() || address.isEmpty() || insurancepPolicy.isEmpty()) {
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

    private void clearData() {
        nameField.setText(null);
        surnameField.setText(null);
        middlenameField.setText(null);
        phoneNumberField.setText(null);
    }

    private void getQuery() {
        if (!update) {
            query = "INSERT Pacient VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            query = "UPDATE `Pacient` SET "
                    + "`name`=?,"
                    + "`birth`=?,"
                    + "`adress`=?,"
                    + "`email`= ? WHERE id = '" + pacientId + "'";
        }
    }

    private void insert() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, surnameField.getText());
            preparedStatement.setString(3, middlenameField.getText());
            preparedStatement.setString(4, genderField.getText());
            preparedStatement.setString(5, birthdayField.getText());
            try {
                preparedStatement.setLong(6, Long.parseLong(phoneNumberField.getText()));
                preparedStatement.setInt(8, Integer.parseInt(insurancepPolicyField.getText()));
            } catch (NumberFormatException e) {
                showMessageErrorSelectItem();
                e.printStackTrace();
            }
            preparedStatement.setString(7, adressFIeld.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMessageErrorSelectItem() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Неккоректные данные!");
        alert.showAndWait();
    }
    private void designButton() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/backArrow.jpg");
            Image img = new Image(input);
            ImageView imgv = new ImageView(img);
            btnBack.setGraphic(imgv);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        designButton();
    }
}
