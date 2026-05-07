package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPanel extends JPanel {
    private JTextArea resultArea = new JTextArea(15, 20);

    public ResultPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Resultados Detalhados"));
        
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setBackground(new Color(245, 245, 245));
        
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public void setResults(List<model.SimulationResult> results) {
        resultArea.setText(" Algoritmo   | Faltas\n" + 
                           "-------------|--------\n" +
                           results.stream()
                .map(r -> String.format(" %-11s | %d", r.getAlgorithmName(), r.getPageFaults()))
                .collect(Collectors.joining("\n")));
    }
}
