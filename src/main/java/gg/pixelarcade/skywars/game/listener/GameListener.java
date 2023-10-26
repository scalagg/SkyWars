package gg.pixelarcade.skywars.game.listener;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.event.GameStartEvent;
import gg.pixelarcade.skywars.game.event.PlayerJoinGameEvent;
import gg.pixelarcade.skywars.item.impl.*;
import gg.pixelarcade.skywars.utils.SpigotConstants;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.cosmetics.cosmetics.killmessages.KillMessageType;
import xyz.refinedev.cosmetics.pxCosmetics;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.general.Tasks;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.game.listener
 * @date 26/09/2023 / 22:12
 */

@RequiredArgsConstructor
public class GameListener implements Listener {

    private final SkyWarsPlugin plugin;

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinGameEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = event.getGame();

        if (game.getPlayers().size() >= game.getGameType().getMaxPlayers())
            game.startCountdown();

        plugin.resetPlayer(player);

        player.getInventory().setItem(0, new LoadoutItem(player).getItem(player));
        player.getInventory().setItem(1, new StatisticItem(player).getItem(player));

        if (player.isOp())
            player.getInventory().setItem(4, new ForceStartItem(player).getItem(player));

        player.getInventory().setItem(7, new CosmeticItem(player).getItem(player));
        player.getInventory().setItem(8, new LeaveGameItem(player).getItem(player));
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        SkyWarsGame game = event.getGame();

        List<Location> zombieSpawns = game.getArena().getZombieSpawns();
        if (zombieSpawns == null || zombieSpawns.isEmpty()) {
            plugin.getLogger().warning("No zombie spawns found for arena " + game.getArena().getArenaSchematic().getName());
            return;
        }

        for (Location zombieSpawn : zombieSpawns) {
            Zombie zombie = game.getArena().getWorld().spawn(zombieSpawn, Zombie.class);
            zombie.setRemoveWhenFarAway(false);
            zombie.setBaby(false);
            zombie.setVillager(false);

            zombie.getEquipment().setBootsDropChance(0);
            zombie.getEquipment().setLeggingsDropChance(0);
            zombie.getEquipment().setChestplateDropChance(0);
            zombie.getEquipment().setHelmetDropChance(0);

            zombie.getEquipment().setArmorContents(new ItemStack[]{
                    new ItemStack(Material.GOLD_HELMET),
                    new ItemStack(Material.GOLD_CHESTPLATE),
                    new ItemStack(Material.GOLD_LEGGINGS),
                    new ItemStack(Material.GOLD_BOOTS)
            });
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        if (event.getFrom().getX() == event.getTo().getX()
                && event.getFrom().getZ() == event.getTo().getZ())
            return;

        player.teleport(event.getFrom());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getGameHandler().isPlayingGame(player.getUniqueId()))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        game.removePlayer(player);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        Player player = event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null)
            return;

        if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID)
            event.getDrops().clear();

        Tasks.runLater(() -> {
            player.spigot().respawn();
            plugin.resetPlayer(player);
        }, 2L);

        String displayName = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getNameWithColor();
        String deathMessage = SpigotConstants.GAME + displayName + "&f has died a horrible death.";

        if (player.getKiller() != null) {
            KillMessageType type = pxCosmetics.getInstance().getProfileHandler().getProfile(player.getKiller().getUniqueId()).getKillMessageType();
            String killerName = SharedAPI.getInstance().getProfileHandler().getProfile(player.getKiller().getUniqueId()).getNameWithColor();

            if (type != KillMessageType.NONE)
                deathMessage = SpigotConstants.GAME + displayName + "&f "
                        + KillMessageType.drawRandom(type) + "&f "
                        + killerName + "&f.";
            else deathMessage = SpigotConstants.GAME + displayName + "&f was &aslain&f by " + killerName + "&f.";
        }

        game.broadcast(deathMessage);

        if (player.getKiller() != null)
            game.onDeath(player, player.getKiller());
        else game.onDeath(player, null);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null)
            return;

        event.setCancelled(!game.hasStarted());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        if (!(event.getDamager() instanceof Player damager))
            return;

        if (!plugin.getGameHandler().isPlayingGame(player.getUniqueId())
                && !plugin.getGameHandler().isPlayingGame(damager.getUniqueId()))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (!game.isInGame(damager)) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(!game.hasStarted());
    }

}
