package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.LoginController;
import DAO.UserDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminPage extends JFrame implements ActionListener {
    JPanel adminPanelBtn;
    JPanel adminPanelimg;
    JPanel adminDashboardPanel;
    JPanel addtaskPanel;
    JPanel dataPanel;
    JButton dashboardBtn;
    JButton addtaskBtn;
    JButton dataBtn;
    JButton logoutBtn;
    JLabel datalbl;
    JLabel dashboardlbl;
    JLabel addtasklbl;
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
    FeedbackViewer feedbackViewerPanel;
    private Connection conn;
    adminDashboardPanel dashboardPage;
    private String username;

    public AdminPage(String username) {
        this.username = username;

        // Database connection setup
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bmi_tasks", "root", "");
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
        adminPanelimg = new JPanel();
        adminPanelBtn = new JPanel();
        dataPanel = new JPanel();
        adminDashboardPanel = new JPanel();
        addtaskPanel = new JPanel();
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

        // AddTask Image
        ImageIcon addTaskimg = new ImageIcon("C:/allcode/College Project Java/img/addTask.png");
        Image addTaskimage = addTaskimg.getImage();
        Image scaledaddTaskImage = addTaskimage.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        addTaskimg = new ImageIcon(scaledaddTaskImage);
        JLabel addTaskbg = new JLabel("", addTaskimg, JLabel.CENTER);
        addTaskbg.setBounds(0, 0, 30, 20);

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

        // Set up adminPanelimg
        adminPanelimg.setBackground(Color.WHITE);
        adminPanelimg.setBounds(250, 0, 1670, 250);
        adminPanelimg.setLayout(null);
        adminPanelimg.add(bg);
        adminPanelimg.setBackground(Color.decode("#faf0b3"));
        adminPanelimg.add(bg2nd);

        // Set up adminPanelBtn
        adminPanelBtn.setBackground(Color.decode("#FAAA70"));
        adminPanelBtn.setBounds(0, 0, 250, 1080);
        adminPanelBtn.setLayout(null);
        adminPanelBtn.add(bg1);

        // Dashboard Button
        dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setBackground(Color.decode("#D9D9D9"));
        dashboardBtn.setFont(new Font("Arial", Font.BOLD, 18));
        dashboardBtn.setBounds(25, 300, 200, 50);
        dashboardBtn.setFocusable(false);
        adminPanelBtn.add(dashboardBtn);
        dashboardBtn.add(dashbg);

        // Add Task Button
        addtaskBtn = new JButton("Add Task");
        addtaskBtn.setBackground(Color.decode("#D9D9D9"));
        addtaskBtn.setFont(new Font("Arial", Font.BOLD, 18));
        addtaskBtn.setBounds(25, 400, 200, 50);
        addtaskBtn.setFocusable(false);
        adminPanelBtn.add(addtaskBtn);
        addtaskBtn.add(addTaskbg);

        // Data Button
        dataBtn = new JButton("Data");
        dataBtn.setBackground(Color.decode("#D9D9D9"));
        dataBtn.setFont(new Font("Arial", Font.BOLD, 18));
        dataBtn.setBounds(25, 500, 200, 50);
        dataBtn.setFocusable(false);
        adminPanelBtn.add(dataBtn);
        dataBtn.add(databg);

        // Feedback Button
        feedbackbtn = new JButton("Feedback");
        feedbackbtn.setBackground(Color.decode("#D9D9D9"));
        feedbackbtn.setFont(new Font("Arial", Font.BOLD, 18));
        feedbackbtn.setBounds(25, 600, 200, 50);
        feedbackbtn.setFocusable(false);
        adminPanelBtn.add(feedbackbtn);
        feedbackbtn.add(feedbackbg);

        // Logout Button
        logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(Color.decode("#D9D9D9"));
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 18));
        logoutBtn.setBounds(25, 700, 200, 50);
        logoutBtn.setFocusable(false);
        adminPanelBtn.add(logoutBtn);
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

        // =============================================================adminDashboardPanel===================================================================
        // Set up adminDashboardPanel
        adminDashboardPanel.setBackground(Color.WHITE);
        adminDashboardPanel.setBounds(250, 250, 1670, 830);
        adminDashboardPanel.setLayout(null);

        dashboardPage = new adminDashboardPanel(username);
        dashboardPage.setBounds(0, 0, 1670, 830);
        adminDashboardPanel.add(dashboardPage);
        adminDashboardPanel.setVisible(true);

        // ==================================================================addTask======================================================================
        // Set up addtaskPanel
        addtaskPanel.setBackground(Color.WHITE);
        addtaskPanel.setBounds(250, 250, 1670, 830);
        addtaskPanel.setLayout(null);

        addtasklbl = new JLabel("Assign Task");
        addtasklbl.setBackground(Color.WHITE);
        addtasklbl.setFont(new Font("Arial", Font.BOLD, 24));
        addtasklbl.setBounds(820, 10, 300, 40);
        addtaskPanel.add(addtasklbl);

        taskTablePanel = new Table();
        taskTablePanel.setBounds(100, 100, 1470, 600);
        addtaskPanel.add(taskTablePanel);

        // ==============================================================FeedbackPannel===================================================================
        feedbackPanel.setBackground(Color.WHITE);
        feedbackPanel.setBounds(250, 250, 1670, 830);
        feedbackPanel.setLayout(null);

        feedbacklbl = new JLabel("Feedback");
        feedbacklbl.setBackground(Color.WHITE);
        feedbacklbl.setFont(new Font("Arial", Font.BOLD, 24));
        feedbacklbl.setBounds(820, 10, 300, 40);
        feedbackPanel.add(feedbacklbl);

        feedbackViewerPanel = new FeedbackViewer(conn);
        feedbackViewerPanel.setBounds(100, 100, 1470, 600);
        feedbackPanel.add(feedbackViewerPanel);

        // =========================================================================================X=========================================================================================

        // Layering
        layeredPane.add(adminPanelBtn, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(adminPanelimg, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(dataPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(adminDashboardPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(addtaskPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(feedbackPanel, JLayeredPane.DEFAULT_LAYER);

        setContentPane(layeredPane);

        // Button Actions
        dataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPanel.setVisible(true);
                adminDashboardPanel.setVisible(false);
                addtaskPanel.setVisible(false);
                feedbackPanel.setVisible(false);
            }
        });

        dashboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminDashboardPanel.setVisible(true);
                dataPanel.setVisible(false);
                addtaskPanel.setVisible(false);
                feedbackPanel.setVisible(false);
            }
        });

        addtaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addtaskPanel.setVisible(true);
                adminDashboardPanel.setVisible(false);
                dataPanel.setVisible(false);
                feedbackPanel.setVisible(false);
            }
        });

        feedbackbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedbackPanel.setVisible(true);
                adminDashboardPanel.setVisible(false);
                dataPanel.setVisible(false);
                addtaskPanel.setVisible(false);
            }
        });

        dataPanel.setVisible(false);
        adminDashboardPanel.setVisible(true);
        addtaskPanel.setVisible(false);
        feedbackPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       LoginPage login = new LoginPage();
        UserDAO ud = new UserDAO();
        LoginController lc = new LoginController(ud, login);
        login.setVisible(true);
                dispose();
    }

    public static void main(String[] args) {

        new AdminPage("admin").setVisible(true);
    }

}
