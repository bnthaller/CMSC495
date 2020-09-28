package group2.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBConnection {
    
    Connection conn;
    private String url = "jdbc:mysql://localhost/topshelf";
    
    public DBConnection() {
        try {
            conn = DriverManager.getConnection(url, "root", "password");
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}