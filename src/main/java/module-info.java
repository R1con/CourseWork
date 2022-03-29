module com.example.testdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;


    opens com.example.testdb to javafx.fxml;
    exports com.example.testdb;
    exports DB;
    opens DB to javafx.fxml;
    exports Model;
    opens Model to javafx.fxml;
}