package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputPanel extends JPanel {
    private JTextField pageField = new JTextField(25);
    private JTextField frameField = new JTextField(5);
    private JButton runButton = new JButton("Executar Simulação");

    public InputPanel(Runnable runAction) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Configurações"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Sequência (ex: 1,2,3):"), gbc);
        gbc.gridx = 1;
        add(pageField, gbc);

        gbc.gridx = 2;
        add(new JLabel("Quadros:"), gbc);
        gbc.gridx = 3;
        add(frameField, gbc);

        gbc.gridx = 4;
        runButton.setBackground(new Color(70, 130, 180));
        runButton.setForeground(Color.WHITE);
        runButton.setFocusPainted(false);
        add(runButton, gbc);

        runButton.addActionListener(e -> runAction.run());
    }

    public List<Integer> getPages() {
        return Arrays.stream(pageField.getText().split(","))
                     .filter(s -> !s.trim().isEmpty())
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    public int getFrameCount() {
        return Integer.parseInt(frameField.getText());
    }
}
