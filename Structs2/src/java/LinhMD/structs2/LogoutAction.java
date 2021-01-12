/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.structs2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author USER
 */
public class LogoutAction {
    private final String SUCCESS = "success";
    public LogoutAction() {
    }
    
    public String logout() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put("USERNAME", "");
        return SUCCESS;
    }
    
}
