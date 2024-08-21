package Controller;

import Model.SignupModel;
import DAO.UserDAO;
import View.SignupPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignupController {
    private final SignupPage signup;
    private final UserDAO userdao;

    public SignupController(SignupPage signup, UserDAO userdao) {
        this.signup = signup;
        this.userdao = userdao;

        this.signup.addRegisterButtonListener(new RegisterButtonListener());
    }

    class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fullName = signup.getFullname().getText();
            String contact = signup.getcontact().getText();
            String email = signup.getemail().getText();
            String username = signup.getusername().getText();
            String password = signup.getpassword().getText();
            String confirmpassword = signup.getconfirm().getText();
            String gender = signup.getSelectedGender();

            // Password and confirmation validation
            if (!password.equals(confirmpassword)) {
                signup.showMessage("Passwords do not match!");
                return;
            }

            // Password length validation
            if (password.length() < 8) {
                signup.showMessage("Password must be at least 8 characters long.");
                return;
            }

            // Contact number validation: check if it contains exactly 10 digits and is
            // numeric

            // Proceed with the registration logic if all validations pass
            SignupModel signupmodel = new SignupModel(fullName, contact, email, username, password, gender,
                    confirmpassword);
            boolean isRegistered = false;

            try {
                isRegistered = userdao.insertdata(signupmodel);
            } catch (SQLException ex) {
                ex.printStackTrace();
                signup.showMessage("An error occurred while processing your registration. Please try again later.");
                return;
            }

            if (isRegistered) {
                signup.showMessage("Registration successful!");
            }
        }

    }

}
