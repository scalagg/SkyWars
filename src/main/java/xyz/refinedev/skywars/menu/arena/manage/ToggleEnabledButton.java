package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.config.MenuConfig;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

@RequiredArgsConstructor
public final class ToggleEnabledButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(schematic.isEnabled() ? Material.EMERALD_BLOCK : Material.REDSTONE_BLOCK);
        String status = schematic.isEnabled() ? "&aEnabled" : "&cDisabled";
        item.setName(status);
        if (schematic.isEnabled()) {
            for (String l1 : Arrays.asList(
                    "",
                    "&7Should the scheduling of matches be allowed?",
                    "",
                    "&eClick to disable."

            )) {
                item.addLoreLine(l1);
            }
        } else {
            for (String l2 : Arrays.asList(
                    "",
                    "&7Should the scheduling of matches be allowed?",
                    "",
                    "&eClick to enable."

            )) {
                item.addLoreLine(l2);
            }
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        schematic.setEnabled(!schematic.isEnabled());
        pxSkyWars.getInstance().getArenaHandler().saveSchematics();
        Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
    }
}