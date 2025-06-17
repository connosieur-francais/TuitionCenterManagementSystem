package tcms.admin;

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
import javax.swing.JLayeredPane;
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
	private JLabel passwordLabel, emailLabel;
	private JTextField usernameTxtfield, emailTxtfield;
	private JPasswordField passwordField;
	private JButton changeUsernameBtn, changePasswordBtn, changeEmailBtn;
	private JToggleButton showPasswordToggleBtn;
	private JLabel lblNewLabel;
	private JTextField addressTxtfield;
	
	private String adminCSVFile = "src//admin.csv";
	private String userCSVFile = "src//users.csv";
	private JButton saveChangesBtn;

	/**
	 * Create the frame.
	 */
	public AdminPage(User user) {
		 // Find the admin class
		int user_id = user.getID();
		AdminManager adminManager = new AdminManager();
		UserManager userManager = new UserManager();
		userManager.loadUsers(userCSVFile);
		adminManager.loadAdmins(adminCSVFile);
		Admin admin = adminManager.findAdminByUserID(user_id);
		
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
		
		// UPDATE PROFILE PANEL -----------------------------------------------------------------------
		updateProfilePanel = new JPanel();
		updateProfilePanel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		contentPanel.setLayer(updateProfilePanel, 1);
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
		usernameTxtfield.setEditable(false);
		usernameTxtfield.setBounds(80, 50, 200, 20);
		updateProfilePanel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBackground(new Color(192, 192, 192));
		passwordField.setEditable(false);
		passwordField.setEchoChar('*');
		passwordField.setBounds(80, 100, 200, 20);
		updateProfilePanel.add(passwordField);

		changeUsernameBtn = new JButton("Change Username");
		changeUsernameBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changeUsernameBtn.setBounds(293, 50, 120, 20);
		updateProfilePanel.add(changeUsernameBtn);

		changePasswordBtn = new JButton("Change Password");
		changePasswordBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changePasswordBtn.setBounds(293, 100, 120, 20);
		updateProfilePanel.add(changePasswordBtn);

		emailLabel = new JLabel("E-mail");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		emailLabel.setBounds(10, 150, 70, 20);
		updateProfilePanel.add(emailLabel);

		changeEmailBtn = new JButton("Change E-mail");
		changeEmailBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changeEmailBtn.setBounds(293, 150, 120, 20);
		updateProfilePanel.add(changeEmailBtn);

		showPasswordToggleBtn = new JToggleButton("Show Password");
		showPasswordToggleBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
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
		emailTxtfield.setEditable(false);
		emailTxtfield.setColumns(10);
		emailTxtfield.setBounds(80, 150, 200, 20);
		updateProfilePanel.add(emailTxtfield);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		addressLabel.setBounds(10, 200, 70, 20);
		updateProfilePanel.add(addressLabel);
		
		addressTxtfield = new JTextField();
		addressTxtfield.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addressTxtfield.setBackground(new Color(192, 192, 192));
		addressTxtfield.setText("<dynamic>");
		addressTxtfield.setEditable(false);
		addressTxtfield.setColumns(10);
		addressTxtfield.setBounds(80, 200, 200, 20);
		updateProfilePanel.add(addressTxtfield);
		
		JButton changeAddressBtn = new JButton("Change Address");
		changeAddressBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
		changeAddressBtn.setBounds(293, 200, 120, 20);
		updateProfilePanel.add(changeAddressBtn);
		
		saveChangesBtn = new JButton("Save Changes");
		saveChangesBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		saveChangesBtn.setBounds(506, 270, 150, 40);
		updateProfilePanel.add(saveChangesBtn);
		
		updateProfilePanelInformation(user, admin); // Update the information in txtfields with logged in user's information
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src\\loginPageBackground.png"));
		lblNewLabel.setBounds(0, 0, 686, 335);
		contentPanel.add(lblNewLabel);
		
		// MANAGE RECEPTIONISTS PANEL ---------------------------------------------------------------------
		JPanel manageReceptionistPanel = new JPanel();
		contentPanel.setLayer(manageReceptionistPanel, 1);
		manageReceptionistPanel.setBackground(new Color(83, 92, 145));
		manageReceptionistPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		manageReceptionistPanel.setBounds(10, 5, 666, 320);
		contentPanel.add(manageReceptionistPanel);
		manageReceptionistPanel.setLayout(null);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// HEADER PANEL --------------------------------
		if (e.getSource() == updateProfileBtn) {
			updateProfilePanel.setVisible(true);
		}
		if (e.getSource() == manageTutorsButton) {
			updateProfilePanel.setVisible(false);
		}
		if (e.getSource() == manageReceptionistsBtn) {
			// to do
		}
		if (e.getSource() == viewIncomeBtn) {
			// to do
		}
		
		// UPDATE PROFILE PANEL ----------------------------------
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
