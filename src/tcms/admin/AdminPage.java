package tcms.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import tcms.users.User;
import tcms.users.UserManager;

public class AdminPage extends JFrame implements ActionListener {

	private String atcBannerImg = "src/tcms/resources/atcBanner4.png";

	private AdminManager adminManager = new AdminManager();
	private UserManager userManager = new UserManager();

	private static Admin admin;
	private static User user;
	private String adminCSVFile = "src//admins.csv";
	private String userCSVFile = "src//users.csv";

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private CustomRoundedPanel contentPane;
	private JPanel headerPanel;
	private CustomJButton updateProfileBtn, manageTutorsButton;
	private CustomJButton manageReceptionistsBtn, viewIncomeBtn;
	private JLabel atcBannerLabel;
	private JLayeredPane contentPanel;

	// UPDATE PROFILE PAGE ------------------------
	private JPanel updateProfilePanel;
	private JLabel profileSettingsLabel, usernameLabel;
	private JLabel passwordLabel, emailLabel, addressLabel, contactLabel;
	private JTextField usernameTxtfield, emailTxtfield;
	private JPasswordField passwordField;
	private CustomJButton saveChangesBtn;
	private CustomJButton changeUsernameBtn, changePasswordBtn, changeEmailBtn, changeAddressBtn, changeContactBtn;
	private CustomJToggleButton showPasswordToggleBtn;
	private JTextField addressTxtfield;

	// MANAGE TUTORS PAGE ------------------------------------
	private JPanel manageTutorsPanel;

	// MANAGE RECEPTIONISTS PAGE ------------------------------
	private JPanel manageReceptionistPanel;
	private JLabel ManageReceptionistsLabel;

	// VIEW MONTHLY INCOME PAGE -------------------------------
	private CustomRoundedPanel viewMonthlyIncomePanel;
	private JLabel viewMonthlyIncomeLabel;
	private JComboBox<String> yearSelector;
	private JComboBox<String> monthSelector;
	private JButton generateReportBtn;
	private JTextField contactTxtfield;

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
		frame.setBounds(100, 100, 1200, 750);
		contentPane = new CustomRoundedPanel();
		contentPane.addColor(new ModelColor(Color.decode("#DFD9FF"), 0f), new ModelColor(Color.decode("#9F75FF"), 0.5f),
				new ModelColor(Color.decode("#4D9AFF"), 1f));
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);

		headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1186, 86);
		headerPanel.setBackground(new Color(30, 33, 36));

		atcBannerLabel = new JLabel("");
		atcBannerLabel.setIcon(new ImageIcon(atcBannerImg));

		updateProfileBtn = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\profile.png"), "<html>\r\n<body>\r\n<p>Update</p>\r\n<p>Profile</p>\r\n</body>\r\n</html>");
		updateProfileBtn.addActionListener(this);

		manageTutorsButton = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\profile.png"), "<html>\r\n<body>\r\n<p>Manage</p>\r\n<p>Tutors</p>\r\n</body>\r\n</html>");
		manageTutorsButton.setRadius(25);
		manageTutorsButton.addActionListener(this);
		manageTutorsButton.setIcon(new ImageIcon("src\\tcms\\resources\\tutor.png"));
		manageTutorsButton.setBackground(new Color(96, 76, 195));
		manageTutorsButton.setForeground(new Color(220, 221, 222));
		manageTutorsButton.setBorder(null);
		manageTutorsButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		manageTutorsButton.setColorClick(new Color(60, 69, 165));
		manageTutorsButton.setColor(new Color(88, 101, 242));
		manageTutorsButton.setColorOver(new Color(79, 82, 196));
		manageTutorsButton.setBorderColor(new Color(43, 45, 49));
		manageTutorsButton.setFocusable(false);

		manageReceptionistsBtn = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\profile.png"), "<html>\r\n<body>\r\n<p>Manage</p>\r\n<p>Receptionists</p>\r\n</body>\r\n</html>");
		manageReceptionistsBtn.setRadius(25);
		manageReceptionistsBtn.addActionListener(this);
		manageReceptionistsBtn.setIcon(new ImageIcon("src\\tcms\\resources\\receptionist.png"));
		manageReceptionistsBtn.setBackground(new Color(96, 76, 195));
		manageReceptionistsBtn.setForeground(new Color(220, 221, 222));
		manageReceptionistsBtn.setBorder(null);
		manageReceptionistsBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		manageReceptionistsBtn.setColorClick(new Color(60, 69, 165));
		manageReceptionistsBtn.setColor(new Color(88, 101, 242));
		manageReceptionistsBtn.setColorOver(new Color(79, 82, 196));
		manageReceptionistsBtn.setBorderColor(new Color(43, 45, 49));
		manageReceptionistsBtn.setFocusable(false);

		viewIncomeBtn = HeaderButton.createHeaderButton(new ImageIcon("src\\tcms\\resources\\profile.png"), "<html>\r\n<body>\r\n<p>View</p>\r\n<p>Income</p>\r\n<p>Report</p>\r\n</body>\r\n</html>");
		viewIncomeBtn.setRadius(25);
		viewIncomeBtn.addActionListener(this);
		viewIncomeBtn.setIcon(new ImageIcon("src\\tcms\\resources\\incomereport.png"));
		viewIncomeBtn.setBackground(new Color(96, 76, 195));
		viewIncomeBtn.setForeground(new Color(220, 221, 222));
		viewIncomeBtn.setBorder(null);
		viewIncomeBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		viewIncomeBtn.setColorClick(new Color(60, 69, 165));
		viewIncomeBtn.setColor(new Color(88, 101, 242));
		viewIncomeBtn.setColorOver(new Color(79, 82, 196));
		viewIncomeBtn.setBorderColor(new Color(43, 45, 49));
		viewIncomeBtn.setFocusable(false);

		contentPanel = new JLayeredPane();
		contentPanel.setBounds(0, 85, 1186, 628);
		contentPanel.setBorder(null);
		contentPanel.setBackground(new Color(44, 47, 51));

		// UPDATE PROFILE PANEL
		// -----------------------------------------------------------------------
		updateProfilePanel = new JPanel();
		updateProfilePanel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		contentPanel.setLayer(updateProfilePanel, 1);
		updateProfilePanel.setBorder(null);
		updateProfilePanel.setBackground(new Color(44, 47, 51));
		contentPanel.setLayout(new CardLayout(0, 0));
		contentPanel.add(updateProfilePanel, "name_164342192424500");

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
		saveChangesBtn.setColorOver(new Color(92, 141, 241));
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

		updateProfilePanelInformation(user, admin); // Update the information in txtfields with logged in user's
													// information

		// MANAGE RECEPTIONISTS PANEL

		manageReceptionistPanel = new JPanel();
		contentPanel.setLayer(manageReceptionistPanel, 2);
		manageReceptionistPanel.setBackground(new Color(44, 47, 51));
		manageReceptionistPanel.setVisible(false);
		manageReceptionistPanel.setBorder(null);
		contentPanel.add(manageReceptionistPanel, "name_164342236052900");
		manageReceptionistPanel.setLayout(null);

		ManageReceptionistsLabel = new JLabel("Manage Receptionists");
		ManageReceptionistsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ManageReceptionistsLabel.setForeground(new Color(255, 255, 255));
		ManageReceptionistsLabel.setFont(new Font("Arial Black", Font.BOLD, 32));
		ManageReceptionistsLabel.setBounds(375, 10, 450, 60);
		manageReceptionistPanel.add(ManageReceptionistsLabel);

		// VIEW MONTHLY INCOME PANEL ------------------------------------------

		viewMonthlyIncomePanel = new CustomRoundedPanel();
		viewMonthlyIncomePanel.addColor(new ModelColor(Color.decode("#D9D9FF"), 0f),
				new ModelColor(Color.decode("#C2DFFF"), 1f));
		viewMonthlyIncomePanel.setBorder(null);
		viewMonthlyIncomePanel.setBackground(new Color(83, 92, 145));
		viewMonthlyIncomePanel.setLayout(null);
		contentPanel.setLayer(viewMonthlyIncomePanel, 4);
		contentPanel.add(viewMonthlyIncomePanel, "name_164342258048700");

		viewMonthlyIncomeLabel = new JLabel("View Monthly Income Report");
		viewMonthlyIncomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		viewMonthlyIncomeLabel.setForeground(new Color(0, 0, 0));
		viewMonthlyIncomeLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
		viewMonthlyIncomeLabel.setBounds(350, 10, 500, 40);
		viewMonthlyIncomePanel.add(viewMonthlyIncomeLabel);

		monthSelector = new JComboBox<String>();
		monthSelector.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		monthSelector.setMaximumRowCount(12);
		monthSelector.setModel(
				new DefaultComboBoxModel<String>(new String[] { "- Select Month -", "January", "February", "March",
						"April", "May", "June", "July", "August", "September", "October", "November", "December" }));
		monthSelector.setFont(new Font("SansSerif", Font.PLAIN, 12));
		monthSelector.setBounds(20, 55, 200, 20);
		viewMonthlyIncomePanel.add(monthSelector);

		yearSelector = new JComboBox<String>();
		yearSelector.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		yearSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "- Select Year -" }));
		yearSelector.setMaximumRowCount(12);
		yearSelector.setFont(new Font("SansSerif", Font.PLAIN, 12));
		yearSelector.setBounds(230, 56, 200, 20);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println("Year Selector: Current Year = 2025");
		for (int i = 1950; i <= currentYear; i++) {
			yearSelector.addItem(String.valueOf(i));
		}
		viewMonthlyIncomePanel.add(yearSelector);

		generateReportBtn = new JButton("Generate Report");
		generateReportBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		generateReportBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		generateReportBtn.addActionListener(this);
		generateReportBtn.setFocusable(false);
		generateReportBtn.setBounds(480, 120, 160, 80);
		viewMonthlyIncomePanel.add(generateReportBtn);

		manageTutorsPanel = new JPanel();
		contentPanel.setLayer(manageTutorsPanel, 3);
		manageTutorsPanel.setBorder(null);
		manageTutorsPanel.setBackground(new Color(83, 92, 145));
		manageTutorsPanel.setVisible(false);
		contentPanel.add(manageTutorsPanel, "name_164342281094100");
		manageTutorsPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("MANAGE TUTORS");
		lblNewLabel.setFont(new Font("Serif", Font.ITALIC, 28));
		lblNewLabel.setBounds(91, 37, 502, 169);
		manageTutorsPanel.add(lblNewLabel);

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

			resetUpdateProfilePage();

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

			resetUpdateProfilePage();

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
		if (e.getSource() == generateReportBtn) {
			String year = (String) yearSelector.getSelectedItem();
			String month = (String) monthSelector.getSelectedItem();

			if (year.contains("-") || month.contains("-")) { // Simple validation check if the user didnt select any
																// option
				JOptionPane.showMessageDialog(this, "You must select a month and year!", "Selection Error",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			System.out.println("GenerateMonthlyIncomeReport: Selected year = " + year);
			System.out.println("GenerateMonthlyIncomeReport: Selected month = " + month);

			new AdminPageGenerateReport(month, year);
		}

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
