package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.SimulationResult;
import java.awt.*;
import java.util.List;

public class ResultPanel extends JPanel {
    private JTabbedPane tabbedPane = new JTabbedPane();

    public ResultPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 0, 0, 0));
        add(tabbedPane, BorderLayout.CENTER);
    }

    public void setResults(List<SimulationResult> results) {
        tabbedPane.removeAll();
        for (SimulationResult res : results) {
            tabbedPane.addTab(res.getAlgorithmName(), createAlgorithmTable(res));
        }
    }

    private JComponent createAlgorithmTable(SimulationResult res) {
        int frameCount = res.getFramesHistory().get(0).size();
        for(List<Integer> step : res.getFramesHistory()) {
            frameCount = Math.max(frameCount, step.size());
        }

        String[] columnNames = new String[frameCount + 2];
        columnNames[0] = "Página";
        for (int i = 0; i < frameCount; i++) {
            columnNames[i + 1] = "Quadro " + (i + 1);
        }
        columnNames[frameCount + 1] = "Falta?";

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Integer> sequence = res.getPageSequence();
        List<List<Integer>> framesHistory = res.getFramesHistory();
        List<Boolean> faultHistory = res.getFaultHistory();

        for (int i = 0; i < sequence.size(); i++) {
            Object[] row = new Object[frameCount + 2];
            row[0] = sequence.get(i);
            List<Integer> frames = framesHistory.get(i);
            for (int j = 0; j < frameCount; j++) {
                row[j + 1] = (j < frames.size()) ? frames.get(j) : "-";
            }
            row[frameCount + 1] = faultHistory.get(i) ? "SIM" : "NÃO";
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        
        // Center alignment renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Custom renderer for the "Falta?" column
        table.getColumnModel().getColumn(frameCount + 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                if ("SIM".equals(value)) {
                    c.setForeground(new Color(200, 0, 0));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(new Color(0, 150, 0));
                    c.setFont(c.getFont().deriveFont(Font.PLAIN));
                }
                return c;
            }
        });

        return new JScrollPane(table);
    }
}
