package tcms.admin.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;

import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.receptionists.ReceptionistManager;
import tcms.tutors.TutorManager;
import tcms.utils.Constants;

public class IncomeReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static TutorManager tutorManager;
	private static ReceptionistManager receptionistManager;

	// Component variables declared at the top
	private JLabel reportTitle;
	private JLabel reportSubtitle;
	private JLabel totalIncomeLabel;
	private CustomRoundedPanel breakdownBySubjectPanel;
	private JLabel lblBreakdownBySubject;
	private JList subjectList;

	private CustomRoundedPanel breakdownByLevelPanel;
	private JLabel lblBreakdownByLevel;
	private JList levelList;

	/**
	 * Create the panel.
	 */
	public IncomeReportPanel(double totalIncome, int year, String month, TutorManager tm, ReceptionistManager rm) {
		tutorManager = tm;
		receptionistManager = rm;

		setSize(new Dimension(1166, 513));
		setBackground(Constants.CANVAS_COLOR);
		setLayout(null);

		// Report Title
		reportTitle = new JLabel("Income Report");
		reportTitle.setFont(new Font("Arial", Font.BOLD, 24));
		reportTitle.setForeground(Constants.GREEN_BUTTON);
		reportTitle.setBounds(10, 10, 200, 30);
		add(reportTitle);

		// Report Subtitle (with dynamic text)
		reportSubtitle = new JLabel(String.format("For the month ended %s, %s", month, year));
		reportSubtitle.setFont(new Font("Arial", Font.PLAIN, 20));
		reportSubtitle.setForeground(Constants.GREEN_BUTTON);
		reportSubtitle.setBounds(10, 40, 420, 30);
		add(reportSubtitle);

		// Total Income (dynamic)
		totalIncomeLabel = new JLabel(String.format("Total Income: RM %.2f", totalIncome));
		totalIncomeLabel.setForeground(Constants.BLURPLE);
		totalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		totalIncomeLabel.setBounds(10, 73, 420, 30);
		add(totalIncomeLabel);

		// Breakdown By Subject Panel
		breakdownBySubjectPanel = new CustomRoundedPanel();
		breakdownBySubjectPanel.setBackground(Constants.DARK_BUT_NOT_BLACK);
		breakdownBySubjectPanel.setRoundTopRight(20);
		breakdownBySubjectPanel.setRoundTopLeft(20);
		breakdownBySubjectPanel.setRoundBottomRight(20);
		breakdownBySubjectPanel.setRoundBottomLeft(20);
		breakdownBySubjectPanel.setBounds(10, 103, 570, 400);
		breakdownBySubjectPanel.setLayout(null);
		add(breakdownBySubjectPanel);

		lblBreakdownBySubject = new JLabel("Breakdown By Subject");
		lblBreakdownBySubject.setForeground(Constants.FUCHSIA);
		lblBreakdownBySubject.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBreakdownBySubject.setBounds(10, 10, 402, 30);
		breakdownBySubjectPanel.add(lblBreakdownBySubject);

		subjectList = new JList();
		subjectList.setBounds(10, 50, 550, 340);
		breakdownBySubjectPanel.add(subjectList);

		// Breakdown By Level Panel
		breakdownByLevelPanel = new CustomRoundedPanel();
		breakdownByLevelPanel.setBackground(Constants.DARK_BUT_NOT_BLACK);
		breakdownByLevelPanel.setRoundTopRight(20);
		breakdownByLevelPanel.setRoundTopLeft(20);
		breakdownByLevelPanel.setRoundBottomRight(20);
		breakdownByLevelPanel.setRoundBottomLeft(20);
		breakdownByLevelPanel.setBounds(586, 103, 570, 400);
		breakdownByLevelPanel.setLayout(null);
		add(breakdownByLevelPanel);

		lblBreakdownByLevel = new JLabel("Breakdown By Level");
		lblBreakdownByLevel.setForeground(Constants.FUCHSIA);
		lblBreakdownByLevel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBreakdownByLevel.setBounds(10, 10, 402, 30);
		breakdownByLevelPanel.add(lblBreakdownByLevel);

		levelList = new JList();
		levelList.setBounds(10, 50, 550, 340);
		breakdownByLevelPanel.add(levelList);
	}
}
