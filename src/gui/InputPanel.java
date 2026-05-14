package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputPanel extends JPanel {
    private JTextField pageField = new JTextField("1,2,2,4,5", 30);
    private JTextField frameField = new JTextField("3", 5);
    private JButton runButton = new JButton("Simular");

    public InputPanel(Runnable runAction) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        add(createFieldPanel("Sequência de Páginas:", pageField));
        add(createFieldPanel("Quantidade de Quadros:", frameField));

        runButton.setPreferredSize(new Dimension(120, 35));
        runButton.setBackground(new Color(52, 152, 219));
        runButton.setForeground(Color.WHITE);
        runButton.setFocusPainted(false);
        runButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        runButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(runButton);

        runButton.addActionListener(e -> runAction.run());
    }

    private JPanel createFieldPanel(String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout(5, 2));
        p.setOpaque(false);
        JLabel l = new JLabel(label);
        l.setFont(new Font("SansSerif", Font.PLAIN, 12));
        p.add(l, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return p;
    }

    public List<Integer> getPages() {
        return Arrays.stream(pageField.getText().split(","))
                     .filter(s -> !s.trim().isEmpty())
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    public int getFrameCount() {
        return Integer.parseInt(frameField.getText().trim());
    }
}
