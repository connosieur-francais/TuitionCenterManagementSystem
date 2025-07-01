package tcms.custom_gui_components;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class IncomeChartPanel extends JPanel {

    private static final String PAYMENTS_CSV = "src/payments.csv"; // adjust if needed

    public IncomeChartPanel(int targetMonth, int targetYear) {
        setLayout(null);
        setBounds(600, 10, 576, 380);
        setBackground(new Color(240, 240, 240));

        DefaultCategoryDataset dataset = buildIncomeDataset(targetMonth, targetYear);

        JFreeChart chart = ChartFactory.createLineChart(
                "Daily Income Chart",
                "Day",
                "Amount Paid (RM)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 0, 576, 380);
        this.add(chartPanel);
    }

    private DefaultCategoryDataset buildIncomeDataset(int month, int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Integer, Double> dayTotals = new TreeMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(PAYMENTS_CSV))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // skip header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String dateStr = parts[2].trim(); // payment_date
                String amountStr = parts[3].trim(); // amount

                try {
                    Date date = dateFormat.parse(dateStr);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);

                    int entryYear = cal.get(Calendar.YEAR);
                    int entryMonth = cal.get(Calendar.MONTH) + 1; // Java month is 0-based
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    if (entryYear == year && entryMonth == month) {
                        double amount = Double.parseDouble(amountStr);
                        dayTotals.put(day, dayTotals.getOrDefault(day, 0.0) + amount);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, Double> entry : dayTotals.entrySet()) {
            dataset.addValue(entry.getValue(), "Income", String.valueOf(entry.getKey()));
        }

        return dataset;
    }
}