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
        buildButtonGrid();

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

    
    private void buildButtonGrid() {
        JPanel grid = new JPanel(new java.awt.GridLayout(2, 2, 50, 50));
        grid.setBackground(DARK_GREY);
        grid.setBounds(100, 380, 1000, 300);
        contentPane.add(grid);

        CustomJButton btnSchedule = createTileButton("Class Schedule");
        CustomJButton btnRequests = createTileButton("Requests");
        CustomJButton btnPayments = createTileButton("Payment Status");
        CustomJButton btnProfile = createTileButton("View Profile");

        grid.add(btnSchedule);
        grid.add(btnRequests);
        grid.add(btnPayments);
        grid.add(btnProfile);
    }

    private CustomJButton createTileButton(String text) {
        CustomJButton btn = new CustomJButton();
        btn.setText(text);
        btn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        btn.setForeground(WHITE_TEXT);
        btn.setColor(BLUE_ACCENT);
        btn.setBackground(BLUE_ACCENT);
        btn.setRadius(30);
        btn.setFocusPainted(false);
        btn.addActionListener(this);
        return btn;
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
                case "View Profile" -> JOptionPane.showMessageDialog(this, "Viewing Profile...");
                default -> {}
            }
        }
    }


    private void updateDashboardInformation() {
        usernameLabel.setText(user.getUsername());
        studentIDLabel.setText(String.valueOf(student.getStudentID()));
    }
}
