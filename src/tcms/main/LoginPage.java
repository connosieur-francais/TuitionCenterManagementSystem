package tcms.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import tcms.admin.AdminManager;
import tcms.admin.AdminPage;
import tcms.receptionists.ReceptionistManager;
import tcms.students.StudentDashboard2;
import tcms.students.StudentManager;
import tcms.tutors.TutorManager;
import tcms.users.User;
import tcms.users.UserManager;

public class LoginPage extends JFrame implements ActionListener {

	private String userFilePath = "src/users.csv";
	private String tutorFilePath = "src/tutors.csv";
	private String studentFilePath = "src/students.csv";
	private String adminFilePath = "src/admins.csv";
	private String receptionistFilePath = "src/receptionists.csv";
	private String loginBackground = "src/tcms/resources/loginPageBackground.png";
	private UserManager userManager = new UserManager();
	private AdminManager adminManager = new AdminManager();
	private ReceptionistManager receptionistManager = new ReceptionistManager();
	private TutorManager tutorManager = new TutorManager();
	private StudentManager studentManager = new StudentManager();
	

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField usernameTxtfield;
	protected JPasswordField passwordField;
	private JPanel loginPanel, decoPanel;
	private JLabel loginLabel, usernameLabel, passwordLabel;
	private JToggleButton showPasswordBtn;
	private JButton loginBtn, resetBtn;
	private JLabel usernameErrorLabel, passwordErrorLabel;
	private JLabel loginAttemptLabel;
	private JLabel attemptsLeftLabel;
	private JLabel loginDisabledLabel;

	// Settings
	private Font titleFont = new Font("SansSerif", Font.BOLD, 32);
	private Font textFont = new Font("SansSerif", Font.BOLD, 15);
	private Color bgColor = new Color(7, 15, 43);
	private Color panelColor = new Color(27, 26, 85);
	private Color buttonColor = new Color(146, 144, 195);
	private JLabel backgroundIconDeco;

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		frame.setTitle("Tuition Center Management System");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(bgColor);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		loginPanel = new JPanel();
		loginPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loginPanel.setBackground(panelColor);
		loginPanel.setBounds(100, 80, 500, 250);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);

		loginLabel = new JLabel("Account Login");
		loginLabel.setForeground(new Color(146, 144, 195));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(titleFont);
		loginLabel.setBounds(100, 20, 300, 50);
		loginPanel.add(loginLabel);

		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setFont(textFont);
		usernameLabel.setBounds(70, 90, 80, 25);
		loginPanel.add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setFont(textFont);
		passwordLabel.setBounds(70, 140, 80, 25);
		loginPanel.add(passwordLabel);

		usernameTxtfield = new JTextField();
		usernameTxtfield.setBounds(150, 90, 270, 25);
		loginPanel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);

		showPasswordBtn = new JToggleButton("Show Password");
		showPasswordBtn.setBackground(buttonColor);
		showPasswordBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		showPasswordBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
		showPasswordBtn.setBounds(300, 165, 120, 15);
		showPasswordBtn.setFocusable(false);
		showPasswordBtn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		loginPanel.add(showPasswordBtn);

		loginBtn = new JButton("Login");
		loginBtn.setBackground(buttonColor);
		loginBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginBtn.setBounds(320, 200, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);
		loginPanel.add(loginBtn);

		resetBtn = new JButton("Reset");
		resetBtn.setBackground(buttonColor);
		resetBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		resetBtn.setBounds(220, 200, 100, 25);
		resetBtn.setFocusable(false);
		resetBtn.addActionListener(this);
		loginPanel.add(resetBtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 140, 270, 25);
		passwordField.setEchoChar('*');
		loginPanel.add(passwordField);

		usernameErrorLabel = new JLabel("* Username not registered");
		usernameErrorLabel.setForeground(Color.RED);
		usernameErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		usernameErrorLabel.setBounds(150, 75, 200, 15);
		usernameErrorLabel.setVisible(false); // Do not make it visible for now
		loginPanel.add(usernameErrorLabel);

		passwordErrorLabel = new JLabel("* Invalid password");
		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		passwordErrorLabel.setBounds(150, 125, 200, 15);
		passwordErrorLabel.setVisible(false);
		loginPanel.add(passwordErrorLabel);

		loginAttemptLabel = new JLabel("Attempts left:");
		loginAttemptLabel.setForeground(new Color(255, 255, 255));
		loginAttemptLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		loginAttemptLabel.setBounds(70, 205, 80, 15);
		loginAttemptLabel.setVisible(false); // Set invis until username is correct
		loginPanel.add(loginAttemptLabel);

		attemptsLeftLabel = new JLabel("");
		attemptsLeftLabel.setForeground(new Color(255, 255, 255));
		attemptsLeftLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		attemptsLeftLabel.setBounds(150, 205, 50, 15);
		attemptsLeftLabel.setVisible(false);
		loginPanel.add(attemptsLeftLabel);

		loginDisabledLabel = new JLabel("Login Disabled - contact admin to reset!");
		loginDisabledLabel.setForeground(Color.RED);
		loginDisabledLabel.setFont(new Font("SansSerif", Font.ITALIC, 10));
		loginDisabledLabel.setBounds(220, 185, 200, 15);
		loginDisabledLabel.setVisible(false);
		loginPanel.add(loginDisabledLabel);

		decoPanel = new JPanel();
		decoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		decoPanel.setBackground(new Color(83, 92, 145));
		decoPanel.setBounds(90, 70, 520, 270);
		contentPane.add(decoPanel);

		backgroundIconDeco = new JLabel("New label");
		backgroundIconDeco.setBounds(0, 0, 686, 413);
		contentPane.add(backgroundIconDeco);
		backgroundIconDeco.setIcon(new ImageIcon(loginBackground));

		frame.setVisible(true);

		// Load CSV information -------------------------------------
		userManager.loadUsers(userFilePath);
		adminManager.loadAdmins(adminFilePath);
		tutorManager.loadTutors(tutorFilePath);
		studentManager.loadStudents(studentFilePath);
		receptionistManager.loadReceptionists(receptionistFilePath);
		
		adminManager.updateAdminsInCSV();
		tutorManager.updateTutorsInCSV();
		receptionistManager.updateReceptionistInCSV();
		studentManager.updateStudentsInCSV();
		

		// Create a list to store the user data
		List<User> users = userManager.getAllUsers();

		// reset all the login attempts for the users ( TO BE EDITED )
		for (User user : users) {
			userManager.resetLoginAttempts(user);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resetBtn) {
			passwordField.setText("");
			usernameTxtfield.setText("");
			usernameErrorLabel.setVisible(false);
			passwordErrorLabel.setVisible(false);
			loginAttemptLabel.setVisible(false);
			loginDisabledLabel.setVisible(false);
			attemptsLeftLabel.setVisible(false);
			loginBtn.setEnabled(true);
			usernameTxtfield.grabFocus();
		}

		if (e.getSource() == loginBtn) {
			String username = usernameTxtfield.getText();
			String password = String.valueOf(passwordField.getPassword());

			if (!checkUsername(username)) {
				usernameErrorLabel.setVisible(true);
				passwordErrorLabel.setVisible(false);
				passwordField.setText("");
			} else {
				User user = userManager.findUserByUsername(username);

			if (!checkPassword(username, password)) {
				passwordErrorLabel.setVisible(true);
				usernameErrorLabel.setVisible(false);
				loginAttemptLabel.setText("Attempts left:");
				loginAttemptLabel.setVisible(true);
				attemptsLeftLabel.setVisible(true);
				checkLoginAttempts(user);
				passwordField.setText("");
			} else {
				selectRoleLoginPage(user);
			}}
		}
	}

	private boolean checkUsername(String username) {
		if (userManager.findUserByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkPassword(String username, String password) {
		User user = userManager.findUserByUsername(username);
		String userPassword = user.getPassword();
		if (password.equals(userPassword)) {
			return true;
		} else {
			return false;
		}
	}

	private void checkLoginAttempts(User user) {
		if (user.getLoginAttempts() <= 0) {
			loginDisabledLabel.setVisible(true);
			loginBtn.setEnabled(false);
			userManager.saveUsers(userFilePath);
		} else {
			userManager.decrementLoginAttempts(user);
			attemptsLeftLabel.setText(String.valueOf(user.getLoginAttempts()));
		}
	}

	private void selectRoleLoginPage(User user) { // TO DO ----------------------------------
		String role = user.getRole();
		switch (role) {
		case "admin":
			userManager.saveUsers(userFilePath); // save users before moving on
			new AdminPage(user);
			frame.dispose();
			break;
		case "receptionist":
			// to-do
		case "tutor":
			// to-do
		case "student":
			userManager.saveUsers(userFilePath);
			new StudentDashboard2(user);
			frame.dispose();
			break;
		}
	}
}
