package gg.pixelarcade.skywars.item.impl;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.item.Item;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.item.impl
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
        SkyWarsGame game = SkyWarsPlugin.getInstance().getGameHandler().getGame(player);
        if (game == null) {
            player.sendMessage(Color.translate("&4&lERROR: &cYou are not in a game."));
            return;
        }

        game.removePlayer(player);
        player.sendMessage(Color.translate("&cYou have left the game."));
    }
}
