package xyz.refinedev.skywars.menu.arena.manage;

import com.sk89q.worldedit.Vector;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.bolt.config.MenuConfig;
import xyz.refinedev.bolt.config.MessageConfig;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

@RequiredArgsConstructor
public final class TeleportToModelButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.BREWING_STAND_ITEM);
        item.setName(MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.TELEPORT_NAME);
        for (String s : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.TELEPORT_LORE) {
            item.addLoreLine(s);
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();

        ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();
        Vector arenaStart = schematic.getModelArenaLocation();

        // we add 50 so players don't spawn in the very botton corner of
        // the schematic. perhaps later we should apply the same logic we
        // do for spectators to center the player
        player.teleport(new Location(
                arenaHandler.getArenaWorld(),
                arenaStart.getX() + 50,
                arenaStart.getY() + 50,
                arenaStart.getZ() + 50
        ));

        player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.TELEPORTING_TO_ARENA
                .replace("<arena>", schematic.getName())));
    }
}