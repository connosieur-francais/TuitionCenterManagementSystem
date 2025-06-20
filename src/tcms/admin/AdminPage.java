package tcms.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import tcms.users.User;
import tcms.users.UserManager;

public class AdminPage extends JFrame implements ActionListener {

	private AdminManager adminManager = new AdminManager();
	private UserManager userManager = new UserManager();

	private Admin admin;
	private User user;
	private String adminCSVFile = "src//admins.csv";
	private String userCSVFile = "src//users.csv";

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JPanel headerPanel;
	private JButton updateProfileBtn, manageTutorsButton;
	private JButton manageReceptionistsBtn, viewIncomeBtn;
	private JLabel atcBannerLabel;
	private JLayeredPane contentPanel;

	// HEADER LABEL SETTINGS
	private Color buttonColor = new Color(83, 92, 145);
	private Color buttonTxtColor = Color.white;
	private Font buttonFont = new Font("SansSerif", Font.BOLD, 12);

	// UPDATE PROFILE PAGE ------------------------
	private JPanel updateProfilePanel;
	private JLabel profileSettingsLabel, usernameLabel;
	private JLabel passwordLabel, emailLabel, addressLabel;
	private JTextField usernameTxtfield, emailTxtfield;
	private JPasswordField passwordField;
	private JButton changeUsernameBtn, changePasswordBtn, changeEmailBtn, changeAddressBtn, saveChangesBtn;
	private JToggleButton showPasswordToggleBtn;
	private JLabel lblNewLabel;
	private JTextField addressTxtfield;

	// MANAGE TUTORS PAGE ------------------------------------
	private JPanel manageTutorsPanel;

	// MANAGE RECEPTIONISTS PAGE ------------------------------
	private JPanel manageReceptionistPanel;
	private JLabel ManageReceptionistsLabel;

	// VIEW MONTHLY INCOME PAGE -------------------------------
	private JPanel viewMonthlyIncomePanel;
	private JLabel viewMonthlyIncomeLabel;
	private JComboBox<String> yearSelector;
	private JComboBox<String> monthSelector;

	/**
	 * Create the frame.
	 */
	public AdminPage(User u) {

		user = u;
		// Upon starting this page, load all admins and check if all admin accounts
		// exist
		int user_id = user.getID();
		adminManager.loadAdmins(adminCSVFile); // Loads admin accounts
		userManager.loadUsers(userCSVFile); // Load user accounts (connected to admin)
		adminManager.updateAdminsInCSV(); // Checks if all admin accounts are created properly.
		adminManager.saveAdmins(adminCSVFile);

		admin = adminManager.findAdminByUserID(user_id);

		frame.setResizable(false);
		frame.setTitle("Admin Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		headerPanel = new JPanel();
		headerPanel.setBackground(new Color(7, 15, 43));
		headerPanel.setBounds(0, 0, 686, 80);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		atcBannerLabel = new JLabel("");
		atcBannerLabel.setIcon(
				new ImageIcon("C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\atcBanner.png"));
		atcBannerLabel.setBounds(0, 0, 150, 75);
		headerPanel.add(atcBannerLabel);

		updateProfileBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<p>Update</p>\r\n<p>Profile</p>\r\n</body>\r\n</html>");
		updateProfileBtn.setBackground(buttonColor);
		updateProfileBtn.setForeground(buttonTxtColor);
		updateProfileBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		updateProfileBtn.setFont(buttonFont);
		updateProfileBtn.addActionListener(this);
		updateProfileBtn.setFocusable(false);
		updateProfileBtn.setBounds(160, 10, 106, 60);
		headerPanel.add(updateProfileBtn);

		manageTutorsButton = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>Manage</p>\r\n<p>Tutors</p>\r\n</body>\r\n</html>");
		manageTutorsButton.setBackground(buttonColor);
		manageTutorsButton.setForeground(buttonTxtColor);
		manageTutorsButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		manageTutorsButton.setFont(buttonFont);
		manageTutorsButton.addActionListener(this);
		manageTutorsButton.setBounds(276, 10, 116, 60);
		manageTutorsButton.setFocusable(false);
		headerPanel.add(manageTutorsButton);

		manageReceptionistsBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<p>Manage</p>\r\n<p>Receptionists</p>\r\n</body>\r\n</html>");
		manageReceptionistsBtn.setBackground(buttonColor);
		manageReceptionistsBtn.setForeground(buttonTxtColor);
		manageReceptionistsBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		manageReceptionistsBtn.setFont(buttonFont);
		manageReceptionistsBtn.addActionListener(this);
		manageReceptionistsBtn.setBounds(402, 10, 106, 60);
		manageReceptionistsBtn.setFocusable(false);
		headerPanel.add(manageReceptionistsBtn);

		viewIncomeBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>View Monthly</p>\r\n<p>Income Report</p>\r\n</body>\r\n</html>");
		viewIncomeBtn.setBackground(buttonColor);
		viewIncomeBtn.setForeground(buttonTxtColor);
		viewIncomeBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		viewIncomeBtn.setFont(buttonFont);
		viewIncomeBtn.addActionListener(this);
		viewIncomeBtn.setBounds(518, 10, 116, 60);
		viewIncomeBtn.setFocusable(false);
		headerPanel.add(viewIncomeBtn);

		contentPanel = new JLayeredPane();
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPanel.setBackground(new Color(27, 26, 85));
		contentPanel.setBounds(0, 80, 686, 335);
		contentPane.add(contentPanel);
		contentPanel.setLayout(null);

		// UPDATE PROFILE PANEL
		// -----------------------------------------------------------------------
		updateProfilePanel = new JPanel();
		updateProfilePanel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		contentPanel.setLayer(updateProfilePanel, 0);
		updateProfilePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		updateProfilePanel.setBackground(new Color(83, 92, 145));
		updateProfilePanel.setBounds(10, 5, 666, 320);
		updateProfilePanel.setVisible(false);
		contentPanel.add(updateProfilePanel);
		updateProfilePanel.setLayout(null);

		profileSettingsLabel = new JLabel("Profile Settings");
		profileSettingsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		profileSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSettingsLabel.setBounds(10, 0, 120, 40);
		profileSettingsLabel.setBackground(new Color(255, 255, 255));
		profileSettingsLabel.setForeground(new Color(255, 255, 255));
		profileSettingsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		updateProfilePanel.add(profileSettingsLabel);

		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		usernameLabel.setBounds(10, 50, 70, 20);
		updateProfilePanel.add(usernameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		passwordLabel.setBounds(10, 100, 70, 20);
		updateProfilePanel.add(passwordLabel);

		usernameTxtfield = new JTextField();
		usernameTxtfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		usernameTxtfield.setBackground(new Color(192, 192, 192));
		usernameTxtfield.setEnabled(false);
		usernameTxtfield.setDisabledTextColor(Color.GRAY);
		usernameTxtfield.setBounds(80, 50, 200, 20);
		updateProfilePanel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBackground(new Color(192, 192, 192));
		passwordField.setEnabled(false);
		passwordField.setDisabledTextColor(Color.GRAY);
		passwordField.setEchoChar('*');
		passwordField.setBounds(80, 100, 200, 20);
		updateProfilePanel.add(passwordField);

		changeUsernameBtn = new JButton("Change Username");
		changeUsernameBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		changeUsernameBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changeUsernameBtn.setBackground(new Color(235, 235, 235));
		changeUsernameBtn.setFocusable(false);
		changeUsernameBtn.setBounds(293, 50, 120, 20);
		changeUsernameBtn.addActionListener(this);
		updateProfilePanel.add(changeUsernameBtn);

		changePasswordBtn = new JButton("Change Password");
		changePasswordBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		changePasswordBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changePasswordBtn.setBackground(new Color(235, 235, 235));
		changePasswordBtn.addActionListener(this);
		changePasswordBtn.setFocusable(false);
		changePasswordBtn.setBounds(293, 100, 120, 20);
		updateProfilePanel.add(changePasswordBtn);

		emailLabel = new JLabel("E-mail");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		emailLabel.setBounds(10, 150, 70, 20);
		updateProfilePanel.add(emailLabel);

		changeEmailBtn = new JButton("Change E-mail");
		changeEmailBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		changeEmailBtn.setBackground(new Color(235, 235, 235));
		changeEmailBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changeEmailBtn.addActionListener(this);
		changeEmailBtn.setFocusable(false);
		changeEmailBtn.setBounds(293, 150, 120, 20);
		updateProfilePanel.add(changeEmailBtn);

		showPasswordToggleBtn = new JToggleButton("Show Password");
		showPasswordToggleBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		showPasswordToggleBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		showPasswordToggleBtn.setBackground(new Color(235, 235, 235));
		showPasswordToggleBtn.setFocusable(false);
		showPasswordToggleBtn.setBounds(160, 120, 120, 15);
		showPasswordToggleBtn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0); // 0 Shows the characters
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		updateProfilePanel.add(showPasswordToggleBtn);

		emailTxtfield = new JTextField();
		emailTxtfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		emailTxtfield.setBackground(new Color(192, 192, 192));
		emailTxtfield.setEnabled(false);
		emailTxtfield.setDisabledTextColor(Color.GRAY);
		emailTxtfield.setColumns(10);
		emailTxtfield.setBounds(80, 150, 200, 20);
		updateProfilePanel.add(emailTxtfield);

		addressLabel = new JLabel("Address");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		addressLabel.setBounds(10, 200, 70, 20);
		updateProfilePanel.add(addressLabel);

		addressTxtfield = new JTextField();
		addressTxtfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addressTxtfield.setBackground(new Color(192, 192, 192));
		addressTxtfield.setText("<dynamic>");
		addressTxtfield.setEnabled(false);
		addressTxtfield.setDisabledTextColor(Color.GRAY);
		addressTxtfield.setColumns(10);
		addressTxtfield.setBounds(80, 200, 200, 20);
		updateProfilePanel.add(addressTxtfield);

		changeAddressBtn = new JButton("Change Address");
		changeAddressBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		changeAddressBtn.setBackground(new Color(235, 235, 235));
		changeAddressBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changeAddressBtn.addActionListener(this);
		changeAddressBtn.setFocusable(false);
		changeAddressBtn.setBounds(293, 200, 120, 20);
		updateProfilePanel.add(changeAddressBtn);

		saveChangesBtn = new JButton("Save Changes");
		saveChangesBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		saveChangesBtn.setBackground(new Color(235, 235, 235));
		saveChangesBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		saveChangesBtn.addActionListener(this);
		saveChangesBtn.setFocusable(false);
		saveChangesBtn.setBounds(506, 270, 150, 40);
		updateProfilePanel.add(saveChangesBtn);

		updateProfilePanelInformation(user, admin); // Update the information in txtfields with logged in user's
													// information

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src\\loginPageBackground.png"));
		lblNewLabel.setBounds(0, 0, 686, 335);
		contentPanel.add(lblNewLabel);

		// MANAGE RECEPTIONISTS PANEL
		// ---------------------------------------------------------------------
		manageReceptionistPanel = new JPanel();
		contentPanel.setLayer(manageReceptionistPanel, 1);
		manageReceptionistPanel.setBackground(new Color(83, 92, 145));
		manageReceptionistPanel.setVisible(false);
		manageReceptionistPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		manageReceptionistPanel.setBounds(10, 5, 666, 320);
		contentPanel.add(manageReceptionistPanel);
		manageReceptionistPanel.setLayout(null);

		ManageReceptionistsLabel = new JLabel("Manage Receptionists");
		ManageReceptionistsLabel.setForeground(new Color(255, 255, 255));
		ManageReceptionistsLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		ManageReceptionistsLabel.setBounds(10, 10, 286, 41);
		manageReceptionistPanel.add(ManageReceptionistsLabel);

		viewMonthlyIncomePanel = new JPanel();
		viewMonthlyIncomePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		viewMonthlyIncomePanel.setBackground(new Color(83, 92, 145));
		viewMonthlyIncomePanel.setVisible(false);
		viewMonthlyIncomePanel.setLayout(null);
		viewMonthlyIncomePanel.setBounds(10, 5, 666, 320);
		contentPanel.setLayer(viewMonthlyIncomePanel, 3);
		contentPanel.add(viewMonthlyIncomePanel);

		viewMonthlyIncomeLabel = new JLabel("View Monthly Income Report");
		viewMonthlyIncomeLabel.setForeground(new Color(255, 255, 255));
		viewMonthlyIncomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		viewMonthlyIncomeLabel.setBounds(10, 5, 220, 40);
		viewMonthlyIncomePanel.add(viewMonthlyIncomeLabel);

		monthSelector = new JComboBox<String>();
		monthSelector.setMaximumRowCount(12);
		monthSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Select Month *", "January", "February",
				"March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
		monthSelector.setFont(new Font("SansSerif", Font.PLAIN, 12));
		monthSelector.setBounds(20, 55, 200, 20);
		viewMonthlyIncomePanel.add(monthSelector);

		yearSelector = new JComboBox<String>();
		yearSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Select Year *" }));
		yearSelector.setMaximumRowCount(12);
		yearSelector.setFont(new Font("SansSerif", Font.PLAIN, 12));
		yearSelector.setBounds(230, 56, 200, 20);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		System.out.print(currentYear);
		for (int i = 1950; i <= currentYear; i++) {
			yearSelector.addItem(String.valueOf(i));
		}
		viewMonthlyIncomePanel.add(yearSelector);

		JButton generateReportBtn = new JButton("Generate Report");
		generateReportBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		generateReportBtn.setBounds(480, 120, 160, 80);
		viewMonthlyIncomePanel.add(generateReportBtn);

		manageTutorsPanel = new JPanel();
		manageTutorsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		manageTutorsPanel.setBackground(new Color(83, 92, 145));
		manageTutorsPanel.setVisible(false);
		manageTutorsPanel.setBounds(10, 5, 666, 320);
		contentPanel.add(manageTutorsPanel);
		manageTutorsPanel.setLayout(null);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// HEADER PANEL --------------------------------
		if (e.getSource() == updateProfileBtn) {
			updateProfilePanel.setVisible(true);
			manageTutorsPanel.setVisible(false);
			manageReceptionistPanel.setVisible(false);
			viewMonthlyIncomePanel.setVisible(false);

			contentPanel.setLayer(updateProfilePanel, 1);
			contentPanel.setLayer(manageReceptionistPanel, 0);
			contentPanel.setLayer(viewMonthlyIncomePanel, 0);
			contentPanel.setLayer(manageTutorsPanel, 0);

			updateProfileBtn.setEnabled(false);
			manageTutorsButton.setEnabled(true);
			manageReceptionistsBtn.setEnabled(true);
			viewIncomeBtn.setEnabled(true);

			System.out.println("Opened Update Profile Panel");
		}
		if (e.getSource() == manageTutorsButton) {
			manageTutorsPanel.setVisible(true);
			updateProfilePanel.setVisible(false);
			manageReceptionistPanel.setVisible(false);
			viewMonthlyIncomePanel.setVisible(false);

			contentPanel.setLayer(manageTutorsPanel, 1);
			contentPanel.setLayer(updateProfilePanel, 0);
			contentPanel.setLayer(manageReceptionistPanel, 0);
			contentPanel.setLayer(viewMonthlyIncomePanel, 0);

			manageTutorsButton.setEnabled(false);
			updateProfileBtn.setEnabled(true);
			manageReceptionistsBtn.setEnabled(true);
			viewIncomeBtn.setEnabled(true);

			System.out.println("Opened Manage Tutors Panel");
		}
		if (e.getSource() == manageReceptionistsBtn) {
			manageReceptionistPanel.setVisible(true);
			viewMonthlyIncomePanel.setVisible(false);
			updateProfilePanel.setVisible(false);
			manageTutorsPanel.setVisible(false);

			contentPanel.setLayer(manageReceptionistPanel, 1);
			contentPanel.setLayer(updateProfilePanel, 0);
			contentPanel.setLayer(viewMonthlyIncomePanel, 0);
			contentPanel.setLayer(manageTutorsPanel, 0);

			manageReceptionistsBtn.setEnabled(false);
			updateProfileBtn.setEnabled(true);
			manageTutorsButton.setEnabled(true);
			viewIncomeBtn.setEnabled(true);

			System.out.println("Opened Manage Receptionists Panel");
		}
		if (e.getSource() == viewIncomeBtn) {
			viewMonthlyIncomePanel.setVisible(true);
			manageReceptionistPanel.setVisible(false);
			updateProfilePanel.setVisible(false);
			manageTutorsPanel.setVisible(false);

			viewIncomeBtn.setEnabled(false);
			contentPanel.setLayer(viewMonthlyIncomePanel, 1);
			contentPanel.setLayer(updateProfilePanel, 0);
			contentPanel.setLayer(manageReceptionistPanel, 0);
			contentPanel.setLayer(manageTutorsPanel, 0);

			viewIncomeBtn.setEnabled(false);
			updateProfileBtn.setEnabled(true);
			manageTutorsButton.setEnabled(true);
			manageReceptionistsBtn.setEnabled(true);

			System.out.println("Opened View Income Panel");
		}

		// UPDATE PROFILE PANEL ----------------------------------
		if (e.getSource() == changeUsernameBtn) {
			usernameTxtfield.setEnabled(true);
			changeUsernameBtn.setEnabled(false);
			System.out.println("Button pressed: Change username");
		}
		if (e.getSource() == changePasswordBtn) {
			passwordField.setEnabled(true);
			changePasswordBtn.setEnabled(false);
			System.out.println("Button pressed: Change password");
		}
		if (e.getSource() == changeEmailBtn) {
			emailTxtfield.setEnabled(true);
			changeEmailBtn.setEnabled(false);
			System.out.println("Button pressed: Change e-mail");
		}
		if (e.getSource() == changeAddressBtn) {
			addressTxtfield.setEnabled(true);
			changeAddressBtn.setEnabled(false);
		}
		if (e.getSource() == saveChangesBtn) {
			String newUsername = usernameTxtfield.getText();
			String newPassword = String.valueOf(passwordField.getPassword());
			String newEmail = emailTxtfield.getText();
			String newAddress = addressTxtfield.getText();

			// Basic Validation (Checks for empty fields)
			if (newUsername.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty() || newAddress.isEmpty()) {
				JOptionPane.showMessageDialog(this, "All fields must be filled in.", "Input Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!adminManager.isValidEmail(newEmail)) { // If email invalid, display error message
				JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Input error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			updateUserDetails(admin, user, newUsername, newPassword, newEmail, newAddress);

			JOptionPane.showMessageDialog(this, "Saved profile settings");
			resetUpdateProfilePage();

			return;
		}
	}

	public void resetUpdateProfilePage() {
		// Enable all the buttons
		changeUsernameBtn.setEnabled(true);
		changePasswordBtn.setEnabled(true);
		changeEmailBtn.setEnabled(true);
		changeAddressBtn.setEnabled(true);

		// Disable all the text fields
		usernameTxtfield.setEnabled(false);
		passwordField.setEnabled(false);
		emailTxtfield.setEnabled(false);
		addressTxtfield.setEnabled(false);
	}

	public void updateUserDetails(Admin admin, User user, String newUsername, String newPassword, String newEmail,
			String newAddress) { // to be used by save changes button

		if (!userManager.renameUser(user.getID(), newUsername)) {
			JOptionPane.showMessageDialog(this, "Username failed to update.", "Username Already Exists",
					JOptionPane.ERROR_MESSAGE);
			usernameTxtfield.setText(user.getUsername());
		}
		user.setPassword(newPassword);
		admin.setEmail(newEmail);
		admin.setAddress(newAddress);
		userManager.saveUsers(userCSVFile);
		adminManager.saveAdmins(adminCSVFile);

		System.out.println("Updated User Details For AdminID = " + admin.getAdminID());
	}

	private void updateProfilePanelInformation(User user, Admin admin) {
		String username = user.getUsername();
		String password = user.getPassword();
		String email = admin.getEmail();
		String address = admin.getAddress();

		usernameTxtfield.setText(username);
		passwordField.setText(password);
		emailTxtfield.setText(email);
		addressTxtfield.setText(address);
	}
}
