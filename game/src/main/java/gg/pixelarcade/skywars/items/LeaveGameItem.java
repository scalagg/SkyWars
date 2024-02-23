package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.item.Item;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 08/10/2023 / 14:09
 */

public class LeaveGameItem extends Item {

    private final Player player;

    public LeaveGameItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.BED)
                .setName(Color.translate("&cLeave Game &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        player.sendMessage(Color.translate("&cYou have left the game."));
        SkyWarsGame.getGameInstance().getGameHandler().removePlayer(player);
    }
}
