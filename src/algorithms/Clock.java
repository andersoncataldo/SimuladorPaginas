package algorithms;

import model.SimulationResult;
import java.util.*;

public class Clock implements PageAlgorithm {
    private static class PageNode {
        int page;
        boolean usedBit;

        PageNode(int page) {
            this.page = page;
            this.usedBit = true;
        }
    }

    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        List<PageNode> frames = new ArrayList<>();
        int pointer = 0;
        int faults = 0;

        for (int page : pages) {
            boolean found = false;
            for (PageNode node : frames) {
                if (node.page == page) {
                    node.usedBit = true;
                    found = true;
                    break;
                }
            }

            if (!found) {
                faults++;
                if (frames.size() < frameCount) {
                    frames.add(new PageNode(page));
                } else {
                    while (frames.get(pointer).usedBit) {
                        frames.get(pointer).usedBit = false;
                        pointer = (pointer + 1) % frameCount;
                    }
                    frames.set(pointer, new PageNode(page));
                    pointer = (pointer + 1) % frameCount;
                }
            }
        }
        return new SimulationResult(getName(), faults);
    }

    @Override
    public String getName() {
        return "Clock";
    }
}
