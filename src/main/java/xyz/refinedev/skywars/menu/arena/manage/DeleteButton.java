package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.menu.arena.ArenasMenu;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;

import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

@RequiredArgsConstructor
public final class DeleteButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.REDSTONE_BLOCK);
        item.setName("&4Delete Arena");
        for (String s : Arrays.asList(
                "",
                "&4Caution",
                "&cThis will delete all copies.",
                "",
                "&eClick to delete."
        )) {
            item.addLoreLine( schematic.getName());
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();

        if (arenaHandler.getGrid().isBusy()) {
            player.sendMessage("&cPlease wait for the current operation to finish.");
            return;
        }

        player.sendMessage(Color.translate("&BSkyWars &8► &cRepasting arena &e" + schematic.getName() + "&c..."));

        arenaHandler.getGrid().scaleCopies(schematic, 0, () -> {
            player.sendMessage(Color.translate("&BSkyWars &8► &cSuccessfully deleted arena &e" + schematic.getName() + "&c."));
            Phoenix.getInstance().getMenuHandler().openMenu(new ArenasMenu(), player);
        });
        arenaHandler.removeSchematic(schematic.getName());
        arenaHandler.saveSchematics();
    }
}