package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;

import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.utils.SpigotConstants;

import java.util.Arrays;

@RequiredArgsConstructor
public final class RemoveCopiesButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.REDSTONE_BLOCK);
        item.setName("&4Remove Copies");
        for (String s : Arrays.asList(
                "",
                "&7Use /arena scale <schematic> (count)",
                "&7to scale the arena directly.",
                "",
                "&eClick to remove 1 copy.",
                "&eShift click to remove 10 copies."
        )){
            item.addLoreLine(s.replace("<schemaitc>", schematic.getName()));
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();
        int existing = arenaHandler.countArenas(schematic);
        int remove = clickType.isShiftClick() ? 10 : 1;
        int desired = Math.max(existing - remove, 0);

        if (arenaHandler.getGrid().isBusy()) {
            player.sendMessage(Color.translate(SpigotConstants.GAME_NAME + "&cThe grid is busy, please try again later."));
            return;
        }

        player.sendMessage(Color.translate(SpigotConstants.GAME_NAME + "&aStarting to remove " + remove + " copies of " + schematic.getName() + "."));

        arenaHandler.getGrid().scaleCopies(schematic, desired, () -> {
            player.sendMessage(Color.translate("&aSuccessfully removed <count> copies of <arena>."
                    .replace("<arena>", schematic.getName())
                    .replace("<count>", String.valueOf(desired))));
            Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
        });
    }
}