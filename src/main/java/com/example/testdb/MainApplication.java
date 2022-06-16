package com.example.testdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Scene-Doctor.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login-Scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 667, 462);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}