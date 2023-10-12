package gg.pixelarcade.skywars.item.impl;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import gg.pixelarcade.skywars.item.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.item.impl
 * @date 08/10/2023 / 15:21
 */

public class ForceStartItem extends Item {

    private final Player player;

    public ForceStartItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.DIAMOND)
                .setName("&aForce Start &7(Right Click)")
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        SkyWarsGame game = SkyWarsPlugin.getInstance().getGameHandler().getGame(player);
        game.setForceStarted(true);
        game.startCountdown();
    }
}
