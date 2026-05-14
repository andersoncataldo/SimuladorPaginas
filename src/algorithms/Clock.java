package algorithms;

import model.SimulationResult;
import java.util.*;

public class Clock implements PageAlgorithm {
    private static class PageNode {
        int page;
        boolean referenced;

        PageNode(int page) {
            this.page = page;
            this.referenced = true;
        }
    }

    @Override
    public SimulationResult simulate(List<Integer> pages, int frameCount) {
        List<PageNode> frames = new ArrayList<>();
        int pointer = 0;
        int faults = 0;
        List<List<Integer>> framesHistory = new ArrayList<>();
        List<Boolean> faultHistory = new ArrayList<>();

        for (int page : pages) {
            boolean fault = false;
            int foundIndex = -1;
            for (int i = 0; i < frames.size(); i++) {
                if (frames.get(i).page == page) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex != -1) {
                frames.get(foundIndex).referenced = true;
            } else {
                fault = true;
                faults++;
                if (frames.size() < frameCount) {
                    frames.add(new PageNode(page));
                } else {
                    while (frames.get(pointer).referenced) {
                        frames.get(pointer).referenced = false;
                        pointer = (pointer + 1) % frameCount;
                    }
                    frames.get(pointer).page = page;
                    frames.get(pointer).referenced = true;
                    pointer = (pointer + 1) % frameCount;
                }
            }
            
            faultHistory.add(fault);
            List<Integer> currentFrames = new ArrayList<>();
            for (PageNode node : frames) {
                currentFrames.add(node.page);
            }
            framesHistory.add(currentFrames);
        }
        return new SimulationResult(getName(), faults, pages, framesHistory, faultHistory);
    }

    @Override
    public String getName() {
        return "Clock";
    }
}
