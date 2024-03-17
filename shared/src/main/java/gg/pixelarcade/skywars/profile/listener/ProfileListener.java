package gg.pixelarcade.skywars.profile.listener;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.util.UUID;

@RequiredArgsConstructor
public class ProfileListener implements Listener {

    private final SkyWarsShared shared;

    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();
        SkyWarsProfile profile = shared.getProfileHandler().getProfile(uuid);

        if (profile != null) {
            profile.load();
            profile.setName(event.getName());
            SkyWarsProfile finalProfile = profile;
            Tasks.runLater(() -> {
                finalProfile.setName(event.getName());
                finalProfile.save();
            }, 20L * 5);
            return;
        }

        profile = shared.getProfileHandler().addProfile(uuid);
        profile.setName(event.getName());
        profile.save();
    }

}
