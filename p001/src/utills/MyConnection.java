package utills;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MyConnection {
    public static Connection makeConnection(){
        try {
            Properties properties = new Properties();
            properties.load(MyConnection.class.getResourceAsStream("config.properties"));
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = properties.getProperty("jdbc.url");
            return DriverManager.getConnection(url, properties.getProperty("jdbc.account"), properties.getProperty("jdbc.password"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection connection = makeConnection();
        System.out.println(connection);
    }
}
