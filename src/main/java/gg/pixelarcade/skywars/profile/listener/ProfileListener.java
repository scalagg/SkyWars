package gg.pixelarcade.skywars.profile.listener;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@RequiredArgsConstructor
public class ProfileListener implements Listener {

    private final SkyWarsPlugin plugin;

    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();
        SkyWarsProfile profile = plugin.getProfileHandler().getProfile(uuid);

        if (profile != null) {
            profile.load();
            profile.setName(event.getName());
            return;
        }

        profile = plugin.getProfileHandler().addProfile(uuid);
        profile.setName(event.getName());
        profile.save();
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        SkyWarsPlugin.getInstance().giveItems(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.resetPlayer(event.getPlayer());
    }

}
