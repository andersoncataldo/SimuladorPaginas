package algorithms;

import model.SimulationResult;
import java.util.*;

public class LRU implements PageAlgorithm {
    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        LinkedHashMap<Integer, Integer> memory = new LinkedHashMap<>(frameCount, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > frameCount;
            }
        };
        
        int faults = 0;
        for (int page : pages) {
            if (!memory.containsKey(page)) {
                faults++;
                memory.put(page, 1);
            } else {
                memory.get(page); // Access to trigger reordering
            }
        }
        return new SimulationResult(getName(), faults);
    }

    @Override
    public String getName() {
        return "LRU";
    }
}
