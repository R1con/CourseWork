package com.example.testdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDoctorController implements Initializable{

    @FXML
    private Button btnBackToSceneDoctor;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
