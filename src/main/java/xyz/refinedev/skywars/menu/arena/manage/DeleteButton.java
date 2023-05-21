package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.bolt.config.MenuConfig;
import xyz.refinedev.bolt.config.MessageConfig;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.bolt.menus.arena.ArenasMenu;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

@RequiredArgsConstructor
public final class DeleteButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.REDSTONE_BLOCK);
        item.setName(MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.DELETE_ARENA_BUTTON_NAME);
        for (String s : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.DELETE_ARENA_BUTTON_LORE) {
            item.addLoreLine(s.replace("<schematic>", schematic.getName()));
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();

        if (arenaHandler.getGrid().isBusy()) {
            player.sendMessage(MessageConfig.IMP.ARENA.GRID_IS_BUSY);
            return;
        }

        player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.REPASTING_OR_SCALING_ARENAS_STARTING));

        arenaHandler.getGrid().scaleCopies(schematic, 0, () -> {
            player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_DELETE
                    .replace("<arena>", schematic.getName())));
            Phoenix.getInstance().getMenuHandler().openMenu(new ArenasMenu(), player);
        });
        arenaHandler.removeSchematic(schematic.getName());
        arenaHandler.saveSchematics();
    }
}