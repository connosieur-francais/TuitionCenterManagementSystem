package tcms.admin.frontend;

import java.awt.Dimension;
import java.awt.Font;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.receptionists.Payment;
import tcms.receptionists.ReceptionistManager;
import tcms.tutors.Subject;
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

	private CustomRoundedPanel breakdownByLevelPanel;
	private JLabel lblBreakdownByLevel;
	private JTable subjectTable;
	private JTable levelTable;

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
		
		subjectTable = new JTable();
		subjectTable.setBounds(10, 50, 550, 340);
		subjectTable.setBackground(Constants.SLATE);
		subjectTable.setForeground(Constants.TEXT_COLOR);
		breakdownBySubjectPanel.add(subjectTable);

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
		
		levelTable = new JTable();
		levelTable.setBounds(10, 50, 550, 340);
		levelTable.setBackground(Constants.SLATE);
		levelTable.setForeground(Constants.TEXT_COLOR);
		breakdownByLevelPanel.add(levelTable);
		
		// ========== Level Breakdown ============
		// Column Names for Breakdown By Level
		String[] levelColumnNames = { "Level Name", "Amount Paid (RM)" };

		// Step 1: Aggregate payments per level ID
		Map<Integer, Double> incomePerLevel = new HashMap<>();
		for (Payment payment : receptionistManager.getAllPayments()) {
		    LocalDate date = payment.getDate();
		    if (date.getYear() == year && date.getMonthValue() == monthStringToNumber(month)) {
		        int subjectID = payment.getSubjectID();
		        Subject subject = tutorManager.findSubjectBySubjectID(subjectID); // You must implement this method
		        int levelID = subject.getLevelID();
		        double amount = payment.getAmount();
		        incomePerLevel.put(levelID, incomePerLevel.getOrDefault(levelID, 0.0) + amount);
		    }
		}

		// Step 2: Convert to JTable format
		Object[][] levelTableData = new Object[incomePerLevel.size()][2];
		int rowL = 0;
		for (Map.Entry<Integer, Double> entry : incomePerLevel.entrySet()) {
		    int levelID = entry.getKey();
		    String levelName = tutorManager.findLevelNameByLevelID(levelID); // You must implement this
		    double totalAmount = entry.getValue();

		    levelTableData[rowL][0] = levelName;
		    levelTableData[rowL][1] = String.format("RM %.2f", totalAmount);
		    rowL++;
		}

		// Step 3: Build and set table model
		DefaultTableModel levelModel = new DefaultTableModel(levelTableData, levelColumnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		levelTable.setModel(levelModel);
		
		// ========== Subject Breakdown =============
		// Column Names
		String[] subjectColumnNames = { "Subject Name", "Amount Paid (RM)" };

		// Step 1: Aggregate payments per subject ID
		Map<Integer, Double> incomePerSubject = new HashMap<>();
		for (Payment payment : receptionistManager.getAllPayments()) {
		    LocalDate date = payment.getDate();
		    if (date.getYear() == year && date.getMonthValue() == monthStringToNumber(month)) {
		        int subjectID = payment.getSubjectID();
		        double amount = payment.getAmount();
		        incomePerSubject.put(subjectID, incomePerSubject.getOrDefault(subjectID, 0.0) + amount);
		    }
		}

		// Step 2: Convert to JTable format
		Object[][] subjectTableData = new Object[incomePerSubject.size()][2];
		int row = 0;
		for (Map.Entry<Integer, Double> entry : incomePerSubject.entrySet()) {
		    int subjectID = entry.getKey();
		    Subject subject = tutorManager.findSubjectBySubjectID(subjectID); // You must implement this
		    String subjectName = subject.getSubjectName();
		    double totalAmount = entry.getValue();

		    subjectTableData[row][0] = subjectName;
		    subjectTableData[row][1] = String.format("RM %.2f", totalAmount);
		    row++;
		}

		// Step 3: Build and set table model
		DefaultTableModel subjectModel = new DefaultTableModel(subjectTableData, subjectColumnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		subjectTable.setModel(subjectModel);
	}
	
	public int monthStringToNumber(String monthName) {
		return switch (monthName.toLowerCase()) {
			case "january" -> 1;
			case "february" -> 2;
			case "march" -> 3;
			case "april" -> 4;
			case "may" -> 5;
			case "june" -> 6;
			case "july" -> 7;
			case "august" -> 8;
			case "september" -> 9;
			case "october" -> 10;
			case "november" -> 11;
			case "december" -> 12;
			default -> 0;
		};
	}
	
}
