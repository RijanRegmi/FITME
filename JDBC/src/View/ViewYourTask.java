
package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewYourTask extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private Connection conn;

    public ViewYourTask() {
        setTitle("View Your Tasks");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 7));
        String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        // Create buttons for each day
        for (String day : daysOfWeek) {
            JButton dayButton = new JButton(day);
            dayButton.addActionListener(this);
            buttonPanel.add(dayButton);
        }

        add(buttonPanel, BorderLayout.NORTH);

        // Table setup for displaying tasks
        tableModel = new DefaultTableModel();
        taskTable = new JTable(tableModel);
        tableModel.addColumn("Day");
        tableModel.addColumn("Task ID");
        tableModel.addColumn("Diet");
        tableModel.addColumn("Workout");
        tableModel.addColumn("BMI Range");
        JScrollPane scrollPane = new JScrollPane(taskTable);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dayClicked = e.getActionCommand();
        fetchTasksForDay(dayClicked);
    }

    private void fetchTasksForDay(String dayOfWeek) {
        try {
            String sql = "SELECT day, task_id, diet, workout, bmi_range FROM tasks WHERE day = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dayOfWeek);
            ResultSet rs = statement.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                String day = rs.getString("day");
                int taskID = rs.getInt("task_id");
                String diet = rs.getString("diet");
                String workout = rs.getString("workout");
                String bmiRange = rs.getString("bmi_range");
                tableModel.addRow(new Object[] { day, taskID, diet, workout, bmiRange });
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewYourTask frame = new ViewYourTask();
            frame.setVisible(true);
        });
    }
}
