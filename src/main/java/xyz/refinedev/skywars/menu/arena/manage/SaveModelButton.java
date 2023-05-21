package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.WorldEditUtils;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.utils.SpigotConstants;

import java.util.Arrays;

@RequiredArgsConstructor
public final class SaveModelButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.PISTON_BASE);
        item.setName(Color.translate("&eSave Model"));
        for (String s : Arrays.asList(
                "",
                "&eClick to save the model."
        )) {
            item.addLoreLine(s);
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        try {
            WorldEditUtils.save(schematic, schematic.getModelArenaLocation());
        } catch (Exception ex) {
            player.sendMessage(Color.translate(SpigotConstants.GAME + "&cFailed to save model for <arena>! <error>"
                    .replace("<arena>", schematic.getName())
                    .replace("<error>", ex.getMessage())));
            ex.printStackTrace();
        }
    }
}
