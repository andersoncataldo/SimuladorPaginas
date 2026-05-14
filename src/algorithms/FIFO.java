package algorithms;

import model.SimulationResult;
import java.util.*;

public class FIFO implements PageAlgorithm {
    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        List<Integer> frames = new ArrayList<>();
        int faults = 0;
        List<List<Integer>> framesHistory = new ArrayList<>();
        List<Boolean> faultHistory = new ArrayList<>();

        for (int page : pages) {
            boolean fault = false;
            if (!frames.contains(page)) {
                fault = true;
                faults++;
                if (frames.size() < frameCount) {
                    frames.add(page);
                } else {
                    frames.remove(0);
                    frames.add(page);
                }
            }
            faultHistory.add(fault);
            framesHistory.add(new ArrayList<>(frames));
        }
        return new SimulationResult(getName(), faults, pages, framesHistory, faultHistory);
    }

    @Override
    public String getName() {
        return "FIFO";
    }
}
