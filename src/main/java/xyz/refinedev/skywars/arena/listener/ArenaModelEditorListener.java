package xyz.refinedev.skywars.arena.listener;

import com.grinderwolf.swm.api.SlimePlugin;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.WorldManager;
import xyz.refinedev.skywars.pxSkyWars;

public final class ArenaModelEditorListener implements Listener {

    public final static ItemStack exitItem = new ItemBuilder(Material.NETHER_STAR)
            .setName("§cExit Model Builder")
            .toItemStack();

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("ModelEditor")) return;

        if (event.getMessage().equalsIgnoreCase("/exiteditor")) {
            event.setCancelled(true);
            command(player);
        }
    }

    private void command(Player player) {
        exit(player, player.getWorld());
        player.sendMessage("§aYou have exited the model editor.");
        player.removeMetadata("Build", pxSkyWars.getInstance());

        //TODO: tp to spawn
    }

    // listen for right click on exit item
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("ModelEditor")) return;
        if (event.getItem() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (player.getItemInHand().isSimilar(exitItem)) {
            ArenaSchematic schematic = (ArenaSchematic) player.getMetadata("Model").get(0).value();
            try {
                // TODO: save after editing
                // if (schematic.save(player.getWorld(), player)) {
                command(player);
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§cThere was an error saving the model.");
            }
        }
    }

    // if the player is teleported to a different world, exit the model editor
    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("ModelEditor")) return;
        if (event.getTo().getWorld() != player.getWorld()) {
            exit(player, event.getFrom().getWorld());
//            pxSkyWars.getInstance().getLobbyHandler().returnToLobby(player);
            player.sendMessage("§cYou were teleported outside of the model world, exiting.");
        }
    }

    private void exit(Player player, World world) {

        player.removeMetadata("ModelEditor", pxSkyWars.getInstance());
        player.removeMetadata("Model", pxSkyWars.getInstance());

        if (world.getName().contains("swm")) {
            pxSkyWars.getInstance().getServer().getScheduler().runTaskLater(pxSkyWars.getInstance(), () ->
                    pxSkyWars.getInstance().getArenaHandler().getWorldManager().deleteWorld(world), 100L);
        }
    }

    // on disconnect, exit the model editor
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("ModelEditor")) return;
        exit(player, player.getWorld());
    }

}