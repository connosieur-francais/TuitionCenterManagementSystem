package tcms.gui;

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

import tcms.admin.AdminPage;
import tcms.users.User;
import tcms.users.UserManager;
import javax.swing.ImageIcon;

public class LoginPage extends JFrame implements ActionListener{

	String userFilePath = "src/users.csv";
	UserManager userManager = new UserManager();

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
	Font titleFont = new Font("SansSerif", Font.BOLD, 32);
	Font textFont = new Font("SansSerif", Font.BOLD, 14);
	Color bgColor = new Color(7, 15, 43);
	Color panelColor = new Color(27, 26, 85);
	Color textColor = new Color(255,255,255);
	private JLabel backgroundIconDeco;

	/**
	 * Create the frame.
	 */
	public LoginPage()
	{
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
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(textFont);
		usernameLabel.setBounds(70, 90, 75, 25);
		loginPanel.add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(textFont);
		passwordLabel.setBounds(70, 140, 75, 25);
		loginPanel.add(passwordLabel);

		usernameTxtfield = new JTextField();
		usernameTxtfield.setBounds(150, 90, 270, 25);
		loginPanel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);

		showPasswordBtn = new JToggleButton("Show Password");
		showPasswordBtn.setBounds(305, 165, 115, 15);
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
		loginBtn.setBounds(320, 200, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);
		loginPanel.add(loginBtn);

		resetBtn = new JButton("Reset");
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
		backgroundIconDeco.setIcon(new ImageIcon("src\\loginPageBackground.png"));

		frame.setVisible(true);

		// Load CSV -------------------------------------
		userManager.loadUsers(userFilePath);

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
						attemptsLeftLabel.setVisible(false);
					} else {
						passwordErrorLabel.setVisible(true);
						usernameErrorLabel.setVisible(false);
						loginAttemptLabel.setText("Attempts left:");
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

	private void selectRoleLoginPage(User user) { // TO DO ----------------------------------
		String role = user.getRole();
		switch (role) {
		case "admin":
			userManager.saveUsers(userFilePath); // save users before moving on
			new AdminPage(user);
			frame.dispose();
		case "receptionist":
			// to-do
		case "tutor":
			// to-do
		case "student":
			// to-do
		}
	}
}
