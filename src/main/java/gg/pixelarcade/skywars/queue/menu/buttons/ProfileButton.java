package gg.pixelarcade.skywars.queue.menu.buttons;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars
 * @date 22/10/2023 / 01:40
 */

public class ProfileButton extends Button {

    @Override
    public ItemStack getItem(Player player) {
        SkyWarsProfile skyProfile = SkyWarsPlugin.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        List<String> lore = Arrays.asList(
                "",
                "&6■ &fRank&7: &e" + profile.getHighestRank().getDisplayName(),
                ""
        );

        return new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("&6&lMy Profile")
                .setSkullOwner(player.getName())
                .setLore(lore)
                .toItemStack();
    }
}
