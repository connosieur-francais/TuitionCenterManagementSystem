package tcms.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class AdminPageGenerateReport extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JFrame frame = new JFrame();
    private JPanel topBorder, leftBorder, bottomBorder, rightBorder;
    private JLabel monthEndedLabel, atcLogo, incomeStatementLabel, totalIncomeLabel;
    private JPanel incomeStatementPanel;
    private Color borderColor = new Color(44, 44, 122);

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
        incomeStatementPanel.setBackground(Color.WHITE);
        incomeStatementPanel.setLayout(null);
        contentPane.add(incomeStatementPanel, BorderLayout.CENTER);

        incomeStatementLabel = new JLabel("Income Statement");
        incomeStatementLabel.setBounds(45, 40, 302, 40);
        incomeStatementLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
        incomeStatementPanel.add(incomeStatementLabel);

        atcLogo = new JLabel("");
        atcLogo.setHorizontalAlignment(SwingConstants.RIGHT);
        atcLogo.setBounds(610, 40, 200, 70);
        atcLogo.setIcon(new ImageIcon("src//atcBannerWhite.png"));
        incomeStatementPanel.add(atcLogo);

        monthEndedLabel = new JLabel(String.format("for the month ended %s, %s", month, year));
        monthEndedLabel.setBounds(45, 85, 334, 20);
        monthEndedLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        incomeStatementPanel.add(monthEndedLabel);

        totalIncomeLabel = new JLabel();
        totalIncomeLabel.setBounds(45, 120, 600, 30);
        totalIncomeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        totalIncomeLabel.setForeground(new Color(0, 128, 0));
        incomeStatementPanel.add(totalIncomeLabel);

        double total = calculateTotalIncome(month, year);
        totalIncomeLabel.setText(String.format("Total Income: RM %.2f", total));

        JPanel breakdownPanel = createBreakdownPanel(month, year);
        breakdownPanel.setBounds(45, 170, 780, 460);
        incomeStatementPanel.add(breakdownPanel);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private double calculateTotalIncome(String monthName, String yearStr) {
        double total = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int targetMonth = LocalDate.parse("01/" + monthName + "/" + yearStr, DateTimeFormatter.ofPattern("dd/MMMM/yyyy")).getMonthValue();
        int targetYear = Integer.parseInt(yearStr);

        try (BufferedReader br = new BufferedReader(new FileReader("src/payments.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                try {
                    LocalDate paymentDate = LocalDate.parse(data[4].trim(), formatter);
                    if (paymentDate.getMonthValue() == targetMonth && paymentDate.getYear() == targetYear) {
                        total += Double.parseDouble(data[3].trim());
                    }
                } catch (DateTimeParseException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return total;
    }

    private JPanel createBreakdownPanel(String monthName, String yearStr) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(Color.WHITE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int targetMonth = LocalDate.parse("01/" + monthName + "/" + yearStr, DateTimeFormatter.ofPattern("dd/MMMM/yyyy")).getMonthValue();
        int targetYear = Integer.parseInt(yearStr);

        Map<String, Double> subjectIncomeMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/payments.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                try {
                    LocalDate paymentDate = LocalDate.parse(data[4].trim(), formatter);
                    if (paymentDate.getMonthValue() == targetMonth && paymentDate.getYear() == targetYear) {
                        String studentID = data[1].trim();
                        String subjectID = data[2].trim();
                        double amount = Double.parseDouble(data[3].trim());

                        String key = "Subject ID: " + subjectID + " | Student ID: " + studentID;
                        subjectIncomeMap.put(key, subjectIncomeMap.getOrDefault(key, 0.0) + amount);
                    }
                } catch (DateTimeParseException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Double> entry : subjectIncomeMap.entrySet()) {
            JLabel row = new JLabel(String.format("%s -> RM %.2f", entry.getKey(), entry.getValue()));
            row.setFont(new Font("SansSerif", Font.PLAIN, 14));
            panel.add(row);
        }

        return panel;
    }
}
