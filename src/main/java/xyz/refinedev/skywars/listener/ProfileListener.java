package xyz.refinedev.skywars.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.UUID;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@RequiredArgsConstructor
public class ProfileListener implements Listener {
    private final pxSkyWars plugin;

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
}
