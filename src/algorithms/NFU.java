package algorithms;

import model.SimulationResult;
import java.util.*;

public class NFU implements PageAlgorithm {
    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        Map<Integer, Integer> counters = new HashMap<>();
        List<Integer> frames = new ArrayList<>();
        int faults = 0;

        for (int page : pages) {
            if (!frames.contains(page)) {
                faults++;
                if (frames.size() == frameCount) {
                    int pageToReplace = frames.stream()
                            .min(Comparator.comparingInt(counters::get))
                            .orElse(frames.get(0));
                    
                    frames.remove(Integer.valueOf(pageToReplace));
                    // Optional: we can keep the counter or remove it. 
                    // Usually NFU counters persist or are reset. 
                    // Let's stick to a simple version where we remove it to keep the map small.
                    counters.remove(pageToReplace);
                }
                frames.add(page);
                counters.put(page, 1);
            } else {
                counters.put(page, counters.get(page) + 1);
            }
        }
        return new SimulationResult(getName(), faults);
    }

    @Override
    public String getName() {
        return "NFU";
    }
}
