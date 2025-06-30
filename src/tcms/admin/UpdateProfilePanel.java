package tcms.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.text.JTextComponent;

import tcms.custom_gui_components.CustomJButton;
import tcms.users.User;
import tcms.users.UserManager;

public class UpdateProfilePanel extends JPanel implements ActionListener {

	// === Fields ===
	private JTextField usernameTxtfield, emailTxtfield, contactTxtfield, addressTxtfield;
	private JPasswordField passwordField;
	private JButton changeUsernameBtn, changePasswordBtn, changeEmailBtn, changeAddressBtn, changeContactBtn;
	private JLabel usernameLabel, passwordLabel, emailLabel, addressLabel, contactLabel, profileSettingsLabel;
	private JToggleButton showPasswordToggleBtn;
	private CustomJButton saveChangesBtn;
	private static AdminManager adminManager = new AdminManager();
	private static UserManager userManager = new UserManager();
	
	private String adminCSVFile = "src//admins.csv";
	private String userCSVFile = "src//users.csv";

	private User user;
	private Admin admin;

	// === Constructor ===
	public UpdateProfilePanel(User user, Admin admin) {
		this.user = user;
		this.admin = admin;

		setFont(new Font("SansSerif", Font.PLAIN, 10));
		setBorder(null);
		setBackground(new Color(43, 45, 49));
		setLayout(null); // Use CardLayout if needed at parent level

		initComponents(); // Modular init
		updateProfilePanelInformation(user, admin); // Load user info if needed
	}

	// === Initialization ===
	private void initComponents() {
		// Label
		profileSettingsLabel = new JLabel("Update Profile");
		profileSettingsLabel.setBounds(440, 10, 300, 40);
		profileSettingsLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
		profileSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSettingsLabel.setForeground(new Color(220, 221, 222));
		add(profileSettingsLabel);

		// Username Label
		usernameLabel = createLabel("Username", 100, 60);
		add(usernameLabel);

		// Password Label
		passwordLabel = createLabel("Password", 640, 60);
		add(passwordLabel);

		// Email Label
		emailLabel = createLabel("E-mail", 640, 170);
		add(emailLabel);

		// Address Label
		addressLabel = createLabel("Address", 100, 280);
		add(addressLabel);

		// Contact Label
		contactLabel = createLabel("Contact", 100, 170);
		add(contactLabel);

		// Username Text Field
		usernameTxtfield = createTextField(100, 85, 450);
		usernameTxtfield.setEnabled(false);
		add(usernameTxtfield);

		// Password Field
		passwordField = new JPasswordField();
		styleInputField(passwordField, 640, 85);
		passwordField.setEchoChar('*');
		passwordField.setEnabled(false);
		add(passwordField);

		// Email Text Field
		emailTxtfield = createTextField(640, 195, 450);
		emailTxtfield.setEnabled(false);
		add(emailTxtfield);

		// Address Text Field
		addressTxtfield = createTextField(100, 298, 990);
		addressTxtfield.setText("<dynamic>");
		addressTxtfield.setEnabled(false);
		add(addressTxtfield);

		// Contact Text Field
		contactTxtfield = createTextField(100, 195, 450);
		contactTxtfield.setText("<dynamic>");
		contactTxtfield.setEnabled(false);
		add(contactTxtfield);

		// Buttons
		changeUsernameBtn = createButton("Change Username", 360, 134);
		add(changeUsernameBtn);

		changePasswordBtn = createButton("Change Password", 900, 135);
		add(changePasswordBtn);

		changeEmailBtn = createButton("Change E-mail", 900, 245);
		add(changeEmailBtn);

		changeAddressBtn = createButton("Change Address", 900, 355);
		add(changeAddressBtn);

		changeContactBtn = createButton("Change Contact", 360, 245);
		add(changeContactBtn);

		// Toggle Button
		showPasswordToggleBtn = new JToggleButton("Show Password");
		showPasswordToggleBtn.setBounds(779, 135, 120, 25);
		showPasswordToggleBtn.setForeground(new Color(220, 221, 222));
		showPasswordToggleBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		showPasswordToggleBtn.setBackground(new Color(30, 31, 34));
		showPasswordToggleBtn.setBorder(new MatteBorder(0, 0, 2, 0, new Color(30, 31, 34)));
		showPasswordToggleBtn.setFocusable(false);
		showPasswordToggleBtn.addItemListener(e -> {
			passwordField.setEchoChar(e.getStateChange() == ItemEvent.SELECTED ? (char) 0 : '*');
		});
		add(showPasswordToggleBtn);

		// Save Button (custom)
		saveChangesBtn = new CustomJButton();
		saveChangesBtn.setBounds(510, 420, 200, 50);
		saveChangesBtn.setText("Update Profile");
		saveChangesBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		saveChangesBtn.setColor(new Color(88, 101, 242));
		saveChangesBtn.setColorOver(new Color(92, 141, 241));
		saveChangesBtn.setColorClick(new Color(96, 76, 195));
		saveChangesBtn.setBorderColor(new Color(88, 101, 242));
		saveChangesBtn.setRadius(25);
		saveChangesBtn.setBackground(new Color(88, 101, 242));
		saveChangesBtn.setForeground(new Color(220, 221, 222));
		saveChangesBtn.setFocusable(false);
		add(saveChangesBtn);
	}

	// === Helper: Label Factory ===
	private JLabel createLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, 100, 25);
		label.setForeground(new Color(220, 221, 222));
		label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		return label;
	}

	// === Helper: Text Field Factory ===
	private JTextField createTextField(int x, int y, int width) {
		JTextField field = new JTextField();
		styleInputField(field, x, y);
		field.setSize(width, 50);
		field.setColumns(10);
		return field;
	}

	// === Helper: Style Input Fields ===
	private void styleInputField(JTextComponent field, int x, int y) {
		field.setBounds(x, y, 450, 50);
		field.setForeground(new Color(220, 221, 222));
		field.setFont(new Font("SansSerif", Font.PLAIN, 16));
		field.setBackground(new Color(56, 58, 65));
		field.setBorder(new MatteBorder(0, 0, 2, 0, new Color(30, 31, 34)));
		field.setDisabledTextColor(new Color(79, 84, 92));
	}

	// === Helper: Button Factory ===
	private JButton createButton(String text, int x, int y) {
		JButton btn = new JButton(text);
		btn.setBounds(x, y, 190, 25);
		btn.setForeground(new Color(220, 221, 222));
		btn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btn.setBackground(new Color(30, 31, 34));
		btn.setBorder(new MatteBorder(0, 0, 2, 0, new Color(30, 31, 34)));
		btn.setFocusable(false);
		btn.addActionListener(e -> System.out.println(text + " clicked")); // or this
		return btn;
	}

	// === Placeholder for Data Update ===
	private void updateProfilePanelInformation(Object user, Object admin) {
		// Populate fields here from your user/admin object
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		if (e.getSource() == changeContactBtn) {
			contactTxtfield.setEnabled(true);
			changeContactBtn.setEnabled(false);
			System.out.println("Button pressed: Change Contact");
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
			String newContact = contactTxtfield.getText();
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

			updateUserDetails(admin, user, newUsername, newPassword, newContact, newEmail, newAddress);

			JOptionPane.showMessageDialog(this, "Saved profile settings");
			resetUpdateProfilePage();

			return;
		}

	}

	private void updateProfilePanelInformation(User user, Admin admin) {
		String username = user.getUsername();
		String password = user.getPassword();
		String contact = admin.getContact();
		String email = admin.getEmail();
		String address = admin.getAddress();

		usernameTxtfield.setText(username);
		passwordField.setText(password);
		contactTxtfield.setText(contact);
		emailTxtfield.setText(email);
		addressTxtfield.setText(address);
	}

	public void updateUserDetails(Admin admin, User user, String newUsername, String newPassword, String newContact,
			String newEmail, String newAddress) { // to be used by save changes button

		if (!userManager.renameUser(user.getID(), newUsername)) {
			JOptionPane.showMessageDialog(this, "Username failed to update.", "Username Already Exists",
					JOptionPane.ERROR_MESSAGE);
			usernameTxtfield.setText(user.getUsername());
		}
		user.setPassword(newPassword);
		admin.setEmail(newEmail);
		admin.setAddress(newAddress);
		admin.setContact(newContact);
		userManager.saveUsers(userCSVFile);
		adminManager.saveAdmins(adminCSVFile);

		System.out.println("Updated User Details For AdminID = " + admin.getAdminID());
	}

	public void resetUpdateProfilePage() {
		// Enable all the buttons
		changeUsernameBtn.setEnabled(true);
		changePasswordBtn.setEnabled(true);
		changeContactBtn.setEnabled(true);
		changeEmailBtn.setEnabled(true);
		changeAddressBtn.setEnabled(true);

		// Disable all the text fields
		usernameTxtfield.setEnabled(false);
		passwordField.setEnabled(false);
		contactTxtfield.setEnabled(false);
		emailTxtfield.setEnabled(false);
		addressTxtfield.setEnabled(false);

		updateProfilePanelInformation(user, admin);
	}

}
