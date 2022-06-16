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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private TableColumn<Doctor, String> columnPost;
    @FXML
    private TableView<Doctor> tableDoctor;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnDelete;
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
    private Label labelCountRow = new Label();

    private ObservableList<Doctor> dataDoctor = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        designButton();
        try {
            labelCountRow.setText("Количество записей: " + countRow());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleButtonActionPacient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Pacient.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) btnOpenScenePacient.getScene().getWindow();
        stage.close();
        stage.setTitle("Pacient");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleButtonActionDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Doctor.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) btnOpenSceneDoctor.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleButtonActionPolyclinic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Polyclinic.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) btnOpenScenePolyclinic.getScene().getWindow();
        stage.close();
        stage.setTitle("Polyclinic");
        stage.setScene(new Scene(root1));
        stage.show();
    }

//    @FXML
//    void handleButtonActionSchedule(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-.fxml"));
//        Parent root1 = fxmlLoader.load();
//        Stage stage = (Stage) btnOpenScenePolyclinic.getScene().getWindow();
//        stage.close();
//        stage.setTitle("Add Doctor");
//        stage.setScene(new Scene(root1));
//        stage.show();
//    }

    @FXML
    void handleButtonActionAddDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-AddDoctor.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) btnOpenScenePolyclinic.getScene().getWindow();
        stage.close();
        stage.setTitle("Add Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void refreshData() {
        String query = "SELECT * FROM Doctor";
        Connection con = ConnectionDB.getConnection();

        try {
            dataDoctor.clear();
            preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dataDoctor.add(new Doctor(resultSet.getInt("doctorId"), resultSet.getString("Name"), resultSet.getString("Surname"),
                        resultSet.getString("MiddleName"), resultSet.getString("PhoneNumber"),
                        resultSet.getInt("Office"), resultSet.getString("post")));
                tableDoctor.setItems(dataDoctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        refreshData();

        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        colMiddleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
        colOffice.setCellValueFactory(new PropertyValueFactory<>("Office"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        columnPost.setCellValueFactory(new PropertyValueFactory<>("Post"));
    }

    @FXML
    void handleButtonActionDelete(ActionEvent event) {
        try {
            Doctor doctor = tableDoctor.getSelectionModel()
                    .getSelectedItem();
            if (doctor == null) {
                showMessageErrorSelectItem();
                throw new RuntimeException("is not get item");
            }

            String query = "DELETE FROM Doctor WHERE DoctorID  =" + doctor.getDoctorId();
            Connection con = ConnectionDB.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            refreshData();

        } catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMessageErrorSelectItem() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Вы не выбрали поле для удаления!");
        alert.showAndWait();
    }

    private Integer countRow() throws SQLException {
        Connection con = ConnectionDB.getConnection();
        int count;

        try {
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT COUNT(*)  FROM Doctor");
            resultSet.next();
            count = resultSet.getInt(1);
            resultSet.close();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        System.out.println("MyTable has " + count + " row(s).");

        return count;
    }


    private void designButton() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/Pacient.jpg");
            FileInputStream input1 = new FileInputStream("src/main/resources/com/example/testdb/assets/doctor.jpg");
            FileInputStream input2 = new FileInputStream("src/main/resources/assets/polyclinic.jpg");
            FileInputStream input4 = new FileInputStream("src/main/resources/assets/addDoctor.jpg");
            FileInputStream input5 = new FileInputStream("src/main/resources/com/example/testdb/assets/delete.jpg");
            FileInputStream input6 = new FileInputStream("src/main/resources/com/example/testdb/assets/refresh.jpg");


            Image img = new Image(input);
            Image img1 = new Image(input1);
            Image img2 = new Image(input2);
            Image img4 = new Image(input4);
            Image img5 = new Image(input5);
            Image img6 = new Image(input6);

            ImageView imgv = new ImageView(img);
            ImageView imgv1 = new ImageView(img1);
            ImageView imgv2 = new ImageView(img2);
            ImageView imgv4 = new ImageView(img4);
            ImageView imgv5 = new ImageView(img5);
            ImageView imgv6 = new ImageView(img6);

            btnOpenScenePacient.setGraphic(imgv);
            btnOpenSceneDoctor.setGraphic(imgv1);
            btnOpenScenePolyclinic.setGraphic(imgv2);
            btnAddDoctor.setGraphic(imgv4);
            btnDelete.setGraphic(imgv5);
            btnRefresh.setGraphic(imgv6);
            btnRefresh.setGraphic(imgv6);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
