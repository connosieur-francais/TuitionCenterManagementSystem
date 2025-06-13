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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 100, 413);
		contentPane.add(tabbedPane);
		
		String name = user.getUsername();
		String user_id = String.valueOf(user.getID());
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
