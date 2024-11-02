package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.item.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import net.evilblock.cubed.util.bukkit.ItemBuilder;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 08/10/2023 / 15:21
 */

public class ForceStartItem extends Item {

    private final Player player;

    public ForceStartItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.DIAMOND)
                .name("&aForce Start &7(Right Click)")
                .build();
    }

    @Override
    public void click(Action action) {
        SkyWarsGame.getGameInstance().getGameHandler().getGame().setForceStarted(true);
        SkyWarsGame.getGameInstance().getGameHandler().startCountdown();
    }
}
