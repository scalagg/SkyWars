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

        for (UUID players : game.getPlayers()) {
            Player player = SkyWarsPlugin.getInstance().getServer().getPlayer(players);
            if (player == null)
                continue;

            for (int reminder : reminders) {
                if (countdown == reminder) {
                    game.broadcast("&eGame starting in &c" + reminder + " &eseconds.");
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                }
            }
        }
    }
}
