package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    public static Connection ConnectionMyDB(){
        Connection con = null;

        String url = "jdbc:sqlserver://localhost:1433;databaseName=PatientAdmissionRecords;encrypt=true;trustServerCertificate=true;";
        String username = "Course";
        String password = "FVVXtcwjjX";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to DB is true");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("flase");
        }

        return con;
    }
}
