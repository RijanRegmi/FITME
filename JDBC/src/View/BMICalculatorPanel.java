package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import View.AdminPage;

public class BMICalculatorPanel extends JPanel implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JTextField weightTextField, feetTextField, inchesTextField;
    private JButton calculateButton, viewTasksButton;
    private JLabel resultLabel;

    private Connection conn;

    public BMICalculatorPanel() {
        setLayout(null);

        JLabel titleLabel = new JLabel("BMI Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setSize(300, 35);
        titleLabel.setLocation(120, 30);
        add(titleLabel);

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 26));
        weightLabel.setSize(300, 30);
        weightLabel.setLocation(50, 100);
        add(weightLabel);

        weightTextField = new JTextField();
        weightTextField.setFont(new Font("Arial", Font.BOLD, 26));
        weightTextField.setSize(250, 35);
        weightTextField.setLocation(250, 100);
        add(weightTextField);

        JLabel heightLabel = new JLabel("Height (ft, in):");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 26));
        heightLabel.setSize(300, 30);
        heightLabel.setLocation(50, 200);
        add(heightLabel);

        feetTextField = new JTextField();
        feetTextField.setFont(new Font("Arial", Font.BOLD, 26));
        feetTextField.setSize(100, 35);
        feetTextField.setLocation(250, 200);
        add(feetTextField);

        inchesTextField = new JTextField();
        inchesTextField.setFont(new Font("Arial", Font.BOLD, 26));
        inchesTextField.setSize(100, 35);
        inchesTextField.setLocation(400, 200);
        add(inchesTextField);

        calculateButton = new JButton("Calculate BMI");
        calculateButton.setBackground(Color.decode("#FAAA70"));
        calculateButton.setFont(new Font("Arial", Font.BOLD, 26));
        calculateButton.setSize(250, 50);
        calculateButton.setLocation(70, 300);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);
        add(calculateButton);

        viewTasksButton = new JButton("View Tasks");
        viewTasksButton.setBackground(Color.decode("#FAAA70"));
        viewTasksButton.setSize(250, 50);
        viewTasksButton.setFocusable(false);
        viewTasksButton.setFont(new Font("Arial", Font.BOLD, 26));
        viewTasksButton.setLocation(370, 300);
        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveTasksFromDatabase();

            }
        });
        add(viewTasksButton);
        this.add(viewTasksButton);

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 26));
        resultLabel.setSize(600, 35);
        resultLabel.setLocation(100, 450);
        add(resultLabel);

        tableModel = new DefaultTableModel();
        taskTable = new JTable(tableModel);
        tableModel.addColumn("Day");
        tableModel.addColumn("Task ID");
        tableModel.addColumn("Diet");
        tableModel.addColumn("Workout");
        tableModel.addColumn("BMI Range");
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setSize(780, 500);
        scrollPane.setLocation(650, 50);
        add(scrollPane);

        JTableHeader header = taskTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                label.setFont(new Font("Arial", Font.BOLD, 20));
                label.setHorizontalAlignment(JLabel.LEFT);
                label.setBackground(Color.decode("#FAAA70"));

                return label;
            }
        });

        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

        taskTable.getTableHeader().setReorderingAllowed(false);
        taskTable.setFont(new Font("Arial", Font.PLAIN, 20));
        taskTable.setRowHeight(60);
        // taskTable.setHorizontalAlignment(JLabel.CENTER);

        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        setPreferredSize(new Dimension(1470, 600));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double weight = Double.parseDouble(weightTextField.getText());
            int feet = Integer.parseInt(feetTextField.getText());
            int inches = Integer.parseInt(inchesTextField.getText());

            if (weight <= 0 || feet <= 0 || inches < 0) {
                throw new NumberFormatException();
            }

            // Convert feet and inches to meters
            double heightInMeters = (feet * 0.3048) + (inches * 0.0254);
            double bmi = weight / (heightInMeters * heightInMeters);
            String status;

            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi >= 18.5 && bmi < 24.9) {
                status = "Normal";
            } else {
                status = "Overweight";
            }

            resultLabel.setText(String.format("Your BMI: %.2f (%s)", bmi, status));

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private void retrieveTasksFromDatabase() {
        String weightText = weightTextField.getText().trim();
        String feetText = feetTextField.getText().trim();
        String inchesText = inchesTextField.getText().trim();

        if (weightText.isEmpty() || feetText.isEmpty() || inchesText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your weight and height before viewing tasks.",
                    "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double weight = Double.parseDouble(weightText);
            int feet = Integer.parseInt(feetText);
            int inches = Integer.parseInt(inchesText);

            // Convert feet and inches to meters
            double heightInMeters = (feet * 0.3048) + (inches * 0.0254);
            double bmi = weight / (heightInMeters * heightInMeters);

            String bmiCategory;
            if (bmi < 18.5) {
                bmiCategory = "Underweight";
            } else if (bmi >= 18.5 && bmi < 24.9) {
                bmiCategory = "Normal";
            } else {
                bmiCategory = "Overweight";
            }

            String sql = "SELECT day, task_id, diet, workout, bmi_range FROM tasks WHERE bmi_range = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, bmiCategory);
            ResultSet rs = statement.executeQuery();

            tableModel.setRowCount(0);
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
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BMI Calculator and Task Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BMICalculatorPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
