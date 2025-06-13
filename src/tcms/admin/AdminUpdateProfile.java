package tcms.admin;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class AdminUpdateProfile extends JFrame implements ActionListener{

	private static final long serialVersionUID = 2L;
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JPanel panel;
	private JLabel updateProfileLabel;
	private JButton previousPageBtn;
	private JTextField changeUsernameTextField;
	/**
	 * Create the frame.
	 */
	public AdminUpdateProfile() {
		frame.setResizable(false);
		frame.setTitle("Update Admin Profile");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 218, 185));
		panel.setBounds(10, 10, 666, 393);
		contentPane.add(panel);
		panel.setLayout(null);
		
		updateProfileLabel = new JLabel("Updating Account Details");
		updateProfileLabel.setForeground(new Color(160, 82, 45));
		updateProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateProfileLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		updateProfileLabel.setBounds(83, 10, 500, 50);
		panel.add(updateProfileLabel);
		
		previousPageBtn = new JButton("<- Back to previous page");
		previousPageBtn.setBackground(new Color(224, 255, 255));
		previousPageBtn.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		previousPageBtn.addActionListener(this);
		previousPageBtn.setFocusable(false);
		previousPageBtn.setBounds(456, 362, 200, 21);
		panel.add(previousPageBtn);
		
		JLabel changeUsernameLabel = new JLabel("Username");
		changeUsernameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		changeUsernameLabel.setBounds(50, 70, 80, 30);
		panel.add(changeUsernameLabel);
		
		changeUsernameTextField = new JTextField();
		changeUsernameTextField.setBounds(140, 70, 460, 30);
		panel.add(changeUsernameTextField);
		changeUsernameTextField.setColumns(10);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == previousPageBtn) {
			new AdminPage(null);
			frame.dispose();
		}
		
	}
}
