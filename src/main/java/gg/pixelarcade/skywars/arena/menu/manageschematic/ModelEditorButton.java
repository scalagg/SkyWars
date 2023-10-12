package gg.pixelarcade.skywars.arena.menu.manageschematic;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.utils.cuboid.Cuboid;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.arena.ArenaHandler;

import java.util.List;

final class ModelEditorButton extends Button {

    private final ArenaSchematic schematic;

    ModelEditorButton(ArenaSchematic schematic) {
        this.schematic = Preconditions.checkNotNull(schematic, "schematic");
    }

    public String getName(Player player) {
        return ChatColor.AQUA + "Model Editor";
    }

    public List<String> getDescription(Player player) {
        return ImmutableList.of(
            ChatColor.WHITE + "Click to launch the model editor."
        );
    }

    public Material getMaterial(Player player) {
        return Material.PISTON_BASE;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(getMaterial(player)).setName(getName(player)).setLore(getDescription(player)).toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();
            ArenaHandler arenaHandler = SkyWarsPlugin.getInstance().getArenaHandler();

            player.sendMessage(ChatColor.GREEN + "Launching model editor...");

            World world = arenaHandler.getWorldManager().createArenaWorld(schematic);
            if (world == null) {
                SkyWarsPlugin.getInstance().getLogger().warning("Failed to start match: Failed to create world for schematic " + schematic.getName());
                player.sendMessage(ChatColor.RED + "There was an error while launching the model editor! (WORLD_ERROR)");
                return;
            }

            Cuboid bounds = schematic.pasteArena(world);
            if (bounds == null) {
                SkyWarsPlugin.getInstance().getLogger().warning("Failed to start match: Failed to paste schematic " + schematic.getName());
                player.sendMessage(ChatColor.RED + "There was an error while launching the model editor! (SCHEMATIC_ERROR)");
                return;
            }

            final int timeout = 10 * 20; // 10 seconds in ticks
            new BukkitRunnable() {
                private int counter = 0;

                @Override
                public void run() {
                    if (!bounds.isEmpty()) {
                        schematic.launchEditor(player, bounds);
                        cancel();
                        return;
                    }

                    if (++counter > timeout) {
                        SkyWarsPlugin.getInstance().getArenaHandler().getWorldManager().deleteWorld(world);
                        player.sendMessage(ChatColor.RED + "There was an error while launching the model editor! (PASTE_TIMEOUT)");
                        cancel();
                    }
                }
            }.runTaskTimer(SkyWarsPlugin.getInstance(), 0L, 2L);
    }
}
