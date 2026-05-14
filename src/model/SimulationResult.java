package model;

import java.util.ArrayList;
import java.util.List;

public class SimulationResult {
    private final String algorithmName;
    private final int pageFaults;
    private final List<Integer> pageSequence;
    private final List<List<Integer>> framesHistory;
    private final List<Boolean> faultHistory;

    public SimulationResult(String algorithmName, int pageFaults, List<Integer> pageSequence, 
                            List<List<Integer>> framesHistory, List<Boolean> faultHistory) {
        this.algorithmName = algorithmName;
        this.pageFaults = pageFaults;
        this.pageSequence = new ArrayList<>(pageSequence);
        this.framesHistory = new ArrayList<>();
        for (List<Integer> frames : framesHistory) {
            this.framesHistory.add(new ArrayList<>(frames));
        }
        this.faultHistory = new ArrayList<>(faultHistory);
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public List<Integer> getPageSequence() {
        return pageSequence;
    }

    public List<List<Integer>> getFramesHistory() {
        return framesHistory;
    }

    public List<Boolean> getFaultHistory() {
        return faultHistory;
    }
}
