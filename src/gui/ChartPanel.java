package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<model.SimulationResult> results = new ArrayList<>();

    public ChartPanel() {
        setBorder(BorderFactory.createTitledBorder("Gráfico Comparativo"));
        setPreferredSize(new Dimension(500, 300));
    }

    public void setResults(List<model.SimulationResult> results) {
        this.results = results;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (results.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int maxFaults = results.stream().mapToInt(model.SimulationResult::getPageFaults).max().orElse(1);
        int margin = 50;
        int chartWidth = getWidth() - 2 * margin;
        int chartHeight = getHeight() - 2 * margin;
        int barWidth = (chartWidth / results.size()) - 20;

        // Draw axes
        g2.drawLine(margin, getHeight() - margin, getWidth() - margin, getHeight() - margin);
        g2.drawLine(margin, margin, margin, getHeight() - margin);

        for (int i = 0; i < results.size(); i++) {
            model.SimulationResult res = results.get(i);
            int barHeight = (int) (((double) res.getPageFaults() / maxFaults) * chartHeight);
            int x = margin + 15 + i * (barWidth + 20);
            
            g2.setColor(new Color(100, 149, 237));
            g2.fillRect(x, getHeight() - margin - barHeight, barWidth, barHeight);
            
            g2.setColor(Color.BLACK);
            g2.drawRect(x, getHeight() - margin - barHeight, barWidth, barHeight);
            
            // Labels
            g2.drawString(res.getAlgorithmName(), x + 5, getHeight() - margin + 20);
            g2.drawString(String.valueOf(res.getPageFaults()), x + (barWidth/2) - 5, getHeight() - margin - barHeight - 5);
        }
    }
}
