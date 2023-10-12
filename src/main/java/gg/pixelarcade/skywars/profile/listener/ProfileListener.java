package gg.pixelarcade.skywars.profile.listener;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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

        if (profile == null) {
            profile = plugin.getProfileHandler().addProfile(uuid);
            profile.setName(event.getName());
            profile.save();
        } else {
            profile.load();
        }

        profile.setName(event.getName());
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        SkyWarsPlugin.getInstance().giveItems(player);
    }
}
