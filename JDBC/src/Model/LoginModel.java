package Model;

public class LoginModel {
    private String usernameField;
    private String passwordField;

    public LoginModel(String usernameField, String passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    public String getusernameField() {
        return usernameField;
    }

    public void getusernameField(String usernameField) {
        this.usernameField = usernameField;
    }

    public String getpasswordField() {
        return passwordField;
    }

    public void getpasswordField(String passwordField) {
        this.passwordField = passwordField;
    }
}
