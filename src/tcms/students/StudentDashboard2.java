package tcms.students;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tcms.users.User;
import tcms.users.UserManager;

public class StudentDashboard2 extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static UserManager um = new UserManager();
	private static StudentManager sm = new StudentManager();
	private static User user;
	private static Student student;
	
	private JButton notificationsBtn, logoutBtn;
	
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
					new StudentDashboard2(new User("Gracious", "123", "student"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentDashboard2(User u) {
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
		contentPane.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1186, 60);
		contentPane.add(headerPanel);
		
		JLabel usernameLabel = new JLabel("<dynamic>");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));
		
		JLabel idLabel = new JLabel("| ID:");
		idLabel.setFont(new Font("Arial", Font.BOLD, 24));
		
		JLabel userIDLabel = new JLabel("<dynamic>");
		userIDLabel.setFont(new Font("Arial", Font.BOLD, 24));
		
		notificationsBtn = new JButton("🔔");
		notificationsBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		notificationsBtn.setFocusable(false);
		notificationsBtn.setBackground(blueAcc);
		notificationsBtn.setForeground(whiteText);
		notificationsBtn.addActionListener(this);
		notificationsBtn.setFocusPainted(false);
		
		logoutBtn = new JButton("⍈ Logout");
		logoutBtn.setFont(new Font("Arial", Font.BOLD, 16));
		logoutBtn.setBackground(blueAcc);
		logoutBtn.setForeground(whiteText);
		logoutBtn.setFocusPainted(false);
		
		GroupLayout gl_headerPanel = new GroupLayout(headerPanel);
		gl_headerPanel.setHorizontalGroup(
			gl_headerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPanel.createSequentialGroup()
					.addGap(540)
					.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(userIDLabel)
					.addGap(11)
					.addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
		);
		gl_headerPanel.setVerticalGroup(
			gl_headerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(notificationsBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(userIDLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		headerPanel.setLayout(gl_headerPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == notificationsBtn) {
			JOptionPane.showMessageDialog(this, "No new notifications.");
		}
	}
}
