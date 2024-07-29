package Model;

import java.math.BigInteger;


public class SignupModel {
    private String fullnameField;
    private String contactField;
    private String emailField;
    private String usernameField;
    private String passwordField;
    private String confirmField;
    // private String maleRadioButton;

    public SignupModel(String fullnameField, String contactField, String emailField, String usernameField, String passwordField,String confirmField){
        this.fullnameField = fullnameField;
        this.contactField = contactField;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.confirmField = confirmField;

    }

    public String getFullname() {
        return fullnameField;
    }

    public void setFullname(String fullnameField) {
        this.fullnameField = fullnameField;
    }

    public String getcontact() {
        return contactField;
    }

    public void setcontact(String contactField) {
        this.contactField = contactField;
    }

    public String getemail() {
        return emailField;
    }

    public void setemail(String emailField) {
        this.emailField = emailField;
    }

    public String getusername() {
        return usernameField;
    }

    public void setusername(String usernameField) {
        this.usernameField = usernameField;
    }

    public String getpassword() {
        return passwordField;
    }

    public void setpassword(String passwordField) {
        this.passwordField = passwordField;
    }

    public String getconfirm() {
        return confirmField;
    }

    public void setconfirm(String confirmField) {
        this.confirmField = confirmField;
    }

    // public boolean isMaleSelected() {
    //     return maleRadioButton.isSelected();
    // }


}
       
