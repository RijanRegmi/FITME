import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class BMICalculatorPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JTextField weightTextField, feetTextField, inchesTextField;
    private JButton calculateButton, viewTasksButton;
    private JLabel resultLabel;

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
        calculateButton.setFocusable(false);
        add(calculateButton);

        //view task button 
        viewTasksButton = new JButton("View Tasks");
        viewTasksButton.setBackground(Color.decode("#FAAA70"));
        viewTasksButton.setSize(250, 50);
        viewTasksButton.setFocusable(false);
        viewTasksButton.setFont(new Font("Arial", Font.BOLD, 26));
        viewTasksButton.setLocation(370, 300);

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

        setPreferredSize(new Dimension(1470, 600));
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
