package tcms.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class AdminPageGenerateReport extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private JPanel topBorder, leftBorder, bottomBorder, rightBorder;

	private JLabel monthEndedlabel, atcLogo;
	private JLabel incomeStatementLabel;
	private JPanel incomeStatementPanel;
	private Color borderColor = new Color(44, 44, 122);

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			new generateReport("December", "2025");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the frame.
	 */
	public AdminPageGenerateReport(String month, String year) {

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 900, 700);
		frame.setResizable(false);
		frame.setTitle(String.format("Monthly Income Report | For the month ended %s, %s", month, year));
		frame.setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout(0, 0));

		topBorder = new JPanel();
		topBorder.setBackground(borderColor);
		contentPane.add(topBorder, BorderLayout.NORTH);

		leftBorder = new JPanel();
		leftBorder.setBackground(borderColor);
		contentPane.add(leftBorder, BorderLayout.WEST);

		bottomBorder = new JPanel();
		bottomBorder.setBackground(borderColor);
		contentPane.add(bottomBorder, BorderLayout.SOUTH);

		rightBorder = new JPanel();
		rightBorder.setBackground(borderColor);
		contentPane.add(rightBorder, BorderLayout.EAST);

		incomeStatementPanel = new JPanel();
		incomeStatementPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		incomeStatementPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(incomeStatementPanel, BorderLayout.CENTER);
		incomeStatementPanel.setLayout(null);

		incomeStatementLabel = new JLabel("Income Statement");
		incomeStatementLabel.setBounds(45, 40, 302, 40);
		incomeStatementLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		incomeStatementPanel.add(incomeStatementLabel);

		atcLogo = new JLabel("");
		atcLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		atcLogo.setBounds(610, 40, 200, 70);
		atcLogo.setIcon(new ImageIcon("src//atcBannerWhite.png"));
		incomeStatementPanel.add(atcLogo);

		monthEndedlabel = new JLabel(String.format("for the month ended %s, %s", month, year));
		monthEndedlabel.setBounds(45, 85, 334, 20);
		monthEndedlabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		incomeStatementPanel.add(monthEndedlabel);

		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
