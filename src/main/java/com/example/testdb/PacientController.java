package com.example.testdb;

import DB.ConnectionDB;
import Model.Pacient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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


public class PacientController implements Initializable {

    @FXML
    private Button btnAddPacient;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDoctor;

    @FXML
    private Button btnPacient;

    @FXML
    private Button btnPlyclinic;

    @FXML
    private Button btnRefresh;

    @FXML
    private TableColumn<Pacient, String> columnAdress;

    @FXML
    private TableColumn<Pacient, String> columnBirthday;

    @FXML
    private TableColumn<Pacient, String> columnGender;

    @FXML
    private TableColumn<Pacient, Integer> columnInsurancepPolicy;

    @FXML
    private TableColumn<Pacient, String> columnMiddleName;

    @FXML
    private TableColumn<Pacient, String> columnName;

    @FXML
    private TableColumn<Pacient, Integer> columnPhoneNumber;

    @FXML
    private TableColumn<Pacient, String> columnSurName;

    @FXML
    private TableView<Pacient> tablePacient;

    private ObservableList<Pacient> listPacient = FXCollections.observableArrayList();
    private PreparedStatement preparedStatement = null;
    private Pacient pacient;

    @FXML
    private Label countRow1 = new Label();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        designButton();


        try {
            System.out.println("MyTable has " + countRow() + " row(s).");
            countRow1.setText("Количество записей: " + String.valueOf(countRow()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshTable() {
        String query = "SELECT * FROM Pacient";
        Connection con = ConnectionDB.getConnection();

        try {
            listPacient.clear();
            preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listPacient.add(new Pacient(resultSet.getInt(1),resultSet.getString("name"), resultSet.getString("surname"),
                        resultSet.getString("middleName"), resultSet.getString("Gender"),
                        resultSet.getString("birthday"), resultSet.getString("phoneNumber"),
                        resultSet.getString("adress"), resultSet.getLong("insurancepPolicy")));
                tablePacient.setItems(listPacient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Integer countRow() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        Statement statement = null;
        int count = 0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)  FROM Pacient");
            resultSet.next();
            count = resultSet.getInt(1);
            resultSet.close();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        System.out.println("MyTable has " + count + " row(s).");
        return count;
    }


    public void loadData() throws SQLException {
        refreshTable();

//        countRow1.setText(countRow());

        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnSurName.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        columnMiddleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        columnBirthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        columnAdress.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        columnInsurancepPolicy.setCellValueFactory(new PropertyValueFactory<>("InsurancepPolicy"));

    }

    @FXML
    void handeToDoctor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Doctor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnDoctor.getScene().getWindow();
        stage.close();
        stage.setTitle("Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleToAddPacient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-AddPacient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnAddPacient.getScene().getWindow();
        stage.close();
        stage.setTitle("Add Doctor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Вы не выбрали поле для удаления!");
        alert.showAndWait();
    }

    @FXML
    void handleToDeleteFromTable(ActionEvent event) throws IOException {
        try {
            pacient = tablePacient.getSelectionModel().getSelectedItem();
            if (pacient == null)
                showMessage();


            String query = "DELETE Pacient WHERE PacientID  =" + pacient.getPacientID();
            Connection con = ConnectionDB.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            refreshTable();

        } catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void handleToPacient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Pacient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnPacient.getScene().getWindow();
        stage.close();
        stage.setTitle("Pacient");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void handleToPolyclinic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene-Polyclinic.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) btnPlyclinic.getScene().getWindow();
        stage.close();
        stage.setTitle("Polyclinic");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    void handleToRefresh(ActionEvent event) {
        refreshTable();
    }

    public void designButton() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/Pacient.jpg");
            FileInputStream input1 = new FileInputStream("src/main/resources/com/example/testdb/assets/doctor.jpg");
            FileInputStream input2 = new FileInputStream("src/main/resources/assets/polyclinic.jpg");
            FileInputStream input3 = new FileInputStream("src/main/resources/assets/schedule.jpg");
            FileInputStream input4 = new FileInputStream("src/main/resources/assets/Group 2.jpg");
            FileInputStream input5 = new FileInputStream("src/main/resources/com/example/testdb/assets/delete.jpg");
            FileInputStream input6 = new FileInputStream("src/main/resources/com/example/testdb/assets/refresh.jpg");


            Image img = new Image(input);
            Image img1 = new Image(input1);
            Image img2 = new Image(input2);
            Image img3 = new Image(input3);
            Image img4 = new Image(input4);
            Image img5 = new Image(input5);
            Image img6 = new Image(input6);

            ImageView imgv = new ImageView(img);
            ImageView imgv1 = new ImageView(img1);
            ImageView imgv2 = new ImageView(img2);
            ImageView imgv3 = new ImageView(img3);
            ImageView imgv4 = new ImageView(img4);
            ImageView imgv5 = new ImageView(img5);
            ImageView imgv6 = new ImageView(img6);

            btnPacient.setGraphic(imgv);
            btnDoctor.setGraphic(imgv1);
            btnPlyclinic.setGraphic(imgv2);
            //btnOpenSceneSchedule.setGraphic(imgv3);
            btnAddPacient.setGraphic(imgv4);
            btnDelete.setGraphic(imgv5);
            btnRefresh.setGraphic(imgv6);
            btnRefresh.setGraphic(imgv6);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
