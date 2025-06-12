import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private JPanel accountPanel, buttonHolder;
	private JLabel userIDLabel, userID, usernameLabel, username, welcomeLabel;
	private JButton manageTutorsBtn, manageReceptionistsBtn, viewMonthlyIncomeBtn, updateProfileBtn;
	/**
	 * Create the frame.
	 */
	public AdminPage(User user) {
		frame.setResizable(false);
		frame.setTitle("Admin Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		accountPanel = new JPanel();
		accountPanel.setBounds(10, 10, 666, 393);
		contentPane.add(accountPanel);
		accountPanel.setLayout(null);

		userIDLabel = new JLabel("UserID: ");
		userIDLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		userIDLabel.setBounds(10, 10, 70, 20);
		accountPanel.add(userIDLabel);

		userID = new JLabel("unknown");
		userID.setFont(new Font("SansSerif", Font.BOLD, 16));
		userID.setBounds(80, 10, 45, 20);
		accountPanel.add(userID);

		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		usernameLabel.setBounds(10, 40, 100, 20);
		accountPanel.add(usernameLabel);

		username = new JLabel("unknown");
		username.setFont(new Font("SansSerif", Font.BOLD, 16));
		username.setBounds(99, 40, 100, 20);
		accountPanel.add(username);

		buttonHolder = new JPanel();
		buttonHolder.setBounds(10, 80, 646, 303);
		accountPanel.add(buttonHolder);
		buttonHolder.setLayout(new GridLayout(2, 2, 25, 25));

		manageTutorsBtn = new JButton("Manage Tutors");
		manageTutorsBtn.setIcon(new ImageIcon("src//tutor.png"));
		manageTutorsBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		manageTutorsBtn.setFocusable(false);
		manageTutorsBtn.setBackground(new Color(244, 164, 96));
		manageTutorsBtn.addActionListener(this);
		buttonHolder.add(manageTutorsBtn);

		manageReceptionistsBtn = new JButton("Mange Receptionists");
		manageReceptionistsBtn.setIcon(new ImageIcon("src//receptionist.png"));
		manageReceptionistsBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		manageReceptionistsBtn.setFocusable(false);
		manageReceptionistsBtn.setBackground(new Color(244, 164, 96));
		manageReceptionistsBtn.addActionListener(this);
		buttonHolder.add(manageReceptionistsBtn);

		viewMonthlyIncomeBtn = new JButton("View Monthly Income Report");
		viewMonthlyIncomeBtn.setIcon(new ImageIcon("src//incomereport.png"));
		viewMonthlyIncomeBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		viewMonthlyIncomeBtn.setFocusable(false);
		viewMonthlyIncomeBtn.setBackground(new Color(244, 164, 96));
		viewMonthlyIncomeBtn.addActionListener(this);
		buttonHolder.add(viewMonthlyIncomeBtn);

		updateProfileBtn = new JButton("Update Profile");
		updateProfileBtn.setIcon(new ImageIcon("src//profile.png"));
		updateProfileBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		updateProfileBtn.setFocusable(false);
		updateProfileBtn.setBackground(new Color(244, 164, 96));
		updateProfileBtn.addActionListener(this);
		buttonHolder.add(updateProfileBtn);

		welcomeLabel = new JLabel("Welcome to Admin Panel");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		welcomeLabel.setBounds(196, 17, 460, 43);
		accountPanel.add(welcomeLabel);
		
		String name = user.getUsername();
		String user_id = String.valueOf(user.getID());
		username.setText(name);
		userID.setText(user_id);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == manageTutorsBtn) {

		}
		if (e.getSource() == manageReceptionistsBtn) {

		}
		if (e.getSource() == viewMonthlyIncomeBtn) {

		}
		if (e.getSource() == updateProfileBtn) {
			frame.dispose();
			new AdminUpdateProfile();
		}

	}
}
