package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.item.Item;
import gg.pixelarcade.skywars.menu.mapSelector.GameModeSelectorMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 25/09/2023 / 22:45
 */

public class DoublesQueueItem extends Item {

    private final Player player;
    private final GameTeamType gameType = GameTeamType.DOUBLES;

    public DoublesQueueItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(XMaterial.ENCHANTED_BOOK, 1)
                .setName(Color.translate("&aQueue Doubles SkyWars &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        Menu GameModeSelectorMenu = new GameModeSelectorMenu(gameType);
        Phoenix.getInstance().getMenuHandler().openMenu(GameModeSelectorMenu, player);

    }
}
