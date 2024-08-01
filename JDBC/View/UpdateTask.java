package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateTask extends javax.swing.JFrame {

        // Variables declaration
        private javax.swing.JComboBox<String> BMIComboBox;
        private javax.swing.JLabel BMIlbl;
        private javax.swing.JLabel Daylbl;
        private javax.swing.JTextField DietField;
        private javax.swing.JLabel Dietlbl;
        private javax.swing.JLabel TaskIDlbl;
        private javax.swing.JTextField TaskIdField;
        private javax.swing.JButton UpdateButton;
        private javax.swing.JTextField WorkoutField;
        private javax.swing.JLabel Workoutlbl;
        private javax.swing.JComboBox<String> dayCombobox;
        private javax.swing.JLabel detaillbl;

        
        // Constructor with parameters
        public UpdateTask(String day, Long taskId, String diet, String workout, String bmi) {
               
                initComponents();
                dayCombobox.setSelectedItem(day);
                TaskIdField.setText(taskId.toString());
                DietField.setText(diet);
                WorkoutField.setText(workout);
                BMIComboBox.setSelectedItem(bmi);
        }

        private void initComponents() {
                // Initialize components as before
                detaillbl = new javax.swing.JLabel();
                Daylbl = new javax.swing.JLabel();
                TaskIDlbl = new javax.swing.JLabel();
                Dietlbl = new javax.swing.JLabel();
                Workoutlbl = new javax.swing.JLabel();
                BMIlbl = new javax.swing.JLabel();
                dayCombobox = new javax.swing.JComboBox<>();
                TaskIdField = new javax.swing.JTextField();
                DietField = new javax.swing.JTextField();
                WorkoutField = new javax.swing.JTextField();
                BMIComboBox = new javax.swing.JComboBox<>();
                UpdateButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                detaillbl.setFont(new java.awt.Font("Tahoma", 1, 18));
                detaillbl.setText("Update Task Details");

                Daylbl.setText("Day:");

                TaskIDlbl.setText("Task ID:");

                Dietlbl.setText("Diet:");

                Workoutlbl.setText("Workout:");

                BMIlbl.setText("BMI:");

                dayCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                                                "Sunday" }));

                BMIComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Underweight", "Normal weight", "Overweight", "Obesity" }));

                UpdateButton.setText("Update Task");
                UpdateButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                
                        }
                });

                // Layout and add components
                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(150, 150, 150)
                                                                                                .addComponent(detaillbl))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(Daylbl)
                                                                                                                .addComponent(TaskIDlbl)
                                                                                                                .addComponent(Dietlbl)
                                                                                                                .addComponent(Workoutlbl)
                                                                                                                .addComponent(BMIlbl))
                                                                                                .addGap(38, 38, 38)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                                .addComponent(dayCombobox,
                                                                                                                                0,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(TaskIdField)
                                                                                                                .addComponent(DietField)
                                                                                                                .addComponent(WorkoutField)
                                                                                                                .addComponent(BMIComboBox,
                                                                                                                                0,
                                                                                                                                150,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(UpdateButton,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                120,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(100, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(detaillbl)
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Daylbl)
                                                                                .addComponent(dayCombobox,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(TaskIDlbl)
                                                                                .addComponent(TaskIdField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Dietlbl)
                                                                                .addComponent(DietField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Workoutlbl)
                                                                                .addComponent(WorkoutField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(BMIlbl)
                                                                                .addComponent(BMIComboBox,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(30, 30, 30)
                                                                .addComponent(UpdateButton)
                                                                .addContainerGap(30, Short.MAX_VALUE)));

                pack();
        }

        public static void main(String args[]) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                              
                        }
                });
        }
}
