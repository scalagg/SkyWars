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
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

@RequiredArgsConstructor
public final class CreateCopiesButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.EMERALD_BLOCK);
        item.setName(MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.CREATE_COPIES_BUTTON_NAME);
        for (String s : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.CREATE_COPIES_BUTTON_LORE){
            item.addLoreLine(s.replace("<schematic>", schematic.getName()));
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();
        int existing = arenaHandler.countArenas(schematic);
        int create = clickType.isShiftClick() ? 10 : 1;
        int desired = existing + create;

        if (arenaHandler.getGrid().isBusy()) {
            player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.GRID_IS_BUSY));
            return;
        }

        try {
            player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.REPASTING_OR_SCALING_ARENAS_STARTING));

            arenaHandler.getGrid().scaleCopies(schematic, desired, () -> {
                player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_SCALE
                        .replace("<arena>", schematic.getName())
                        .replace("<count>", String.valueOf(desired))));
                Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
            });
        } catch (Exception ex) {
            player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_SCALE_FAILED
                    .replace("<arena>", schematic.getName())
                    .replace("<error>", ex.getMessage())));
            ex.printStackTrace();
        }
    }
}