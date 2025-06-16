package tcms.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import tcms.users.User;

public class AdminPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JPanel headerPanel;
	private JButton updateProfileBtn, manageTutorsButton;
	private JButton manageReceptionistsBtn, viewIncomeBtn;
	private JLabel atcBannerLabel;
	
	private Color buttonColor = new Color(83, 92, 145);
	private Color buttonTxtColor = Color.white;
	private Font buttonFont = new Font("SansSerif", Font.BOLD, 12);

	public static void main(String[] args) {
		new AdminPage(new User("eason", "admin", "active"));
	}

	/**
	 * Create the frame.
	 */
	public AdminPage(User user) {
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

		atcBannerLabel = new JLabel("New label");
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
		manageTutorsButton.setBounds(276, 10, 116, 60);
		manageTutorsButton.setFocusable(false);
		headerPanel.add(manageTutorsButton);

		manageReceptionistsBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<p>Manage</p>\r\n<p>Receptionists</p>\r\n</body>\r\n</html>");
		manageReceptionistsBtn.setBackground(buttonColor);
		manageReceptionistsBtn.setForeground(buttonTxtColor);
		manageReceptionistsBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		manageReceptionistsBtn.setFont(buttonFont);
		manageReceptionistsBtn.setBounds(402, 10, 106, 60);
		manageReceptionistsBtn.setFocusable(false);
		headerPanel.add(manageReceptionistsBtn);

		viewIncomeBtn = new JButton(
				"<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>View Monthly</p>\r\n<p>Income Report</p>\r\n</body>\r\n</html>");
		viewIncomeBtn.setBackground(buttonColor);
		viewIncomeBtn.setForeground(buttonTxtColor);
		viewIncomeBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		viewIncomeBtn.setFont(buttonFont);
		viewIncomeBtn.setBounds(518, 10, 116, 60);
		viewIncomeBtn.setFocusable(false);
		headerPanel.add(viewIncomeBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(27, 26, 85));
		panel_1.setBounds(0, 78, 686, 335);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateProfileBtn) {
			//to do
		}
		if (e.getSource() == manageTutorsButton) {
			//to do
		}
		if (e.getSource() == manageReceptionistsBtn) {
			//to do
		}
		if (e.getSource() == viewIncomeBtn) {
			//to do
		}
	}
}
