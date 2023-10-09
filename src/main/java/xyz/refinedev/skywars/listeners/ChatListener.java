package xyz.refinedev.skywars.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.listeners
 * @date 07/10/2023 / 09:01
 */

@RequiredArgsConstructor
public class ChatListener implements Listener {

    private final pxSkyWars plugin;

    @EventHandler(priority = EventPriority.LOW)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

        for (Player recipients : event.getRecipients()) {
            Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(recipients.getUniqueId());
            SkyWarsGame game = plugin.getGameHandler().getGame(recipients);

            if (game == null || !profile.hasMetadata("inGame"))
                recipients.sendMessage(Phoenix.getInstance().getChatHandler().getReplacedChatFormat(player.getUniqueId(), event.getMessage()));
            else game.broadcast(Phoenix.getInstance().getChatHandler().getReplacedChatFormat(player.getUniqueId(), event.getMessage()));
        }
    }

}
