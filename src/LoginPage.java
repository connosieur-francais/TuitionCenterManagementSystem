import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

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
import javax.swing.JLayeredPane;

public class LoginPage extends JFrame implements ActionListener {

	String userFilePath = "src/users.csv";
	UserManager userManager = new UserManager();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxtfield;
	protected JPasswordField passwordField;
	private JPanel panel;
	private JLabel loginLabel, usernameLabel, passwordLabel;
	private JToggleButton showPasswordBtn;
	private JButton loginBtn, resetBtn;
	private JLabel usernameErrorLabel, passwordErrorLabel;
	private JLabel loginAttemptLabel;
	private JLabel attemptsLeftLabel;
	private JLabel loginDisabledLabel;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginPageTest frame = new LoginPageTest();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("Tuition Center Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(100, 80, 500, 250);
		contentPane.add(panel);
		panel.setLayout(null);

		loginLabel = new JLabel("Account Login");
		loginLabel.setBackground(new Color(255, 255, 255));
		loginLabel.setForeground(new Color(25, 25, 112));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		loginLabel.setBounds(100, 20, 300, 50);
		panel.add(loginLabel);

		usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Sans Serif Collection", Font.PLAIN, 14));
		usernameLabel.setBounds(70, 90, 75, 25);
		panel.add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Sans Serif Collection", Font.PLAIN, 14));
		passwordLabel.setBounds(70, 140, 75, 25);
		panel.add(passwordLabel);

		usernameTxtfield = new JTextField();
		usernameTxtfield.setBounds(150, 90, 270, 25);
		panel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);

		showPasswordBtn = new JToggleButton("Show Password");
		showPasswordBtn.setBounds(305, 165, 115, 15);
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
		panel.add(showPasswordBtn);

		loginBtn = new JButton("Login");
		loginBtn.setBounds(320, 200, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);
		panel.add(loginBtn);

		resetBtn = new JButton("Reset");
		resetBtn.setBounds(220, 200, 100, 25);
		resetBtn.setFocusable(false);
		resetBtn.addActionListener(this);
		panel.add(resetBtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 140, 270, 25);
		passwordField.setEchoChar('*');
		panel.add(passwordField);

		usernameErrorLabel = new JLabel("* Username not registered");
		usernameErrorLabel.setForeground(Color.RED);
		usernameErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		usernameErrorLabel.setBounds(150, 75, 200, 15);
		usernameErrorLabel.setVisible(false); // Do not make it visible for now
		panel.add(usernameErrorLabel);

		passwordErrorLabel = new JLabel("* Invalid password");
		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		passwordErrorLabel.setBounds(150, 125, 200, 15);
		passwordErrorLabel.setVisible(false);
		panel.add(passwordErrorLabel);

		loginAttemptLabel = new JLabel("Attempts left:");
		loginAttemptLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		loginAttemptLabel.setBounds(70, 205, 80, 15);
		loginAttemptLabel.setVisible(false); // Set invis until username is correct
		panel.add(loginAttemptLabel);

		attemptsLeftLabel = new JLabel("");
		attemptsLeftLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		attemptsLeftLabel.setBounds(150, 205, 50, 15);
		attemptsLeftLabel.setVisible(false);
		panel.add(attemptsLeftLabel);

		loginDisabledLabel = new JLabel("Login Disabled - contact admin to reset!");
		loginDisabledLabel.setForeground(Color.RED);
		loginDisabledLabel.setFont(new Font("SansSerif", Font.ITALIC, 10));
		loginDisabledLabel.setBounds(220, 185, 200, 15);
		loginDisabledLabel.setVisible(false);
		panel.add(loginDisabledLabel);

		// Load CSV -------------------------------------
		userManager.loadUsers(userFilePath);

		// Create a list to store the user data
		List<User> users = userManager.getAllUsers();

		// reset all the login attempts for the users
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
			loginDisabledLabel.setVisible(false);
			loginBtn.setEnabled(true);
			usernameTxtfield.grabFocus();
		}

		if (e.getSource() == loginBtn) {
			String username = usernameTxtfield.getText();
			String password = String.valueOf(passwordField.getPassword());

			if (checkUsername(username)) { // Checks for username match in users.csv
				User user = userManager.findUserByUsername(username); // if username exists; Create user here to check
																		// for login attempts
				if (checkPassword(username, password)) { // Check if password matches the set password.
					selectRoleLoginPage(user); // Opens the respective user page given the role of the user
				} else { // If password is false, show that password is false and decrement login
							// attempts by 1
					if (checkRole(user)) { // Admin - Infinite attempts. Other roles - only 3 attempts.
						passwordErrorLabel.setVisible(true);
						usernameErrorLabel.setVisible(false);
						loginAttemptLabel.setBounds(70, 205, 200, 15);
						loginAttemptLabel.setText("Login Attempts: Infinite");
						loginAttemptLabel.setVisible(true);
					} else {
						passwordErrorLabel.setVisible(true);
						usernameErrorLabel.setVisible(false);
						loginAttemptLabel.setVisible(true);
						attemptsLeftLabel.setVisible(true);
						checkLoginAttempts(user);
						passwordField.setText("");
					}
				}
			} else {
				usernameErrorLabel.setVisible(true);
				passwordErrorLabel.setVisible(false);
				passwordField.setText("");
			}
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

	private boolean checkRole(User user) { // This method checks if the username's role = Admin
											// In which case the login attempts should be infinite
		if (user.getRole().equalsIgnoreCase("admin")) {
			return true;
		} else {
			return false;
		}
	}

	private void selectRoleLoginPage(User user) {
		String role = user.getRole();
		switch (role) {
		case "admin":
			userManager.saveUsers(userFilePath); // save users before moving on
			new AdminPage();
			this.dispose();
		case "receptionist":
			// to-do
		case "tutor":
			// to-do
		case "student":
			// to-do
		}
	}
}
