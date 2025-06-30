package tcms.students;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.users.User;
import tcms.users.UserManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class StudentDashboard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, headerPanel;
	private JPanel buttonPanel, carouselHolderPanel, whatsNewPanel, profilePicturePanel;

	private JLabel usernameLabel, idLabel, studentIDLabel, profilePictureLabel;
	private JButton notificationsBtn, logoutBtn, nextBtn, previousBtn;
	private CustomJButton btn1, btn2, btn3, btn4;
	private CardLayout cardLayout;
	private GroupLayout grpLayoutHeaderPanel;

	private static UserManager um = new UserManager();
	private static StudentManager sm = new StudentManager();
	private static User user;
	private static Student student;

	private String userCSVFile = "src/users.csv";
	private String studentCSVFile = "src/students.csv";
	private Color darkGrey = new Color(34, 34, 34);
	private Color blueAcc = new Color(70, 130, 180);
	private Color whiteText = Color.WHITE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new StudentDashboard(new User(2, "Gracious", "123", "student", 3, "active"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentDashboard(User u) {
		sm.loadStudents(studentCSVFile);
		um.loadUsers(userCSVFile);

		user = u;
		student = sm.findStudentByUserID(u.getID());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Dashboard");
		setBounds(100, 100, 1200, 750);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(darkGrey);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		headerPanel = new JPanel();
		headerPanel.setBackground(new Color(34, 34, 34));
		headerPanel.setBounds(0, 0, 1186, 60);
		contentPane.add(headerPanel);

		usernameLabel = new JLabel("<dynamic>");
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));

		idLabel = new JLabel("| ID:");
		idLabel.setForeground(new Color(255, 255, 255));
		idLabel.setFont(new Font("Arial", Font.BOLD, 24));

		studentIDLabel = new JLabel("<dynamic>");
		studentIDLabel.setForeground(new Color(255, 255, 255));
		studentIDLabel.setFont(new Font("Arial", Font.BOLD, 24));

		notificationsBtn = new JButton("🔔");
		notificationsBtn.setBackground(blueAcc);
		notificationsBtn.setForeground(whiteText);
		notificationsBtn.addActionListener(this);
		notificationsBtn.setFocusPainted(false);
		notificationsBtn.addActionListener(this);

		logoutBtn = new JButton("⍈ Logout");
		logoutBtn.setBackground(blueAcc);
		logoutBtn.setForeground(whiteText);
		logoutBtn.setFocusPainted(false);
		logoutBtn.addActionListener(this);
		grpLayoutHeaderPanel = new GroupLayout(headerPanel);
		grpLayoutHeaderPanel.setHorizontalGroup(grpLayoutHeaderPanel.createParallelGroup(Alignment.LEADING).addGroup(grpLayoutHeaderPanel
				.createSequentialGroup().addGap(628)
				.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(studentIDLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(9)
				.addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)));
		grpLayoutHeaderPanel.setVerticalGroup(grpLayoutHeaderPanel.createParallelGroup(Alignment.LEADING).addGroup(grpLayoutHeaderPanel
				.createSequentialGroup().addGap(10)
				.addGroup(grpLayoutHeaderPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentIDLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))));
		headerPanel.setLayout(grpLayoutHeaderPanel);

		profilePicturePanel = new JPanel();
		profilePicturePanel.setBackground(new Color(34, 34, 34));
		profilePicturePanel.setBounds(90, 70, 500, 300);
		contentPane.add(profilePicturePanel);

		profilePictureLabel = new JLabel();
		ImageIcon origIcon = new ImageIcon("src\\tcms\\resources\\student_profile_picture.jpg");
		Image scaledImg = origIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		profilePictureLabel.setIcon(new ImageIcon(scaledImg));
		profilePicturePanel.add(profilePictureLabel);

		// IMAGE CAROUSEL SECTION
		cardLayout = new CardLayout();
		whatsNewPanel = new JPanel(cardLayout);
		whatsNewPanel.setBackground(darkGrey);
		whatsNewPanel.setPreferredSize(new Dimension(300, 300));

		String[] imagePaths = { "src\\tcms\\resources\\carousel_schedule.jpg",
				"src\\tcms\\resources\\carousel_briefing.png", "src\\tcms\\resources\\carousel_enroll.jpg" };

		for (int i = 0; i < imagePaths.length; i++) {
			ImageIcon icon = new ImageIcon(imagePaths[i]);
			Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			JLabel imageLabel = new JLabel(new ImageIcon(scaled), JLabel.RIGHT);
			imageLabel.setHorizontalAlignment(JLabel.CENTER);
			whatsNewPanel.add(imageLabel, "card" + i);
		}

		Timer autoSlide = new Timer(4000, new ActionListener() {
			int currentIndex = 0;

			public void actionPerformed(ActionEvent e) {
				currentIndex = (currentIndex + 1) % imagePaths.length;
				cardLayout.show(whatsNewPanel, "card" + currentIndex);
			}
		});
		autoSlide.start();

		carouselHolderPanel = new JPanel();
		carouselHolderPanel.setBounds(610, 70, 500, 300);
		contentPane.add(carouselHolderPanel);
		carouselHolderPanel.setLayout(new BorderLayout(0, 0));
		carouselHolderPanel.add(whatsNewPanel, BorderLayout.CENTER);

		previousBtn = new JButton("◀");
		previousBtn.setMaximumSize(new Dimension(40, 20));
		previousBtn.setFocusPainted(false);
		carouselHolderPanel.add(previousBtn, BorderLayout.WEST);

		nextBtn = new JButton("▶");
		nextBtn.setMaximumSize(new Dimension(40, 20));
		nextBtn.setFocusPainted(false);
		carouselHolderPanel.add(nextBtn, BorderLayout.EAST);

		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(34, 34, 34));
		buttonPanel.setBounds(100, 380, 1000, 300);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(2, 2, 50, 50));

		btn1 = new CustomJButton();
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		btn1.setColor(new Color(70, 130, 180));
		btn1.setRadius(30);
		btn1.setText("Class Schedule");
		btn1.setBackground(blueAcc);
		btn1.addActionListener(this);
		buttonPanel.add(btn1);

		btn2 = new CustomJButton();
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		btn2.setRadius(30);
		btn2.setText("Requests");
		btn2.setColor(blueAcc);
		btn2.addActionListener(this);
		buttonPanel.add(btn2);

		btn3 = new CustomJButton();
		btn3.setForeground(new Color(255, 255, 255));
		btn3.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		btn3.setRadius(30);
		btn3.setText("Payment Status");
		btn3.setColor(blueAcc);
		btn3.addActionListener(this);
		buttonPanel.add(btn3);

		btn4 = new CustomJButton();
		btn4.setForeground(new Color(255, 255, 255));
		btn4.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		btn4.setRadius(30);
		btn4.setText("View Profile");
		btn4.setColor(blueAcc);
		btn4.addActionListener(this);
		buttonPanel.add(btn4);

		updateDashboardInformation(user, student);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// HEADER PANEL
		if (e.getSource() == notificationsBtn) {
			JOptionPane.showMessageDialog(this, "No new notifications.");
		}
		if (e.getSource() == logoutBtn) {
			JOptionPane.showMessageDialog(this, "Logging Out...");
			dispose();
		}
		// CAROUSEL PANEL
		if (e.getSource() == nextBtn) {
			cardLayout.previous(whatsNewPanel);
		}
		if (e.getSource() == previousBtn) {
			cardLayout.next(whatsNewPanel);
		}

		// BUTTON PANEL
		if (e.getSource() == btn1) {
			JOptionPane.showMessageDialog(this, "Loading Schedule...");
		}
		if (e.getSource() == btn2) {
			JOptionPane.showMessageDialog(this, "Opening Requests...");
		}
		if (e.getSource() == btn3) {
			JOptionPane.showMessageDialog(this, "Opening Payment Window...");
		}
		if (e.getSource() == btn4) {
			JOptionPane.showMessageDialog(this, "Viewing Profile...");
		}
	}

	public void updateDashboardInformation(User u, Student s) {
		usernameLabel.setText(u.getUsername());
		studentIDLabel.setText(String.valueOf(s.getStudentID()));
	}
}
