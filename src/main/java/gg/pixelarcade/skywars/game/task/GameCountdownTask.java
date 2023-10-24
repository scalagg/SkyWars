package gg.pixelarcade.skywars.game.task;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import gg.pixelarcade.skywars.game.SkyWarsGame;

import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.game.task
 * @date 26/09/2023 / 21:36
 */

@RequiredArgsConstructor
public class GameCountdownTask extends BukkitRunnable {

    private final SkyWarsGame game;
    private final int[] reminders = new int[]{30, 15, 10, 5, 4, 3, 2, 1};
    private int countdown = (SkyWarsPlugin.getInstance().isDebug() ? 6 : 31);

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
                game.playSound(Sound.NOTE_PLING);
            }
        }
    }
}
