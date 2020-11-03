/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinhMD.registration;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class RegistrationCreateError implements Serializable{
    private String userNameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatched;
    private String fullnameLengthErr;
    private String usernameIsExisted;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String userNameLengthErr, String passwordLengthErr, String confirmNotMatched, String fullnameLengthErr, String usernameIsExisted) {
        this.userNameLengthErr = userNameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmNotMatched = confirmNotMatched;
        this.fullnameLengthErr = fullnameLengthErr;
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUserNameLengthErr() {
        return userNameLengthErr;
    }

    public void setUserNameLengthErr(String userNameLengthErr) {
        this.userNameLengthErr = userNameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
}
