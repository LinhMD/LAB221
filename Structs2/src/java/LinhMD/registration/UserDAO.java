/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.registration;

import LinhMD.user.UserDTO;
import LinhMD.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class UserDAO implements Serializable{
    public boolean checkLogin(String userName, String password) throws SQLException, ClassNotFoundException, NamingException{
        //1. make connection
       
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            cn = DBHelpers.makeConnection();
            //2. create connection String
            if(cn != null){
                String sql = "select username, password "
                        + "from tblUser "
                        + "where username = ? and password = ?";
                //3.Create statement
                statement = cn.prepareStatement(sql);
                statement.setString(1, userName);
                statement.setString(2, password);
                //4.execute query
                resultSet = statement.executeQuery();
                return resultSet.next();
            }//end if connection is connected
        }finally{
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(cn != null) cn.close();
        }
        return false;
    }
    
    private List<UserDTO> accountList;

    public List<UserDTO> getAccountList() {
        return accountList;
    }
    
    
    public void searchLastName(String searchValue) throws ClassNotFoundException, SQLException, NamingException{
         //1. make connection
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            cn = DBHelpers.makeConnection();
            //2. create connection String
            if(cn != null){
                String sql = "select u.username, u.lastname, u.password, u.isAdmin\n" +
                                "from tblUser u\n" +
                                "where u.lastname like ?";
                //3.Create statement
                statement = cn.prepareStatement(sql);
                statement.setString(1, "%" + searchValue + "%");
                //4.execute query
                resultSet = statement.executeQuery();
                //5. process result
                
                while(resultSet.next()){
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String fullname = resultSet.getString("lastname");
                    boolean role = resultSet.getBoolean("isAdmin");
                    if(this.accountList == null){
                        this.accountList = new Vector<>();
                    }
                    this.accountList.add(new UserDTO(username, password, fullname, role));
                }
            }//end if connection is connected
        }finally{
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(cn != null) cn.close();
        }
    }
    
    public boolean deleteAccount(String userName) throws ClassNotFoundException, SQLException, NamingException{
        
        Connection cn = null;
        PreparedStatement statement = null;
        
        try{
            cn = DBHelpers.makeConnection();
            //2. create connection String
            if(cn != null){
                String sql = "delete "
                        + "from tblUser "
                        + "where username = ?";
                //3.Create statement
                statement = cn.prepareStatement(sql);
                statement.setString(1, userName);
                int row = statement.executeUpdate();
                if(row > 0)
                    return true;
            }//end if connection is connected
        }finally{
           
            if(statement != null) statement.close();
            if(cn != null) cn.close();
        }
        return false;
    }
    public boolean updateAccount(UserDTO user) throws SQLException, ClassNotFoundException, NamingException{
         
        Connection cn = null;
        PreparedStatement statement = null;
        
        try{
            cn = DBHelpers.makeConnection();
            //2. create connection String
            if(cn != null){
                String sql = "update tblUser\n" +
                                "set isAdmin = ?, lastname = ?, password = ?\n" +
                                "where username = ?";
                //3.Create statement
                statement = cn.prepareStatement(sql);
                statement.setBoolean(1, user.isRole());
                statement.setString(2, user.getFullName());
                statement.setString(3, user.getPassWord());
                statement.setString(4, user.getUserName());
                int row = statement.executeUpdate();
                if(row > 0)
                    return true;
            }//end if connection is connected
        }finally{
           
            if(statement != null) statement.close();
            if(cn != null) cn.close();
        }
        return false;
    }
    
    public boolean insertAccount(String userName, String password, String fullname, boolean role) throws SQLException, ClassNotFoundException, NamingException{
        Connection cn = null;
        PreparedStatement statement = null;
        
        try{
            cn = DBHelpers.makeConnection();
            //2. create connection String
            if(cn != null){
                String sql = "insert into tblUser(username, password, lastname, isAdmin)\n" +
                        "values(?, ?, ?, ?)";
                //3.Create statement
                statement = cn.prepareStatement(sql);
                statement.setString(1, userName);
                statement.setString(2, password);
                statement.setString(3, fullname);
                statement.setBoolean(4, role);
                int row = statement.executeUpdate();
                if(row > 0)
                    return true;
            }//end if connection is connected
        }finally{
            if(statement != null) statement.close();
            if(cn != null) cn.close();
        }
        return false;
       
    }
    
}
