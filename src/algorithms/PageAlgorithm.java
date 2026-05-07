package algorithms;

import model.SimulationResult;
import java.util.List;

public interface PageAlgorithm {
    SimulationResult simulate(List<Integer> pages, int frameCount);
    String getName();
}
