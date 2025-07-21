package tcms.receptionists;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PaymentPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtPaymentID, txtStudentID, txtSubjectID, txtAmount, txtDate, txtReceiptID;

    public PaymentPage() {
        setTitle("Payment & Receipt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 420);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPaymentID = new JLabel("Payment ID:");
        lblPaymentID.setBounds(30, 40, 150, 20);
        contentPane.add(lblPaymentID);

        JLabel lblStudentID = new JLabel("Student ID:");
        lblStudentID.setBounds(30, 70, 150, 20);
        contentPane.add(lblStudentID);

        JLabel lblSubjectID = new JLabel("Subject ID:");
        lblSubjectID.setBounds(30, 100, 150, 20);
        contentPane.add(lblSubjectID);

        JLabel lblAmount = new JLabel("Amount (RM):");
        lblAmount.setBounds(30, 130, 150, 20);
        contentPane.add(lblAmount);

        JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblDate.setBounds(30, 160, 150, 20);
        contentPane.add(lblDate);

        JLabel lblReceiptID = new JLabel("Receipt ID:");
        lblReceiptID.setBounds(30, 190, 150, 20);
        contentPane.add(lblReceiptID);

        txtPaymentID = new JTextField();
        txtPaymentID.setBounds(190, 40, 150, 20);
        contentPane.add(txtPaymentID);

        txtStudentID = new JTextField();
        txtStudentID.setBounds(190, 70, 150, 20);
        contentPane.add(txtStudentID);

        txtSubjectID = new JTextField();
        txtSubjectID.setBounds(190, 100, 150, 20);
        contentPane.add(txtSubjectID);

        txtAmount = new JTextField();
        txtAmount.setBounds(190, 130, 150, 20);
        contentPane.add(txtAmount);

        txtDate = new JTextField();
        txtDate.setBounds(190, 160, 150, 20);
        contentPane.add(txtDate);

        txtReceiptID = new JTextField();
        txtReceiptID.setBounds(190, 190, 150, 20);
        contentPane.add(txtReceiptID);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(30, 250, 100, 30);
        contentPane.add(btnSubmit);

        JButton btnReceipt = new JButton("Receipt Output");
        btnReceipt.setBounds(158, 250, 108, 30);
        contentPane.add(btnReceipt);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(290, 250, 80, 30);
        contentPane.add(btnBack);
        
        JLabel lblNewLabel = new JLabel("Payment & Receipt");
        lblNewLabel.setBounds(30, 17, 100, 13);
        contentPane.add(lblNewLabel);

        btnSubmit.addActionListener(e -> {
            try {
                int paymentID = Integer.parseInt(txtPaymentID.getText().trim());
                int studentID = Integer.parseInt(txtStudentID.getText().trim());
                int subjectID = Integer.parseInt(txtSubjectID.getText().trim());
                double amount = Double.parseDouble(txtAmount.getText().trim());
                LocalDate date = LocalDate.parse(txtDate.getText().trim());
                int receiptID = Integer.parseInt(txtReceiptID.getText().trim());

                Payment payment = new Payment(paymentID, studentID, subjectID, amount, date, receiptID);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("payments.txt", true))) {
                    writer.write(payment.toCSV());
                    writer.newLine();
                    JOptionPane.showMessageDialog(this, "Payment saved successfully.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Date Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving payment: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnReceipt.addActionListener(e -> {
            StringBuilder receipt = new StringBuilder();
            receipt.append("---- Payment Receipt ----\n");
            receipt.append("Payment ID: ").append(txtPaymentID.getText()).append("\n");
            receipt.append("Student ID: ").append(txtStudentID.getText()).append("\n");
            receipt.append("Subject ID: ").append(txtSubjectID.getText()).append("\n");
            receipt.append("Amount: RM").append(txtAmount.getText()).append("\n");
            receipt.append("Date: ").append(txtDate.getText()).append("\n");
            receipt.append("Receipt ID: ").append(txtReceiptID.getText()).append("\n");
            JOptionPane.showMessageDialog(this, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
        });

        btnBack.addActionListener(e -> {
            new ReceptionistDashboard().setVisible(true);
            dispose();
        });
        
        setVisible(true);
    }
}
