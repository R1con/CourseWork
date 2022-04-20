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

public class AddDoctorController implements Initializable{

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

    private String query = "INSERT Doctor VALUES (?, ?, ?, ?, ?)";
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Doctor doctor = null;
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
        nameField.setText(null);
        surnameField.setText(null);
        middlenameField.setText(null);
        numberOfficeField.setText(null);
        phoneNumberField.setText(null);
    }

    @FXML
    void handleSaveData(ActionEvent event) throws SQLException {
        connection = ConnectionDB.getConnection();
        String name = nameField.getText();
        String  surname = surnameField.getText();
        String middleName = middlenameField.getText();
        String numberOffice = numberOfficeField.getText();
        String phoneNumber = phoneNumberField.getText();

        if (name.isEmpty() || surname.isEmpty() || middleName.isEmpty() || numberOffice.isEmpty() || phoneNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");
            alert.showAndWait();
        } else {
            int phoneNumber1 = Integer.valueOf(phoneNumberField.getText());
            //Inter numberOffice1 = Integer.valueOf(numberOfficeField.getText());


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, surnameField.getText());
            preparedStatement.setString(3, middlenameField.getText());
            preparedStatement.setInt(4, phoneNumber1);
            //preparedStatement.setInt(5, numberOffice1);
            preparedStatement.executeQuery();
        }
    }

    public void insertData() throws SQLException {
        int phoneNumber = Integer.valueOf(phoneNumberField.getText());
        int numberOffice = Integer.valueOf(numberOfficeField.getText());


        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nameField.getText());
        preparedStatement.setString(2, surnameField.getText());
        preparedStatement.setString(3, middlenameField.getText());
        preparedStatement.setInt(4, phoneNumber);
        preparedStatement.setInt(5, numberOffice);
        preparedStatement.executeQuery();
    }

    public void designButton() {
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
