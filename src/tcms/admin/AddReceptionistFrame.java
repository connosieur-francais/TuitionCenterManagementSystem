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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomTextField;
import tcms.receptionists.Receptionist;
import tcms.receptionists.ReceptionistManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;
import tcms.utils.Validators;

public class AddReceptionistFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private static UserManager userManager;
	private static ReceptionistManager receptionistManager;

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
	private String receptionistsCSVFile = Constants.RECEPTIONISTS_CSV;
	private String usersCSVFile = Constants.USERS_CSV;

	public AddReceptionistFrame(UserManager um, ReceptionistManager rm) {
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 738, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		userManager = um;
		receptionistManager = rm;

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Constants.DARK_BUT_NOT_BLACK);
		getContentPane().add(new CustomTitleBar(), BorderLayout.NORTH);

		contentPanel.setBackground(Constants.CANVAS_COLOR);
		contentPanel.setBorder(new MatteBorder(5, 5, 0, 5, (Color) Constants.SLATE));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		titleLabel = new JLabel("Add Receptionist");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(270, 20, 210, 30);
		titleLabel.setForeground(Constants.TEXT_COLOR);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		contentPanel.add(titleLabel);

		recepIDLabel = new JLabel("Receptionist ID :");
		recepIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		recepIDLabel.setForeground(Constants.TEXT_COLOR);
		recepIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
		recepIDLabel.setBounds(100, 70, 125, 30);
		contentPanel.add(recepIDLabel);

		recepIDTxtfield = new CustomTextField();
		recepIDTxtfield.setDisabledTextColor(Constants.TEXT_COLOR);
		recepIDTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		recepIDTxtfield.setText("<dynamic>");
		recepIDTxtfield.setColor(Constants.NOT_QUITE_BLACK);
		recepIDTxtfield.setBackground(Constants.NOT_QUITE_BLACK);
		recepIDTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		recepIDTxtfield.setFocusable(false);
		recepIDTxtfield.setEnabled(false);
		recepIDTxtfield.setRadius(10);
		recepIDTxtfield.setBounds(235, 70, 403, 30);
		contentPanel.add(recepIDTxtfield);

		recepNameLabel = new JLabel("Name :");
		recepNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		recepNameLabel.setForeground(Constants.TEXT_COLOR);
		recepNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		recepNameLabel.setBounds(100, 140, 125, 30);
		contentPanel.add(recepNameLabel);

		nameTxtfield = new CustomTextField();
		nameTxtfield.setForeground(new Color(163, 166, 170));
		nameTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		nameTxtfield.setColor(Constants.DEEP_DARK);
		nameTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		nameTxtfield.setRadius(10);
		nameTxtfield.setBounds(235, 140, 403, 30);
		contentPanel.add(nameTxtfield);

		passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLabel.setForeground(Constants.TEXT_COLOR);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		passwordLabel.setBounds(100, 210, 125, 30);
		contentPanel.add(passwordLabel);

		passwordTxtfield = new CustomTextField();
		passwordTxtfield.setRadius(10);
		passwordTxtfield.setForeground(new Color(163, 166, 170));
		passwordTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		passwordTxtfield.setColor(Constants.DEEP_DARK);
		passwordTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		passwordTxtfield.setBounds(235, 210, 403, 30);
		contentPanel.add(passwordTxtfield);

		emailLabel = new JLabel("Email :");
		emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		emailLabel.setForeground(Constants.TEXT_COLOR);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		emailLabel.setBounds(100, 280, 125, 30);
		contentPanel.add(emailLabel);

		emailTxtfield = new CustomTextField();
		emailTxtfield.setForeground(new Color(163, 166, 170));
		emailTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		emailTxtfield.setColor(Constants.DEEP_DARK);
		emailTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		emailTxtfield.setRadius(10);
		emailTxtfield.setBounds(235, 280, 403, 30);
		contentPanel.add(emailTxtfield);

		contactLabel = new JLabel("Contact :");
		contactLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		contactLabel.setForeground(Constants.TEXT_COLOR);
		contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
		contactLabel.setBounds(100, 350, 125, 30);
		contentPanel.add(contactLabel);

		contactTxtfield = new CustomTextField();
		contactTxtfield.setForeground(new Color(163, 166, 170));
		contactTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		contactTxtfield.setColor(Constants.DEEP_DARK);
		contactTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		contactTxtfield.setRadius(10);
		contactTxtfield.setBounds(235, 350, 403, 30);
		contentPanel.add(contactTxtfield);

		addressLabel = new JLabel("Address :");
		addressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addressLabel.setForeground(Constants.TEXT_COLOR);
		addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
		addressLabel.setBounds(100, 420, 125, 30);
		contentPanel.add(addressLabel);

		addressTxtfield = new CustomTextField();
		addressTxtfield.setMargin(new Insets(5, 5, 5, 5));
		addressTxtfield.setForeground(new Color(163, 166, 170));
		addressTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		addressTxtfield.setColor(Constants.DEEP_DARK);
		addressTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		addressTxtfield.setRadius(10);
		addressTxtfield.setBounds(235, 420, 403, 30);
		contentPanel.add(addressTxtfield);

		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBorder(new MatteBorder(0, 5, 5, 5, (Color) Constants.SLATE));
		buttonPane.setPreferredSize(new Dimension(10, 50));
		buttonPane.setBackground(Constants.CANVAS_COLOR);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		createRecepBtn = new CustomJButton();
		createRecepBtn.setText("Create Receptionist");
		createRecepBtn.setFont(new Font("Arial", Font.BOLD, 14));
		createRecepBtn.setBackground(Constants.BLURPLE);
		createRecepBtn.setColor(Constants.BLURPLE);
		createRecepBtn.setColorOver(Constants.BLURPLE_HOVER);
		createRecepBtn.setColorClick(Constants.BLURPLE_CLICK);
		createRecepBtn.setForeground(Constants.TEXT_COLOR);
		createRecepBtn.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		createRecepBtn.setPreferredSize(new Dimension(160, 40));
		createRecepBtn.setFocusPainted(false);
		createRecepBtn.setRadius(8);
		createRecepBtn.addActionListener(this);
		buttonPane.add(createRecepBtn);

		cancelButton = new CustomJButton();
		cancelButton.setText("Cancel");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
		cancelButton.setBackground(Constants.RED);
		cancelButton.setColor(Constants.RED);
		cancelButton.setColorOver(Constants.RED_HOVER);
		cancelButton.setColorClick(Constants.RED_CLICK);
		cancelButton.setForeground(Constants.TEXT_COLOR);
		cancelButton.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		cancelButton.setPreferredSize(new Dimension(80, 40));
		cancelButton.setFocusPainted(false);
		cancelButton.setRadius(8);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);

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
			setBackground(Constants.DARK_GRAY);

			JLabel title = new JLabel("   Add Receptionist");
			title.setForeground(Color.WHITE);
			title.setFont(new Font("Arial", Font.BOLD, 14));
			add(title, BorderLayout.WEST);

			JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			controls.setOpaque(false);

			CustomJButton minBtn = new CustomJButton();
			minBtn.setText("—");
			minBtn.setFont(new Font("Arial", Font.BOLD, 12));
			minBtn.setBackground(Constants.DARK_GRAY);
			minBtn.setColor(Constants.DARK_GRAY);
			minBtn.setColorOver(new Color(60, 63, 68));
			minBtn.setColorClick(new Color(40, 42, 46));
			minBtn.setForeground(Color.WHITE);
			minBtn.setBorderColor(Constants.DEEP_DARK);
			minBtn.setFocusPainted(false);
			minBtn.setPreferredSize(new Dimension(45, 40));
			minBtn.setRadius(0);
			minBtn.addActionListener(e -> setState(JFrame.ICONIFIED));
			controls.add(minBtn);

			CustomJButton closeBtn = new CustomJButton();
			closeBtn.setText("X");
			closeBtn.setFont(new Font("Arial", Font.PLAIN, 16));
			closeBtn.setBackground(Constants.RED);
			closeBtn.setColor(Constants.RED);
			closeBtn.setColorOver(Constants.RED_BUTTON_HOVER);
			closeBtn.setColorClick(Constants.RED_BUTTON_CLICK);
			closeBtn.setForeground(Color.WHITE);
			closeBtn.setBorderColor(Constants.DEEP_DARK);
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
					int thisX = AddReceptionistFrame.this.getLocation().x;
					int thisY = AddReceptionistFrame.this.getLocation().y;
					int xMoved = e.getX() - initialClick.x;
					int yMoved = e.getY() - initialClick.y;
					AddReceptionistFrame.this.setLocation(thisX + xMoved, thisY + yMoved);
				}
			});
		}
	}

	public void updateAddReceptionistFrame() {
		int nextAvailableID = receptionistManager.nextAvailableReceptionistID();
		recepIDTxtfield.setText(String.valueOf(nextAvailableID));
	}

	private boolean checkReceptionistDetails() {
		String name = nameTxtfield.getText();
		String password = passwordTxtfield.getText();
		String contact = contactTxtfield.getText();
		String email = emailTxtfield.getText();
		String address = addressTxtfield.getText();

		// Only one dialog will show — first error encountered
		if (!Validators.isValidName(name)) {
			return false;
		}
		if (!Validators.isValidPassword(password)) {
			return false;
		}
		if (!Validators.isValidEmail(email)) {
			return false;
		}
		if (!Validators.isValidContact(contact)) {
			return false;
		}
		if (!Validators.isValidAddress(address)) {
			return false;
		}

		return true;
	}

	private void createNewReceptionist() {
		int firstAvailableUserID = userManager.getFirstAvailableUserID();
		int firstAvailableRecepID = receptionistManager.getFirstAvailableReceptionistID();
		String name = nameTxtfield.getText();
		String password = passwordTxtfield.getText();
		String contact = contactTxtfield.getText();
		String email = emailTxtfield.getText();
		String address = addressTxtfield.getText();
		String role = "receptionist"; // Hard coded because its creating a receptionist :P

		User receptionistUserAccount = new User(firstAvailableUserID, name, password, role);
		int nextAvailableUserID = receptionistUserAccount.getID();

		Receptionist receptionistAccount = new Receptionist(firstAvailableRecepID, nextAvailableUserID, contact, email,
				address);

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

			// Add a new line in users.csv and also receptionist.csv for the new
			// receptionist created
			createNewReceptionist();
			this.dispose();

		}
	}
}
