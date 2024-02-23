package gg.pixelarcade.skywars.game.task;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.task
 * @date 26/09/2023 / 21:36
 */

@RequiredArgsConstructor
public class GameCountdownTask extends BukkitRunnable {

    private final GameHandler game = SkyWarsGame.getGameInstance().getGameHandler();
    private final int[] reminders = new int[]{30, 15, 10, 5, 4, 3, 2, 1};

    private int countdown = (SkyWarsPlugin.getInstance().isDebug() ? 6 : 31);

    @Override
    public void run() {
        if (--countdown == 0) {
            Bukkit.broadcastMessage("&eGame started.");
            game.startGame();
            cancel();
            return;
        }

        for (int reminder : reminders) {
            if (countdown == reminder) {
                Bukkit.broadcastMessage("&eGame starting in &c" + reminder + " &eseconds.");
                game.playSound(Sound.NOTE_PLING);
            }
        }
    }
}
