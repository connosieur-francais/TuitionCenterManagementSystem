package tcms.students;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;


public class UpdateStudentWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    
    private final UserManager um;
    private final StudentManager sm;
    private final User user;
    private final Student student;

   
    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JLabel studentIDLabel;
    private JLabel subjectLabel;

    private static final Color DARK_GREY = new Color(34, 34, 34);
    private static final Color WHITE = Color.WHITE;

   
    public UpdateStudentWindow(User u, UserManager userManager, StudentManager studentManager) {
        this.um = userManager;
        this.sm = studentManager;
        this.user = u;
        this.student = sm.findStudentByUserID(u.getID());

        buildUi();
        populateDynamicFields();
    }

  
    private void buildUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 1002, 599);
        setTitle("Student | Edit Profile");

        
        mainPanel = new JPanel();
        mainPanel.setBackground(DARK_GREY);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setLayout(null); 
        setContentPane(mainPanel);


        
        JLabel title = new JLabel("PROFILE");
        title.setForeground(WHITE);
        title.setFont(new Font("Arial Black", Font.BOLD, 20));
        title.setBounds(425, 0, 101, 48);
        mainPanel.add(title);

       
        JLabel pfpLabel = new JLabel();
        pfpLabel.setBounds(36, 120, 282, 199);
        mainPanel.add(pfpLabel);

        ImageIcon raw = new ImageIcon(Constants.STUDENT_USER_ICON_FILE);
        Image scaled = raw.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
        pfpLabel.setIcon(new ImageIcon(scaled));

        
        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
        bottomLeft.setOpaque(false); 
        bottomLeft.setBounds(36, 357, 282, 175);
        mainPanel.add(bottomLeft);

        studentIDLabel = new JLabel();
        studentIDLabel.setForeground(WHITE);
        studentIDLabel.setFont(new Font("Arial", Font.BOLD, 22));
        studentIDLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomLeft.add(studentIDLabel);
        bottomLeft.add(Box.createVerticalStrut(8));

        usernameLabel = new JLabel();
        usernameLabel.setForeground(WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomLeft.add(usernameLabel);
        bottomLeft.add(Box.createVerticalStrut(8));

        subjectLabel = new JLabel();
        subjectLabel.setForeground(WHITE);
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subjectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomLeft.add(subjectLabel);
    }

   
    private void populateDynamicFields() {
        usernameLabel.setText("Username: " + user.getUsername());
        studentIDLabel.setText("Student ID: " + student.getStudentID());

        String subject = subjectView.getSubjectByStudentId(student.getStudentID());
        subjectLabel.setText("Subject: " + subject);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }
    public static void main(String[] args) {
        
        UserManager um = new UserManager();
        StudentManager sm = new StudentManager();
        um.loadUsers(Constants.USERS_CSV);
        sm.loadStudents(Constants.STUDENTS_CSV);

        User sampleUser = um.findUserByUsername("Gracious"); 
        if (sampleUser != null) {
            UpdateStudentWindow window = new UpdateStudentWindow(sampleUser, um, sm);
            window.setVisible(true);
        } else {
            System.out.println("Test user not found.");
        }
    }

}
