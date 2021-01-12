/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.structs2;

import LinhMD.registration.UserDAO;

/**
 *
 * @author USER
 */
public class DeleteAction {
    private String pk;
    private String lastSearchValue;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public DeleteAction() {
    }

    public String getPk() {
        
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }
    
    
    public String deleteAccount() throws Exception{
        UserDAO userDAO = new UserDAO();
        boolean deleteAccount = userDAO.deleteAccount(pk);
        if(deleteAccount)
            return SUCCESS;
        else
            return FAIL;
        
    }
}
