package p0004.dao;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;


public class MyConnection {
    public static Connection makeConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=book_management";
            return DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection connection = makeConnection();
        System.out.println(connection);
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
    }
}
