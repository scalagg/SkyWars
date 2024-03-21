package net.mccade.skywars.listeners;

import net.mccade.skywars.Limbo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class QueueListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().isOp())
            return;

        if (!event.getMessage().equalsIgnoreCase("deployskywarsserver"))
            return;

        Limbo.getLobbyInstance().getLobbyHandler().deployGame(true);
        event.getPlayer().sendMessage("Deploying SkyWars Server...");
        event.setCancelled(true);
    }

}
