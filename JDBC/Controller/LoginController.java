package Controller;

import java.sql.SQLException;
import DAO.UserDAO;
import Model.LoginModel;
import View.LoginPage;
import View.AdminPage;
import View.UserPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private final UserDAO userdao;
    private final LoginPage loginPage;

    public LoginController(UserDAO userdao, LoginPage loginPage) {
        this.userdao = userdao;
        this.loginPage = loginPage;

        this.loginPage.LoginButton(new loginButtonListener());
    }

    class loginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginPage.getusernameField().getText();
            String password = loginPage.getpasswordField().getText();

            LoginModel logninmodel = new LoginModel(username, password);

            boolean check = false;
            boolean admincheck = false;
            try {
                check = userdao.getdata(logninmodel);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                admincheck = userdao.getadmindata(logninmodel);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (admincheck) {
                AdminPage admin = new AdminPage(username);
                admin.setVisible(true);
                loginPage.dispose();

            }

            else if (check) {
                UserPage user = new UserPage(username);

                user.setVisible(true);
                loginPage.dispose();
            } else {
                loginPage.setMessage("Login fail.");
            }

        }
    }
}
