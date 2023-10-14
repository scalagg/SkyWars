package gg.pixelarcade.skywars.item.impl;

import gg.pixelarcade.skywars.item.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.menu.impl.ProfileMenu;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.item.impl
 * @date 25/09/2023 / 22:24
 */

//TODO: @Alfie if you wanna redo this or fix it  phoenix has changes and wat not

//public class ProfileItem extends Item {

//    private final Player player;
//
//    public ProfileItem(Player player) {
//        super(player);
//        this.player = player;
//    }
//
//    @Override
//    public ItemStack getItem(Player player) {
//        return new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
//                .setName(Color.translate("&aProfile &7(Right Click)"))
//                .setLore(Color.translate("&7Click to view your profile."))
//                .setSkullOwner(player.getName())
//                .toItemStack();
//    }
//
//
//    @Override
//    public void click(Action action, UUID uuid) {
//        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
////        Phoenix.getInstance().getMenuHandler().openMenu(new ProfileMenu(profile.getName(), true));
//    }
//}
