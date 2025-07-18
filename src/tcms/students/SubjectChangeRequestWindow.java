package tcms.students;

import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SubjectChangeRequestWindow extends JFrame implements ActionListener {
    private final Student student;
    private final SubjectChangeRequestManager manager;

    private JTable requestTable;
    private DefaultTableModel tableModel;
    private JTextArea reasonField;
    private JButton submitButton, deleteButton;
    private JComboBox<String> newSubjectDropdown;

    public SubjectChangeRequestWindow(Student student, SubjectChangeRequestManager manager) {
        this.student = student;
        this.manager = manager;

        buildUI();
        loadStudentRequests();

        setVisible(true);
    }

    private void buildUI() {
        setTitle("Subject Change Requests");
        setBounds(100, 100, 1200, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 34, 34));
        panel.setLayout(null); 
        setContentPane(panel);

        
        JLabel lblNewLabel = new JLabel("SUBJECT CHANGE REQUESTS");
        lblNewLabel.setBounds(373, 20, 322, 26);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
        panel.add(lblNewLabel);

        // Table setup
        String[] columns = {"Request ID", "New Subject", "Reason"};
        tableModel = new DefaultTableModel(columns, 0);
        requestTable = new JTable(tableModel);
        requestTable.setBackground(Constants.DARK_GRAY);
        requestTable.setForeground(Color.WHITE);
        requestTable.setSelectionBackground(new Color(173, 216, 230));
        requestTable.setSelectionForeground(Color.BLACK);
        requestTable.setFont(new Font("Arial Black", Font.PLAIN, 14));
        requestTable.setRowHeight(25);

        JTableHeader header = requestTable.getTableHeader();
        header.setBackground(new Color(80, 101, 242));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial Black", Font.BOLD, 14));

        JScrollPane tableScrollPane = new JScrollPane(requestTable);
        tableScrollPane.setBounds(132, 70, 833, 250);
        panel.add(tableScrollPane);
        tableScrollPane.getViewport().setBackground(Constants.DARK_GRAY);
        requestTable.setGridColor(Constants.LIGHT_GREY);
        requestTable.setShowGrid(true);

        // New Subject 
        JLabel subjectLabel = new JLabel("New Subject:");
        subjectLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
        subjectLabel.setForeground(Color.WHITE);
        subjectLabel.setBounds(22, 348, 105, 25);
        panel.add(subjectLabel);

        // Dropdown 
        String[] subjects = {"-- Select Subject --", "Mathematics", "Science", "English", "History", "Geography", "Physics", "Chemistry", "Biology","Add Math", "Economics", "Information Tech", "Moral Edu", "Visual Arts"};
        newSubjectDropdown = new JComboBox<>(subjects);
        newSubjectDropdown.setBounds(137, 350, 300, 25);
        panel.add(newSubjectDropdown);

        // Reason label
        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
        reasonLabel.setForeground(Color.WHITE);
        reasonLabel.setBounds(22, 406, 100, 25);
        panel.add(reasonLabel);

        // Reason 
        reasonField = new JTextArea();
        reasonField.setBounds(137, 408, 498, 98);
        panel.add(reasonField);

        // Submit 
        submitButton = new JButton("Submit Request");
        submitButton.setBackground(new Color(88, 101, 242));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(137, 532, 180, 30);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        // Delete
        deleteButton = new JButton("Delete Selected Request");
        deleteButton.setBackground(new Color(88, 101, 242));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBounds(373, 532, 220, 30);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(41, 41, 41));
        panel_1.setBounds(0, 0, 1059, 60);
        panel.add(panel_1);
        
        
    }

    private void loadStudentRequests() {
        tableModel.setRowCount(0);
        List<SubjectChangeRequest> requests = manager.getRequestsByStudentId(student.getStudentID());
        for (SubjectChangeRequest req : requests) {
            tableModel.addRow(new Object[]{req.getRequestId(), req.getNewSubject(), req.getReason()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String newSubject = (String) newSubjectDropdown.getSelectedItem();
            String reason = reasonField.getText().trim();

            if (newSubject == null || newSubject.equals("-- Select Subject --")) {
                JOptionPane.showMessageDialog(this, "Please select a subject.");
                return;
            }
            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a reason for the subject change.");
                return;
            }

            int newId = manager.getNextRequestId();
            SubjectChangeRequest req = new SubjectChangeRequest(newId, student.getStudentID(), newSubject, reason);
            manager.addRequest(req);
            manager.saveRequests(Constants.SUBJECT_CHANGE_REQUESTS_CSV);
            loadStudentRequests();
            newSubjectDropdown.setSelectedIndex(0);
            reasonField.setText("");
            JOptionPane.showMessageDialog(this, "Request submitted successfully.");
        } else if (e.getSource() == deleteButton) {
            int selectedRow = requestTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a request to delete.");
                return;
            }
            int requestId = (int) tableModel.getValueAt(selectedRow, 0);
            manager.deleteRequestById(requestId);
            manager.saveRequests(Constants.SUBJECT_CHANGE_REQUESTS_CSV);
            loadStudentRequests();
            JOptionPane.showMessageDialog(this, "Request deleted.");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        UserManager um = new UserManager();
        StudentManager sm = new StudentManager();
        um.loadUsers(Constants.USERS_CSV);
        sm.loadStudents(Constants.STUDENTS_CSV);

        User user = um.findUserByUsername("Gracious");
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        Student student = sm.findStudentByUserID(user.getID());
        SubjectChangeRequestManager scm = new SubjectChangeRequestManager();
        scm.loadRequests(Constants.SUBJECT_CHANGE_REQUESTS_CSV);

        new SubjectChangeRequestWindow(student, scm);
    }
}
