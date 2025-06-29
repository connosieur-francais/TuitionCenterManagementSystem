package tcms.admin;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.custom_gui_components.ModelColor;
import tcms.users.User;
import tcms.users.UserManager;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class AdminPage extends JFrame implements ActionListener {

	private String atcBannerImg = "src/tcms/resources/atcBanner2.png";
	private String backgroundImg = "src/tcms/resources/loginPageBackground2.jpg";

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
	private JButton updateProfileBtn, manageTutorsButton;
	private JButton manageReceptionistsBtn, viewIncomeBtn;
	private JLabel atcBannerLabel;
	private JLayeredPane contentPanel;

	// HEADER LABEL SETTINGS
	private Color buttonColor = new Color(83, 92, 145);

	// UPDATE PROFILE PAGE ------------------------
	private CustomRoundedPanel updateProfilePanel;
	private JLabel profileSettingsLabel, usernameLabel;
	private JLabel passwordLabel, emailLabel, addressLabel, contactLabel;
	private JTextField usernameTxtfield, emailTxtfield;
	private JPasswordField passwordField;
	private JButton changeUsernameBtn, changePasswordBtn, changeEmailBtn, changeAddressBtn, saveChangesBtn,
			changeContactBtn;
	private JToggleButton showPasswordToggleBtn;
	private JLabel background;
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
		headerPanel.setBackground(new Color(7, 15, 43));

		atcBannerLabel = new JLabel("");
		atcBannerLabel.setIcon(new ImageIcon(atcBannerImg));

		updateProfileBtn = new JButton("Update Profile");
		updateProfileBtn.setIcon(new ImageIcon(
				"C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\tcms\\resources\\profile.png"));
		updateProfileBtn.setBackground(new Color(182, 177, 220));
		updateProfileBtn.setForeground(new Color(18, 21, 62));
		updateProfileBtn.setBorder(new LineBorder(new Color(18, 21, 62), 2));
		updateProfileBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		updateProfileBtn.addActionListener(this);
		updateProfileBtn.setFocusable(false);

		manageTutorsButton = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>Manage</p>\r\n<p>Tutors</p>\r\n</body>\r\n</html>");
		manageTutorsButton.setIcon(new ImageIcon(
				"C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\tcms\\resources\\tutor.png"));
		manageTutorsButton.setBackground(new Color(182, 177, 220));
		manageTutorsButton.setForeground(new Color(18, 21, 62));
		manageTutorsButton.setBorder(new LineBorder(new Color(18, 21, 62), 2));
		manageTutorsButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		manageTutorsButton.addActionListener(this);
		manageTutorsButton.setFocusable(false);

		manageReceptionistsBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<p>Manage</p>\r\n<p>Receptionists</p>\r\n</body>\r\n</html>");
		manageReceptionistsBtn.setIcon(new ImageIcon(
				"C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\tcms\\resources\\receptionist.png"));
		manageReceptionistsBtn.setBackground(new Color(182, 177, 220));
		manageReceptionistsBtn.setForeground(new Color(18, 21, 62));
		manageReceptionistsBtn.setBorder(new LineBorder(new Color(18, 21, 62), 2));
		manageReceptionistsBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		manageReceptionistsBtn.addActionListener(this);
		manageReceptionistsBtn.setFocusable(false);

		viewIncomeBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>View Monthly</p>\r\n<p>Income Report</p>\r\n</body>\r\n</html>");
		viewIncomeBtn.setIcon(new ImageIcon(
				"C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\tcms\\resources\\incomereport.png"));
		viewIncomeBtn.setBackground(new Color(182, 177, 220));
		viewIncomeBtn.setForeground(new Color(18, 21, 62));
		viewIncomeBtn.setBorder(new LineBorder(new Color(18, 21, 62), 2));
		viewIncomeBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		viewIncomeBtn.addActionListener(this);
		viewIncomeBtn.setFocusable(false);

		contentPanel = new JLayeredPane();
		contentPanel.setBounds(0, 85, 1186, 628);
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPanel.setBackground(new Color(27, 26, 85));

		// UPDATE PROFILE PANEL
		// -----------------------------------------------------------------------
		updateProfilePanel = new CustomRoundedPanel();
		updateProfilePanel.addColor(new ModelColor(Color.decode("#D9D9FF"), 0f), new ModelColor(Color.decode("#C2DFFF"), 1f));
		updateProfilePanel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		contentPanel.setLayer(updateProfilePanel, 1);
		updateProfilePanel.setBorder(new LineBorder(new Color(7, 15, 43), 5));
		updateProfilePanel.setBackground(new Color(240, 248, 255));
		contentPanel.setLayout(new CardLayout(0, 0));
		contentPanel.add(updateProfilePanel, "name_164342192424500");

		profileSettingsLabel = new JLabel("Update Profile");
		profileSettingsLabel.setBounds(440, 10, 300, 40);
		profileSettingsLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
		profileSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSettingsLabel.setForeground(new Color(0, 0, 0));

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 60, 100, 25);
		usernameLabel.setForeground(new Color(0, 0, 0));
		usernameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(640, 60, 100, 25);
		passwordLabel.setForeground(new Color(0, 0, 0));
		passwordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		usernameTxtfield = new JTextField();
		usernameTxtfield.setFont(new Font("SansSerif", Font.PLAIN, 16));
		usernameTxtfield.setBounds(100, 85, 450, 50);
		usernameTxtfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		usernameTxtfield.setBackground(new Color(223, 239, 255));
		usernameTxtfield.setEnabled(false);
		usernameTxtfield.setDisabledTextColor(Color.GRAY);
		usernameTxtfield.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordField.setBounds(640, 85, 450, 50);
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		passwordField.setBackground(new Color(223, 239, 255));
		passwordField.setEnabled(false);
		passwordField.setDisabledTextColor(Color.GRAY);
		passwordField.setEchoChar('*');

		changeUsernameBtn = new JButton("Change Username");
		changeUsernameBtn.setForeground(new Color(240, 236, 232));
		changeUsernameBtn.setLocation(360, 134);
		changeUsernameBtn.setSize(new Dimension(190, 25));
		changeUsernameBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		changeUsernameBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeUsernameBtn.setBackground(new Color(7, 15, 43));
		changeUsernameBtn.setFocusable(false);
		changeUsernameBtn.addActionListener(this);

		changePasswordBtn = new JButton("Change Password");
		changePasswordBtn.setForeground(new Color(240, 236, 232));
		changePasswordBtn.setBounds(900, 135, 190, 25);
		changePasswordBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		changePasswordBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changePasswordBtn.setBackground(new Color(7, 15, 43));
		changePasswordBtn.addActionListener(this);
		changePasswordBtn.setFocusable(false);

		emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(640, 170, 70, 25);
		emailLabel.setForeground(new Color(0, 0, 0));
		emailLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		changeEmailBtn = new JButton("Change E-mail");
		changeEmailBtn.setForeground(new Color(240, 236, 232));
		changeEmailBtn.setBounds(900, 245, 190, 25);
		changeEmailBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		changeEmailBtn.setBackground(new Color(7, 15, 43));
		changeEmailBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeEmailBtn.addActionListener(this);
		changeEmailBtn.setFocusable(false);

		showPasswordToggleBtn = new JToggleButton("Show Password");
		showPasswordToggleBtn.setForeground(new Color(240, 236, 232));
		showPasswordToggleBtn.setBounds(779, 135, 120, 25);
		showPasswordToggleBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		showPasswordToggleBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		showPasswordToggleBtn.setBackground(new Color(7, 15, 43));
		showPasswordToggleBtn.setFocusable(false);
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

		emailTxtfield = new JTextField();
		emailTxtfield.setFont(new Font("SansSerif", Font.PLAIN, 16));
		emailTxtfield.setBounds(640, 195, 450, 50);
		emailTxtfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		emailTxtfield.setBackground(new Color(223, 239, 255));
		emailTxtfield.setEnabled(false);
		emailTxtfield.setDisabledTextColor(Color.GRAY);
		emailTxtfield.setColumns(10);

		addressLabel = new JLabel("Address");
		addressLabel.setBounds(100, 280, 70, 25);
		addressLabel.setForeground(new Color(0, 0, 0));
		addressLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		addressTxtfield = new JTextField();
		addressTxtfield.setFont(new Font("SansSerif", Font.PLAIN, 16));
		addressTxtfield.setBounds(100, 305, 990, 50);
		addressTxtfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		addressTxtfield.setBackground(new Color(223, 239, 255));
		addressTxtfield.setText("<dynamic>");
		addressTxtfield.setEnabled(false);
		addressTxtfield.setDisabledTextColor(Color.GRAY);
		addressTxtfield.setColumns(10);

		changeAddressBtn = new JButton("Change Address");
		changeAddressBtn.setForeground(new Color(240, 236, 232));
		changeAddressBtn.setBounds(900, 355, 190, 25);
		changeAddressBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		changeAddressBtn.setBackground(new Color(7, 15, 43));
		changeAddressBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeAddressBtn.addActionListener(this);
		changeAddressBtn.setFocusable(false);

		saveChangesBtn = new JButton("Save Changes");
		saveChangesBtn.setForeground(new Color(244, 244, 240));
		saveChangesBtn.setBounds(972, 564, 200, 50);
		saveChangesBtn.setBorder(new LineBorder(new Color(7, 15, 43), 2));
		saveChangesBtn.setBackground(new Color(7, 15, 43));
		saveChangesBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		saveChangesBtn.addActionListener(this);
		saveChangesBtn.setFocusable(false);

		contactLabel = new JLabel("Contact");
		contactLabel.setBounds(100, 170, 100, 25);
		contactLabel.setForeground(new Color(0, 0, 0));
		contactLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

		contactTxtfield = new JTextField();
		contactTxtfield.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contactTxtfield.setBounds(100, 195, 450, 50);
		contactTxtfield.setText("<dynamic>");
		contactTxtfield.setEnabled(false);
		contactTxtfield.setDisabledTextColor(Color.GRAY);
		contactTxtfield.setColumns(10);
		contactTxtfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		contactTxtfield.setBackground(new Color(223, 239, 255));

		changeContactBtn = new JButton("Change Contact");
		changeContactBtn.setForeground(new Color(240, 236, 232));
		changeContactBtn.setBounds(360, 245, 190, 25);
		changeContactBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		changeContactBtn.setFocusable(false);
		changeContactBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		changeContactBtn.addActionListener(this);
		changeContactBtn.setBackground(new Color(7, 15, 43));
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

		background = new JLabel("");
		contentPanel.setLayer(background, 5);
		background.setIcon(new ImageIcon(backgroundImg));
		contentPanel.add(background, "name_164342214344200");

		// MANAGE RECEPTIONISTS PANEL

		manageReceptionistPanel = new JPanel();
		contentPanel.setLayer(manageReceptionistPanel, 2);
		manageReceptionistPanel.setBackground(new Color(83, 92, 145));
		manageReceptionistPanel.setVisible(false);
		manageReceptionistPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(manageReceptionistPanel, "name_164342236052900");
		manageReceptionistPanel.setLayout(null);

		ManageReceptionistsLabel = new JLabel("Manage Receptionists");
		ManageReceptionistsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ManageReceptionistsLabel.setForeground(new Color(255, 255, 255));
		ManageReceptionistsLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		ManageReceptionistsLabel.setBounds(440, 10, 286, 41);
		manageReceptionistPanel.add(ManageReceptionistsLabel);

		// VIEW MONTHLY INCOME PANEL ------------------------------------------

		viewMonthlyIncomePanel = new JPanel();
		viewMonthlyIncomePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		viewMonthlyIncomePanel.setBackground(new Color(83, 92, 145));
		viewMonthlyIncomePanel.setVisible(false);
		viewMonthlyIncomePanel.setLayout(null);
		contentPanel.setLayer(viewMonthlyIncomePanel, 4);
		contentPanel.add(viewMonthlyIncomePanel, "name_164342258048700");

		viewMonthlyIncomeLabel = new JLabel("View Monthly Income Report");
		viewMonthlyIncomeLabel.setForeground(new Color(255, 255, 255));
		viewMonthlyIncomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		viewMonthlyIncomeLabel.setBounds(10, 5, 220, 40);
		viewMonthlyIncomePanel.add(viewMonthlyIncomeLabel);

		monthSelector = new JComboBox<String>();
		monthSelector.setMaximumRowCount(12);
		monthSelector.setModel(
				new DefaultComboBoxModel<String>(new String[] { "- Select Month -", "January", "February", "March",
						"April", "May", "June", "July", "August", "September", "October", "November", "December" }));
		monthSelector.setFont(new Font("SansSerif", Font.PLAIN, 12));
		monthSelector.setBounds(20, 55, 200, 20);
		viewMonthlyIncomePanel.add(monthSelector);

		yearSelector = new JComboBox<String>();
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
		generateReportBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		generateReportBtn.addActionListener(this);
		generateReportBtn.setFocusable(false);
		generateReportBtn.setBounds(480, 120, 160, 80);
		viewMonthlyIncomePanel.add(generateReportBtn);

		manageTutorsPanel = new JPanel();
		contentPanel.setLayer(manageTutorsPanel, 3);
		manageTutorsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
				.createSequentialGroup()
				.addComponent(atcBannerLabel, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(updateProfileBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addComponent(manageTutorsButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
				.addGap(30)
				.addComponent(manageReceptionistsBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
				.addGap(30).addComponent(viewIncomeBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)));
		gl_headerPanel.setVerticalGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(atcBannerLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_headerPanel.createSequentialGroup().addGap(10).addComponent(updateProfileBtn,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_headerPanel.createSequentialGroup().addGap(10).addComponent(manageTutorsButton,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_headerPanel.createSequentialGroup().addGap(10).addComponent(manageReceptionistsBtn,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_headerPanel.createSequentialGroup().addGap(10).addComponent(viewIncomeBtn,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)));
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
