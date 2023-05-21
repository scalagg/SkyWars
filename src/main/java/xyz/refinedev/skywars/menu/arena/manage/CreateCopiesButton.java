package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpigotConstants;

import java.util.Arrays;

@RequiredArgsConstructor
public final class CreateCopiesButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.EMERALD_BLOCK);
        item.setName(SpigotConstants.GAME_NAME + "");
        for (String s : Arrays.asList(
                "",
                "&7Use /arena scale <schematic> (count)",
                "&7to scale the arena directly.",
                "",
                "&eClick to create 1 copy.",
                "&eShift click to create 10 copies."
        )){
            item.addLoreLine(s.replace("<schematic>", schematic.getName()));
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();
        int existing = arenaHandler.countArenas(schematic);
        int create = clickType.isShiftClick() ? 10 : 1;
        int desired = existing + create;

        if (arenaHandler.getGrid().isBusy()) {
            player.sendMessage(Color.translate(SpigotConstants.GAME_NAME + "&cThe grid is busy, please try again later."));
            return;
        }

        try {
            player.sendMessage(Color.translate(SpigotConstants.GAME + "&aStarting to create " + create + " copies of " + schematic.getName() + "."));

            arenaHandler.getGrid().scaleCopies(schematic, desired, () -> {
                player.sendMessage(Color.translate(SpigotConstants.GAME + "&aSuccessfully created " + create + " copies of " + schematic.getName() + "."));

                Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
            });
        } catch (Exception ex) {
            player.sendMessage(Color.translate(SpigotConstants.GAME + "&cAn error occurred while creating copies of " + schematic.getName() + "." + org.bukkit.Color.RED + " (" + ex.getMessage() + ")" + org.bukkit.Color.RED + "."));
            ex.printStackTrace();
        }
    }
}