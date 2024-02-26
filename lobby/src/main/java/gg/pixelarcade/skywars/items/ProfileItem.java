package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.item.Item;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.impl.ProfileMenu;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 25/09/2023 / 22:45
 */

public class ProfileItem extends Item {

    private final Player player;

    public ProfileItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) SkullType.PLAYER.ordinal());
        item.setSkullOwner(player.getName());
        item.setName(Color.translate("&6Profile &7(Right Click)"));
        return item.toItemStack();
    }

    @Override
    public void click(Action action) {
        Profile profile = new Profile(player.getUniqueId());
        Phoenix.getInstance().getMenuHandler().openMenu(new ProfileMenu(profile, true), player);
    }
}
