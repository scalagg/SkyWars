package xyz.refinedev.skywars.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.item.Item;
import xyz.refinedev.skywars.pxSkyWars;

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
        SkyWarsGame game = pxSkyWars.getInstance().getGameHandler().getGame(player);
        game.setForceStarted(true);
        game.startCountdown();
    }
}
