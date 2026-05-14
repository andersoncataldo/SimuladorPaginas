package gui;

import javax.swing.*;
import model.SimulationResult;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<SimulationResult> results = new ArrayList<>();

    public ChartPanel() {
        setBackground(Color.WHITE);
    }

    public void setResults(List<SimulationResult> results) {
        this.results = results;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (results.isEmpty()) {
            drawEmptyState(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int maxFaults = results.stream().mapToInt(SimulationResult::getPageFaults).max().orElse(1);
        int margin = 60;
        int chartWidth = getWidth() - 2 * margin;
        int chartHeight = getHeight() - 2 * margin;
        int barWidth = (chartWidth / results.size()) - 40;

        // Draw background lines
        g2.setColor(new Color(240, 240, 240));
        for (int i = 0; i <= 5; i++) {
            int y = getHeight() - margin - (i * chartHeight / 5);
            g2.drawLine(margin, y, getWidth() - margin, y);
            g2.setColor(Color.GRAY);
            g2.drawString(String.valueOf(i * maxFaults / 5), margin - 30, y + 5);
            g2.setColor(new Color(240, 240, 240));
        }

        // Draw axes
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(margin, getHeight() - margin, getWidth() - margin, getHeight() - margin);
        g2.drawLine(margin, margin, margin, getHeight() - margin);

        Color[] colors = {
            new Color(46, 204, 113), // Emerald
            new Color(52, 152, 219), // Peter River
            new Color(155, 89, 182), // Amethyst
            new Color(230, 126, 34)  // Carrot
        };

        for (int i = 0; i < results.size(); i++) {
            SimulationResult res = results.get(i);
            int barHeight = (int) (((double) res.getPageFaults() / maxFaults) * chartHeight);
            int x = margin + 30 + i * (barWidth + 40);
            int y = getHeight() - margin - barHeight;
            
            // Bar with gradient
            GradientPaint gp = new GradientPaint(x, y, colors[i % colors.length], x, y + barHeight, colors[i % colors.length].darker());
            g2.setPaint(gp);
            g2.fillRect(x, y, barWidth, barHeight);
            
            g2.setColor(colors[i % colors.length].darker());
            g2.drawRect(x, y, barWidth, barHeight);
            
            // Labels
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.BOLD, 12));
            String name = res.getAlgorithmName();
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(name, x + (barWidth - fm.stringWidth(name)) / 2, getHeight() - margin + 25);
            
            String val = String.valueOf(res.getPageFaults());
            g2.drawString(val, x + (barWidth - fm.stringWidth(val)) / 2, y - 10);
        }
    }

    private void drawEmptyState(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(new Font("SansSerif", Font.ITALIC, 14));
        String msg = "Aguardando simulação...";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2);
    }
}
