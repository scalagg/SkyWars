package xyz.refinedev.skywars.profile.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.skywars.game.event.GameEndEvent;
import xyz.refinedev.skywars.item.impl.*;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpigotConstants;

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

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        pxSkyWars.getInstance().giveItems(player);
    }
}
