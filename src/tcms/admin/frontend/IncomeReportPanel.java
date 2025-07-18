package tcms.admin.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.receptionists.ReceptionistManager;
import tcms.tutors.TutorManager;
import tcms.utils.Constants;

public class IncomeReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static TutorManager tutorManager;
	private static ReceptionistManager receptionistManager;

	/**
	 * Create the panel.
	 */
	public IncomeReportPanel(double totalIncome, int year, int month, TutorManager tm, ReceptionistManager rm) {
		tutorManager = tm;
		receptionistManager = rm;
		
		setSize(new Dimension(1166, 513));
		setBackground(Constants.CANVAS_COLOR);
		setLayout(null);
		
		JLabel reportTitle = new JLabel("Income Report");
		reportTitle.setLabelFor(this);
		reportTitle.setFont(new Font("Arial", Font.BOLD, 24));
		reportTitle.setForeground(Constants.GREEN_BUTTON);
		reportTitle.setBounds(10, 10, 200, 30);
		add(reportTitle);
		
		JLabel reportSubtitle = new JLabel("For the month ended <month>, <year>");
		reportSubtitle.setLabelFor(this);
		reportSubtitle.setFont(new Font("Arial", Font.PLAIN, 20));
		reportSubtitle.setForeground(Constants.GREEN_BUTTON);
		reportSubtitle.setBounds(10, 40, 420, 30);
		add(reportSubtitle);
		
		CustomRoundedPanel breakdownBySubjectPanel = new CustomRoundedPanel();
		breakdownBySubjectPanel.setBackground(Constants.DARK_BUT_NOT_BLACK);
		breakdownBySubjectPanel.setRoundTopRight(20);
		breakdownBySubjectPanel.setRoundTopLeft(20);
		breakdownBySubjectPanel.setRoundBottomRight(20);
		breakdownBySubjectPanel.setRoundBottomLeft(20);
		breakdownBySubjectPanel.setBounds(10, 103, 570, 400);
		add(breakdownBySubjectPanel);
		breakdownBySubjectPanel.setLayout(null);
		
		JLabel lblBreakdownBySubject = new JLabel("Breakdown By Subject\r\n");
		lblBreakdownBySubject.setLabelFor(breakdownBySubjectPanel);
		lblBreakdownBySubject.setForeground(Constants.FUCHSIA);
		lblBreakdownBySubject.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBreakdownBySubject.setBounds(10, 10, 402, 30);
		breakdownBySubjectPanel.add(lblBreakdownBySubject);
		
		CustomRoundedPanel breakdownByLevelPanel = new CustomRoundedPanel();
		breakdownByLevelPanel.setRoundTopRight(20);
		breakdownByLevelPanel.setRoundTopLeft(20);
		breakdownByLevelPanel.setRoundBottomRight(20);
		breakdownByLevelPanel.setRoundBottomLeft(20);
		breakdownByLevelPanel.setBackground(new Color(44, 47, 51));
		breakdownByLevelPanel.setBounds(586, 103, 570, 400);
		add(breakdownByLevelPanel);
		breakdownByLevelPanel.setLayout(null);
		
		JLabel lblBreakdownByLevel = new JLabel("Breakdown By Level");
		lblBreakdownByLevel.setLabelFor(breakdownByLevelPanel);
		lblBreakdownByLevel.setForeground(new Color(235, 69, 158));
		lblBreakdownByLevel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBreakdownByLevel.setBounds(10, 10, 402, 30);
		breakdownByLevelPanel.add(lblBreakdownByLevel);
		
		JLabel totalIncomeLabel = new JLabel("Total Income: RM <dynamic>");
		totalIncomeLabel.setLabelFor(this);
		totalIncomeLabel.setForeground(Constants.BLURPLE);
		totalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		totalIncomeLabel.setBounds(10, 73, 420, 30);
		add(totalIncomeLabel);
		
	}
}
