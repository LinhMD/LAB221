package p0004.dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;


public class MyConnection {
    public static Connection makeConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=book_management";
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=p0013";
            return DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection connection = makeConnection();
        System.out.println(connection);
        
    }
}
