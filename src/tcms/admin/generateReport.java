package tcms.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class generateReport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private JPanel topBorder;
	
	private Color borderColor = new Color(44, 44, 122);

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//				try {
//					generateReport frame = new generateReport("December", "2025");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public generateReport(String month, String year) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JPanel leftBorder = new JPanel();
		leftBorder.setBackground(borderColor);
		contentPane.add(leftBorder, BorderLayout.WEST);

		JPanel bottomBorder = new JPanel();
		bottomBorder.setBackground(borderColor);
		contentPane.add(bottomBorder, BorderLayout.SOUTH);

		JPanel rightBorder = new JPanel();
		rightBorder.setBackground(borderColor);
		contentPane.add(rightBorder, BorderLayout.EAST);

		JPanel incomeStatementPanel = new JPanel();
		incomeStatementPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(incomeStatementPanel, BorderLayout.CENTER);
		incomeStatementPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][]", "[][][]"));
		
		Component verticalStrut = Box.createVerticalStrut(25);
		incomeStatementPanel.add(verticalStrut, "cell 1 0");
		
		Component horizontalStrut = Box.createHorizontalStrut(25);
		incomeStatementPanel.add(horizontalStrut, "cell 0 1");
		
		JLabel lblNewLabel = new JLabel("Income Statement");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		incomeStatementPanel.add(lblNewLabel, "cell 1 1");
		
		Component rigidArea = Box.createRigidArea(new Dimension(300, 40));
		incomeStatementPanel.add(rigidArea, "cell 2 1 11 2");
		
		JLabel atcLogo = new JLabel("");
		atcLogo.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\TuitionCenterManagementSystem\\src\\atcBannerWhite.png"));
		incomeStatementPanel.add(atcLogo, "cell 13 1 1 2");
		
		JLabel lblNewLabel_1 = new JLabel(String.format("for the month ended %s, %s", month, year));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		incomeStatementPanel.add(lblNewLabel_1, "cell 1 2");

		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}

}
