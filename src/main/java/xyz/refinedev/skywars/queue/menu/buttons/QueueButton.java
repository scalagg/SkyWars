package xyz.refinedev.skywars.queue.menu.buttons;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.queue.menu.buttons
 * @date 02/10/2023 / 01:18
 */

@RequiredArgsConstructor
public class QueueButton extends Button {

    private final GameType type;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.BOOK)
                .setName("&a" + type.name())
                .toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();

        player.sendMessage("§aYou have joined the " + type.name() + " queue.");
        player.performCommand("game join " + type.name().toUpperCase());
    }
}
