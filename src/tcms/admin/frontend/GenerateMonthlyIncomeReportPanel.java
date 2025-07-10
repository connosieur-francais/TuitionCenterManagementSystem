package tcms.admin.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GenerateMonthlyIncomeReportPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel reportContainer;
	private JButton monthSelectBtn;
	private JButton yearSelectBtn;
	private JButton generateReportBtn;
	private JButton downloadBtn;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	
	private double income;
	private String month;
	private int year;

	private JPopupMenu yearSelectionMenu;
	private JPopupMenu monthSelectionMenu;
	private JMenuItem item1, item2;

	public GenerateMonthlyIncomeReportPanel() {
		setBackground(new Color(35, 39, 42));
		setSize(1186, 628);
		setLayout(null);

		// Create the button and Popup menu for month selection

		monthSelectBtn = new JButton("Select Month");
		monthSelectBtn.setBounds(10, 70, 125, 25);
		monthSelectBtn.setFocusPainted(false);
		monthSelectBtn.addActionListener(this);
		add(monthSelectBtn);

		monthSelectionMenu = createMonthSelectionMenu(); // Creates a popup menu
		add(monthSelectionMenu);

		yearSelectBtn = new JButton("Select Year");
		yearSelectBtn.setBounds(145, 70, 125, 25);
		yearSelectBtn.setFocusPainted(false);
		yearSelectBtn.addActionListener(this);
		add(yearSelectBtn);

		yearSelectionMenu = createYearSelectionMenu(); // Creates another popup menu for year
		add(yearSelectionMenu);

		lblNewLabel = new JLabel("View Monthly Income Report");
		lblNewLabel.setForeground(new Color(220, 221, 222));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 32));
		lblNewLabel.setBounds(10, 10, 445, 50);
		add(lblNewLabel);

		generateReportBtn = new JButton("Generate Report");
		generateReportBtn.setBounds(280, 70, 175, 25);
		add(generateReportBtn);

		downloadBtn = new JButton("Download Report");
		downloadBtn.setBounds(1026, 70, 150, 25);
		add(downloadBtn);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(30, 33, 36));
		scrollPane.setBounds(10, 105, 1166, 513);
		add(scrollPane);

		reportContainer = new JPanel();
		reportContainer.setBackground(new Color(54, 57, 63));
		reportContainer.setLayout(new BoxLayout(reportContainer, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(reportContainer);

		addHeaderSection(month, year, income);
		addBodySections();
	}
	
	private double getTotalIncome() {
		
	}

	private JPopupMenu createMonthSelectionMenu() {
		monthSelectionMenu = new JPopupMenu();
		monthSelectionMenu.setBackground(new Color(35, 39, 42));
		monthSelectionMenu.setBorder(null);

		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		for (String month : months) {
			item1 = new JMenuItem(month);
			item1.setBorder(null);
			item1.setForeground(new Color(220, 221, 222));
			item1.setBackground(new Color(54, 57, 63));
			item1.setFont(new Font("Arial", Font.BOLD, 12));

			// Add a hover effect
			item1.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JMenuItem source = (JMenuItem) e.getSource();
					if (source.isArmed()) {
						source.setBackground(new Color(88, 101, 242));
					} else {
						source.setBackground(new Color(54, 57, 63));
					}
				}
			});
			// Add action listener to each of the menu items
			item1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					monthSelectBtn.setText(month);
				}
			});
			monthSelectionMenu.add(item1);
		}
		return monthSelectionMenu;
	}

	private JPopupMenu createYearSelectionMenu() {

		// Create the popup menu
		yearSelectionMenu = new JPopupMenu();
		yearSelectionMenu.setBackground(new Color(35, 39, 42));
		yearSelectionMenu.setBorder(null);

		// Get current year
		LocalDate today = LocalDate.now();
		year = today.getYear();

		// Initiate a list of current year down to 2020
		ArrayList<String> years = new ArrayList<String>();
		for (int i = year; i >= 2020; i--) {
			years.add(String.valueOf(i));
		}

		// Create a JMenuItem for each year in the list
		for (String year : years) {
			item2 = new JMenuItem(year);
			item2.setBorder(null);
			item2.setForeground(new Color(220, 221, 222));
			item2.setBackground(new Color(54, 57, 63));
			item2.setFont(new Font("Arial", Font.BOLD, 12));

			item2.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JMenuItem source = (JMenuItem) e.getSource();
					if (source.isArmed()) {
						source.setBackground(new Color(88, 101, 242));
					} else {
						source.setBackground(new Color(54, 57, 63));
					}
				}
			});
			
			item2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					yearSelectBtn.setText(year);
				}
			});
			yearSelectionMenu.add(item2);
		}
		return yearSelectionMenu;
	}

	private void addHeaderSection(String month, int year, double income) {
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(54, 57, 63));
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Income Statement");
		titleLabel.setBounds(10, 10, 358, 33);
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		headerPanel.add(titleLabel);

		JLabel subtitleLabel = new JLabel();
		subtitleLabel.setText(String.format("for the month ended %s, %s", month, String.valueOf(year)));
		subtitleLabel.setBounds(10, 43, 430, 22);
		subtitleLabel.setForeground(new Color(220, 221, 222));
		subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		headerPanel.add(subtitleLabel);

		JLabel totalLabel = new JLabel();
		totalLabel.setText(String.format("Total Income: %s", income));
		totalLabel.setBounds(10, 65, 242, 24);
		totalLabel.setForeground(new Color(0, 255, 128));
		totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
		headerPanel.add(totalLabel);

		reportContainer.add(headerPanel);
	}

	private void addBodySections() {
		JPanel subjectBreakdownPanel = new JPanel();
		subjectBreakdownPanel.setBackground(new Color(64, 68, 75));
		subjectBreakdownPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		subjectBreakdownPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Breakdown by Subject");
		titleLabel.setBounds(10, 10, 199, 22);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setForeground(new Color(255, 255, 255));
		subjectBreakdownPanel.add(titleLabel);

		JLabel placeholder = new JLabel(" - data goes here -");
		placeholder.setBounds(10, 32, 86, 13);
		placeholder.setForeground(new Color(200, 200, 200));
		subjectBreakdownPanel.add(placeholder);

		subjectBreakdownPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel levelBreakdownPanel = new JPanel();
		levelBreakdownPanel.setBackground(new Color(64, 68, 75));
		levelBreakdownPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		levelBreakdownPanel.setLayout(null);

		JLabel levelBreakdownLabel = new JLabel("Breakdown By Level");
		levelBreakdownLabel.setBounds(10, 10, 199, 22);
		levelBreakdownLabel.setFont(new Font("Arial", Font.BOLD, 18));
		levelBreakdownLabel.setForeground(new Color(255, 255, 255));
		levelBreakdownPanel.add(levelBreakdownLabel);

		JLabel test = new JLabel(" - data goes here -");
		test.setBounds(10, 32, 86, 13);
		test.setForeground(new Color(200, 200, 200));
		levelBreakdownPanel.add(test);

		levelBreakdownPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		reportContainer.add(subjectBreakdownPanel);
		reportContainer.add(levelBreakdownPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == yearSelectBtn) {
			yearSelectionMenu.setPreferredSize(new Dimension(yearSelectBtn.getWidth(), yearSelectionMenu.getPreferredSize().height));
			yearSelectionMenu.show(yearSelectBtn, 0, yearSelectBtn.getHeight());
		}
		if (e.getSource() == monthSelectBtn) {
			monthSelectionMenu.setPreferredSize(new Dimension(monthSelectBtn.getWidth(), monthSelectionMenu.getPreferredSize().height));
			monthSelectionMenu.show(monthSelectBtn, 0, monthSelectBtn.getHeight());
		}
	}
}