
package tcms.students;

import javax.swing.*;
import java.awt.*;

public class StudentProfileWindow extends JFrame {
    public StudentProfileWindow(String studentID, StudentManager sm) {
        setTitle("View / Edit Profile");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Colors
        Color darkGrey = new Color(34, 34, 34);
        Color blueAcc = new Color(70, 130, 180);
        Color whiteText = Color.WHITE;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(darkGrey);
        add(mainPanel);

        JLabel titleLabel = new JLabel("Update Your Profile");
        titleLabel.setForeground(whiteText);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 30, 20));
        formPanel.setBackground(darkGrey);
        formPanel.setBorder(BorderFactory.createEmptyBorder(60, 300, 60, 300));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        JTextField contactField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        for (JLabel lbl : new JLabel[]{emailLabel, contactLabel, addressLabel}) {
            lbl.setForeground(whiteText);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
        }

        for (JTextField tf : new JTextField[]{emailField, contactField, addressField}) {
            tf.setFont(new Font("SansSerif", Font.PLAIN, 16));
        }

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);

        // Add Update Button
        JButton updateBtn = new JButton("Update Profile");
        updateBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        updateBtn.setBackground(blueAcc);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);
        updateBtn.setPreferredSize(new Dimension(200, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(darkGrey);
        buttonPanel.add(updateBtn);

        formPanel.add(new JLabel()); // empty space
        formPanel.add(buttonPanel);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Load student data
        Student student = sm.findStudentByStudentID(Integer.parseInt(studentID));
        if (student != null) {
            emailField.setText(student.getEmail());
            contactField.setText(student.getContact());
            addressField.setText(student.getAddress());
        }

        updateBtn.addActionListener(e -> {
            boolean updated = sm.updateStudentProfile(
                student.getUserID(),
                emailField.getText().trim(),
                contactField.getText().trim(),
                addressField.getText().trim()
            );
            JOptionPane.showMessageDialog(this, updated ? "Profile updated." : "Update failed.");
        });

        setVisible(true);
    }
}

class ClassScheduleWindow extends JFrame {
    public ClassScheduleWindow(String studentID) {
        setTitle("Class Schedule");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTextArea scheduleArea = new JTextArea("[Placeholder] Schedule for Student ID: " + studentID);
        scheduleArea.setEditable(false);
        add(new JScrollPane(scheduleArea));

        setVisible(true);
    }
}

class StudentRequestWindow extends JFrame {
    public StudentRequestWindow(String studentID) {
        setTitle("Request Module");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea requestInput = new JTextArea("Write your request here...");
        JButton submitBtn = new JButton("Submit Request");

        submitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Request submitted for Student ID: " + studentID);
            requestInput.setText("");
        });

        add(new JScrollPane(requestInput), BorderLayout.CENTER);
        add(submitBtn, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class PaymentStatusWindow extends JFrame {
    public PaymentStatusWindow(String studentID) {
        setTitle("Payment Status");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JLabel paymentLabel = new JLabel("[Placeholder] Payment status for Student ID: " + studentID);
        paymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(paymentLabel);

        setVisible(true);
    }
}
