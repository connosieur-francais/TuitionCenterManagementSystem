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
import javax.swing.border.MatteBorder;

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
		title.setBounds(415, 10, 208, 48);
		mainPanel.add(title);

		JLabel pfpLabel = new JLabel();
		pfpLabel.setBounds(44, 77, 269, 199);
		ImageIcon raw = new ImageIcon(Constants.STUDENT_USER_ICON_FILE);
		Image scaled = raw.getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH);
		pfpLabel.setIcon(new ImageIcon(scaled));
		mainPanel.add(pfpLabel);

		JPanel bottomLeft = new JPanel();
		bottomLeft.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(41, 41, 41)));
		bottomLeft.setLayout(null);
		bottomLeft.setOpaque(false);
		bottomLeft.setBounds(403, 123, 506, 313);
		mainPanel.add(bottomLeft);

		emailField = new JTextField();
		emailField.setBounds(190, 112, 300, 56);
		bottomLeft.add(emailField);

		contactField = new JTextField();
		contactField.setBounds(190, 37, 300, 51);
		bottomLeft.add(contactField);

		saveButton = new JButton("Save Changes");
		saveButton.setBackground(new Color(125, 185, 117));
		saveButton.setBounds(347, 199, 143, 28);
		saveButton.addActionListener(this);
		bottomLeft.add(saveButton);

		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(98, 125, 64, 13);
		bottomLeft.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contact number");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 52, 152, 26);
		bottomLeft.add(lblNewLabel_1);

		JButton backButton = new JButton("<~Back to Dashboard");
		backButton.setBackground(new Color(167, 167, 167));
		backButton.setBounds(727, 486, 180, 48);
		mainPanel.add(backButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(41, 41, 41));
		panel.setBounds(44, 310, 269, 199);
		mainPanel.add(panel);
		panel.setLayout(null);
		
				subjectLabel = new JLabel();
				subjectLabel.setBounds(10, 161, 282, 24);
				panel.add(subjectLabel);
				subjectLabel.setForeground(WHITE);
				subjectLabel.setFont(new Font("Arial", Font.BOLD, 20));
				
						studentIDLabel = new JLabel();
						studentIDLabel.setBounds(13, 111, 259, 26);
						panel.add(studentIDLabel);
						studentIDLabel.setForeground(WHITE);
						studentIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
						
						usernameLabel = new JLabel();
						usernameLabel.setBounds(10, 62, 259, 24);
						panel.add(usernameLabel);
						usernameLabel.setForeground(WHITE);
						usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Your Information");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(63, 0, 176, 31);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(41, 41, 41));
		panel_1.setBounds(346, 77, 10, 432);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
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
