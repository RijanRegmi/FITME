import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JPanel loginPanelimg;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel loginPage;
    private JButton close;
    private JButton forgotPassword;
    private JLabel accountLabel;
    private JButton signupButton;
    private ImageIcon icon;

    public LoginPage() {
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        // icon
        icon = new ImageIcon("C:/allcode/FITME/JDBC/img/health.png");
        setIconImage(icon.getImage());

        // Maximize the window
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Background Image
        ImageIcon img = new ImageIcon("C:/allcode/FITME/JDBC/img/bg.jpg");
        Image image = img.getImage();
        Image scaledImage = image.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        img = new ImageIcon(scaledImage);

        JLabel bg = new JLabel("", img, JLabel.CENTER);
        bg.setBounds(0, 0, 1920, 1080);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));
        layeredPane.setLayout(null);

        // Background Layer
        layeredPane.add(bg, JLayeredPane.DEFAULT_LAYER);

        // Login Panel Image Container
        loginPanelimg = new JPanel();
        loginPanelimg.setLayout(null);
        loginPanelimg.setBackground(Color.decode("#FAAA70"));
        loginPanelimg.setBounds(250, 100, 1400, 800);
        layeredPane.add(loginPanelimg, JLayeredPane.PALETTE_LAYER);

        // Add health image to loginPanelimg
        ImageIcon img1 = new ImageIcon("C:/allcode/FITME/JDBC/img/health.png");
        Image image1 = img1.getImage();
        Image scaledImage1 = image1.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        img1 = new ImageIcon(scaledImage1);

        JLabel imgLabel = new JLabel(img1);
        imgLabel.setBounds(0, 100, 700, 500);
        loginPanelimg.add(imgLabel);

        // login Image
        ImageIcon loginimg = new ImageIcon("C:/allcode/FITME/JDBC/img/login.png");
        Image loginimage = loginimg.getImage();
        Image scaledloginImage = loginimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        loginimg = new ImageIcon(scaledloginImage);
        JLabel loginbg = new JLabel("", loginimg, JLabel.CENTER);
        loginbg.setBounds(0, 0, 30, 20);

        // Add text to loginPanelimg
        JLabel textLabel = new JLabel("Trackoo");
        textLabel.setBounds(280, 600, 300, 50);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setForeground(Color.RED);
        loginPanelimg.add(textLabel);

        // CloseButton
        close = new JButton("❌");
        close.setFont(new Font("", Font.BOLD, 26));
        close.setBounds(0, 5, 70, 70);
        close.setOpaque(false);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.setFocusable(false);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginPanelimg.add(close);

        // Login Panel for Form
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBounds(710, 10, 680, 780);
        loginPanel.setLayout(null);
        loginPanelimg.add(loginPanel);

        // loginLabel
        loginPage = new JLabel("Login");
        loginPage.setFont(new Font("Arial", Font.BOLD, 24));
        loginPage.setBounds(320, 20, 300, 40);
        loginPanel.add(loginPage);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 26));
        usernameLabel.setBounds(290, 100, 200, 50);
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 24));
        usernameField.setBounds(210, 155, 300, 40);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 26));
        passwordLabel.setBounds(290, 250, 200, 50);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 24));
        passwordField.setBounds(210, 305, 300, 40);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        // Forgot Password
        forgotPassword = new JButton("Forgot Password?");
        forgotPassword.setFont(new Font("", Font.BOLD, 26));
        forgotPassword.setBounds(240, 455, 263, 33);
        forgotPassword.setForeground(Color.BLUE);
        forgotPassword.setOpaque(false);
        forgotPassword.setContentAreaFilled(false);
        forgotPassword.setBorderPainted(false);
        loginPanel.add(forgotPassword);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.decode("#FAAA70"));
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        loginButton.setBounds(270, 525, 200, 50);
        loginButton.setFocusable(false);
        loginPanel.add(loginButton);
        loginButton.add(loginbg);

        // Account Label
        accountLabel = new JLabel("Don't have an account?");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 22));
        accountLabel.setBounds(180, 655, 250, 50);
        loginPanel.add(accountLabel);

        // Signup Button
        signupButton = new JButton("Sign up");
        signupButton.setForeground(Color.blue);
        signupButton.setFont(new Font("Arial", Font.BOLD, 24));
        signupButton.setBounds(415, 665, 124, 30);
        signupButton.setFocusable(false);
        signupButton.setOpaque(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorderPainted(false);
        signupButton.setFocusable(false);
        loginPanel.add(signupButton);

        setContentPane(layeredPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

}
