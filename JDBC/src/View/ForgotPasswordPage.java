package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class ForgotPasswordPage extends JFrame {
    private JPanel forgotPasswordPanelimg;
    private JPanel emailPanel;
    private JTextField emailField;
    private JButton sendCodeButton;
    private JLabel ForgotPasswordPage;
    private JButton close;
    private JButton signupButton;
    private ImageIcon icon;
    private JPanel codePanel;
    private JButton verifyButton;
    private JLabel accountLabel;
    private JLabel info1Label;
    private JLabel info2Label;
    private JLabel info3Label;
    private JLabel info4Label;
    private JLabel info5Label;
    private JLabel info6Label;
    private JTextField codeField;
    private JPanel passwordPanel;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton changePasswordButton;
    private JButton back1Button;
    private JButton back2Button;
    private JButton back3Button;
    private RoundLoadingPanel loadingPanel;

    // Variables to store email and verification code
    private String userEmail;
    private String verificationCode;

    public ForgotPasswordPage() {
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        // icon
        icon = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        setIconImage(icon.getImage());

        // Maximize the window
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        emailPanel = new JPanel();
        codePanel = new JPanel();
        passwordPanel = new JPanel();

        // Background Image
        ImageIcon img = new ImageIcon("C:/allcode/College Project Java/img/bg.jpg");
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
        forgotPasswordPanelimg = new JPanel();
        forgotPasswordPanelimg.setLayout(null);
        forgotPasswordPanelimg.setBackground(Color.decode("#FAAA70"));
        forgotPasswordPanelimg.setBounds(250, 100, 1400, 800);
        layeredPane.add(forgotPasswordPanelimg, JLayeredPane.PALETTE_LAYER);

        // Add health image to loginPanelimg
        ImageIcon img1 = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        Image image1 = img1.getImage();
        Image scaledImage1 = image1.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        img1 = new ImageIcon(scaledImage1);

        JLabel imgLabel = new JLabel(img1);
        imgLabel.setBounds(0, 100, 700, 500);
        forgotPasswordPanelimg.add(imgLabel);

        // login Image
        ImageIcon verifyimg = new ImageIcon("C:/allcode/College Project Java/img/verify.png");
        Image verifyimage = verifyimg.getImage();
        Image scaledverifyImage = verifyimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        verifyimg = new ImageIcon(scaledverifyImage);
        JLabel verifybg = new JLabel("", verifyimg, JLabel.CENTER);
        verifybg.setBounds(0, 0, 30, 20);

        // Add text to loginPanelimg
        JLabel textLabel = new JLabel("Trackoo");
        textLabel.setBounds(280, 600, 300, 50);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setForeground(Color.RED);
        forgotPasswordPanelimg.add(textLabel);

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
        forgotPasswordPanelimg.add(close);

        // Forgot password Panel for Form
        emailPanel = new JPanel();
        emailPanel.setBackground(Color.WHITE);
        emailPanel.setBounds(710, 10, 680, 780);
        emailPanel.setLayout(null);
        forgotPasswordPanelimg.add(emailPanel);

        passwordPanel = new JPanel();
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setBounds(710, 10, 680, 780);
        passwordPanel.setLayout(null);
        forgotPasswordPanelimg.add(passwordPanel);

        codePanel = new JPanel();
        codePanel.setBackground(Color.WHITE);
        codePanel.setBounds(710, 10, 680, 780);
        codePanel.setLayout(null);
        forgotPasswordPanelimg.add(codePanel);

        loadingPanel = new RoundLoadingPanel();
        loadingPanel.setBounds(320, 420, 50, 50);

        // EmailPanel
        back1Button = new JButton("🔙");
        back1Button.setBackground(Color.decode("#FAAA70"));
        back1Button.setFont(new Font("", Font.BOLD, 24));
        back1Button.setBounds(10, 10, 60, 60);
        back1Button.setFocusable(false);
        back1Button.setOpaque(false);
        back1Button.setContentAreaFilled(false);
        back1Button.setBorderPainted(false);
        emailPanel.add(back1Button);
        back1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            }
        });

        // ForgotpasswordLabel
        ForgotPasswordPage = new JLabel("Forgot Password");
        ForgotPasswordPage.setFont(new Font("Arial", Font.BOLD, 24));
        ForgotPasswordPage.setBounds(250, 20, 300, 40);
        emailPanel.add(ForgotPasswordPage);

        info1Label = new JLabel("Please enter your email to verify!");
        info1Label.setBackground(Color.decode("#FAAA70"));
        info1Label.setFont(new Font("Arial", Font.BOLD, 24));
        info1Label.setBounds(157, 125, 480, 50);
        emailPanel.add(info1Label);

        info2Label = new JLabel("The verification code will be sent");
        info2Label.setBackground(Color.decode("#FAAA70"));
        info2Label.setFont(new Font("Arial", Font.BOLD, 24));
        info2Label.setBounds(155, 175, 580, 50);
        emailPanel.add(info2Label);

        info3Label = new JLabel("after you clicked Send code Button");
        info3Label.setBackground(Color.decode("#FAAA70"));
        info3Label.setFont(new Font("Arial", Font.BOLD, 24));
        info3Label.setBounds(150, 200, 580, 50);
        emailPanel.add(info3Label);

        // Email
        JLabel emailLabel = new JLabel("Enter your email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 26));
        emailLabel.setBounds(250, 300, 300, 50);
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.BOLD, 24));
        emailField.setBounds(150, 355, 400, 40);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Send code Button
        sendCodeButton = new JButton("Send Code");
        sendCodeButton.setBackground(Color.decode("#FAAA70"));
        sendCodeButton.setFont(new Font("Arial", Font.BOLD, 24));
        sendCodeButton.setBounds(250, 500, 200, 50);
        sendCodeButton.setFocusable(false);
        emailPanel.add(sendCodeButton);
        sendCodeButton.add(verifybg);

        // CodePanel
        back2Button = new JButton("🔙");
        back2Button.setBackground(Color.decode("#FAAA70"));
        back2Button.setFont(new Font("", Font.BOLD, 24));
        back2Button.setBounds(10, 10, 60, 60);
        back2Button.setFocusable(false);
        back2Button.setOpaque(false);
        back2Button.setContentAreaFilled(false);
        back2Button.setBorderPainted(false);
        codePanel.add(back2Button);

        ForgotPasswordPage = new JLabel("Forgot Password");
        ForgotPasswordPage.setFont(new Font("Arial", Font.BOLD, 24));
        ForgotPasswordPage.setBounds(250, 20, 300, 40);
        codePanel.add(ForgotPasswordPage);

        info4Label = new JLabel("Code has been sent to your mail");
        info4Label.setBackground(Color.decode("#FAAA70"));
        info4Label.setFont(new Font("Arial", Font.BOLD, 24));
        info4Label.setBounds(156, 125, 580, 50);
        codePanel.add(info4Label);

        info5Label = new JLabel("Please check your Email Inbox");
        info5Label.setBackground(Color.decode("#FAAA70"));
        info5Label.setFont(new Font("Arial", Font.BOLD, 24));
        info5Label.setBounds(155, 175, 580, 50);
        codePanel.add(info5Label);

        info6Label = new JLabel("and copy the code from Email");
        info6Label.setBackground(Color.decode("#FAAA70"));
        info6Label.setFont(new Font("Arial", Font.BOLD, 24));
        info6Label.setBounds(150, 200, 580, 50);
        codePanel.add(info6Label);

        // Enter Code Label
        JLabel codeLabel = new JLabel("Enter code:");
        codeLabel.setFont(new Font("Arial", Font.BOLD, 26));
        codeLabel.setBounds(250, 300, 300, 50);
        codeField = new JTextField();
        codeField.setFont(new Font("Arial", Font.BOLD, 24));
        codeField.setBounds(150, 355, 400, 40);
        codePanel.add(codeLabel);
        codePanel.add(codeField);

        // Verify Button
        verifyButton = new JButton("Verify");
        verifyButton.setBackground(Color.decode("#FAAA70"));
        verifyButton.setFont(new Font("Arial", Font.BOLD, 24));
        verifyButton.setBounds(250, 500, 200, 50);
        verifyButton.setFocusable(false);
        codePanel.add(verifyButton);

        // PasswordPanel
        back3Button = new JButton("🔙");
        back3Button.setBackground(Color.decode("#FAAA70"));
        back3Button.setFont(new Font("", Font.BOLD, 24));
        back3Button.setBounds(10, 10, 60, 60);
        back3Button.setFocusable(false);
        back3Button.setOpaque(false);
        back3Button.setContentAreaFilled(false);
        back3Button.setBorderPainted(false);
        passwordPanel.add(back3Button);

        ForgotPasswordPage = new JLabel("Forgot Password");
        ForgotPasswordPage.setFont(new Font("Arial", Font.BOLD, 24));
        ForgotPasswordPage.setBounds(250, 20, 300, 40);
        passwordPanel.add(ForgotPasswordPage);

        JLabel newPasswordLabel = new JLabel("Enter new password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 26));
        newPasswordLabel.setBounds(200, 150, 400, 50);
        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Arial", Font.BOLD, 24));
        newPasswordField.setBounds(150, 205, 400, 40);
        passwordPanel.add(newPasswordLabel);
        passwordPanel.add(newPasswordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 26));
        confirmPasswordLabel.setBounds(200, 300, 400, 50);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.BOLD, 24));
        confirmPasswordField.setBounds(150, 355, 400, 40);
        passwordPanel.add(confirmPasswordLabel);
        passwordPanel.add(confirmPasswordField);

        // Change Password Button
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(Color.decode("#FAAA70"));
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 24));
        changePasswordButton.setBounds(200, 555, 300, 50);
        changePasswordButton.setFocusable(false);
        passwordPanel.add(changePasswordButton);

        emailPanel.add(loadingPanel);
        loadingPanel.setVisible(false);

        // Account Label
        accountLabel = new JLabel("Don't have an account?");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 22));
        accountLabel.setBounds(180, 655, 250, 50);
        emailPanel.add(accountLabel);

        signupButton = new JButton("Sign up");
        signupButton.setForeground(Color.blue);
        signupButton.setFont(new Font("Arial", Font.BOLD, 24));
        signupButton.setBounds(415, 665, 124, 30);
        signupButton.setFocusable(false);
        signupButton.setOpaque(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorderPainted(false);
        signupButton.setFocusable(false);
        emailPanel.add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupPage signupPage = new SignupPage();
                signupPage.setVisible(true);
                dispose();
            }
        });

        // Account Label
        accountLabel = new JLabel("Don't have an account?");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 22));
        accountLabel.setBounds(180, 655, 250, 50);
        codePanel.add(accountLabel);

        signupButton = new JButton("Sign up");
        signupButton.setForeground(Color.blue);
        signupButton.setFont(new Font("Arial", Font.BOLD, 24));
        signupButton.setBounds(415, 665, 124, 30);
        signupButton.setFocusable(false);
        signupButton.setOpaque(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorderPainted(false);
        signupButton.setFocusable(false);
        codePanel.add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupPage signupPage = new SignupPage();
                signupPage.setVisible(true);
                dispose();
            }
        });

        add(layeredPane);

        // Hide codePanel and passwordPanel initially
        codePanel.setVisible(false);
        passwordPanel.setVisible(false);

        sendCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userEmail = emailField.getText();
                if (userEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your email.");
                } else {
                    // Show loading components
                    loadingPanel.setVisible(true);

                    // Run the email sending task in a new thread
                    SwingUtilities.invokeLater(() -> {
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                                sendVerificationCode(userEmail);
                                return null;
                            }

                            @Override
                            protected void done() {
                                // Hide loading components and show next panel
                                loadingPanel.setVisible(false);
                                emailPanel.setVisible(false);
                                codePanel.setVisible(true);
                                passwordPanel.setVisible(false);
                                JOptionPane.showMessageDialog(null, "Verification code sent to your email.");
                            }
                        }.execute();
                    });
                }
            }
        });

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredCode = codeField.getText();
                if (enteredCode.equals(verificationCode)) {
                    JOptionPane.showMessageDialog(null, "Code verified successfully.");
                    emailPanel.setVisible(false); // Hide the email panel
                    codePanel.setVisible(false); // Hide the code verification panel
                    passwordPanel.setVisible(true); // Show the password reset panel
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid verification code. Please try again.");
                }
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                if (newPassword.equals(confirmPassword)) {
                    if (updatePassword(userEmail, newPassword)) {
                        JOptionPane.showMessageDialog(null, "Password changed successfully.");
                        LoginPage loginPage = new LoginPage();
                        loginPage.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to change password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
                }
            }
        });
    }

    private boolean isEmailRegistered(String email) {
        boolean isRegistered = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
            String query = "SELECT COUNT(*) FROM signup WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                isRegistered = true;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error.");
        }
        return isRegistered;
    }

    private void sendVerificationCode(String email) {
        verificationCode = generateVerificationCode();
        String to = email;
        String from = "ruyoicregmi@gmail.com"; // Replace with your email
        String host = "smtp.gmail.com"; // Replace with your SMTP host

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ruyoicregmi@gmail.com", "geku fivq qwki axfa");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("FITME Password Reset Verification Code");

            String msg = "<h1>FITME Password Reset Request</h1>"
                    + "<p>Dear User,</p>"
                    + "<p>We received a request to reset your password for your FITME account.</p>"
                    + "<p>Your password reset code is: <b>" + verificationCode + "</b></p>"
                    + "<p>Please enter this code in the application to proceed with resetting your password.</p>"
                    + "<p>If you did not request a password reset, please ignore this email or contact support if you have questions.</p>"
                    + "<br>"
                    + "<p>Best regards,</p>"
                    + "<p>The FITME Team</p>";

            message.setContent(msg, "text/html");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private boolean updatePassword(String email, String newPassword) {
        boolean isUpdated = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
            String query = "UPDATE signup SET password = ? WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error.");
        }
        return isUpdated;
    }

    public static void main(String[] args) {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        forgotPasswordPage.setVisible(true);
    }
}
