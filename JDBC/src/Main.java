
import View.LoginPage;
import View.SignupPage;
import Controller.LoginController;
import Controller.SignupController;
import DAO.UserDAO;
import Model.SignupModel;

public class Main {
    public static void main(String[] args) {
        openLoginPage();
    }

    public static void openLoginPage() {
        LoginPage login = new LoginPage();
        UserDAO ud = new UserDAO();
        LoginController lc = new LoginController(ud, login);
            // SignupController Sc = new SignupController(view, ud);

    }

    public static void openSignupPage(){
    UserDAO ud = new UserDAO();
    SignupPage view = new SignupPage();

    SignupController Sc = new SignupController(view,ud);
    }
}
