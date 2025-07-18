package tcms.admin.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tcms.receptionists.Payment;
import tcms.receptionists.ReceptionistManager;
import tcms.tutors.TutorManager;

public class GenerateMonthlyIncomeReportPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static ReceptionistManager receptionistManager;

	private JButton monthSelectBtn;
	private JButton yearSelectBtn;
	private JButton generateReportBtn;
	private JButton downloadBtn;
	private JLabel lblNewLabel;
	
	private double income;
	private String month;
	private int year;

	private JPopupMenu yearSelectionMenu;
	private JPopupMenu monthSelectionMenu;
	private JMenuItem item1, item2;

	public GenerateMonthlyIncomeReportPanel(ReceptionistManager rm, TutorManager tm) {
		receptionistManager = rm;
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 105, 1166, 513);
		add(panel);
		
		income = getTotalIncome();
	}
	
	private int monthStringToNumber(String monthName) {
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
	
	private double getTotalIncome() {
		if (month == null || year == 0) return 0;

		int selectedMonth = monthStringToNumber(month); // e.g. "January" → 1
		List<Payment> payments = receptionistManager.getAllPayments();
		double total = 0;

		for (Payment p : payments) {
			if (p.getDate().getMonthValue() == selectedMonth && p.getDate().getYear() == year) {
				total += p.getAmount();
			}
		}
		return total;
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
		if (e.getSource() == generateReportBtn) {
			// TO - DO
		}
	}
}