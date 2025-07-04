package tcms.students;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class UpdateStudentWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentWindow frame = new UpdateStudentWindow();
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
	public UpdateStudentWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 599);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 34, 34));
		contentPane.setForeground(new Color(34, 34, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("PROFILE");
		Title.setForeground(new Color(255, 255, 255));
		Title.setFont(new Font("Arial Black", Font.BOLD, 18));
		Title.setBounds(425, 0, 101, 48);
		contentPane.add(Title);
		
		JLabel PFP = new JLabel("");
		PFP.setBounds(61, 64, 258, 228);
		contentPane.add(PFP);
		ImageIcon x = new ImageIcon(UpdateStudentWindow.class.getResource("/tcms/resources/blank_profile"));
		Image i = x.getImage().getScaledInstance(275,275, Image.SCALE_SMOOTH);
		ImageIcon pfp = new ImageIcon(i);
		PFP.setIcon(pfp);
		PFP.setFont(new Font("Arial Black", Font.BOLD, 14));
		PFP.setForeground(new Color(255, 255, 255));

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
