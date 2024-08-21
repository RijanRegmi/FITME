package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.MysqlConnection;

public class adminDashboardPanel extends JPanel {
    private Connection conn;
    private String username;
    private String name;
    private JTextField nameField, contactField, emailField;
    private JComboBox<String> genderComboBox;
    private JButton updateButton, uploadButton;
    private JLabel profileLabel;
    private ImageIcon profileImageIcon;
    private MysqlConnection mysqlConnection;

    private JPanel movingTextPanel;
    private JLabel dashboardTitle;
    private int titleX;
    private int titleY = 10;
    private int titleSpeed = 2;
    private Timer timer;

    public adminDashboardPanel(String username) {
        this.username = username;
        mysqlConnection = new MysqlConnection();
        this.conn = mysqlConnection.openConnection(); // Initialize the connection

        setLayout(null);
        setBackground(Color.WHITE); // Set main background color
        setPreferredSize(new Dimension(1470, 600));

        // Initialize fields with default values
        nameField = new JTextField("Name");
        contactField = new JTextField("Contact");
        emailField = new JTextField("Email");
        genderComboBox = new JComboBox<>(new String[] { "Male", "Female", "Other" });

        // Set bounds and fonts for fields
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setBounds(600, 160, 300, 30);
        add(nameField);

        contactField.setFont(new Font("Arial", Font.PLAIN, 18));
        contactField.setBounds(600, 210, 300, 30);
        add(contactField);

        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setBounds(600, 260, 300, 30);
        add(emailField);

        genderComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        genderComboBox.setBounds(600, 310, 300, 30);
        add(genderComboBox);

        // Add labels
        addLabel("Name:", 500, 160);
        addLabel("Contact:", 500, 210);
        addLabel("Email:", 500, 260);
        addLabel("Gender:", 500, 310);

        // Profile Picture Section
        profileLabel = new JLabel();
        profileLabel.setBounds(100, 160, 250, 250);
        profileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(profileLabel);

        uploadButton = new JButton("Upload Photo");
        uploadButton.setBounds(150, 420, 150, 30);
        uploadButton.setBackground(Color.decode("#FAAA70"));
        uploadButton.setFocusable(false);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        Image profileImage = ImageIO.read(selectedFile);
                        profileImage = profileImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                        profileImageIcon = new ImageIcon(profileImage);
                        profileLabel.setIcon(profileImageIcon);

                        // Save the file path to the database
                        String filePath = selectedFile.getAbsolutePath();
                        saveProfilePhotoPath(filePath);

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(adminDashboardPanel.this, "Failed to load image.");
                    }
                }
            }
        });
        add(uploadButton);

        // Update button
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateButton.setBounds(670, 360, 150, 40);
        updateButton.setBackground(Color.decode("#FAAA70"));
        updateButton.setFocusable(false);

        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserDetails();
            }
        });

        fetchUserDetails();

        movingTextPanel = new JPanel();
        movingTextPanel.setBackground(Color.decode("#FAAA70"));
        movingTextPanel.setLayout(null);
        movingTextPanel.setBounds(0, 0, 1670, 60);
        add(movingTextPanel);

        dashboardTitle = new JLabel("Welcome, " + name);
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardTitle.setBounds(titleX, titleY, calculateTitleWidth(), 40);
        dashboardTitle.setForeground(Color.WHITE);
        ;
        movingTextPanel.add(dashboardTitle);

        // Set initial position of the title to the right edge
        titleX = movingTextPanel.getWidth();

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveText();
            }
        });
        timer.start();
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setBounds(x, y, 200, 30);
        add(label);
    }

    private void fetchUserDetails() {
        if (conn != null) {
            try {
                String query = "SELECT name, contact, email, gender, profile_photo_path FROM Admin WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    name = rs.getString("name");
                    nameField.setText(name);
                    contactField.setText(rs.getString("contact"));
                    emailField.setText(rs.getString("email"));
                    genderComboBox.setSelectedItem(rs.getString("gender"));

                    // Load profile photo if available
                    String profilePhotoPath = rs.getString("profile_photo_path");
                    if (profilePhotoPath != null && !profilePhotoPath.isEmpty()) {
                        Image profileImage = ImageIO.read(new File(profilePhotoPath));
                        profileImage = profileImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                        profileImageIcon = new ImageIcon(profileImage);
                        profileLabel.setIcon(profileImageIcon);
                    }
                }
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching user details.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Database connection not available.");
        }
    }

    private void saveProfilePhotoPath(String filePath) {
        if (conn != null) {
            try {
                String query = "UPDATE Admin SET profile_photo_path = ? WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, filePath);
                stmt.setString(2, username);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Profile photo updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update profile photo.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating profile photo.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Database connection not available.");
        }
    }

    private void updateUserDetails() {
        if (conn != null) {
            try {
                String query = "UPDATE Admin SET name = ?, contact = ?, email = ?, gender = ? WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nameField.getText());
                stmt.setString(2, contactField.getText());
                stmt.setString(3, emailField.getText());
                stmt.setString(4, (String) genderComboBox.getSelectedItem());
                stmt.setString(5, username);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Details updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating user details.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Database connection not available.");
        }
    }

    private void moveText() {
        titleX -= titleSpeed;
        if (titleX < -calculateTitleWidth()) {
            titleX = movingTextPanel.getWidth();
        }
        dashboardTitle.setBounds(titleX, titleY, calculateTitleWidth(), 40);
        movingTextPanel.repaint();
    }

    private int calculateTitleWidth() {
        FontMetrics metrics = dashboardTitle.getFontMetrics(dashboardTitle.getFont());
        return metrics.stringWidth(dashboardTitle.getText()) + 20; // Add some padding
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the DashboardPanel
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1470, 600);

        // Initialize your connection (you may want to pass this from your LoginPage)
        MysqlConnection mysqlConnection = new MysqlConnection();
        Connection conn = mysqlConnection.openConnection(); // Ensure this is properly initialized

        // Replace with a valid username for testing or test with a static username
        String testUsername = "yo"; // Ensure this username exists in your 'signup' table

        // Create and add DashboardPanel with username
        DashboardPanel panel = new DashboardPanel(testUsername);
        frame.add(panel);

        frame.setVisible(true);
    }
}
