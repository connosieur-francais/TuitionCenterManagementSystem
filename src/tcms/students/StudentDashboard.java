package tcms.students;

import tcms.tutors.*;
import tcms.receptionists.*;

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
import javax.swing.border.MatteBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;



public class StudentDashboard extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

   
    private final UserManager um;
    private final StudentManager sm;
    private final User user;
    private final Student student;
    private final SubjectChangeRequestManager requestManager;

    private CustomJButton btnSchedule;
    private CustomJButton btnRequests;
    private CustomJButton btnPayments;
    private CustomJButton btnProfile;
    
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
    private JLabel lblNewLabel_1;
    private JPanel UpdateLog;
    private JPanel StudentInfoLog;

   
    public StudentDashboard(User u, UserManager userManager, StudentManager studentManager) {
        this.um = userManager;
        this.sm = studentManager;
        this.user = u;
        this.student = sm.findStudentByUserID(u.getID());
        this.requestManager = new SubjectChangeRequestManager();
        this.requestManager.loadRequests(Constants.SUBJECT_CHANGE_REQUESTS_CSV);
        
        buildUi();
        updateDashboardInformation();
    }

  
    private void buildUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Dashboard");
        setBounds(100, 100, 1200, 750);
        setResizable(false); 

        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(43, 45, 49));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        buildHeader();
        buildProfileSection();
        buildCarouselSection();
        
        JPanel OptionsLog = new JPanel();
        OptionsLog.setBorder(new MatteBorder(2, 3, 3, 3, (Color) new Color(70, 70, 70)));
        OptionsLog.setBackground(new Color(37, 37, 37));
        OptionsLog.setBounds(931, 151, 225, 520);
        contentPane.add(OptionsLog);
        OptionsLog.setLayout(null);
        
        btnSchedule = new CustomJButton();
        btnSchedule.setBounds(22, 44, 175, 53);
        OptionsLog.add(btnSchedule);
        btnSchedule.setText("Class Schedule");
        btnSchedule.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        btnSchedule.setForeground(WHITE_TEXT);
        btnSchedule.setBackground(new Color(70, 70, 70));
        btnSchedule.setRadius(30);
        btnSchedule.setFocusPainted(false);
        
                btnRequests = new CustomJButton();
                btnRequests.setBounds(22, 166, 175, 53);
                OptionsLog.add(btnRequests);
                btnRequests.setText("Requests");
                btnRequests.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
                btnRequests.setForeground(WHITE_TEXT);
                btnRequests.setBackground(new Color(70, 70, 70));
                btnRequests.setRadius(30);
                btnRequests.setFocusPainted(false);
                
                        btnPayments = new CustomJButton();
                        btnPayments.setBounds(22, 285, 175, 53);
                        OptionsLog.add(btnPayments);
                        btnPayments.setText("Payment Status");
                        btnPayments.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
                        btnPayments.setForeground(WHITE_TEXT);
                        btnPayments.setBackground(new Color(70, 70, 70));
                        btnPayments.setRadius(30);
                        btnPayments.setFocusPainted(false);
                        
                                btnProfile = new CustomJButton();
                                btnProfile.setBounds(22, 409, 175, 53);
                                OptionsLog.add(btnProfile);
                                btnProfile.setText("View Profile");
                                btnProfile.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
                                btnProfile.setForeground(WHITE_TEXT);
                                btnProfile.setBackground(new Color(70, 70, 70));
                                btnProfile.setRadius(30);
                                btnProfile.setFocusPainted(false);
                                
                                JPanel WelcomeLog = new JPanel();
                                WelcomeLog.setBorder(new MatteBorder(3, 0, 3, 3, (Color) new Color(83, 101, 242)));
                                WelcomeLog.setBackground(new Color(37, 37, 37));
                                WelcomeLog.setBounds(204, 84, 652, 176);
                                contentPane.add(WelcomeLog);
								WelcomeLog.setLayout(null);

								lblNewLabel_1 = new JLabel("WELCOME,");
								lblNewLabel_1.setForeground(new Color(255, 255, 255));
								lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
								lblNewLabel_1.setBounds(41, 91, 158, 35);
								WelcomeLog.add(lblNewLabel_1);

								usernameLabel = new JLabel();
								usernameLabel.setBounds(171, 88, 134, 40);
								WelcomeLog.add(usernameLabel);
								usernameLabel.setForeground(WHITE_TEXT);
								usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
								usernameLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));

								JLabel idLabel = new JLabel("| ID:");
								idLabel.setBounds(531, 136, 44, 40);
								WelcomeLog.add(idLabel);
								idLabel.setForeground(WHITE_TEXT);
								idLabel.setFont(new Font("Arial", Font.BOLD, 18));

								studentIDLabel = new JLabel();
								studentIDLabel.setBounds(574, 136, 22, 40);
								WelcomeLog.add(studentIDLabel);
								studentIDLabel.setForeground(WHITE_TEXT);
								studentIDLabel.setFont(new Font("Arial", Font.BOLD, 18));

								JPanel panel_3 = new JPanel();
								panel_3.setBackground(new Color(40, 43, 48));
								panel_3.setBounds(0, 49, 1186, 40);
								contentPane.add(panel_3);
								panel_3.setLayout(null);

								JPanel QuickAccessLog = new JPanel();
								QuickAccessLog.setBounds(931, 91, 225, 58);
								contentPane.add(QuickAccessLog);
								QuickAccessLog.setBackground(new Color(88, 101, 242));
								QuickAccessLog.setLayout(null);

								JLabel lblNewLabel = new JLabel("QUICK ACCESS");
								lblNewLabel.setBounds(10, 16, 197, 32);
								QuickAccessLog.add(lblNewLabel);
								lblNewLabel.setBackground(new Color(30, 80, 130));
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 22));
								lblNewLabel.setForeground(new Color(255, 255, 255));

								UpdateLog = new JPanel();
								UpdateLog.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(83, 101, 242)));
								UpdateLog.setBackground(new Color(37, 37, 37));
								UpdateLog.setBounds(39, 344, 383, 50);
								contentPane.add(UpdateLog);
								UpdateLog.setLayout(null);

								JLabel lblNewLabel_2 = new JLabel("Important Updates!!");
								lblNewLabel_2.setBounds(87, 10, 223, 26);
								UpdateLog.add(lblNewLabel_2);
								lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2.setForeground(new Color(255, 255, 255));
								lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));

								StudentInfoLog = new JPanel();
								StudentInfoLog.setBackground(new Color(37, 37, 37));
								StudentInfoLog.setBounds(519, 344, 337, 327);
								contentPane.add(StudentInfoLog);

								btnProfile.addActionListener(this);
								btnPayments.addActionListener(this);
								btnRequests.addActionListener(this);
								btnSchedule.addActionListener(this);

								setVisible(true);
							}

							private void buildHeader() {
								JPanel headerPanel = new JPanel();
								headerPanel.setBackground(new Color(37, 37, 37));
								headerPanel.setBounds(0, 0, 1186, 50);
								contentPane.add(headerPanel);

								notificationsBtn = new JButton("🔔");
								notificationsBtn.setBackground(new Color(70, 70, 70));
								notificationsBtn.setForeground(WHITE_TEXT);
								notificationsBtn.setFocusPainted(false);
								notificationsBtn.addActionListener(this);

								logoutBtn = new JButton("⍈ Logout");
								logoutBtn.setBackground(new Color(70, 70, 70));
								logoutBtn.setForeground(WHITE_TEXT);
								logoutBtn.setFocusPainted(false);
								logoutBtn.addActionListener(this);

								GroupLayout gl = new GroupLayout(headerPanel);
								gl.setHorizontalGroup(gl.createParallelGroup(Alignment.LEADING)
										.addGroup(gl.createSequentialGroup().addGap(934)
												.addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addGap(30).addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 125,
														GroupLayout.PREFERRED_SIZE)
        			.addGap(33))
        );
        gl.setVerticalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addGap(10)
        			.addGroup(gl.createParallelGroup(Alignment.BASELINE)
        				.addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        				.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        );
        headerPanel.setLayout(gl);
    }

    
    private void buildProfileSection() {
        JPanel profilePanel = new JPanel();
        profilePanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(83, 101, 242)));
        profilePanel.setBackground(DARK_GREY);
        profilePanel.setBounds(39, 84, 165, 176);
        contentPane.add(profilePanel);

        
        ImageIcon origIcon = new ImageIcon(Constants.STUDENT_USER_ICON_FILE);
        Image scaledImg = origIcon.getImage().getScaledInstance(150, 159, Image.SCALE_SMOOTH);

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
        holder.setBounds(39, 399, 382, 272);
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
        }
        if (src == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging Out...");
            dispose();
        }
        if (src == nextBtn) {
            cardLayout.next(whatsNewPanel); 
        }
        if (src == previousBtn) {
            cardLayout.previous(whatsNewPanel); 
        }
        if (e.getSource() == btnRequests) {
        	 SwingUtilities.invokeLater(() -> new SubjectChangeRequestWindow(student, requestManager).setVisible(true));
        }
        if (e.getSource() == btnSchedule) {
        	if (e.getSource() == btnSchedule) {
        	    ScheduleManager scheduleManager = new ScheduleManager();
        	    scheduleManager.loadSchedule(Constants.CLASSES_CSV); // path to your CSV
        	    SwingUtilities.invokeLater(() -> new ScheduleViewer(student.getStudentID(), scheduleManager));
        	}

        }
        if (e.getSource() == btnPayments) {
        	if (e.getSource() == btnPayments) {
        		SwingUtilities.invokeLater(() -> {
        		    ReceptionistManager rm = new ReceptionistManager();
        		    rm.loadPayments(Constants.PAYMENTS_CSV);  // Load the payment records
        		    new StudentPaymentWindow(rm, student.getStudentID());
        		});
;
        	}

        }
        if (e.getSource() == btnProfile) {
        	new UpdateStudentWindow(user, um, sm).setVisible(true);
        	
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