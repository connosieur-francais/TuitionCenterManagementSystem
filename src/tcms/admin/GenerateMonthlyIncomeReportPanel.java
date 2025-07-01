package tcms.admin;

import javax.swing.*;
import java.awt.*;

public class GenerateMonthlyIncomeReportPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel reportContainer;
    private JButton monthSelectBtn;
    private JButton yearSelectBtn;
    private JButton generateReportBtn;
    private JButton downloadBtn;
    private JLabel lblNewLabel;
    private JScrollPane scrollPane;

    public GenerateMonthlyIncomeReportPanel() {
        setBackground(new Color(35, 39, 42));
        setSize(1186, 628);
        setLayout(null);

        monthSelectBtn = new JButton("Select Month");
        monthSelectBtn.setBounds(10, 70, 125, 25);
        add(monthSelectBtn);

        yearSelectBtn = new JButton("Select Year");
        yearSelectBtn.setBounds(145, 70, 125, 25);
        add(yearSelectBtn);

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

        addHeaderSection();
        addBodySections();
    }

    private void addHeaderSection() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(54, 57, 63));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Income Statement");
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("for the month ended April 2024");
        subtitleLabel.setForeground(new Color(220, 221, 222));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        headerPanel.add(subtitleLabel);

        JLabel totalLabel = new JLabel("Total Income: RM 2330.00");
        totalLabel.setForeground(new Color(0, 255, 128));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(totalLabel);

        reportContainer.add(headerPanel);
    }

    private void addBodySections() {
        reportContainer.add(createSectionPanel("Breakdown by Subject"));
        reportContainer.add(createSectionPanel("Breakdown by Level"));
        reportContainer.add(createSectionPanel("Breakdown by Student"));
    }

    private JPanel createSectionPanel(String title) {
        JPanel section = new JPanel();
        section.setBackground(new Color(64, 68, 75));
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(255, 255, 255));
        section.add(titleLabel);

        JLabel placeholder = new JLabel(" - data goes here -");
        placeholder.setForeground(new Color(200, 200, 200));
        section.add(placeholder);

        section.setAlignmentX(LEFT_ALIGNMENT);
        return section;
    }
}