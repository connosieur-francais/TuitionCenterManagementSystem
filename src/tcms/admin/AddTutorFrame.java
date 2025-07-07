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
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomTextField;
import tcms.tutors.Level;
import tcms.tutors.Subject;
import tcms.tutors.Tutor;
import tcms.tutors.TutorManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;
import tcms.utils.Validators;

public class AddTutorFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private static UserManager userManager;
	private static TutorManager tutorManager;
	// === Special ===
	private JList<String> subjectList;
	private DefaultListModel<String> subjectListModel;
	private Map<String, Integer> subjectNameToIDMap = new HashMap<>();

	private JComboBox<String> levelComboBox;
	private Map<String, Integer> levelNameToIDMap = new HashMap<>();

	// === Labels ===
	private JLabel titleLabel;
	private JLabel tutorIDLabel;
	private JLabel tutorNameLabel;
	private JLabel passwordLabel;
	private JLabel emailLabel;
	private JLabel contactLabel;
	private JLabel addressLabel;

	// === Text Fields ===
	private CustomTextField tutorIDTxtfield;
	private CustomTextField nameTxtfield;
	private CustomTextField passwordTxtfield;
	private CustomTextField emailTxtfield;
	private CustomTextField contactTxtfield;
	private CustomTextField addressTxtfield;

	// === Buttons ===
	private CustomJButton createTutorBtn;
	private CustomJButton cancelButton;
	private JLabel levelLabel;

	public AddTutorFrame(UserManager um, TutorManager tm) {
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 738, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		userManager = um;
		tutorManager = tm;

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Constants.DARK_BUT_NOT_BLACK);
		getContentPane().add(new CustomTitleBar(), BorderLayout.NORTH);

		contentPanel.setBackground(Constants.CANVAS_COLOR);
		contentPanel.setBorder(new MatteBorder(5, 5, 0, 5, (Color) Constants.SLATE));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		titleLabel = new JLabel("Add Tutor");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(270, 20, 210, 30);
		titleLabel.setForeground(Constants.TEXT_COLOR);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		contentPanel.add(titleLabel);

		tutorIDLabel = new JLabel("Tutor ID :");
		tutorIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		tutorIDLabel.setForeground(Constants.TEXT_COLOR);
		tutorIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
		tutorIDLabel.setBounds(30, 70, 125, 30);
		contentPanel.add(tutorIDLabel);

		tutorIDTxtfield = new CustomTextField();
		tutorIDTxtfield.setDisabledTextColor(Constants.TEXT_COLOR);
		tutorIDTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		tutorIDTxtfield.setText("<dynamic>");
		tutorIDTxtfield.setColor(Constants.NOT_QUITE_BLACK);
		tutorIDTxtfield.setBackground(Constants.NOT_QUITE_BLACK);
		tutorIDTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		tutorIDTxtfield.setFocusable(false);
		tutorIDTxtfield.setEnabled(false);
		tutorIDTxtfield.setRadius(10);
		tutorIDTxtfield.setBounds(165, 70, 210, 30);
		contentPanel.add(tutorIDTxtfield);

		tutorNameLabel = new JLabel("Name :");
		tutorNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		tutorNameLabel.setForeground(Constants.TEXT_COLOR);
		tutorNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		tutorNameLabel.setBounds(30, 140, 125, 30);
		contentPanel.add(tutorNameLabel);

		nameTxtfield = new CustomTextField();
		nameTxtfield.setForeground(new Color(163, 166, 170));
		nameTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		nameTxtfield.setColor(Constants.DEEP_DARK);
		nameTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		nameTxtfield.setRadius(10);
		nameTxtfield.setBounds(165, 140, 210, 30);
		contentPanel.add(nameTxtfield);

		passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLabel.setForeground(Constants.TEXT_COLOR);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		passwordLabel.setBounds(30, 210, 125, 30);
		contentPanel.add(passwordLabel);

		passwordTxtfield = new CustomTextField();
		passwordTxtfield.setRadius(10);
		passwordTxtfield.setForeground(new Color(163, 166, 170));
		passwordTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		passwordTxtfield.setColor(Constants.DEEP_DARK);
		passwordTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		passwordTxtfield.setBounds(165, 210, 210, 30);
		contentPanel.add(passwordTxtfield);

		emailLabel = new JLabel("Email :");
		emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		emailLabel.setForeground(Constants.TEXT_COLOR);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		emailLabel.setBounds(30, 280, 125, 30);
		contentPanel.add(emailLabel);

		emailTxtfield = new CustomTextField();
		emailTxtfield.setForeground(new Color(163, 166, 170));
		emailTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		emailTxtfield.setColor(Constants.DEEP_DARK);
		emailTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		emailTxtfield.setRadius(10);
		emailTxtfield.setBounds(165, 280, 210, 30);
		contentPanel.add(emailTxtfield);

		contactLabel = new JLabel("Contact :");
		contactLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		contactLabel.setForeground(Constants.TEXT_COLOR);
		contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
		contactLabel.setBounds(30, 350, 125, 30);
		contentPanel.add(contactLabel);

		contactTxtfield = new CustomTextField();
		contactTxtfield.setForeground(new Color(163, 166, 170));
		contactTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		contactTxtfield.setColor(Constants.DEEP_DARK);
		contactTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		contactTxtfield.setRadius(10);
		contactTxtfield.setBounds(165, 350, 210, 30);
		contentPanel.add(contactTxtfield);

		addressLabel = new JLabel("Address :");
		addressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addressLabel.setForeground(Constants.TEXT_COLOR);
		addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
		addressLabel.setBounds(30, 420, 125, 30);
		contentPanel.add(addressLabel);

		addressTxtfield = new CustomTextField();
		addressTxtfield.setMargin(new Insets(5, 5, 5, 5));
		addressTxtfield.setForeground(new Color(163, 166, 170));
		addressTxtfield.setFont(new Font("Arial", Font.BOLD, 16));
		addressTxtfield.setColor(Constants.DEEP_DARK);
		addressTxtfield.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		addressTxtfield.setRadius(10);
		addressTxtfield.setBounds(165, 420, 210, 30);
		contentPanel.add(addressTxtfield);

		JPanel panel = new JPanel();
		panel.setBounds(385, 70, 300, 380);
		panel.setBackground(Constants.CANVAS_COLOR);
		contentPanel.add(panel);
		panel.setLayout(null);

		loadSubjectsFromManager(); // Call before using subjectListModel

		// Initialize components
		levelComboBox = new JComboBox<>();
		levelComboBox.setLocation(0, 30);
		levelComboBox.setSize(300, 30);
		levelComboBox.addItem("- Select Level -");
		levelComboBox.setSelectedIndex(-1);

		// Map levels
		for (Level level : tutorManager.getAllLevels()) {
			levelComboBox.addItem(level.getLevel());
			levelNameToIDMap.put(level.getLevel(), level.getLevelID());
		}
		
		// Add level selection listener
		levelComboBox.addActionListener(this);

		// Add components to your panel/frame
		panel.add(levelComboBox);

		JLabel subjectLabel = new JLabel("Choose up to 3 Subjects:");
		subjectLabel.setForeground(Constants.TEXT_COLOR);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
		subjectLabel.setBounds(0, 70, 300, 30);

		panel.add(subjectLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 120, 300, 261);
		panel.add(scrollPane);

		subjectList = new JList<>(subjectListModel);
		scrollPane.setViewportView(subjectList);
		subjectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		subjectList.setFont(new Font("Arial", Font.PLAIN, 14));
		subjectList.setBackground(Constants.DEEP_DARK);
		subjectList.setForeground(Constants.TEXT_COLOR);
		subjectList.setBorder(new MatteBorder(1, 1, 1, 1, Constants.SLATE));

		JLabel reminderLbl = new JLabel("CTRL + Click to select multiple!");
		reminderLbl.setForeground(Constants.BLURPLE);
		reminderLbl.setFont(new Font("Arial", Font.BOLD, 10));
		reminderLbl.setBounds(0, 100, 300, 10);
		panel.add(reminderLbl);

		levelLabel = new JLabel("Select tutor level");
		levelLabel.setForeground(Color.WHITE);
		levelLabel.setFont(new Font("Arial", Font.BOLD, 16));
		levelLabel.setBounds(0, 0, 300, 30);
		panel.add(levelLabel);

		subjectList.addListSelectionListener(e -> {
		    if (subjectList.getSelectedIndices().length > 3) {
		        JOptionPane.showMessageDialog(this, "You can only select up to 3 subjects.", "Limit Exceeded",
		                JOptionPane.WARNING_MESSAGE);
		        subjectList.clearSelection();
		    }
		});


		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBorder(new MatteBorder(0, 5, 5, 5, (Color) Constants.SLATE));
		buttonPane.setPreferredSize(new Dimension(10, 50));
		buttonPane.setBackground(Constants.CANVAS_COLOR);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		createTutorBtn = new CustomJButton();
		createTutorBtn.setText("Create Tutor");
		createTutorBtn.setFont(new Font("Arial", Font.BOLD, 14));
		createTutorBtn.setBackground(Constants.BLURPLE);
		createTutorBtn.setColor(Constants.BLURPLE);
		createTutorBtn.setColorOver(Constants.BLURPLE_HOVER);
		createTutorBtn.setColorClick(Constants.BLURPLE_CLICK);
		createTutorBtn.setForeground(Constants.TEXT_COLOR);
		createTutorBtn.setBorderColor(Constants.DARK_BUT_NOT_BLACK);
		createTutorBtn.setPreferredSize(new Dimension(160, 40));
		createTutorBtn.setFocusPainted(false);
		createTutorBtn.setRadius(8);
		createTutorBtn.addActionListener(this);
		buttonPane.add(createTutorBtn);

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

		updateAddTutorsFrame();
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

			JLabel title = new JLabel("Add Tutor");
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
					int thisX = AddTutorFrame.this.getLocation().x;
					int thisY = AddTutorFrame.this.getLocation().y;
					int xMoved = e.getX() - initialClick.x;
					int yMoved = e.getY() - initialClick.y;
					AddTutorFrame.this.setLocation(thisX + xMoved, thisY + yMoved);
				}
			});
		}
	}

	public void updateAddTutorsFrame() {
		int nextAvailableID = tutorManager.getFirstAvailableTutorID();
		tutorIDTxtfield.setText(String.valueOf(nextAvailableID));
	}

	private boolean checkTutorDetails() {
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

	private void loadSubjectsFromManager() {
		subjectListModel = new DefaultListModel<>();
		for (Subject subject : tutorManager.getAllSubjects()) {
			String subjectName = subject.getSubjectName();
			subjectListModel.addElement(subjectName);
			subjectNameToIDMap.put(subjectName, subject.getSubjectID());
		}
	}

	private void createNewTutor() {
		int firstAvailableUserID = userManager.getFirstAvailableUserID();
		int firstAvailableTutorID = tutorManager.getFirstAvailableTutorID();
		String name = nameTxtfield.getText();
		String password = passwordTxtfield.getText();
		String contact = contactTxtfield.getText();
		String email = emailTxtfield.getText();
		String address = addressTxtfield.getText();
		String role = "tutor";

		java.util.List<String> selectedNames = subjectList.getSelectedValuesList();
		if (selectedNames.size() == 0 || selectedNames.size() > 3) {
			JOptionPane.showMessageDialog(this, "Please select 1 to 3 subjects.", "Subject Selection Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Convert subject names to IDs
		int subjectID1 = 0, subjectID2 = 0, subjectID3 = 0;
		if (selectedNames.size() >= 1)
			subjectID1 = subjectNameToIDMap.getOrDefault(selectedNames.get(0), 0);
		if (selectedNames.size() >= 2)
			subjectID2 = subjectNameToIDMap.getOrDefault(selectedNames.get(1), 0);
		if (selectedNames.size() >= 3)
			subjectID3 = subjectNameToIDMap.getOrDefault(selectedNames.get(2), 0);
		
		String selectedLevel = (String) levelComboBox.getSelectedItem();
		int assignedLevelID = levelNameToIDMap.getOrDefault(selectedLevel, 1);

		User tutorUserAccount = new User(firstAvailableUserID, name, password, role);
		Tutor tutorAccount = new Tutor(firstAvailableTutorID, firstAvailableUserID, contact, email, address,
				assignedLevelID, subjectID1, subjectID2, subjectID3);

		tutorManager.addTutor(tutorAccount, tutorUserAccount);
		userManager.saveUsers(Constants.USERS_CSV);
		tutorManager.saveTutors(Constants.TUTORS_CSV);
	}

	// Level section
	private void updateSubjectListForLevel(int levelID) {
		subjectListModel.clear();
		for (Subject subject : tutorManager.getAllSubjects()) {
			if (subject.getLevelID() == levelID) {
				subjectListModel.addElement(subject.getSubjectName());
			}
		}
	}

	// ============= Button Action Listeners ================
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			JOptionPane.showMessageDialog(this, "Cancelled Tutor Creation", "Action Cancelled",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}

		if (e.getSource() == createTutorBtn) {
			if (!checkTutorDetails()) {
				return;
			}
			// Only show this message if tutor details are fully valid
			JOptionPane.showMessageDialog(this, "Tutor created", "Successfully Created Tutor",
					JOptionPane.INFORMATION_MESSAGE);

			// Add a new line in users.csv and also tutors.csv for the new
			// tutor created
			createNewTutor();
			this.dispose();

		}

		if (e.getSource() == levelComboBox) {
		    String selectedLevel = (String) levelComboBox.getSelectedItem();
		    if (selectedLevel == null || !levelNameToIDMap.containsKey(selectedLevel)) {
		        JOptionPane.showMessageDialog(this, "Please select a level.", "Missing Level", JOptionPane.WARNING_MESSAGE);
		        return;
		    }
		    int levelID = levelNameToIDMap.get(selectedLevel);
		    updateSubjectListForLevel(levelID);
		}
	}
}
