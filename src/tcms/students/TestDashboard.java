package tcms.students;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tcms.users.User;
import tcms.users.UserManager;

public class TestDashboard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private static UserManager um = new UserManager();
	private static StudentManager sm = new StudentManager();
	private static User user;
	private static Student student;

	private Color darkGrey = new Color(34, 34, 34);
	private Color blueAcc = new Color(70, 130, 180);
	private Color whiteText = Color.WHITE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TestDashboard(new User("Gracious", "123", "student"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestDashboard(User u) {
		user = u;
		String username = user.getUsername();
		String id = String.valueOf(user.getID());
		student = sm.findStudentByUserID(u.getID());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Dashboard");
		setBounds(100, 100, 1200, 750);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(darkGrey);

		setContentPane(contentPane);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
