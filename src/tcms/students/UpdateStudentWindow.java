package tcms.students;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;
import tcms.students.subjectView;


public class UpdateStudentWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel MainPanel;
	private JPanel BottomLeft;

	private JLabel usernameLabel, studentIDLabel, profilePictureLabel, email, contact, subjectLabel, level;
	private JButton notificationsBtn, logoutBtn, nextBtn, previousBtn;

	private static UserManager um = new UserManager();
	private static StudentManager sm = new StudentManager();
	private static User user;
	private static Student student;

	private String userCSVFile = Constants.USERS_CSV;
	private String studentCSVFile = Constants.STUDENTS_CSV;
	private String subjectCSVFile = Constants.SUBJECTS_CSV;
	
	private Color darkGrey = new Color(34, 34, 34);
	private Color blueAcc = new Color(70, 130, 180);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentWindow frame = new UpdateStudentWindow(
							new User(2, "Gracious", "123", "student", 3, "active"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateStudentWindow(User u) {

		sm.loadStudents(studentCSVFile);
		um.loadUsers(userCSVFile);

		user = u;
		student = sm.findStudentByUserID(u.getID());
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1002, 599);

		MainPanel = new JPanel();
		MainPanel.setBackground(darkGrey);
		MainPanel.setForeground(darkGrey);
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		MainPanel.setLayout(null);

		JLabel Title = new JLabel("PROFILE");
		Title.setForeground(Color.WHITE);
		Title.setFont(new Font("Arial Black", Font.BOLD, 20));
		Title.setBounds(425, 0, 101, 48);
		MainPanel.add(Title);

		JLabel PFP = new JLabel("");
		PFP.setBounds(70, 99, 282, 199);
		MainPanel.add(PFP);
		ImageIcon x = new ImageIcon(UpdateStudentWindow.class.getResource("/tcms/resources/blank_profile.jpg"));
		Image i = x.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
		ImageIcon pfp = new ImageIcon(i);
		PFP.setIcon(pfp);
		PFP.setFont(new Font("Arial Black", Font.BOLD, 14));
		PFP.setForeground(Color.WHITE);

		// BottomLeft panel 
		BottomLeft = new JPanel();
		BottomLeft.setLayout(new BoxLayout(BottomLeft, BoxLayout.Y_AXIS));
		BottomLeft.setBackground(darkGrey);
		BottomLeft.setBounds(70, 356, 282, 175);
		MainPanel.add(BottomLeft);

		// studentIDLabel
		studentIDLabel = new JLabel("<studentID>");
		studentIDLabel.setForeground(Color.WHITE);
		studentIDLabel.setFont(new Font("Arial", Font.BOLD, 22));
		studentIDLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		BottomLeft.add(studentIDLabel);

		BottomLeft.add(Box.createVerticalStrut(8)); 

		// usernameLabel
		usernameLabel = new JLabel("<username>");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		BottomLeft.add(usernameLabel);
		
		//SubjectLabel
		subjectLabel = new JLabel("<subject>");
		subjectLabel.setForeground(Color.WHITE);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 20));
		subjectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		BottomLeft.add(subjectLabel);

		
		updateStudentWindow(user, student);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public void updateStudentWindow(User u, Student s) {
		usernameLabel.setText("Username: " + u.getUsername());
		studentIDLabel.setText("Student ID: " + s.getStudentID());
		
		 String subject = subjectView.getSubjectByStudentId(s.getStudentID());
		    subjectLabel.setText("Subject: " + subject);
		
	}
}
