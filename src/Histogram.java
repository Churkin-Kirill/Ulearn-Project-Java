import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Histogram {
    public void drawHistogram(Map<String, Double> data) {
        ChartPanel chartPanel = new ChartPanel(createParticipantsChart(createParticipantsDataset(data)));
        chartPanel.setPreferredSize(new Dimension(600, 300));
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().add(chartPanel);
        jFrame.setSize(1200, 600);
        jFrame.setVisible(true);
    }

    private JFreeChart createParticipantsChart(DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createBarChart("The amount of transfers for each month of 2020",
                "Month",
                "Amount in dollars",
                dataset,
                PlotOrientation.VERTICAL,true, true, false);
        lineChart.setBackgroundPaint(Color.WHITE);
        return lineChart;
    }

    private DefaultCategoryDataset createParticipantsDataset(Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        data.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> dataset.addValue(entry.getValue(), "Dollars", entry.getKey()));

        return dataset;
    }
}