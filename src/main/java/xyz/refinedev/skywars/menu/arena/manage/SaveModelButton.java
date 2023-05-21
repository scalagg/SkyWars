package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.WorldEditUtils;
import xyz.refinedev.bolt.config.MenuConfig;
import xyz.refinedev.bolt.config.MessageConfig;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

@RequiredArgsConstructor
public final class SaveModelButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.PISTON_BASE);
        item.setName(MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.SAVE_MODEL_NAME);
        for (String s : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.SAVE_MODEL_LORE) {
            item.addLoreLine(s);
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        try {
            WorldEditUtils.save(schematic, schematic.getModelArenaLocation());
        } catch (Exception ex) {
            player.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_SCALE_FAILED
                    .replace("<arena>", schematic.getName())
                    .replace("<error>", ex.getMessage())));
            ex.printStackTrace();
        }
    }
}
