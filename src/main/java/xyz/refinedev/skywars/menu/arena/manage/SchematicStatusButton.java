package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

@RequiredArgsConstructor
final class SchematicStatusButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();
        int totalCopies = 0;
        int inUseCopies = 0;

        for (Arena arena : arenaHandler.getArenas(schematic)) {
            totalCopies++;

            if (arena.isInUse()) {
                inUseCopies++;
            }
        }

        ItemBuilder item = new ItemBuilder(Material.NAME_TAG, arenaHandler.getArenas(schematic).size());
        item.setName("&aSchematic: &e<schematic>"
                .replace("<schematic>", schematic.getName()));
        for (String s : Arrays.asList(
                "",
                "&fCopies: &6<total>",
                "&fCopies (Used): &6<used>"
        )) {
            item.addLoreLine(s.replace("<total>", String.valueOf(totalCopies)).replace("<used>", String.valueOf(inUseCopies)));
        }
        return item.toItemStack();
    }
}