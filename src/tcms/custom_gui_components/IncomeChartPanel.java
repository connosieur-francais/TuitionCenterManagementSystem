package tcms.custom_gui_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class IncomeChartPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int selectedMonth = 1;
    private int selectedYear = 2024;
    private ChartPanel chartPanel;
    private Timer autoSwitchTimer;

    public IncomeChartPanel() {
        setBounds(600, 10, 576, 380);
        setLayout(null);
        setBackground(new Color(35, 39, 42));

        chartPanel = createChartPanel();
        chartPanel.setBounds(0, 0, 576, 340);
        add(chartPanel);

        JButton monthButton = new JButton("Month");
        monthButton.setBounds(10, 345, 80, 25);
        styleButton(monthButton);
        add(monthButton);

        JPopupMenu monthMenu = new JPopupMenu();
        for (int i = 1; i <= 12; i++) {
            int month = i;
            JMenuItem item = new JMenuItem(String.format("%02d", month));
            styleMenuItem(item);
            item.addActionListener(e -> {
                selectedMonth = month;
                refreshChart();
            });
            monthMenu.add(item);
        }
        monthMenu.setPreferredSize(new Dimension(monthButton.getWidth(), monthMenu.getPreferredSize().height));
        monthButton.addActionListener(e -> monthMenu.show(monthButton, 0, monthButton.getHeight()));

        JButton yearButton = new JButton("Year");
        yearButton.setBounds(100, 345, 80, 25);
        styleButton(yearButton);
        add(yearButton);

        JPopupMenu yearMenu = new JPopupMenu();
        for (int i = 2020; i <= LocalDate.now().getYear(); i++) {
            int year = i;
            JMenuItem item = new JMenuItem(String.valueOf(year));
            styleMenuItem(item);
            item.addActionListener(e -> {
                selectedYear = year;
                refreshChart();
            });
            yearMenu.add(item);
        }
        yearMenu.setPreferredSize(new Dimension(yearButton.getWidth(), yearMenu.getPreferredSize().height));
        yearButton.addActionListener(e -> yearMenu.show(yearButton, 0, yearButton.getHeight()));

        startMonthYearSwitchTimer();
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(88, 101, 242));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    private void styleMenuItem(JMenuItem item) {
        item.setBackground(new Color(54, 57, 63));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    private void refreshChart() {
        remove(chartPanel);
        chartPanel = createChartPanel();
        chartPanel.setBounds(0, 0, 576, 340);
        add(chartPanel);
        revalidate();
        repaint();
    }

    private ChartPanel createChartPanel() {
        String monthName = LocalDate.of(selectedYear, selectedMonth, 1).getMonth().name();
        String formattedTitle = "Income for " + monthName.charAt(0) + monthName.substring(1).toLowerCase() + ", " + selectedYear;

        JFreeChart barChart = ChartFactory.createBarChart(
                formattedTitle,
                "Day",
                "Amount Paid (RM)",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, true, false);

        barChart.setBackgroundPaint(new Color(35, 39, 42));
        barChart.getTitle().setPaint(new Color(220, 221, 222));

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(47, 49, 54));
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelPaint(new Color(220, 221, 222));
        domainAxis.setLabelPaint(new Color(220, 221, 222));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelPaint(new Color(220, 221, 222));
        rangeAxis.setLabelPaint(new Color(220, 221, 222));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(114, 137, 218));

        ChartPanel panel = new ChartPanel(barChart);
        panel.setPreferredSize(new Dimension(576, 340));
        panel.setBackground(new Color(35, 39, 42));

        return panel;
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Integer, Double> dayToAmountMap = new TreeMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader("src/payments.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                try {
                    LocalDate paymentDate = LocalDate.parse(data[4].trim(), formatter);
                    if (paymentDate.getMonthValue() == selectedMonth && paymentDate.getYear() == selectedYear) {
                        int day = paymentDate.getDayOfMonth();
                        double amount = Double.parseDouble(data[3].trim());
                        dayToAmountMap.put(day, dayToAmountMap.getOrDefault(day, 0.0) + amount);
                    }
                } catch (DateTimeParseException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, Double> entry : dayToAmountMap.entrySet()) {
            dataset.addValue(entry.getValue(), "Amount", String.valueOf(entry.getKey()));
        }

        return dataset;
    }

    private void startMonthYearSwitchTimer() {
        autoSwitchTimer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMonth++;
                if (selectedMonth > 12) {
                    selectedMonth = 1;
                    selectedYear++;
                }
                refreshChart();
            }
        });
        autoSwitchTimer.start();
    }
}
