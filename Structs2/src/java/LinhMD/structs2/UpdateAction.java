/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.structs2;

import LinhMD.registration.UserDAO;
import LinhMD.user.UserDTO;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class UpdateAction {
    private String userName;
    private String passWord;
    private String fullName;
    private boolean admin;
    private String lastSearchValue;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    public UpdateAction() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }
    
    public String updateAccount(){
        UserDAO userDAO = new UserDAO();
        try {
            boolean result = userDAO.updateAccount(new UserDTO(userName, passWord, fullName, admin));
            if(result)
                return SUCCESS;
            else
                return FAIL;
        } catch (SQLException | NamingException | ClassNotFoundException ex) {
            return FAIL;
        }
    }
}
