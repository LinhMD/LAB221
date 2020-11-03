/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.structs2;

import LinhMD.registration.UserDAO;
import LinhMD.user.UserDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public class SearchLastNameAction {
    private String searchValue;
    private List<UserDTO> accountList;
    private final String SUCCESS = "suceess";
    
    public SearchLastNameAction() {
    }
    
    
    public String searchLastName() throws Exception {
        UserDAO userDAO = new UserDAO();
        userDAO.searchLastName(searchValue);
        this.setAccountList(userDAO.getAccountList());
        
        return SUCCESS;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<UserDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<UserDTO> accountList) {
        this.accountList = accountList;
    }
    
    
}
