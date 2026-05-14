package algorithms;

import model.SimulationResult;
import java.util.*;

public class NFU implements PageAlgorithm {
    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        Map<Integer, Integer> counters = new HashMap<>();
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
                    int minCount = Integer.MAX_VALUE;
                    int pageToReplace = -1;
                    int replaceIndex = -1;

                    for (int i = 0; i < frames.size(); i++) {
                        int p = frames.get(i);
                        int count = counters.getOrDefault(p, 0);
                        if (count < minCount) {
                            minCount = count;
                            pageToReplace = p;
                            replaceIndex = i;
                        }
                    }
                    frames.remove(replaceIndex);
                    frames.add(page);
                }
            }
            counters.put(page, counters.getOrDefault(page, 0) + 1);
            faultHistory.add(fault);
            framesHistory.add(new ArrayList<>(frames));
        }
        return new SimulationResult(getName(), faults, pages, framesHistory, faultHistory);
    }

    @Override
    public String getName() {
        return "NFU";
    }
}
