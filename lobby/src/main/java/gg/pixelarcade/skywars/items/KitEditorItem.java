package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.item.Item;
import gg.pixelarcade.skywars.kit.menu.KitMenu;
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

public class KitEditorItem extends Item {

    private final Player player;

    public KitEditorItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(XMaterial.EMERALD, 1)
                .setName(Color.translate("&aKit Editor &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        Phoenix.getInstance().getMenuHandler()
                .openMenu(new KitMenu(), player);

        //Menu KitListMenu = new KitListMenu(GameType.NORMAL);
        //Phoenix.getInstance().getMenuHandler().openMenu(KitListMenu, player);
    }
}
