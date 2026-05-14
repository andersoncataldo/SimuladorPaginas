package gui;

import algorithms.*;
import model.SimulationResult;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends JFrame {
    private InputPanel inputPanel;
    private ResultPanel resultPanel;
    private ChartPanel chartPanel;
    private List<PageAlgorithm> algorithms = Arrays.asList(
            new FIFO(), new LRU(), new Clock(), new NFU()
    );

    public MainWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setTitle("Simulador de Substituição de Páginas");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        inputPanel = new InputPanel(this::runSimulation);
        resultPanel = new ResultPanel();
        chartPanel = new ChartPanel();

        // Create a central split pane or tabbed pane for summary vs details
        JTabbedPane mainTabs = new JTabbedPane();
        mainTabs.addTab("Dashboard Comparativo", createDashboard());
        mainTabs.addTab("Rastreamento Detalhado", resultPanel);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(mainTabs, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
    }

    private JPanel createDashboard() {
        JPanel dashboard = new JPanel(new BorderLayout(10, 10));
        dashboard.setOpaque(false);
        dashboard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Comparação de Faltas de Página");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        dashboard.add(title, BorderLayout.NORTH);
        dashboard.add(chartPanel, BorderLayout.CENTER);
        
        return dashboard;
    }

    private void runSimulation() {
        try {
            List<Integer> pages = inputPanel.getPages();
            int frames = inputPanel.getFrameCount();
            
            if (frames <= 0) throw new Exception("Quantidade de quadros deve ser maior que zero.");
            if (pages.isEmpty()) throw new Exception("Sequência de páginas não pode estar vazia.");

            List<SimulationResult> results = algorithms.stream()
                    .map(a -> a.simulate(pages, frames))
                    .collect(java.util.stream.Collectors.toList());
            
            resultPanel.setResults(results);
            chartPanel.setResults(results);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira apenas números válidos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
