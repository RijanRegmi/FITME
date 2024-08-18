package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.MysqlConnection;
import Model.LoginModel;
import Model.SignupModel;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    MysqlConnection mysql = new MysqlConnection();
    Connection conn = mysql.openConnection();

    public boolean getdata(LoginModel loginmodel) throws SQLException {
        boolean status = false;
        String query = "SELECT * FROM signup WHERE username = ? AND password = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, loginmodel.getusernameField());
            pst.setString(2, loginmodel.getpasswordField());

            ResultSet resultSet = pst.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error accessing user data", e);
        }
        return status;
    }

    public boolean getadmindata(LoginModel Adminloginmodel) throws SQLException {
        boolean status = false;
        String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, Adminloginmodel.getusernameField());
            pst.setString(2, Adminloginmodel.getpasswordField());

            ResultSet resultSet = pst.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error accessing admin data", e);
        }
        return status;
    }

    public boolean insertdata(SignupModel signupmodel) throws SQLException {
        boolean status = false;
        String query = "INSERT INTO signup (name, contact, email, username, password,gender) VALUES (?, ?, ?, ?,?,?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, signupmodel.getFullname());
            pst.setString(2, signupmodel.getContact());
            pst.setString(3, signupmodel.getEmail());
            pst.setString(4, signupmodel.getUsername());
            pst.setString(5, signupmodel.getPassword());
            pst.setString(6, signupmodel.getGender());

            int rowsAffected = pst.executeUpdate(); // Use executeUpdate for INSERT operations
            status = rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting user data", e);
        }
        return status;
    }
}
