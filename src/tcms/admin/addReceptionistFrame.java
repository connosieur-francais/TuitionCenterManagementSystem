package tcms.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomTextField;
import tcms.receptionists.Receptionist;
import tcms.receptionists.ReceptionistManager;
import tcms.users.User;
import tcms.users.UserManager;

public class addReceptionistFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private static UserManager userManager;
	private static ReceptionistManager receptionistManager;

	// === Color Scheme ===
	private final Color canvasColor = new Color(30, 33, 36);
	private final Color blurple = new Color(88, 101, 242);
	private final Color blurpleHover = new Color(79, 82, 196);
	private final Color blurpleClick = new Color(60, 69, 165);
	private final Color red = new Color(237, 66, 69);
	private final Color redHover = new Color(200, 50, 52);
	private final Color redClick = new Color(165, 30, 33);
	private final Color textColor = Color.WHITE;
	private final Color borderColor = new Color(43, 45, 49);
	private final Color darkGray = new Color(43, 45, 49);

	// === Labels ===
	private JLabel titleLabel;
	private JLabel recepIDLabel;
	private JLabel recepNameLabel;
	private JLabel passwordLabel;
	private JLabel emailLabel;
	private JLabel contactLabel;
	private JLabel addressLabel;

	// === Text Fields ===
	private CustomTextField recepIDTxtfield;
	private CustomTextField nameTxtfield;
	private CustomTextField passwordTxtfield;
	private CustomTextField emailTxtfield;
	private CustomTextField contactTxtfield;
	private CustomTextField addressTxtfield;

	// === Buttons ===
	private CustomJButton createRecepBtn;
	private CustomJButton cancelButton;
	
	// Filenames
	private String receptionistsCSVFile = "src/receptionits.csv";
	private String usersCSVFile = "src/users.csv";

	// === Constructor ===
	public addReceptionistFrame(UserManager um, ReceptionistManager rm) {
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 738, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		userManager = um;
		receptionistManager = rm;

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(canvasColor);
		getContentPane().add(new CustomTitleBar(), BorderLayout.NORTH);

		contentPanel.setBackground(canvasColor);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// === Title ===
		titleLabel = new JLabel("Add Receptionist");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(270, 20, 210, 30);
		titleLabel.setForeground(textColor);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		contentPanel.add(titleLabel);

		// === Receptionist ID ===
		recepIDLabel = new JLabel("Receptionist ID :");
		recepIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		recepIDLabel.setForeground(new Color(220, 221, 222));
		recepIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
		recepIDLabel.setBounds(100, 70, 125, 30);
		contentPanel.add(recepIDLabel);

		recepIDTxtfield = new CustomTextField();
		recepIDTxtfield.setDisabledTextColor(new Color(220, 221, 222));
		recepIDTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		recepIDTxtfield.setText("<dynamic>");
		recepIDTxtfield.setColor(new Color(35, 39, 42));
		recepIDTxtfield.setBackground(new Color(35, 39, 42));
		recepIDTxtfield.setBorderColor(new Color(44, 47, 51));
		recepIDTxtfield.setFocusable(false);
		recepIDTxtfield.setEnabled(false);
		recepIDTxtfield.setRadius(10);
		recepIDTxtfield.setBounds(235, 70, 403, 30);
		contentPanel.add(recepIDTxtfield);

		// === Name ===
		recepNameLabel = new JLabel("Name :");
		recepNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		recepNameLabel.setForeground(new Color(220, 221, 222));
		recepNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		recepNameLabel.setBounds(100, 140, 125, 30);
		contentPanel.add(recepNameLabel);

		nameTxtfield = new CustomTextField();
		nameTxtfield.setForeground(new Color(163, 166, 170));
		nameTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		nameTxtfield.setColor(new Color(54, 57, 62));
		nameTxtfield.setBorderColor(new Color(30, 33, 36));
		nameTxtfield.setRadius(10);
		nameTxtfield.setBounds(235, 140, 403, 30);
		contentPanel.add(nameTxtfield);

		// === Password ===
		passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLabel.setForeground(new Color(220, 221, 222));
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		passwordLabel.setBounds(100, 210, 125, 30);
		contentPanel.add(passwordLabel);

		passwordTxtfield = new CustomTextField();
		passwordTxtfield.setRadius(10);
		passwordTxtfield.setForeground(new Color(163, 166, 170));
		passwordTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		passwordTxtfield.setColor(new Color(54, 57, 62));
		passwordTxtfield.setBorderColor(new Color(30, 33, 36));
		passwordTxtfield.setBounds(235, 210, 403, 30);
		contentPanel.add(passwordTxtfield);

		// === Email ===
		emailLabel = new JLabel("Email :");
		emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		emailLabel.setForeground(new Color(220, 221, 222));
		emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		emailLabel.setBounds(100, 280, 125, 30);
		contentPanel.add(emailLabel);

		emailTxtfield = new CustomTextField();
		emailTxtfield.setForeground(new Color(163, 166, 170));
		emailTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		emailTxtfield.setColor(new Color(54, 57, 62));
		emailTxtfield.setBorderColor(new Color(30, 33, 36));
		emailTxtfield.setRadius(10);
		emailTxtfield.setBounds(235, 280, 403, 30);
		contentPanel.add(emailTxtfield);

		// === Contact ===
		contactLabel = new JLabel("Contact :");
		contactLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		contactLabel.setForeground(new Color(220, 221, 222));
		contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
		contactLabel.setBounds(100, 350, 125, 30);
		contentPanel.add(contactLabel);

		contactTxtfield = new CustomTextField();
		contactTxtfield.setForeground(new Color(163, 166, 170));
		contactTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		contactTxtfield.setColor(new Color(54, 57, 62));
		contactTxtfield.setBorderColor(new Color(30, 33, 36));
		contactTxtfield.setRadius(10);
		contactTxtfield.setBounds(235, 350, 403, 30);
		contentPanel.add(contactTxtfield);

		// === Address ===
		addressLabel = new JLabel("Address :");
		addressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addressLabel.setForeground(new Color(220, 221, 222));
		addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
		addressLabel.setBounds(100, 420, 125, 30);
		contentPanel.add(addressLabel);

		addressTxtfield = new CustomTextField();
		addressTxtfield.setMargin(new Insets(5, 5, 5, 5));
		addressTxtfield.setForeground(new Color(163, 166, 170));
		addressTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		addressTxtfield.setColor(new Color(54, 57, 62));
		addressTxtfield.setBorderColor(new Color(30, 33, 36));
		addressTxtfield.setRadius(10);
		addressTxtfield.setBounds(235, 420, 403, 30);
		contentPanel.add(addressTxtfield);

		// === Bottom Buttons ===
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setPreferredSize(new Dimension(10, 50));
		buttonPane.setBackground(canvasColor);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		createRecepBtn = new CustomJButton();
		createRecepBtn.setText("Create Receptionist");
		createRecepBtn.setFont(new Font("Arial", Font.BOLD, 14));
		createRecepBtn.setBackground(blurple);
		createRecepBtn.setColor(blurple);
		createRecepBtn.setColorOver(blurpleHover);
		createRecepBtn.setColorClick(blurpleClick);
		createRecepBtn.setForeground(textColor);
		createRecepBtn.setBorderColor(borderColor);
		createRecepBtn.setPreferredSize(new Dimension(160, 40));
		createRecepBtn.setFocusPainted(false);
		createRecepBtn.setRadius(8);
		createRecepBtn.addActionListener(this);
		buttonPane.add(createRecepBtn);
//		getRootPane().setDefaultButton(createRecepBtn);

		cancelButton = new CustomJButton();
		cancelButton.setText("Cancel");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
		cancelButton.setBackground(red);
		cancelButton.setColor(red);
		cancelButton.setColorOver(redHover);
		cancelButton.setColorClick(redClick);
		cancelButton.setForeground(textColor);
		cancelButton.setBorderColor(borderColor);
		cancelButton.setPreferredSize(new Dimension(80, 40));
		cancelButton.setFocusPainted(false);
		cancelButton.setRadius(8);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);

		// Update receptionistIDTxtfield with next available ID;
		updateAddReceptionistFrame();

		setVisible(true);
	}

	// === Custom Title Bar Inner Class ===
	@SuppressWarnings("serial")
	private class CustomTitleBar extends JPanel {
		private Point initialClick;

		public CustomTitleBar() {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(getWidth(), 40));
			setBackground(darkGray);

			JLabel title = new JLabel("   Add Receptionist");
			title.setForeground(Color.WHITE);
			title.setFont(new Font("Arial", Font.BOLD, 14));
			add(title, BorderLayout.WEST);

			JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			controls.setOpaque(false);

			CustomJButton minBtn = new CustomJButton();
			minBtn.setText("—");
			minBtn.setFont(new Font("Arial", Font.BOLD, 12));
			minBtn.setBackground(darkGray);
			minBtn.setColor(darkGray);
			minBtn.setColorOver(new Color(60, 63, 68));
			minBtn.setColorClick(new Color(40, 42, 46));
			minBtn.setForeground(Color.WHITE);
			minBtn.setBorderColor(borderColor);
			minBtn.setFocusPainted(false);
			minBtn.setPreferredSize(new Dimension(45, 40));
			minBtn.setRadius(0);
			minBtn.addActionListener(e -> setState(JFrame.ICONIFIED));
			controls.add(minBtn);

			CustomJButton closeBtn = new CustomJButton();
			closeBtn.setText("X");
			closeBtn.setFont(new Font("Arial", Font.PLAIN, 16));
			closeBtn.setBackground(red);
			closeBtn.setColor(red);
			closeBtn.setColorOver(redHover);
			closeBtn.setColorClick(redClick);
			closeBtn.setForeground(Color.WHITE);
			closeBtn.setBorderColor(borderColor);
			closeBtn.setFocusPainted(false);
			closeBtn.setPreferredSize(new Dimension(45, 40));
			closeBtn.setRadius(0);
			closeBtn.addActionListener(e -> dispose());
			controls.add(closeBtn);

			add(controls, BorderLayout.EAST);

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					initialClick = e.getPoint();
				}
			});

			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					int thisX = addReceptionistFrame.this.getLocation().x;
					int thisY = addReceptionistFrame.this.getLocation().y;
					int xMoved = e.getX() - initialClick.x;
					int yMoved = e.getY() - initialClick.y;
					addReceptionistFrame.this.setLocation(thisX + xMoved, thisY + yMoved);
				}
			});
		}
	}

	public void updateAddReceptionistFrame() {
		int nextAvailableID = receptionistManager.nextAvailableReceptionistID();
		recepIDTxtfield.setText(String.valueOf(nextAvailableID));
	}

	// ============= Input Validators ====================
	public boolean isValidName(String name) {
		// Check if name is empty
		if (name == null || name.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Name cannot be empty", "Invalid Name", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Checks if name is over 4 characters long
		boolean isValid = name.length() >= 4;
		if (!isValid) {
			JOptionPane.showMessageDialog(null, "Name has to be over 4 characters long", "Invalid Name",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean isValidPassword(String password) {
		// Check if password is empty
		if (password == null || password.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Password cannot be empty", "Invalid Password",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check length
		if (password.length() < 8) {
			JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long", "Invalid Password",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check for digit
		if (!password.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(null, "Password must contain at least one digit", "Invalid Password",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check for uppercase letter
		if (!password.matches(".*[A-Z].*")) {
			JOptionPane.showMessageDialog(null, "Password must contain at least one uppercase letter",
					"Invalid Password", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check for lowercase letter
		if (!password.matches(".*[a-z].*")) {
			JOptionPane.showMessageDialog(null, "Password must contain at least one lowercase letter",
					"Invalid Password", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean isValidEmail(String email) {
		// Check for null or empty string after trimming whitespace
		if (email == null || email.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email cannot be empty.", "Invalid Email", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Find the position of '@' and the last '.'
		int positionOfAt = email.indexOf('@');
		int positionOfLastDot = email.lastIndexOf('.');
		// Conditions for a valid email:
		boolean hasValidAt = positionOfAt > 0;
		boolean hasDotAfterAt = positionOfLastDot > positionOfAt + 1;
		boolean dotNotAtEnd = positionOfLastDot < email.length() - 1;

		boolean isValid = hasValidAt && hasDotAfterAt && dotNotAtEnd;

		if (!isValid) {
			JOptionPane.showMessageDialog(null, "Email format is invalid. Please enter a valid email address.",
					"Invalid Email", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean isValidContact(String contact) {
		// Check if contact is empty
		if (contact == null || contact.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Contact number cannot be empty.", "Invalid Contact",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Remove any spaces in the contact
		contact = contact.trim();
		// Make sure it only contains digits
		if (!contact.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "Contact number must contain only digits.", "Invalid Contact",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Make sure contact is 10 digits long
		if (contact.length() != 10) {
			JOptionPane.showMessageDialog(null, "Contact number must be exactly 10 digits long.", "Invalid Contact",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean isValidAddress(String address) {
		// Check if address is empty
		if (address == null || address.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Address cannot be empty", "Invalid Address",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Make sure address doesn't have commas
		if (address.contains(",")) {
			JOptionPane.showMessageDialog(null, "Address cannot contain commas", "Invalid Address",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (!(address.length() >= 8)) {
			JOptionPane.showMessageDialog(null, "Address has to be over 8 characters long", "Invalid Address",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean checkReceptionistDetails() {
		String name = nameTxtfield.getText();
		String password = passwordTxtfield.getText();
		String contact = contactTxtfield.getText();
		String email = emailTxtfield.getText();
		String address = addressTxtfield.getText();

		// Only one dialog will show — first error encountered
		if (!isValidName(name)) {
			return false;
		}
		if (!isValidPassword(password)) {
			return false;
		}
		if (!isValidEmail(email)) {
			return false;
		}
		if (!isValidContact(contact)) {
			return false;
		}
		if (!isValidAddress(address)) {
			return false;
		}

		return true;
	}
	
	public void createNewReceptionist() {
		int nextAvailableRecepID = receptionistManager.getAllReceptionists().size() + 1; // Size of recep list + 1
		String name = nameTxtfield.getText();
		String password = passwordTxtfield.getText();
		String contact = contactTxtfield.getText();
		String email = emailTxtfield.getText();
		String address = addressTxtfield.getText();
		String role = "receptionist"; // Hard coded because its creating a receptionist :P
		
		User receptionistUserAccount = new User(name, password, role);
		int nextAvailableUserID = receptionistUserAccount.getID();
		
		Receptionist receptionistAccount = new Receptionist(nextAvailableRecepID, nextAvailableUserID, contact, email, address);
		
		receptionistManager.addReceptionist(receptionistAccount, receptionistUserAccount);
		userManager.saveUsers(usersCSVFile);
		receptionistManager.saveReceptionists(receptionistsCSVFile);
		
	}

	// ============= Button Action Listeners ================
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			JOptionPane.showMessageDialog(this, "Cancelled Receptionist Creation", "Action Cancelled",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else if (e.getSource() == createRecepBtn) {
			if (!checkReceptionistDetails()) {
				return;
			}
			// Only show this message if receptionist details are fully valid
			JOptionPane.showMessageDialog(this, "Receptionist created", "Successfully Created Receptionist",
					JOptionPane.INFORMATION_MESSAGE);

			// Add a new line in users.csv and also receptionist.csv for the new receptionist created
			createNewReceptionist();
			this.dispose();

		}
	}
}
