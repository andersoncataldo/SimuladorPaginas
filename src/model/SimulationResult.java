package model;

public class SimulationResult {
    private final String algorithmName;
    private final int pageFaults;

    public SimulationResult(String algorithmName, int pageFaults) {
        this.algorithmName = algorithmName;
        this.pageFaults = pageFaults;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getPageFaults() {
        return pageFaults;
    }
}
