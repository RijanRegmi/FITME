package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AddTask extends javax.swing.JFrame {

        private Table table;

        public AddTask(Table table) {
                this.table = table;
                initComponents();
        }

        private void initComponents() {

                detaillbl = new javax.swing.JLabel();
                dayCombobox = new javax.swing.JComboBox<>();
                TaskIdField = new javax.swing.JTextField();
                Daylbl = new javax.swing.JLabel();
                TaskIDlbl = new javax.swing.JLabel();
                DietField = new javax.swing.JTextField();
                WorkoutField = new javax.swing.JTextField();
                AddButton = new javax.swing.JButton();
                Dietlbl = new javax.swing.JLabel();
                Workoutlbl = new javax.swing.JLabel();
                BMIComboBox = new javax.swing.JComboBox<>();
                BMIlbl = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                detaillbl.setText("Fill Detail of task");

                dayCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                                                "Sunday" }));

                Daylbl.setText("Day");

                TaskIDlbl.setText("Task ID");

                AddButton.setText("Add");
                AddButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                actionPerformed(evt);
                                dispose();
                        }
                });

                Dietlbl.setText("Diet");

                Workoutlbl.setText("Work Out");

                BMIComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Underweight", "Normal", "Overweight", "Obese" }));

                BMIlbl.setText("BMI Range");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(detaillbl)
                                                                .addGroup(layout.createSequentialGroup().addGroup(layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(TaskIDlbl)
                                                                                .addComponent(Daylbl)
                                                                                .addComponent(Dietlbl)
                                                                                .addComponent(Workoutlbl)
                                                                                .addComponent(BMIlbl))
                                                                                .addGap(68, 68, 68)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(BMIComboBox,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                                .addComponent(dayCombobox,
                                                                                                                                0,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(TaskIdField)
                                                                                                                .addComponent(DietField)
                                                                                                                .addComponent(WorkoutField))
                                                                                                .addComponent(AddButton))))
                                                .addContainerGap(170, Short.MAX_VALUE)));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(detaillbl)
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(Daylbl).addComponent(dayCombobox,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(TaskIDlbl).addComponent(TaskIdField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(Dietlbl).addComponent(DietField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(Workoutlbl).addComponent(WorkoutField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(BMIlbl).addComponent(BMIComboBox,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18).addComponent(AddButton)
                                                .addContainerGap(37, Short.MAX_VALUE)));

                pack();
        }


               

        private javax.swing.JButton AddButton;
        private javax.swing.JComboBox<String> BMIComboBox;
        private javax.swing.JLabel BMIlbl;
        private javax.swing.JLabel Daylbl;
        private javax.swing.JTextField DietField;
        private javax.swing.JLabel Dietlbl;
        private javax.swing.JTextField TaskIdField;
        private javax.swing.JLabel TaskIDlbl;
        private javax.swing.JTextField WorkoutField;
        private javax.swing.JLabel Workoutlbl;
        private javax.swing.JComboBox<String> dayCombobox;
        private javax.swing.JLabel detaillbl;
}
