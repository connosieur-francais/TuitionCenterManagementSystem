package tcms.custom_gui_components;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class LoginPageTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private GroupLayout gl_contentPane;
	CustomRoundedPanel customRoundedPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginPageTest();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPageTest() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1258, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		
		customRoundedPanel = new CustomRoundedPanel();
		customRoundedPanel.setBackground(new Color(89, 172, 255));
		customRoundedPanel.addColor(new ModelColor(Color.BLUE, 0f), new ModelColor(Color.PINK, 1f));
		customRoundedPanel.setRoundTopRight(50);
		customRoundedPanel.setRoundTopLeft(50);
		customRoundedPanel.setRoundBottomRight(50);
		customRoundedPanel.setRoundBottomLeft(50);
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(218)
					.addComponent(customRoundedPanel, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
					.addGap(252))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(82)
					.addComponent(customRoundedPanel, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addGap(83))
		);
		contentPane.setLayout(gl_contentPane);
		
		frame.setVisible(true);
	}

}
