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

        setTitle("Simulador de Algoritmos de Substituição de Páginas");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        inputPanel = new InputPanel(this::runSimulation);
        resultPanel = new ResultPanel();
        chartPanel = new ChartPanel();

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.WEST);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
    }

    private void runSimulation() {
        try {
            List<Integer> pages = inputPanel.getPages();
            int frames = inputPanel.getFrameCount();
            List<SimulationResult> results = algorithms.stream()
                    .map(a -> a.simulate(pages, frames))
                    .collect(java.util.stream.Collectors.toList());
            
            resultPanel.setResults(results);
            chartPanel.setResults(results);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
