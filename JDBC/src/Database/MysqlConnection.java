package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlConnection implements DatabaseConnection {

    private static final Logger LOGGER = Logger.getLogger(MysqlConnection.class.getName());
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE = "user";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

    @Override
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (connection == null) {
                LOGGER.log(Level.SEVERE, "Database connection failed");
            } else {
                LOGGER.log(Level.INFO, "Database connection successful");
            }
            return connection;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error opening database connection", e);
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                LOGGER.log(Level.INFO, "Connection closed");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error closing connection", e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error executing query", e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error executing update", e);
            return -1;
        }
    }
}
