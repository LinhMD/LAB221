/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0004.dao;

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author USER
 */
public class UserDAO {
    public boolean login(String userid, String password) throws Exception{
        String sql = "SELECT u.id, u.password, u.status\n" +
                        "FROM _user u\n" +
                        "WHERE u.id = ? AND u.password = ? AND u.status = ?";
        return !SQLQuery.executeQuery(sql, userid, password, 1).isEmpty();
    }
    
    public List<String> getUserNameAndRole(String userid) throws Exception{
        String sql = "SELECT u.user_name, u.role\n" 
                + "FROM _user u\n"
                + "WHERE u.id = ?";
        Vector<Vector<String>> results = SQLQuery.executeQuery(sql, userid);
        return results.get(0);
    }
    public static void main(String[] args) {
        try {
            System.out.println(new UserDAO().login("linhmd", "spirit1233"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
