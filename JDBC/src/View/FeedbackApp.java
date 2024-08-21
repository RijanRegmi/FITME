package View;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FeedbackApp extends JPanel {

    private Connection conn;
    private JButton jButton1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JTextField jTextField1;
    private JPanel feedbackPanel;
    private JScrollPane feedbackScrollPane;
    private JLabel feedbackTitleLabel;
    private JComboBox<String> StarratingCombobox;

    public FeedbackApp() {
        // Initialize components and set layout
        setLayout(new BorderLayout());

        // Set up the form panel
        JPanel formPanel = createFormPanel();

        // Set up the feedback display panel
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));

        // Create and add the title label to the feedback panel
        feedbackTitleLabel = new JLabel("Feedback Review");
        feedbackTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        feedbackTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        feedbackPanel.add(feedbackTitleLabel);

        feedbackScrollPane = new JScrollPane(feedbackPanel);
        feedbackScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a split pane to separate form and feedback display
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, feedbackScrollPane);
        splitPane.setDividerLocation(700);
        splitPane.setResizeWeight(0.5);

        // Add components to the main panel
        add(splitPane, BorderLayout.CENTER);

        // Initialize the database connection
        initDatabaseConnection();

        // Load and display feedback
        loadAndDisplayFeedback();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);

        jLabel2 = new JLabel("Name");
        jLabel2.setFont(new Font("Arial", Font.BOLD, 26));
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setSize(150, 35);
        jLabel2.setLocation(50, 50);

        jTextField1 = new JTextField(20);
        jTextField1.setSize(300, 30);
        jTextField1.setLocation(200, 50);
        jTextField1.setFont(new Font("Arial", Font.PLAIN, 24));

        jLabel3 = new JLabel("Feedback");
        jLabel3.setFont(new Font("Arial", Font.BOLD, 26));
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setSize(150, 35);
        jLabel3.setLocation(50, 100);

        jTextArea1 = new JTextArea(5, 20);
        jTextArea1.setFont(new Font("Arial", Font.PLAIN, 24));
        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setSize(300, 150);
        jScrollPane1.setLocation(200, 100);

        jButton1 = new JButton("Add Feedback");
        jButton1.setFont(new Font("Arial", Font.BOLD, 14));
        jButton1.setForeground(Color.BLACK);
        jButton1.setSize(150, 40);
        jButton1.setLocation(200, 370);
        jButton1.setBackground(Color.decode("#FAAA70"));
        jButton1.setFocusable(false);
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        formPanel.add(jLabel2);
        formPanel.add(jTextField1);
        formPanel.add(jLabel3);
        formPanel.add(jScrollPane1);
        formPanel.add(jButton1);

        StarratingCombobox = new JComboBox<>();
        StarratingCombobox.setModel(new DefaultComboBoxModel<>(new String[] { "★", "★★", "★★★", "★★★★", "★★★★★" }));
        StarratingCombobox.setFont(new Font("", Font.BOLD, 24));
        StarratingCombobox.setBounds(210, 270, 300, 40);
        // StarratingCombobox.setForeground(Color.YELLOW);

        JLabel StarratingLabel = new JLabel("Star Rating");
        StarratingLabel.setFont(new Font("Arial", Font.BOLD, 26));
        StarratingLabel.setBounds(50, 270, 150, 40);

        formPanel.add(StarratingLabel);
        formPanel.add(StarratingCombobox);

        return formPanel;
    }

    private void initDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to database.");
        }
    }

    private void loadAndDisplayFeedback() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT user_name, feedback_text, star_rating FROM feedback ORDER BY submission_date DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // Remove all components except the title label
            feedbackPanel.removeAll();
            feedbackPanel.add(feedbackTitleLabel);

            while (rs.next()) {
                String userName = rs.getString("user_name");
                String feedbackText = rs.getString("feedback_text");
                int starRating = rs.getInt("star_rating");

                addFeedbackPanel(userName, feedbackText, starRating);
            }
            feedbackPanel.revalidate();
            feedbackPanel.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load feedback from database.");
        }
    }

    private void addFeedbackPanel(String userName, String feedbackText, int starRating) {
        JPanel feedbackItemPanel = new JPanel();
        feedbackItemPanel.setLayout(new BorderLayout());
        feedbackItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 8));
        feedbackItemPanel.setPreferredSize(new Dimension(feedbackScrollPane.getWidth() - 50, 150));

        String stars = new String(new char[starRating]).replace("\0", "★");

        JLabel userNameLabel = new JLabel(userName + " " + stars);
        userNameLabel.setFont(new Font("", Font.BOLD, 18));
        userNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userNameLabel.setForeground(Color.decode("#d7be07"));
        feedbackItemPanel.add(userNameLabel, BorderLayout.NORTH);

        JTextArea feedbackTextArea = new JTextArea(feedbackText);
        feedbackTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        feedbackTextArea.setLineWrap(true);
        feedbackTextArea.setWrapStyleWord(true);
        feedbackTextArea.setEditable(false);
        feedbackTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        feedbackItemPanel.add(feedbackTextArea, BorderLayout.CENTER);

        feedbackPanel.add(feedbackItemPanel, 1);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String userName = jTextField1.getText();
        String feedbackText = jTextArea1.getText();
        int starRating = StarratingCombobox.getSelectedIndex() + 1;

        if (userName.isEmpty() || feedbackText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both your name and feedback.");
            return;
        }

        try {
            String sql = "INSERT INTO feedback (user_name, feedback_text, star_rating) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, feedbackText);
            statement.setInt(3, starRating);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Feedback added successfully!");
                jTextField1.setText("");
                jTextArea1.setText("");

                // Add new feedback panel below the title label
                addFeedbackPanel(userName, feedbackText, starRating);

                feedbackPanel.revalidate();
                feedbackPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add feedback. Please try again.");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding feedback to database.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Feedback App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1400, 600);
            frame.setLocationRelativeTo(null);
            frame.add(new FeedbackApp());
            frame.setVisible(true);
        });
    }
}
