package tcms.students;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;
import tcms.utils.Validators;

/**
 * Window that lets a student view / update their personal details.
 */
public class UpdateStudentWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final UserManager um;
	private final StudentManager sm;
	private final User user;
	private final Student student;

	private JPanel mainPanel;
	private JLabel usernameLabel;
	private JLabel studentIDLabel;
	private JLabel subjectLabel;
	private JTextField emailField;
	private JTextField contactField;
	private JButton saveButton;

	private static final Color DARK_GREY = new Color(34, 34, 34);
	private static final Color WHITE = Color.WHITE;

	public UpdateStudentWindow(User u, UserManager userManager, StudentManager studentManager) {
		this.um = userManager;
		this.sm = studentManager;
		this.user = u;
		this.student = sm.findStudentByUserID(u.getID());

		buildUi();
		populateDynamicFields();
	}

	private void buildUi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1002, 599);
		setTitle("Student | Edit Profile");

		mainPanel = new JPanel();
		mainPanel.setBackground(DARK_GREY);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		setContentPane(mainPanel);

		JLabel title = new JLabel("UPDATE PROFILE");
		title.setForeground(WHITE);
		title.setFont(new Font("Arial Black", Font.BOLD, 20));
		title.setBounds(373, 10, 250, 48);
		mainPanel.add(title);

		JLabel pfpLabel = new JLabel();
		pfpLabel.setBounds(28, 244, 282, 199);
		ImageIcon raw = new ImageIcon(Constants.STUDENT_USER_ICON_FILE);
		Image scaled = raw.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
		pfpLabel.setIcon(new ImageIcon(scaled));
		mainPanel.add(pfpLabel);

		JPanel bottomLeft = new JPanel();
		bottomLeft.setLayout(null);
		bottomLeft.setOpaque(false);
		bottomLeft.setBounds(400, 100, 500, 250);
		mainPanel.add(bottomLeft);

		emailField = new JTextField();
		emailField.setBounds(200, 21, 300, 45);
		bottomLeft.add(emailField);

		contactField = new JTextField();
		contactField.setBounds(200, 119, 300, 38);
		bottomLeft.add(contactField);

		saveButton = new JButton("Save Changes");
		saveButton.setBounds(340, 222, 160, 28);
		saveButton.addActionListener(this);
		bottomLeft.add(saveButton);

		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(10, 30, 102, 13);
		bottomLeft.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contact number");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(10, 131, 152, 26);
		bottomLeft.add(lblNewLabel_1);
		
		usernameLabel = new JLabel();
		usernameLabel.setBounds(28, 79, 259, 24);
		mainPanel.add(usernameLabel);
		usernameLabel.setForeground(WHITE);
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));

		studentIDLabel = new JLabel();
		studentIDLabel.setBounds(26, 129, 400, 26);
		mainPanel.add(studentIDLabel);
		studentIDLabel.setForeground(WHITE);
		studentIDLabel.setFont(new Font("Arial", Font.BOLD, 22));

		subjectLabel = new JLabel();
		subjectLabel.setBounds(26, 186, 400, 24);
		mainPanel.add(subjectLabel);
		subjectLabel.setForeground(WHITE);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 20));

		JButton backButton = new JButton("Back to Dashboard");
		backButton.setBounds(720, 476, 180, 28);
		mainPanel.add(backButton);
		backButton.addActionListener(e -> {
			new StudentDashboard(user, um, sm).setVisible(true);
			this.dispose();
		});
	}

	private void populateDynamicFields() {
		usernameLabel.setText("Username: " + user.getUsername());
		studentIDLabel.setText("Student ID: " + student.getStudentID());

		String subject = subjectView.getSubjectByStudentId(student.getStudentID());
		subjectLabel.setText("Subject: " + subject);

		emailField.setText(student.getEmail());
		contactField.setText(student.getContact());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			String newEmail = emailField.getText().trim();
			String newContact = contactField.getText().trim();

			if (!Validators.isValidEmail(newEmail)) {
				return;
			}
			

			if (!Validators.isValidContact(newContact)) {
				return;
			}
			
			student.setEmail(newEmail);
			student.setContact(newContact);

			sm.saveStudents(Constants.STUDENTS_CSV);
			um.saveUsers(Constants.USERS_CSV);

			JOptionPane.showMessageDialog(this, "Profile updated successfully.");
		}
	}

	public static void main(String[] args) {
		UserManager um = new UserManager();
		StudentManager sm = new StudentManager();
		um.loadUsers(Constants.USERS_CSV);
		sm.loadStudents(Constants.STUDENTS_CSV);

		User sampleUser = um.findUserByUsername("Gracious");
		if (sampleUser != null) {
			UpdateStudentWindow window = new UpdateStudentWindow(sampleUser, um, sm);
			window.setVisible(true);
		} else {
			System.out.println("Test user not found.");
		}
	}
}
