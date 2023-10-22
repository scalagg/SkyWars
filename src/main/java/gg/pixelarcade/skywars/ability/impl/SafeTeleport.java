package gg.pixelarcade.skywars.ability.impl;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.ability.Ability;
import gg.pixelarcade.skywars.ability.type.Interact;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.utils.Tuple;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.Exclude;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.ability.impl
 * @date 17/10/2023 / 17:06
 */

public class SafeTeleport extends Ability implements Interact {

    private final static int SAFE_TELEPORT_DURATION = 15;

    private final Map<UUID, Tuple<Location, Long>> lastLocation = new HashMap<>();

    @Override
    public String getName() {
        return Color.translate("&d&lSafe Teleport");
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.NETHER_STAR)
                .setName(getName())
                .setLore(Color.translate("&7Click to teleport where you were last touching the ground."))
                .addLoreLine(" ")
                .addLoreLine(Color.translate("&e&lNOTE: &fWe store your location every 15 seconds."))
                .toItemStack();
    }

    @Override
    public boolean onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!lastLocation.containsKey(player.getUniqueId())
                || lastLocation.get(player.getUniqueId()).value() < System.currentTimeMillis()) {
            player.sendMessage(Color.translate("&c&lERROR: &fCould not find a safe location to teleport to."));
            return false;
        }

        player.sendMessage(Color.translate("&a&lSUCCESS: &fTeleported to your last safe location."));
        player.teleport(lastLocation.get(player.getUniqueId()).key());
        player.setFallDistance(0);
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ())
            return;

        if (!SkyWarsPlugin.getInstance().getGameHandler().isPlayingGame(player.getUniqueId())
                || SkyWarsPlugin.getInstance().getGameHandler().isSpectating(player.getUniqueId())
                || !SkyWarsPlugin.getInstance().getGameHandler().getGame(player.getUniqueId()).hasStarted())
            return;

        if (!player.isOnGround())
            return;

        if (lastLocation.containsKey(player.getUniqueId())
                && lastLocation.get(player.getUniqueId()).value() > System.currentTimeMillis())
            return;

        lastLocation.put(player.getUniqueId(), new Tuple<>(
                player.getLocation(),
                System.currentTimeMillis() + 1000L * SAFE_TELEPORT_DURATION)
        );
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        lastLocation.remove(event.getEntity().getUniqueId());
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        Tasks.runAsync(() -> event.getGame().getPlayers().forEach(lastLocation::remove));
    }

}
