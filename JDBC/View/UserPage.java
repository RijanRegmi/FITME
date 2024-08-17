package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserPage extends JFrame implements ActionListener {
    JPanel userPanelBtn;
    JPanel userPanelimg;
    JPanel dashboardPanel;
    JPanel dataPanel;
    JButton dashboardBtn;
    JButton dataBtn;
    JButton logoutBtn;
    JLabel datalbl;
    JLabel dashboardlbl;
    ImageIcon icon;
    BMICalculatorPanel bmiCalculatorPanel;
    Table taskTablePanel;
    DefaultTableModel tableModel;
    JLabel resultLabel;
    JTable taskTable;
    JTextField weightTextField, feetTextField, inchesTextField;
    JButton calculateButton, viewTasksButton;
    JLabel displayHeightLbl;
    JLabel displayWeightLbl;
    JPanel feedbackPanel;
    JButton feedbackbtn;
    JLabel feedbacklbl;
    FeedbackApp feedbackapp;
    private Connection conn;
    DashboardPanel dashboardPage;
    private String username;

    public UserPage(String username) {
        this.username = username;

        // Database connection setup
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            // ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to database.");
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // icon
        icon = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        setIconImage(icon.getImage());

        // Initialize Panels
        userPanelimg = new JPanel();
        userPanelBtn = new JPanel();
        dataPanel = new JPanel();
        dashboardPanel = new JPanel();
        feedbackPanel = new JPanel();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));

        // Image 1
        ImageIcon img = new ImageIcon("C:/allcode/College Project Java/img/first game.jpg");
        Image image = img.getImage();
        Image scaledImage = image.getScaledInstance(1070, 250, Image.SCALE_SMOOTH);
        img = new ImageIcon(scaledImage);
        JLabel bg = new JLabel("", img, JLabel.CENTER);
        bg.setBounds(0, 0, 1070, 250);

        ImageIcon img2nd = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        Image image2nd = img2nd.getImage();
        Image scaledImage2nd = image2nd.getScaledInstance(630, 250, Image.SCALE_SMOOTH);
        img2nd = new ImageIcon(scaledImage2nd);
        JLabel bg2nd = new JLabel("", img2nd, JLabel.CENTER);
        bg2nd.setBounds(1070, 0, 630, 250);

        // Image 2
        ImageIcon img1 = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        Image image1 = img1.getImage();
        Image scaledImage1 = image1.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        img1 = new ImageIcon(scaledImage1);
        JLabel bg1 = new JLabel("", img1, JLabel.CENTER);
        bg1.setBounds(0, 40, 250, 150);

        // dashboard Image
        ImageIcon dashimg = new ImageIcon("C:/allcode/College Project Java/img/health.png");
        Image dashimage = dashimg.getImage();
        Image scaleddashImage = dashimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        dashimg = new ImageIcon(scaleddashImage);
        JLabel dashbg = new JLabel("", dashimg, JLabel.CENTER);
        dashbg.setBounds(0, 0, 30, 20);

        // data Image
        ImageIcon dataimg = new ImageIcon("C:/allcode/College Project Java/img/Viewdata.png");
        Image dataimage = dataimg.getImage();
        Image scaleddataImage = dataimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        dataimg = new ImageIcon(scaleddataImage);
        JLabel databg = new JLabel("", dataimg, JLabel.CENTER);
        databg.setBounds(0, 0, 30, 20);

        // Feedback Image
        ImageIcon feedbackimg = new ImageIcon("C:/allcode/trakwell/JDBC Project/src/img/feedback.png");
        Image feedbackimage = feedbackimg.getImage();
        Image scaledfeedbackImage = feedbackimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        feedbackimg = new ImageIcon(scaledfeedbackImage);
        JLabel feedbackbg = new JLabel("", feedbackimg, JLabel.CENTER);
        feedbackbg.setBounds(0, 0, 30, 20);

        // logout Image
        ImageIcon logoutimg = new ImageIcon("C:/allcode/College Project Java/img/logout.png");
        Image logoutimage = logoutimg.getImage();
        Image scaledlogoutImage = logoutimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        logoutimg = new ImageIcon(scaledlogoutImage);
        JLabel logoutbg = new JLabel("", logoutimg, JLabel.CENTER);
        logoutbg.setBounds(0, 0, 30, 20);

        // Set up userPanelimg
        userPanelimg.setBackground(Color.WHITE);
        userPanelimg.setBounds(250, 0, 1670, 250);
        userPanelimg.setLayout(null);
        userPanelimg.add(bg);
        userPanelimg.setBackground(Color.decode("#faf0b3"));
        userPanelimg.add(bg2nd);

        // Set up userPanelBtn
        userPanelBtn.setBackground(Color.decode("#FAAA70"));
        userPanelBtn.setBounds(0, 0, 250, 1080);
        userPanelBtn.setLayout(null);
        userPanelBtn.add(bg1);

        // Dashboard Button
        dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setBackground(Color.decode("#D9D9D9"));
        dashboardBtn.setFont(new Font("Arial", Font.BOLD, 18));
        dashboardBtn.setBounds(25, 300, 200, 50);
        dashboardBtn.setFocusable(false);
        userPanelBtn.add(dashboardBtn);
        dashboardBtn.add(dashbg);

        // Data Button
        dataBtn = new JButton("Data");
        dataBtn.setBackground(Color.decode("#D9D9D9"));
        dataBtn.setFont(new Font("Arial", Font.BOLD, 18));
        dataBtn.setBounds(25, 400, 200, 50);
        dataBtn.setFocusable(false);
        userPanelBtn.add(dataBtn);
        dataBtn.add(databg);

        // Feedback Button
        feedbackbtn = new JButton("Feedback");
        feedbackbtn.setBackground(Color.decode("#D9D9D9"));
        feedbackbtn.setFont(new Font("Arial", Font.BOLD, 18));
        feedbackbtn.setBounds(25, 500, 200, 50);
        feedbackbtn.setFocusable(false);
        userPanelBtn.add(feedbackbtn);
        feedbackbtn.add(feedbackbg);

        // Logout Button
        logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(Color.decode("#D9D9D9"));
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 18));
        logoutBtn.setBounds(25, 600, 200, 50);
        logoutBtn.setFocusable(false);
        userPanelBtn.add(logoutBtn);
        logoutBtn.add(logoutbg);
        logoutBtn.addActionListener(this);

        // ===================================================================datapanel==================================================================

        // Set up dataPanel (Data Panel)
        dataPanel.setBackground(Color.white);
        dataPanel.setBounds(250, 250, 1670, 830);
        dataPanel.setLayout(null);

        datalbl = new JLabel("Data");
        datalbl.setBackground(Color.WHITE);
        datalbl.setFont(new Font("Arial", Font.BOLD, 24));
        datalbl.setBounds(820, 10, 300, 40);
        dataPanel.add(datalbl);

        // Add the BMICalculatorPanel
        bmiCalculatorPanel = new BMICalculatorPanel();
        bmiCalculatorPanel.setBounds(100, 100, 1470, 600);
        dataPanel.add(bmiCalculatorPanel);

        // =============================================================dashboardPanel===================================================================
        // Set up dashboardPanel
        dashboardPanel.setBackground(Color.WHITE);
        dashboardPanel.setBounds(250, 250, 1670, 830);
        dashboardPanel.setLayout(null);

        dashboardPage = new DashboardPanel(username);
        dashboardPage.setBounds(0, 0, 1670, 830);
        dashboardPanel.add(dashboardPage);
        dashboardPanel.setVisible(true);

        // ==============================================================FeedbackPannel===================================================================
        feedbackPanel.setBackground(Color.WHITE);
        feedbackPanel.setBounds(250, 250, 1670, 830);
        feedbackPanel.setLayout(null);

        feedbacklbl = new JLabel("Feedback");
        feedbacklbl.setBackground(Color.WHITE);
        feedbacklbl.setFont(new Font("Arial", Font.BOLD, 24));
        feedbacklbl.setBounds(820, 10, 300, 40);
        feedbackPanel.add(feedbacklbl);

        feedbackapp = new FeedbackApp();
        feedbackapp.setBounds(100, 100, 1470, 600);
        feedbackPanel.add(feedbackapp);

        // =========================================================================================X=========================================================================================

        // Layering
        layeredPane.add(userPanelBtn, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(userPanelimg, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(dataPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(dashboardPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(feedbackPanel, JLayeredPane.DEFAULT_LAYER);

        setContentPane(layeredPane);

        // Button Actions
        dataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPanel.setVisible(true);
                dashboardPanel.setVisible(false);
                feedbackPanel.setVisible(false);
            }
        });

        dashboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardPanel.setVisible(true);
                dataPanel.setVisible(false);
                feedbackPanel.setVisible(false);
            }
        });

        feedbackbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedbackPanel.setVisible(true);
                dashboardPanel.setVisible(false);
                dataPanel.setVisible(false);
            }
        });

        dataPanel.setVisible(false);
        dashboardPanel.setVisible(true);
        feedbackPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new LoginPage().setVisible(true);
        dispose();
    }

}
