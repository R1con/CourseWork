package com.example.testdb;

import DB.ConnectionDB;
import Model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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

public class DoctorController implements Initializable {

    @FXML
    private TableColumn<Doctor, String> colMiddleName;

    @FXML
    private TableColumn<Doctor, String> colName;

    @FXML
    private TableColumn<Doctor, Integer> colOffice;

    @FXML
    private TableColumn<Doctor, String> colPhoneNumber;

    @FXML
    private TableColumn<Doctor, String> colSurname;

    @FXML
    private TableColumn<Doctor, Integer> idDoctor;

    @FXML
    private TableView<Doctor> tableDoctor;

    @FXML
    private Button btnOpenScenePacient;

    @FXML
    private Button btnOpenSceneDoctor;

    @FXML
    private Button btnOpenScenePolyclinic;

    @FXML
    private Button btnOpenSceneSchedule;

    @FXML
    private Button btnAddDoctor;

    @FXML
    private Text tst;

    private ObservableList<Doctor> dataDoctor = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        designButton();
    }

    @FXML
    void handleButtonActionPacient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Pacient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnOpenScenePacient.getScene().getWindow();
        stage.close();
        stage.setTitle("Pacient");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleButtonActionDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Doctor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnOpenSceneDoctor.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleButtonActionPolyclinic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Polyclinic.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnAddDoctor.getScene().getWindow();
        stage.close();
        stage.setTitle("Polyclinic");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleButtonActionSchedule(ActionEvent event) {

    }

    @FXML
    void handleButtonActionAddDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-AddDoctor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnOpenScenePolyclinic.getScene().getWindow();
        stage.close();
        stage.setTitle("Add Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void loadData() {
        String query = "SELECT * FROM Doctor";
        Connection con = ConnectionDB.getConnection();

        try {
            dataDoctor.clear();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                dataDoctor.add(new Doctor(rs.getInt("DoctorID"),
                        rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("MiddleName"), rs.getString("PhoneNumber"),
                        rs.getInt("Office")));
                tableDoctor.setItems(dataDoctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        idDoctor.setCellValueFactory(new PropertyValueFactory<>("DoctorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        colMiddleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
        colOffice.setCellValueFactory(new PropertyValueFactory<>("Office"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
    }

    public void designButton() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/Pacient.jpg");
            FileInputStream input1 = new FileInputStream("src/main/resources/assets/doctor.jpg");
            FileInputStream input2 = new FileInputStream("src/main/resources/assets/polyclinic.jpg");
            FileInputStream input3 = new FileInputStream("src/main/resources/assets/schedule.jpg");
            FileInputStream input4 = new FileInputStream("src/main/resources/assets/addDoctor.jpg");

            Image img = new Image(input);
            Image img1 = new Image(input1);
            Image img2 = new Image(input2);
            Image img3 = new Image(input3);
            Image img4 = new Image(input4);

            ImageView imgv = new ImageView(img);
            ImageView imgv1 = new ImageView(img1);
            ImageView imgv2 = new ImageView(img2);
            ImageView imgv3 = new ImageView(img3);
            ImageView imgv4 = new ImageView(img4);

            btnOpenScenePacient.setGraphic(imgv);
            btnOpenSceneDoctor.setGraphic(imgv1);
            btnOpenScenePolyclinic.setGraphic(imgv2);
            btnOpenSceneSchedule.setGraphic(imgv3);
            btnAddDoctor.setGraphic(imgv4);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
