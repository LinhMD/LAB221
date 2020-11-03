/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.structs2;

import LinhMD.registration.UserDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author USER
 */
public class LoginAction {
    
    private String username;
    private String password;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    public LoginAction() {
    }
    
    public String checkLogin() throws Exception {
        UserDAO dao = new UserDAO();
        boolean checkLogin = dao.checkLogin(username, password);
        String url = FAIL;
        if(checkLogin){
            url = SUCCESS;
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("USERNAME", username);
            
        }
        return url;
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
