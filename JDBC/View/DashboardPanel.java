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

public class DashboardPanel extends JPanel {
    private Connection conn;
    private String username;
    private String name;
    private JTextField nameField, contactField, emailField;
    private JComboBox<String> genderComboBox;
    private JButton updateButton, uploadButton;
    private JLabel profileLabel;
    private ImageIcon profileImageIcon;
    private MysqlConnection mysqlConnection;

    // Variables for moving text
    private JPanel movingTextPanel;
    private JLabel dashboardTitle;
    private int titleX;
    private int titleY = 10;
    private int titleSpeed = 2;
    private Timer timer;

    public DashboardPanel(String username) {
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
    
        add(uploadButton);

        // Update button
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateButton.setBounds(670, 360, 150, 40);
        updateButton.setBackground(Color.decode("#FAAA70"));
        updateButton.setFocusable(false);

        add(updateButton);




        movingTextPanel = new JPanel();
        movingTextPanel.setBackground(Color.decode("#FAAA70"));
        movingTextPanel.setLayout(null);
        movingTextPanel.setBounds(0, 0, 1670, 60);
        add(movingTextPanel);

        dashboardTitle = new JLabel("Welcome, " + name);
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardTitle.setBounds(titleX, titleY, 100, 40);
        dashboardTitle.setForeground(Color.WHITE);
        ;
        movingTextPanel.add(dashboardTitle);

        // Set initial position of the title to the right edge
        titleX = movingTextPanel.getWidth();

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
}