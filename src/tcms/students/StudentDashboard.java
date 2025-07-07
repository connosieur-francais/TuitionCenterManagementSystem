package tcms.students;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.*;
import tcms.students.UpdateStudentWindow;



public class StudentDashboard extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

   
    private final UserManager um;
    private final StudentManager sm;
    private final User user;
    private final Student student;

    
    private JPanel contentPane;
    private JLabel usernameLabel;
    private JLabel studentIDLabel;
    private JButton notificationsBtn;
    private JButton logoutBtn;
    private JButton nextBtn;
    private JButton previousBtn;
    private CardLayout cardLayout;
    private JPanel whatsNewPanel;

    
    private static final Color DARK_GREY = new Color(34, 34, 34);
    private static final Color BLUE_ACCENT = new Color(70, 130, 180);
    private static final Color WHITE_TEXT = Color.WHITE;

   
    public StudentDashboard(User u, UserManager userManager, StudentManager studentManager) {
        this.um = userManager;
        this.sm = studentManager;
        this.user = u;
        this.student = sm.findStudentByUserID(u.getID());

        buildUi();
        updateDashboardInformation();
    }

  
    private void buildUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Dashboard");
        setBounds(100, 100, 1200, 750);
        setResizable(false); 

        
        contentPane = new JPanel();
        contentPane.setBackground(DARK_GREY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        buildHeader();
        buildProfileSection();
        buildCarouselSection();
        
        CustomJButton btnSchedule = new CustomJButton();
        btnSchedule.setBounds(90, 380, 500, 128);
        btnSchedule.setText("Class Schedule");
        btnSchedule.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        btnSchedule.setForeground(WHITE_TEXT);
        btnSchedule.setColor(BLUE_ACCENT);
        btnSchedule.setBackground(BLUE_ACCENT);
        btnSchedule.setRadius(30);
        btnSchedule.setFocusPainted(false);
        btnSchedule.addActionListener(this);
        contentPane.add(btnSchedule);

        CustomJButton btnRequests = new CustomJButton();
        btnRequests.setBounds(90, 512, 500, 128);
        btnRequests.setText("Requests");
        btnRequests.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        btnRequests.setForeground(WHITE_TEXT);
        btnRequests.setColor(BLUE_ACCENT);
        btnRequests.setBackground(BLUE_ACCENT);
        btnRequests.setRadius(30);
        btnRequests.setFocusPainted(false);
        btnRequests.addActionListener(this);
        contentPane.add(btnRequests);

        CustomJButton btnPayments = new CustomJButton();
        btnPayments.setBounds(670, 380, 500, 128);
        btnPayments.setText("Payment Status");
        btnPayments.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        btnPayments.setForeground(WHITE_TEXT);
        btnPayments.setColor(BLUE_ACCENT);
        btnPayments.setBackground(BLUE_ACCENT);
        btnPayments.setRadius(30);
        btnPayments.setFocusPainted(false);
        btnPayments.addActionListener(this);
        contentPane.add(btnPayments);

        CustomJButton btnProfile = new CustomJButton();
        btnProfile.setBounds(670, 512, 500, 128);
        btnProfile.setText("View Profile");
        btnProfile.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        btnProfile.setForeground(WHITE_TEXT);
        btnProfile.setColor(BLUE_ACCENT);
        btnProfile.setBackground(BLUE_ACCENT);
        btnProfile.setRadius(30);
        btnProfile.setFocusPainted(false);
        btnProfile.addActionListener(this);
        contentPane.add(btnProfile);

        setVisible(true);
    }

  
    private void buildHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(DARK_GREY);
        headerPanel.setBounds(0, 0, 1186, 60);
        contentPane.add(headerPanel);

        usernameLabel = new JLabel();
        usernameLabel.setForeground(WHITE_TEXT);
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel idLabel = new JLabel("| ID:");
        idLabel.setForeground(WHITE_TEXT);
        idLabel.setFont(new Font("Arial", Font.BOLD, 24));

        studentIDLabel = new JLabel();
        studentIDLabel.setForeground(WHITE_TEXT);
        studentIDLabel.setFont(new Font("Arial", Font.BOLD, 24));

        notificationsBtn = new JButton("🔔");
        notificationsBtn.setBackground(BLUE_ACCENT);
        notificationsBtn.setForeground(WHITE_TEXT);
        notificationsBtn.setFocusPainted(false);
        notificationsBtn.addActionListener(this);

        logoutBtn = new JButton("⍈ Logout");
        logoutBtn.setBackground(BLUE_ACCENT);
        logoutBtn.setForeground(WHITE_TEXT);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(this);

        GroupLayout gl = new GroupLayout(headerPanel);
        gl.setHorizontalGroup(gl.createParallelGroup(Alignment.LEADING)
                .addGroup(gl.createSequentialGroup()
                        .addGap(628)
                        .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(idLabel)
                        .addGap(10)
                        .addComponent(studentIDLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)));
        gl.setVerticalGroup(gl.createParallelGroup(Alignment.LEADING)
                .addGroup(gl.createSequentialGroup().addGap(10)
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(studentIDLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))));
        headerPanel.setLayout(gl);
    }

    
    private void buildProfileSection() {
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(DARK_GREY);
        profilePanel.setBounds(90, 70, 500, 300);
        contentPane.add(profilePanel);

        
        ImageIcon origIcon = new ImageIcon(Constants.STUDENT_USER_ICON_FILE);
        Image scaledImg = origIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);

        JLabel profilePictureLabel = new JLabel(new ImageIcon(scaledImg));
        profilePanel.add(profilePictureLabel);
    }

    private void buildCarouselSection() {
        cardLayout = new CardLayout();
        whatsNewPanel = new JPanel(cardLayout);
        whatsNewPanel.setBackground(DARK_GREY);
        whatsNewPanel.setPreferredSize(new Dimension(300, 300));

        for (int i = 0; i < Constants.IMG_CAROUSEL.length; i++) {
            ImageIcon icon = new ImageIcon(Constants.IMG_CAROUSEL[i]);
            Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            whatsNewPanel.add(imgLabel, "card" + i);
        }

        Timer autoSlide = new Timer(4000, e -> {
            cardLayout.next(whatsNewPanel);
        });
        autoSlide.start();

        JPanel holder = new JPanel();
        holder.setBounds(610, 70, 500, 300);
        holder.setLayout(new BorderLayout());
        contentPane.add(holder);

        previousBtn = new JButton("◀");
        previousBtn.setFocusPainted(false);
        previousBtn.addActionListener(this);
        holder.add(previousBtn, BorderLayout.WEST);

        holder.add(whatsNewPanel, BorderLayout.CENTER);

        nextBtn = new JButton("▶");
        nextBtn.setFocusPainted(false);
        nextBtn.addActionListener(this);
        holder.add(nextBtn, BorderLayout.EAST);
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == notificationsBtn) {
            JOptionPane.showMessageDialog(this, "No new notifications.");
        } else if (src == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging Out...");
            dispose();
        } else if (src == nextBtn) {
            cardLayout.next(whatsNewPanel); 
        } else if (src == previousBtn) {
            cardLayout.previous(whatsNewPanel); 
        } else if (src instanceof CustomJButton btn) {
            switch (btn.getText()) {
                case "Class Schedule" -> JOptionPane.showMessageDialog(this, "Loading Schedule...");
                case "Requests" -> JOptionPane.showMessageDialog(this, "Opening Requests...");
                case "Payment Status" -> JOptionPane.showMessageDialog(this, "Opening Payment Window...");
                case "View Profile" -> {
                    new UpdateStudentWindow(user, um, sm).setVisible(true);  
                    this.dispose();                                         
                }

                default -> {}
            }
        }
    }

    private void updateDashboardInformation() {
        usernameLabel.setText(user.getUsername());
        studentIDLabel.setText(String.valueOf(student.getStudentID()));
    }
    
    public static void main(String[] args) {
      
        UserManager um = new UserManager();
        StudentManager sm = new StudentManager();
        um.loadUsers(Constants.USERS_CSV);
        sm.loadStudents(Constants.STUDENTS_CSV);

       
        User demoUser = um.findUserByUsername("Gracious");   

        if (demoUser != null) {
            new StudentDashboard(demoUser, um, sm).setVisible(true);
        } else {
            System.out.println("Demo user not found. Check the username in users.csv.");
        }
    }
    
}
