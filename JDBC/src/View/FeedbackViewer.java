package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FeedbackViewer extends JPanel {
    private JTable feedbackTable;
    private DefaultTableModel tableModel;
    private static Connection conn;

    public FeedbackViewer(Connection conn) {
        FeedbackViewer.conn = conn;
        initializeUI();
        fetchDataFromDatabase();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Upper panel with title
        JPanel titlePanel = new JPanel();
        // titlePanel.setBackground(Color.ORANGE);
        JLabel titleLabel = new JLabel("FEEDBACK TABLE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel();
        feedbackTable = new JTable(tableModel);
        feedbackTable.setFillsViewportHeight(true);

        // Bold the table column names
        feedbackTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        feedbackTable.getTableHeader().setBackground(Color.decode("#FAAA70"));
        feedbackTable.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(feedbackTable);
        add(scrollPane, BorderLayout.CENTER);

        // Column headers
        tableModel.addColumn("Feedback ID");
        tableModel.addColumn("Submission Date");
        tableModel.addColumn("User Name");
        tableModel.addColumn("Star Rating");
        tableModel.addColumn("Feedback Text");

        // Adjust table borders
        feedbackTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        feedbackTable.setGridColor(Color.BLACK);
        feedbackTable.setShowGrid(true);

        // Adjust table header height
        feedbackTable.getTableHeader().setPreferredSize(new Dimension(100, 30));

        // Adjust row height
        feedbackTable.setRowHeight(25);
    }

    private void fetchDataFromDatabase() {
        try {
            String sql = "SELECT feedback_id, user_name, feedback_text, submission_date, star_rating FROM feedback";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                Date submissionDate = rs.getDate("submission_date");

                String userName = rs.getString("user_name");
                String starRating = rs.getString("star_rating");
                String feedbackText = rs.getString("feedback_text");

                // Add row to table
                tableModel.addRow(new Object[] { feedbackId, submissionDate, userName, starRating, feedbackText });
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching feedback from database.");
        }
    }

    public static void main(String[] args) {
        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bmi_tasks", "root", "");

            // Create and show the feedback viewer
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Feedback Viewer");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1470, 600);
                frame.setLocationRelativeTo(null);
                frame.add(new FeedbackViewer(conn));
                frame.setVisible(true);
            });

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to database.");
        }
    }
}
