/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.structs2;

import LinhMD.registration.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class SignUpAction extends ActionSupport{
    private String userName;
    private String passWord;
    private String comfirm;
    private String fullName;
    private boolean role;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public SignUpAction() {
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

    public String getComfirm() {
        return comfirm;
    }

    public void setComfirm(String comfirm) {
        this.comfirm = comfirm;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
    
    public String signUp(){
        try {
           
            if(new UserDAO().insertAccount(userName, passWord, fullName, role))
                return SUCCESS;
            else
                return FAIL;
        } catch (SQLException | NamingException | ClassNotFoundException ex) {
            return FAIL;
        }
    }
    
    @Override
    public void validate(){
        if(userName == null || userName.trim().isEmpty()){
            addFieldError("userName", "User name required!!!");
        }else if(userName.length() < 6 || userName.length() > 30){
            addFieldError("userName", "user name must in 6-30");
        }
    }
}
