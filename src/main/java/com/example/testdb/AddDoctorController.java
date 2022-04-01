package com.example.testdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDoctorController implements Initializable{

    @FXML
    private Button btnBackToSceneDoctor;

    @FXML
    private Button btnResetData;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
