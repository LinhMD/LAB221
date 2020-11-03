/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.utilities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author USER
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection() throws ClassNotFoundException, SQLException, NamingException {
//        //1. load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. tao connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentManager";
//        //3. open connection
//        return DriverManager.getConnection(url, "sa", "123456");
        //1.get current context
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource da = (DataSource) tomcatContext.lookup("SE1419DS");
        return da.getConnection();
        
    }
}
