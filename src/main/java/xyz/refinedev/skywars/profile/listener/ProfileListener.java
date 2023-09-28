package xyz.refinedev.skywars.profile.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.refinedev.phoenix.utils.Color;
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

        if (player.hasPermission("core.staff")) {
            Bukkit.broadcastMessage(Color.translate(SpigotConstants.LOBBY + " &7>>> &4&k;&c&k;&r &7[&cStaff&7] &a"
                    + player.getName() + " &7has joined the lobby! &c&k;&4&k;&r &7<<<"));
        }
        if (player.hasPermission("skywars.wc") && !player.isOp()) {
            Bukkit.broadcastMessage(Color.translate(SpigotConstants.LOBBY + " &7>>> &B&K;&3&k;&r &7[&aW&2C&7] &a"
                    + player.getName() + " &7has joined the lobby! &3&k;&b&k; &7<<<"));
        }

        if (player.hasPermission("skywars.champion") && !player.isOp()) {
            Bukkit.broadcastMessage(Color.translate(SpigotConstants.LOBBY + " &7>>> &7[&eC&7] &a"
                    + player.getName() + " &7has joined the lobby! &7<<<"));
        }

        player.getInventory().clear();
        player.getInventory().setItem(0, new QueueItem(player).getItem(player));
        player.getInventory().setItem(1, new ProfileItem(player).getItem(player));
        player.getInventory().setItem(4, new CosmeticItem(player).getItem(player));
        player.getInventory().setItem(7, new LoadoutItem(player).getItem(player));
        player.getInventory().setItem(8, new SettingsItem(player).getItem(player));
    }
}
