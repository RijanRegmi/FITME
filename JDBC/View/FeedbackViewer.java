package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FeedbackViewer extends JPanel {
    private JTable feedbackTable;
    private DefaultTableModel tableModel;

    public FeedbackViewer() {
        initializeUI();

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

    public static void main(String[] args) {

    }
}
