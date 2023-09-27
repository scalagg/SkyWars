package xyz.refinedev.skywars.game.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameState;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.task
 * @date 26/09/2023 / 21:36
 */

@RequiredArgsConstructor
public class GameCountdownTask extends BukkitRunnable {

    private final SkyWarsGame game;
    private int countdown = 6;

    private final int[] reminders = new int[]{30, 15, 10, 5, 4, 3, 2, 1};

    @Override
    public void run() {
        if (--countdown == 0) {
            game.broadcast("&eGame started.");
            game.startGame();
            cancel();
            return;
        }

        for (int reminder : reminders) {
            if (countdown == reminder) {
                game.broadcast("&eGame starting in &c" + reminder + " &eseconds.");
            }
        }
    }
}
