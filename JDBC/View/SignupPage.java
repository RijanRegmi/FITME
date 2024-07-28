package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignupPage extends JFrame {
    private JPanel signupPanelimg;
    private JPanel signupPanel;
    private JTextField fullnameField;
    private JTextField contactField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JButton signupButton;
    private JLabel signupPage;
    private JButton closeSignup;
    private JLabel accountLabel;
    private JButton loginButton;
    private ImageIcon icon;

    public SignupPage() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        // Maximize the window
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // icon
        icon = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        setIconImage(icon.getImage());

        // Background Image
        ImageIcon img = new ImageIcon("C:/allcode/College Project Java/img/bg.jpg");
        Image image = img.getImage();
        Image scaledImage = image.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        img = new ImageIcon(scaledImage);
        JLabel bg = new JLabel("", img, JLabel.CENTER);
        bg.setBounds(0, 0, 1920, 1080);

        // LayeredPane for layering components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));
        layeredPane.setBounds(0, 0, 1920, 1080);
        layeredPane.setLayout(null);

        // Background Layer
        layeredPane.add(bg, JLayeredPane.DEFAULT_LAYER);

        // Signup Panel Image Container
        signupPanelimg = new JPanel();
        signupPanelimg.setLayout(null);
        signupPanelimg.setBackground(Color.decode("#FAAA70"));
        signupPanelimg.setBounds(250, 100, 1400, 800);
        layeredPane.add(signupPanelimg, JLayeredPane.PALETTE_LAYER);

        // Health Image
        ImageIcon img1 = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        Image image1 = img1.getImage();
        Image scaledImage1 = image1.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        img1 = new ImageIcon(scaledImage1);
        JLabel imgLabel = new JLabel(img1);
        imgLabel.setBounds(0, 100, 700, 500);
        signupPanelimg.add(imgLabel);

        // Trackoo Label
        JLabel textLabel = new JLabel("Trackoo");
        textLabel.setBounds(280, 600, 300, 50);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setForeground(Color.RED);
        signupPanelimg.add(textLabel);

        // Signup Panel for Form
        signupPanel = new JPanel();
        signupPanel.setBackground(Color.WHITE);
        signupPanel.setBounds(710, 10, 680, 780);
        signupPanel.setLayout(null);
        signupPanelimg.add(signupPanel);

        // Close Button
        closeSignup = new JButton("❌");
        closeSignup.setFont(new Font("", Font.BOLD, 26));
        closeSignup.setBounds(0, 5, 70, 70);
        closeSignup.setOpaque(false);
        closeSignup.setContentAreaFilled(false);
        closeSignup.setBorderPainted(false);
        closeSignup.setFocusable(false);
        signupPanelimg.add(closeSignup);

        // Signup Label
        signupPage = new JLabel("Sign Up");
        signupPage.setBackground(Color.WHITE);
        signupPage.setFont(new Font("Arial", Font.BOLD, 24));
        signupPage.setBounds(320, 20, 300, 40);
        signupPanel.add(signupPage);

        // Full Name
        JLabel fullnameLabel = new JLabel("Full Name:");
        fullnameLabel.setFont(new Font("Arial", Font.BOLD, 26));
        fullnameLabel.setBounds(100, 100, 200, 50);
        fullnameField = new JTextField();
        fullnameField.setFont(new Font("Arial", Font.BOLD, 24));
        fullnameField.setBounds(280, 105, 300, 40);
        signupPanel.add(fullnameLabel);
        signupPanel.add(fullnameField);

        // Contact
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setFont(new Font("Arial", Font.BOLD, 26));
        contactLabel.setBounds(100, 180, 200, 50);
        contactField = new JTextField();
        contactField.setFont(new Font("Arial", Font.BOLD, 24));
        contactField.setBounds(280, 185, 300, 40);
        signupPanel.add(contactLabel);
        signupPanel.add(contactField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 26));
        emailLabel.setBounds(100, 260, 200, 50);
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.BOLD, 24));
        emailField.setBounds(280, 265, 300, 40);
        signupPanel.add(emailLabel);
        signupPanel.add(emailField);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 26));
        usernameLabel.setBounds(100, 340, 200, 50);
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 24));
        usernameField.setBounds(280, 345, 300, 40);
        signupPanel.add(usernameLabel);
        signupPanel.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 26));
        passwordLabel.setBounds(100, 420, 200, 50);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 24));
        passwordField.setBounds(280, 425, 300, 40);
        signupPanel.add(passwordLabel);
        signupPanel.add(passwordField);

        // Confirm Password
        JLabel confirmLabel = new JLabel("Confirm:");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 26));
        confirmLabel.setBounds(100, 500, 200, 50);
        confirmField = new JPasswordField();
        confirmField.setFont(new Font("Arial", Font.BOLD, 24));
        confirmField.setBounds(280, 505, 300, 40);
        signupPanel.add(confirmLabel);
        signupPanel.add(confirmField);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 26));
        genderLabel.setBounds(100, 580, 200, 50);
        signupPanel.add(genderLabel);

        // Male Radio Button
        maleRadio = new JRadioButton("Male");
        maleRadio.setBackground(Color.WHITE);
        maleRadio.setFont(new Font("Arial", Font.BOLD, 24));
        maleRadio.setBounds(285, 592, 80, 20);
        maleRadio.setFocusable(false);
        signupPanel.add(maleRadio);

        // Female Radio Button
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBackground(Color.WHITE);
        femaleRadio.setFont(new Font("Arial", Font.BOLD, 24));
        femaleRadio.setBounds(470, 592, 110, 20);
        femaleRadio.setFocusable(false);
        signupPanel.add(femaleRadio);

        // Group the radio buttons
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Signup Button
        signupButton = new JButton("Sign Up");
        signupButton.setBackground(Color.decode("#FAAA70"));
        signupButton.setFont(new Font("Arial", Font.BOLD, 24));
        signupButton.setBounds(340, 645, 150, 50);
        signupButton.setFocusable(false);
        signupPanel.add(signupButton);

        // Account Label
        accountLabel = new JLabel("Already have Account?");
        accountLabel.setBackground(Color.decode("#FAAA70"));
        accountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        accountLabel.setBounds(150, 725, 280, 50);
        signupPanel.add(accountLabel);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setForeground(Color.blue);
        loginButton.setBackground(Color.black);
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        loginButton.setBounds(410, 735, 101, 30);
        loginButton.setFocusable(false);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        signupPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            }
        });

        // signupButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         btnSignupActionPerformed(e);
        //     }
        // });

        add(layeredPane);
        setVisible(true);
    }

    public void SignupButton(ActionListener listener) {
        signupButton.addActionListener(listener);
    }

    public JTextField getFullname() {
        return fullnameField;
    }

    public void setFullname(JTextField fullnameField) {
        this.fullnameField = fullnameField;
    }

    public JTextField getcontact() {
        return contactField;
    }

    public void setcontact(JTextField contactField) {
        this.contactField = contactField;
    }

    public JTextField getemail() {
        return emailField;
    }

    public void setemail(JTextField emailField) {
        this.emailField = emailField;
    }

    public JTextField getusername() {
        return usernameField;
    }

    public void setusername(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getpassword() {
        return passwordField;
    }

    public void setpassword(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JPasswordField getconfirm() {
        return confirmField;
    }

    public void setconfirm(JPasswordField confirmField) {
        this.confirmField = confirmField;
    }

    


    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addRegisterButtonListener(ActionListener listener) {
        signupButton.addActionListener(listener);
    }
 
}
