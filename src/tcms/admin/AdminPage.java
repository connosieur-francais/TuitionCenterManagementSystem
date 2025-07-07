package tcms.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import tcms.custom_gui_components.CustomComponents;
import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomJToggleButton;
import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.custom_gui_components.HeaderButton;
import tcms.custom_gui_components.ModelColor;
import tcms.receptionists.ReceptionistManager;
import tcms.tutors.TutorManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;
import tcms.utils.Validators;

public class AdminPage extends JFrame implements ActionListener {

	private String atcBannerImg = "src/tcms/resources/atcBanner4.png";
	
	private static AdminManager adminManager;
	private static UserManager userManager;
	private static ReceptionistManager receptionistManager;
	private static TutorManager tutorManager;

	private static Admin admin;
	private static User user;
	private String adminCSVFile = Constants.ADMINS_CSV;
	private String userCSVFile = Constants.USERS_CSV;

	private static final long serialVersionUID = 1L;
	private CustomRoundedPanel contentPane;
	private JPanel headerPanel;
	private CustomJButton updateProfileBtn, manageTutorsButton;
	private CustomJButton manageReceptionistsBtn, viewIncomeBtn;
	private JLabel atcBannerLabel;
	private JLayeredPane contentPanel;
	private CardLayout cl;

	// UPDATE PROFILE PAGE ------------------------
	private JPanel updateProfilePanel;
	private JLabel profileSettingsLabel, usernameLabel;
	private JLabel passwordLabel, emailLabel, addressLabel, contactLabel;
	private JTextField usernameTxtfield, emailTxtfield;
	private JPasswordField passwordField;
	private CustomJButton saveChangesBtn, updateProfileReturnBtn;
	private CustomJButton changeUsernameBtn, changePasswordBtn, changeEmailBtn, changeAddressBtn, changeContactBtn;
	private CustomJToggleButton showPasswordToggleBtn;
	private JTextField addressTxtfield;
	private JTextField contactTxtfield;

	// MANAGE TUTORS PAGE ------------------------------------
	private ManageTutorsPanel manageTutorsPanel;
	private CustomJButton tutorReturnBtn;

	// MANAGE RECEPTIONISTS PAGE ------------------------------
	private JPanel manageReceptionistPanel;
	private CustomJButton receptionistReturnBtn;

	// VIEW MONTHLY INCOME PAGE -------------------------------
	private JPanel viewMonthlyIncomePanel;

	// LANDING PAGE ---------------------------------
	private LandingPagePanel landingPagePanel;

	/**
	 * Create the frame.
	 */
	public AdminPage(User u, AdminManager am, UserManager um, ReceptionistManager rm, TutorManager tm) {
		// Gather all the needed manager classes that were initiated from loginPage;
		adminManager = am;
		userManager = um;
		tutorManager = tm;
		receptionistManager = rm;
		
		// Gets necessary details about the admin
		user = u;
		int user_id = user.getID();
		admin = adminManager.findAdminByUserID(user_id);
		
		
		this.setResizable(false);
		this.setTitle("Admin Panel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1200, 750);
		contentPane = new CustomRoundedPanel();
		contentPane.addColor(new ModelColor(Color.decode("#DFD9FF"), 0f), new ModelColor(Color.decode("#9F75FF"), 0.5f),
				new ModelColor(Color.decode("#4D9AFF"), 1f));
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(contentPane);

		headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1186, 86);
		headerPanel.setBackground(new Color(30, 33, 36));

		atcBannerLabel = new JLabel("");
		atcBannerLabel.setIcon(new ImageIcon(atcBannerImg));

		updateProfileBtn = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\profile.png"),
				"<html>\r\n<body>\r\n<p>Update</p>\r\n<p>Profile</p>\r\n</body>\r\n</html>");
		updateProfileBtn.addActionListener(this);

		manageTutorsButton = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\tutor.png"),
				"<html>\r\n<body>\r\n<p>Manage</p>\r\n<p>Tutors</p>\r\n</body>\r\n</html>");
		manageTutorsButton.addActionListener(this);

		manageReceptionistsBtn = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\receptionist.png"),
				"<html>\r\n<body>\r\n<p>Manage</p>\r\n<p>Receptionists</p>\r\n</body>\r\n</html>");
		manageReceptionistsBtn.addActionListener(this);

		viewIncomeBtn = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\incomereport.png"),
				"<html>\r\n<body>\r\n<p>View</p>\r\n<p>Income</p>\r\n<p>Report</p>\r\n</body>\r\n</html>");
		viewIncomeBtn.addActionListener(this);

		contentPanel = new JLayeredPane();
		contentPanel.setBounds(0, 85, 1186, 628);
		contentPanel.setBorder(null);
		contentPanel.setBackground(new Color(44, 47, 51));
		contentPanel.setLayout(new CardLayout(0, 0));

		// UPDATE PROFILE PANEL
		// -----------------------------------------------------------------------

		updateProfilePanel = new JPanel();
		updateProfilePanel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		contentPanel.setLayer(updateProfilePanel, 1);
		updateProfilePanel.setBorder(null);
		updateProfilePanel.setBackground(new Color(44, 47, 51));
		contentPanel.add(updateProfilePanel, "Update Profile");

		profileSettingsLabel = new JLabel("Update Profile");
		profileSettingsLabel.setBounds(440, 25, 300, 40);
		profileSettingsLabel.setFont(new Font("Arial Black", Font.PLAIN, 32));
		profileSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSettingsLabel.setForeground(new Color(220, 221, 222));

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 100, 100, 25);
		usernameLabel.setForeground(new Color(220, 221, 222));
		usernameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(640, 100, 100, 25);
		passwordLabel.setForeground(new Color(220, 221, 222));
		passwordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		usernameTxtfield = CustomComponents.createJTextField("<dynamic>");
		usernameTxtfield.setBounds(100, 125, 450, 50);

		passwordField = new JPasswordField();
		passwordField.setBounds(640, 125, 450, 50);
		passwordField.setForeground(new Color(220, 221, 222));
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(30, 31, 34)));
		passwordField.setBackground(new Color(56, 58, 65));
		passwordField.setEnabled(false);
		passwordField.setDisabledTextColor(new Color(79, 84, 92));
		passwordField.setEchoChar('*');

		changeUsernameBtn = new CustomJButton();
		changeUsernameBtn.setBounds(360, 175, 190, 25);
		changeUsernameBtn.setText("Change Username");
		changeUsernameBtn.setColorOver(new Color(30, 30, 34));
		changeUsernameBtn.setColorClick(new Color(19, 19, 21));
		changeUsernameBtn.setColor(new Color(30, 31, 34));
		changeUsernameBtn.setBorderColor(new Color(30, 31, 34));
		changeUsernameBtn.setForeground(new Color(220, 221, 222));
		changeUsernameBtn.setBorder(new LineBorder(new Color(30, 31, 34), 2));
		changeUsernameBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeUsernameBtn.setBackground(new Color(30, 31, 34));
		changeUsernameBtn.addActionListener(this);
		changeUsernameBtn.setFocusable(false);

		changePasswordBtn = new CustomJButton();
		changePasswordBtn.setBounds(900, 175, 190, 25);
		changePasswordBtn.setText("Change Password");
		changePasswordBtn.setColorOver(new Color(30, 30, 34));
		changePasswordBtn.setColorClick(new Color(19, 19, 21));
		changePasswordBtn.setColor(new Color(30, 31, 34));
		changePasswordBtn.setBorderColor(new Color(30, 31, 34));
		changePasswordBtn.setForeground(new Color(220, 221, 222));
		changePasswordBtn.setBorder(new LineBorder(new Color(30, 31, 34), 2));
		changePasswordBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changePasswordBtn.setBackground(new Color(30, 31, 34));
		changePasswordBtn.addActionListener(this);
		changePasswordBtn.setFocusable(false);

		emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(640, 210, 70, 25);
		emailLabel.setForeground(new Color(220, 221, 222));
		emailLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		changeEmailBtn = new CustomJButton();
		changeEmailBtn.setBounds(900, 285, 190, 25);
		changeEmailBtn.setText("Change E-mail");
		changeEmailBtn.setColorOver(new Color(30, 30, 34));
		changeEmailBtn.setColorClick(new Color(19, 19, 21));
		changeEmailBtn.setColor(new Color(30, 31, 34));
		changeEmailBtn.setBorderColor(new Color(30, 31, 34));
		changeEmailBtn.setForeground(new Color(220, 221, 222));
		changeEmailBtn.setBorder(new LineBorder(new Color(30, 31, 34), 2));
		changeEmailBtn.setBackground(new Color(30, 31, 34));
		changeEmailBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeEmailBtn.addActionListener(this);
		changeEmailBtn.setFocusable(false);

		showPasswordToggleBtn = new CustomJToggleButton();
		showPasswordToggleBtn.setBounds(640, 175, 120, 25);
		showPasswordToggleBtn.setBackgroundColor(new Color(30, 31, 34));
		showPasswordToggleBtn.setBorderColor(new Color(30, 31, 34));
		showPasswordToggleBtn.setHoverColor(new Color(37, 38, 41));
		showPasswordToggleBtn.setSelectedColor(new Color(52, 54, 58));
		showPasswordToggleBtn.setCornerRadius(0);
		showPasswordToggleBtn.setText("Show Password");
		showPasswordToggleBtn.setForeground(new Color(220, 221, 222));
		showPasswordToggleBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		showPasswordToggleBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		showPasswordToggleBtn.setBackground(new Color(30, 31, 34));
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
		showPasswordToggleBtn.setFocusable(false);

		emailTxtfield = CustomComponents.createJTextField("dynamic");
		emailTxtfield.setBounds(640, 235, 450, 50);

		addressLabel = new JLabel("Address");
		addressLabel.setBounds(100, 320, 70, 25);
		addressLabel.setForeground(new Color(220, 221, 222));
		addressLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		addressTxtfield = CustomComponents.createJTextField("<dynamic>");
		addressTxtfield.setBounds(100, 345, 990, 50);

		changeAddressBtn = new CustomJButton();
		changeAddressBtn.setBounds(900, 395, 190, 25);
		changeAddressBtn.setText("Change Address");
		changeAddressBtn.setColorOver(new Color(30, 30, 34));
		changeAddressBtn.setColorClick(new Color(19, 19, 21));
		changeAddressBtn.setColor(new Color(30, 31, 34));
		changeAddressBtn.setBorderColor(new Color(30, 31, 34));
		changeAddressBtn.setForeground(new Color(220, 221, 222));
		changeAddressBtn.setBorder(new LineBorder(new Color(30, 31, 34), 2));
		changeAddressBtn.setBackground(new Color(30, 31, 34));
		changeAddressBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeAddressBtn.addActionListener(this);
		changeAddressBtn.setFocusable(false);

		saveChangesBtn = new CustomJButton();
		saveChangesBtn.setBounds(510, 460, 200, 50);
		saveChangesBtn.addActionListener(this);
		saveChangesBtn.setColorOver(new Color(79, 82, 196));
		saveChangesBtn.setColorClick(new Color(96, 76, 195));
		saveChangesBtn.setColor(new Color(88, 101, 242));
		saveChangesBtn.setBorderColor(new Color(88, 101, 242));
		saveChangesBtn.setRadius(25);
		saveChangesBtn.setText("Update Profile");
		saveChangesBtn.setForeground(new Color(220, 221, 222));
		saveChangesBtn.setBorder(null);
		saveChangesBtn.setBackground(new Color(88, 101, 242));
		saveChangesBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		saveChangesBtn.setFocusable(false);

		contactLabel = new JLabel("Contact");
		contactLabel.setBounds(100, 210, 100, 25);
		contactLabel.setForeground(new Color(220, 221, 222));
		contactLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		contactTxtfield = CustomComponents.createJTextField("<dynamic>");
		contactTxtfield.setBounds(100, 235, 450, 50);

		changeContactBtn = new CustomJButton();
		changeContactBtn.setBounds(360, 285, 190, 25);
		changeContactBtn.setText("Change Contact");
		changeContactBtn.setColorOver(new Color(30, 30, 34));
		changeContactBtn.setColorClick(new Color(19, 19, 21));
		changeContactBtn.setColor(new Color(30, 31, 34));
		changeContactBtn.setBorderColor(new Color(30, 31, 34));
		changeContactBtn.setForeground(new Color(220, 221, 222));
		changeContactBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeContactBtn.setFocusable(false);
		changeContactBtn.addActionListener(this);
		changeContactBtn.setBorder(new LineBorder(new Color(30, 31, 34), 2));
		changeContactBtn.setBackground(new Color(30, 31, 34));
		updateProfilePanel.setLayout(null);

		updateProfilePanel.add(usernameLabel);
		updateProfilePanel.add(passwordLabel);
		updateProfilePanel.add(contactLabel);
		updateProfilePanel.add(addressLabel);
		updateProfilePanel.add(emailLabel);
		updateProfilePanel.add(addressTxtfield);
		updateProfilePanel.add(emailTxtfield);
		updateProfilePanel.add(contactTxtfield);
		updateProfilePanel.add(usernameTxtfield);
		updateProfilePanel.add(showPasswordToggleBtn);
		updateProfilePanel.add(passwordField);
		updateProfilePanel.add(changeContactBtn);
		updateProfilePanel.add(changePasswordBtn);
		updateProfilePanel.add(changeEmailBtn);
		updateProfilePanel.add(changeAddressBtn);
		updateProfilePanel.add(changeUsernameBtn);
		updateProfilePanel.add(saveChangesBtn);
		updateProfilePanel.add(profileSettingsLabel);

		updateProfileReturnBtn = CustomComponents.createReturnBtn();
		updateProfileReturnBtn.addActionListener(this);
		updateProfileReturnBtn.setBounds(10, 10, 150, 40);
		updateProfilePanel.add(updateProfileReturnBtn);

		updateProfilePanelInformation(user, admin); // Update the information in txtfields with logged in user's
													// information

		// MANAGE RECEPTIONISTS PANEL

		manageReceptionistPanel = new ManageReceptionistsPanel(um, rm);
		contentPanel.add(manageReceptionistPanel, "Manage Receptionists");
		
		receptionistReturnBtn = CustomComponents.createReturnBtn();
		receptionistReturnBtn.setBounds(10, 10, 150, 40);
		receptionistReturnBtn.addActionListener(this);
		manageReceptionistPanel.add(receptionistReturnBtn);
		
		// VIEW MONTHLY INCOME PANEL ------------------------------------------

		viewMonthlyIncomePanel = new GenerateMonthlyIncomeReportPanel();
		contentPanel.add(viewMonthlyIncomePanel, "View Monthly Income");

		// MANAGE TUTORS ------------------------------------

		manageTutorsPanel = new ManageTutorsPanel(um, tm);
		contentPanel.setLayer(manageTutorsPanel, 4);
		manageTutorsPanel.setBorder(null);
		manageTutorsPanel.setLayout(null);
		contentPanel.add(manageTutorsPanel, "Manage Tutors");
		
		tutorReturnBtn = CustomComponents.createReturnBtn();
		tutorReturnBtn.setBounds(10, 10, 150, 40);
		tutorReturnBtn.addActionListener(this);
		manageTutorsPanel.add(tutorReturnBtn);

		// Group layout for top header panel
		contentPane.setLayout(null);
		contentPane.add(headerPanel);
		GroupLayout gl_headerPanel = new GroupLayout(headerPanel);
		gl_headerPanel.setHorizontalGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_headerPanel
				.createSequentialGroup().addContainerGap()
				.addComponent(atcBannerLabel, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(updateProfileBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addComponent(manageTutorsButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
				.addGap(30)
				.addComponent(manageReceptionistsBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
				.addGap(30).addComponent(viewIncomeBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)));
		gl_headerPanel.setVerticalGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_headerPanel
				.createSequentialGroup().addGap(10)
				.addGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_headerPanel.createSequentialGroup()
								.addComponent(atcBannerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(gl_headerPanel.createSequentialGroup().addGroup(gl_headerPanel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(manageTutorsButton, GroupLayout.PREFERRED_SIZE, 60,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(manageReceptionistsBtn, GroupLayout.PREFERRED_SIZE, 60,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(viewIncomeBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addComponent(updateProfileBtn, GroupLayout.PREFERRED_SIZE, 60,
										GroupLayout.PREFERRED_SIZE))
								.addGap(16)))));
		headerPanel.setLayout(gl_headerPanel);
		contentPane.add(contentPanel);

		// LANDING PAGE PANEL --------------------------------

		landingPagePanel = new LandingPagePanel(um, admin, user);
		contentPanel.setLayer(landingPagePanel, 2);
		landingPagePanel.setBackground(new Color(44, 47, 51));
		contentPanel.add(landingPagePanel, "Landing Page");
		cl = (CardLayout) contentPanel.getLayout();
		cl.show(contentPanel, "Landing Page"); // show the landing page as default
		landingPagePanel.setLayout(null);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// RETURN TO DASHBOARD SPECIAL
		if (e.getSource() == updateProfileReturnBtn) {
			landingPagePanel.reloadUserTableData(userManager);
			returnToLandingPage();
		}
		
		if (e.getSource() == tutorReturnBtn) {
			landingPagePanel.reloadUserTableData(userManager);
			returnToLandingPage();
		}
		
		if (e.getSource() == receptionistReturnBtn) {
			landingPagePanel.reloadUserTableData(userManager);
			returnToLandingPage();
		}

		// HEADER PANEL --------------------------------
		if (e.getSource() == updateProfileBtn) {
			cl.show(contentPanel, "Update Profile");
			updateProfileBtn.setEnabled(false);
			manageTutorsButton.setEnabled(true);
			manageReceptionistsBtn.setEnabled(true);
			viewIncomeBtn.setEnabled(true);

			System.out.println("Opened Update Profile Panel");
		}
		if (e.getSource() == manageTutorsButton) {
			cl.show(contentPanel, "Manage Tutors");

			manageTutorsButton.setEnabled(false);
			updateProfileBtn.setEnabled(true);
			manageReceptionistsBtn.setEnabled(true);
			viewIncomeBtn.setEnabled(true);

			resetUpdateProfilePage();

			System.out.println("Opened Manage Tutors Panel");
		}
		if (e.getSource() == manageReceptionistsBtn) {
			cl.show(contentPanel, "Manage Receptionists");

			manageReceptionistsBtn.setEnabled(false);
			updateProfileBtn.setEnabled(true);
			manageTutorsButton.setEnabled(true);
			viewIncomeBtn.setEnabled(true);

			resetUpdateProfilePage();

			System.out.println("Opened Manage Receptionists Panel");
		}
		if (e.getSource() == viewIncomeBtn) {
			cl.show(contentPanel, "View Monthly Income");

			viewIncomeBtn.setEnabled(false);
			updateProfileBtn.setEnabled(true);
			manageTutorsButton.setEnabled(true);
			manageReceptionistsBtn.setEnabled(true);

			resetUpdateProfilePage();

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

			// === Validations using Validators class ===
			if (!Validators.isValidName(newUsername)) return;
			if (!Validators.isValidPassword(newPassword)) return;
			if (!Validators.isValidEmail(newEmail)) return;
			if (!Validators.isValidContact(newContact)) return;
			if (!Validators.isValidAddress(newAddress)) return;

			// === If all validations pass ===
			updateUserDetails(admin, user, newUsername, newPassword, newContact, newEmail, newAddress);
			JOptionPane.showMessageDialog(this, "Saved profile settings");
			resetUpdateProfilePage();
		}

	}

	private void returnToLandingPage() {
		cl.show(contentPanel, "Landing Page");

	    // Reset all header button visuals
	    updateProfileBtn.setEnabled(false);
	    updateProfileBtn.setEnabled(true);

	    manageTutorsButton.setEnabled(false);
	    manageTutorsButton.setEnabled(true);

	    manageReceptionistsBtn.setEnabled(false);
	    manageReceptionistsBtn.setEnabled(true);

	    viewIncomeBtn.setEnabled(false);
	    viewIncomeBtn.setEnabled(true);
	    
	    // Drag focus to a neutral component
	    contentPanel.requestFocusInWindow();
	    
	    System.out.println("Button Pressed: Returned to admin landing page");
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
}
