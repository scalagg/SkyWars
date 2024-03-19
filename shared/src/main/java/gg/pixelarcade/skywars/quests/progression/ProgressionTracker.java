package gg.pixelarcade.skywars.quests.progression;

import lombok.RequiredArgsConstructor;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 16:21
 */

@RequiredArgsConstructor
public abstract class ProgressionTracker {

    private int count = 0;
    private int goal;

    public ProgressionTracker(int goal) {
        this.goal = goal;
    }

    public void increment() {
        if (count < goal)
            count++;
    }

    public boolean isGoalReached() {
        return count >= goal;
    }

}
