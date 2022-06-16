package com.example.testdb;

import DB.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPageController {

    @FXML
    private Button authButton;

    @FXML
    private TextField loginField;


    @FXML
    private PasswordField passwordField;

    private final Connection con = ConnectionDB.getConnection();

    private void showMessageError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showMessageSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void selectDoctorScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Doctor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) authButton.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleButtonAuth(ActionEvent event) throws SQLException, IOException {
        if (loginField.getText().equals("") && passwordField.getText().equals("")) {
            showMessageError("Введите данные");
        } else {
            String query = "SELECT * FROM Users where LoginUser=? and PasswordUser=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, loginField.getText());
            preparedStatement.setString(2, passwordField.getText());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                showMessageSuccess("Вы вошли в систему");
                selectDoctorScene();
            } else {
                showMessageError("неверный логин или пароль");
                loginField.setText(null);
                passwordField.setText(null);
            }
        }
    }
}
