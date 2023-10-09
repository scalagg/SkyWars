package xyz.refinedev.skywars.game.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.cosmetics.cosmetics.killmessages.KillMessageType;
import xyz.refinedev.cosmetics.profile.CosmeticProfile;
import xyz.refinedev.cosmetics.pxCosmetics;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.event.GameEndEvent;
import xyz.refinedev.skywars.game.event.GameStartEvent;
import xyz.refinedev.skywars.game.event.GameStateChangeEvent;
import xyz.refinedev.skywars.game.event.PlayerJoinGameEvent;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.item.impl.*;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpawnUtil;
import xyz.refinedev.skywars.utils.SpigotConstants;

import java.util.List;
import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.listener
 * @date 26/09/2023 / 22:12
 */

@RequiredArgsConstructor
public class GameListener implements Listener {

    private final pxSkyWars plugin;

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinGameEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = event.getGame();
        if (game.getPlayers().size() >= game.getGameType().getMaxPlayers())
            game.startCountdown();

        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setFireTicks(0);

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

        //5 zombies with golden armor that guard middle
        List<Location> zombieSpawns = game.getArena().getZombieSpawns();
        if (zombieSpawns == null || zombieSpawns.isEmpty()) {
            plugin.getLogger().warning("No zombie spawns found for arena " + game.getArena().getArenaSchematic().getName());
            return;
        }

        for (int i = 0; i < 5; i++) {
            Zombie zombie = game.getArena().getWorld().spawn(zombieSpawns.get(i), Zombie.class);
            zombie.setRemoveWhenFarAway(false);
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

    //TODO: 26/09/2023 Move to movement handler from spigot in future, 10x better performance
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

        if (!plugin.getGameHandler().isPlayingGame(player))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        game.removePlayer(player);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        Player player = event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (game == null)
            return;

        if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID)
            event.getDrops().clear();

        Tasks.runLater(() -> player.spigot().respawn(), 4L);
        player.teleport(game.getArena().getSpectatorSpawn());

        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        String deathMessage = SpigotConstants.GAME + profile.getNameWithColor() + "&f has died a horrible death.";

        if (player.getKiller() != null) {
            Player killer = player.getKiller();
            CosmeticProfile cosmeticProfile = pxCosmetics.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
            String displayName = SharedAPI.getInstance().getProfileHandler().getProfile(killer.getUniqueId()).getNameWithColor();

            if (cosmeticProfile.getKillMessageType() != KillMessageType.NONE)
                deathMessage = SpigotConstants.GAME + profile.getNameWithColor() + "&f "
                        + KillMessageType.drawRandom(cosmeticProfile.getKillMessageType()) + "&f "
                        + displayName + "&f.";
            else
                deathMessage = SpigotConstants.GAME + profile.getNameWithColor() + "&f was &aslain&f by " + displayName + "&f.";
        }

        game.broadcast(deathMessage);

        if (player.getKiller() != null)
            game.onDeath(player, player.getKiller());
        else game.onDeath(player, null);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (game == null)
            return;

        event.setCancelled(!game.hasStarted());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        if (!(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if (!plugin.getGameHandler().isPlayingGame(player)
                && !plugin.getGameHandler().isPlayingGame(damager))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (!game.isInGame(damager)) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(!game.hasStarted());
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        SkyWarsGame game = event.getGame();

        for (UUID uuid : game.getPlayers()) {
            Player player = plugin.getServer().getPlayer(uuid);
            if (player == null)
                continue;

            if (!plugin.getGameHandler().isPlayingGame(player))
                continue;

            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(false);
            player.setFlying(false);

            SpawnUtil.teleportToSpawn(player);
        }
    }

}
