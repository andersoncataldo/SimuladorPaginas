package algorithms;

import model.SimulationResult;
import java.util.*;

public class FIFO implements PageAlgorithm {
    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        Queue<Integer> frames = new LinkedList<>();
        Set<Integer> memory = new HashSet<>();
        int faults = 0;

        for (int page : pages) {
            if (!memory.contains(page)) {
                faults++;
                if (memory.size() == frameCount) {
                    int oldest = frames.poll();
                    memory.remove(oldest);
                }
                frames.add(page);
                memory.add(page);
            }
        }
        return new SimulationResult(getName(), faults);
    }

    @Override
    public String getName() {
        return "FIFO";
    }
}
