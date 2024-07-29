import javax.swing.*;
import java.awt.*;

public class FeedbackApp extends JPanel {

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
        jTextField1.setSize(200, 30);
        jTextField1.setLocation(200, 50);

        jLabel3 = new JLabel("Feedback");
        jLabel3.setFont(new Font("Arial", Font.BOLD, 26));
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setSize(150, 35);
        jLabel3.setLocation(50, 100);

        jTextArea1 = new JTextArea(5, 20);
        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setSize(300, 150);
        jScrollPane1.setLocation(200, 100);

        jButton1 = new JButton("Add Feedback");
        jButton1.setFont(new Font("Arial", Font.BOLD, 14));
        jButton1.setForeground(Color.BLACK);
        jButton1.setSize(150, 40);
        jButton1.setLocation(200, 370);

        formPanel.add(jLabel2);
        formPanel.add(jTextField1);
        formPanel.add(jLabel3);
        formPanel.add(jScrollPane1);
        formPanel.add(jButton1);

        StarratingCombobox = new JComboBox<>();
        StarratingCombobox.setModel(new DefaultComboBoxModel<>(new String[] { "⭐", "⭐⭐", "⭐⭐⭐", "⭐⭐⭐⭐", "⭐⭐⭐⭐⭐" }));
        StarratingCombobox.setFont(new Font("Arial", Font.BOLD, 24));
        StarratingCombobox.setBounds(210, 270, 300, 40);

        JLabel StarratingLabel = new JLabel("Star Rating");
        StarratingLabel.setFont(new Font("Arial", Font.BOLD, 26));
        StarratingLabel.setBounds(50, 270, 150, 40);

        formPanel.add(StarratingLabel);
        formPanel.add(StarratingCombobox);

        return formPanel;
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
