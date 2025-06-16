package tcms.admin;
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

import tcms.users.User;

import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class AdminPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame = new JFrame();
	
	public static void main(String[] args) {
		new AdminPage(new User("eason","admin","active"));
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
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(new Color(7, 15, 43));
		sidebarPanel.setBounds(0, 0, 150, 413);
		contentPane.add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
		JLabel atcBannerLabel = new JLabel("New label");
		atcBannerLabel.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\atcBanner.png"));
		atcBannerLabel.setBounds(0, 10, 150, 75);
		sidebarPanel.add(atcBannerLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 255, 255));
		separator.setBounds(0, 80, 150, 5);
		sidebarPanel.add(separator);
		
		JButton updateProfileBtn = new JButton("<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<p>Update</p>\r\n<p>Profile</p>\r\n</body>\r\n</html>");
		updateProfileBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
		updateProfileBtn.addActionListener(this);
		updateProfileBtn.setBounds(10, 80, 130, 60);
		sidebarPanel.add(updateProfileBtn);
		
		JButton manageTutorsButton = new JButton("<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>Manage</p>\r\n<p>Tutors</p>\r\n</body>\r\n</html>");
		manageTutorsButton.setFont(new Font("SansSerif", Font.BOLD, 10));
		manageTutorsButton.setBounds(10, 165, 130, 60);
		sidebarPanel.add(manageTutorsButton);
		
		JButton manageReceptionistsBtn = new JButton("<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<p>Manage</p>\r\n<p>Receptionists</p>\r\n</body>\r\n</html>");
		manageReceptionistsBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
		manageReceptionistsBtn.setBounds(10, 255, 130, 60);
		sidebarPanel.add(manageReceptionistsBtn);
		
		JButton viewIncomeBtn = new JButton("<html>\r\n<head>\r\n<style>\r\np {text-align: center;}\r\n</style>\r\n</head>\r\n<body>\r\n<p>View Monthly</p>\r\n<p>Income Report</p>\r\n</body>\r\n</html>");
		viewIncomeBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
		viewIncomeBtn.setBounds(10, 345, 130, 60);
		sidebarPanel.add(viewIncomeBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(27, 26, 85));
		panel_1.setBounds(149, 0, 537, 413);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
