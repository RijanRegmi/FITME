package View;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Table extends JPanel implements ActionListener {
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton addTaskButton;

    public Table() {
        initComponents();
        loadTableData();

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTask(Table.this);
            }
        });
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        addTaskButton = new JButton("Add Task");

        setLayout(new BorderLayout());

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTask(Table.this).setVisible(true);
            }
        });

        jTable1.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Day", "Task ID", "Diet", "Work Out", "BMI", "Action"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only the "Action" column is editable
            }
        });

        jTable1.setRowHeight(50);

        jTable1.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

        jScrollPane1.setViewportView(jTable1);

        add(addTaskButton, BorderLayout.NORTH);
        add(jScrollPane1, BorderLayout.CENTER);
    }

    public void loadTableData() {
        Object[][] data = fetchDataFromDatabase();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : data) {
            model.addRow(new Object[] { row[0], row[1], row[2], row[3], row[4], "Buttons" });
        }
    }

    private Object[][] fetchDataFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/user";
        String user = "root";
        String password = "root";

        String query = "SELECT day, task_id, diet, workout, bmi_range FROM tasks";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query)) {

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            Object[][] data = new Object[rowCount][5];
            int row = 0;
            while (rs.next()) {
                data[row][0] = rs.getString("day");
                data[row][1] = rs.getLong("task_id");
                data[row][2] = rs.getString("diet");
                data[row][3] = rs.getString("workout");
                data[row][4] = rs.getString("bmi_range");
                row++;
            }
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0][5];
        }
    }

    public void updateRow(Long taskId, String day, String diet, String workout, String bmi) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            if (model.getValueAt(row, 1).equals(taskId)) {
                model.setValueAt(day, row, 0);
                model.setValueAt(diet, row, 2);
                model.setValueAt(workout, row, 3);
                model.setValueAt(bmi, row, 4);
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton updateButton = new JButton("Update");
        private JButton deleteButton = new JButton("Delete");

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            add(updateButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JPanel panel = new JPanel();
        private JButton updateButton = new JButton("Update");
        private JButton deleteButton = new JButton("Delete");

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            panel.add(updateButton);
            panel.add(deleteButton);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = jTable1.getSelectedRow();
                    Long taskId = (Long) jTable1.getValueAt(row, 1);
                    deleteRowFromDatabase(taskId);
                    ((DefaultTableModel) jTable1.getModel()).removeRow(row);
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = jTable1.getSelectedRow();
                    String day = (String) jTable1.getValueAt(row, 0);
                    Long taskId = (Long) jTable1.getValueAt(row, 1);
                    String diet = (String) jTable1.getValueAt(row, 2);
                    String workout = (String) jTable1.getValueAt(row, 3);
                    String bmi = (String) jTable1.getValueAt(row, 4);

                    new UpdateTask(Table.this, day, taskId, diet, workout, bmi).setVisible(true);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

    private void deleteRowFromDatabase(Long taskId) {
        String url = "jdbc:mysql://localhost:3306/user";
        String user = "root";
        String password = "root";

        String query = "DELETE FROM tasks WHERE task_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Task Table");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new Table());
                frame.setSize(1470, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
