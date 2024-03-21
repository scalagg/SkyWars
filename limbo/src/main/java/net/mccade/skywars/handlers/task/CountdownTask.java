package net.mccade.skywars.handlers.task;

import lombok.Getter;
import lombok.Setter;
import net.mccade.skywars.Limbo;
import net.mccade.skywars.LimboPlugin;
import net.mccade.skywars.handlers.LobbyHandler;
import net.mccade.skywars.util.LimboState;
import net.mccade.skywars.util.StartingState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.refinedev.phoenix.utils.Utilities;

public class CountdownTask extends BukkitRunnable {

    private final LobbyHandler lobby = Limbo.getLobbyInstance().getLobbyHandler();
    private final int [] reminders = new int[]{30, 15, 10, 5,4,3,2,1};

    @Setter
    private boolean stop;

    @Getter
    @Setter
    private int countdown;

    public CountdownTask(int countdown) {
        Bukkit.broadcastMessage("Countdown constructed");
        this.countdown = countdown;
    }

    @Override
    public void run() {

        if (!stop) {

            if (--countdown == 0) {

                Bukkit.broadcastMessage(ChatColor.RED + "Sending players");
                lobby.getLobby().setLimboState(LimboState.SENDING);
                String serverName = Limbo.getLobbyInstance().getLobbyHandler().getLobby().getServerName();

                for (Player player : Bukkit.getOnlinePlayers()) {
                    Utilities.sendPlayer(player, serverName, LimboPlugin.getInstance());
                }

                lobby.getLobby().setLimboState(LimboState.STALE);
                lobby.getLobby().setStartingState(StartingState.WAITING);
                Limbo.getLobbyInstance().getLobbyHandler().getLobby().setServerDeployed(false);
                cancel();
                return;
            }

            for (int reminder : reminders) {
                if (countdown == reminder) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Game starting in "
                            + ChatColor.RED + reminder + ChatColor.GREEN + " seconds.");
                }
            }
        }
    }
}
