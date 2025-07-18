package tcms.students;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import tcms.receptionists.Payment;
import tcms.receptionists.ReceptionistManager;
import tcms.utils.Constants;

public class StudentPaymentWindow extends JFrame {

    private ReceptionistManager receptionistManager;
    private int studentID;

    // UI components
    private JTextArea paymentArea;

    public StudentPaymentWindow(ReceptionistManager receptionistManager, int studentID) {
        this.receptionistManager = receptionistManager;
        this.studentID = studentID;

        setTitle("View My Payments");
        setBounds(100, 100, 1200, 750); 
        setLocationRelativeTo(null); 
        setResizable(true); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 34, 34));
        getContentPane().setLayout(null);

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        // Title Label
        JLabel titleLabel = new JLabel("Viewing Payments");
        titleLabel.setBackground(new Color(88, 101, 242));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBounds(348, 10, 312, 50);
        getContentPane().add(titleLabel);

        // Text Area inside Scroll Pane
        paymentArea = new JTextArea();
        paymentArea.setBackground(new Color(41, 41, 41));
        paymentArea.setEditable(false);
        paymentArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        paymentArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(paymentArea);
        scrollPane.setBounds(80, 100, 820, 450);
        getContentPane().add(scrollPane);

        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        closeButton.setBackground(new Color(200, 50, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBounds(425, 580, 150, 40);
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(41, 41, 41));
        panel.setBounds(0, 0, 986, 70);
        getContentPane().add(panel);

        // Load payment
        loadPayments();
    }

    private void loadPayments() {
        List<Payment> studentPayments = receptionistManager.getPaymentsForStudent(studentID);
        if (studentPayments.isEmpty()) {
            paymentArea.setText("No payment records found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Payment p : studentPayments) {
                sb.append("Payment ID: ").append(p.getPaymentID()).append("\n")
                  .append("Subject ID: ").append(p.getSubjectID()).append("\n")
                  .append("Amount Paid: RM").append(String.format("%.2f", p.getAmount())).append("\n")
                  .append("Date: ").append(p.getDate().format(Constants.CSV_DATE_FORMAT)).append("\n")
                  .append("Receipt ID: ").append(p.getReceiptID()).append("\n")
                  .append("Status: ").append(p.getReceiptID() == 0 ? "Unpaid" : "Paid").append("\n")
                  .append("-------------------------------\n");
            }
            paymentArea.setText(sb.toString());
            paymentArea.setFont(new Font("Arial Black", Font.PLAIN, 16));
            paymentArea.setForeground(Color.WHITE);
        }
    }

   
    public static void main(String[] args) {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        receptionistManager.loadPayments(Constants.PAYMENTS_CSV); 

        int testStudentID = 2; 

        SwingUtilities.invokeLater(() -> {
            new StudentPaymentWindow(receptionistManager, testStudentID);
        });
    }
}
