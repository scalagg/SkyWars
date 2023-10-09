package xyz.refinedev.skywars.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.item.Item;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.item.impl
 * @date 08/10/2023 / 14:03
 */

public class StatisticItem extends Item {

    private final Player player;

    public StatisticItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.BOOK)
                .setName(Color.translate("&aStatistics &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        Profile pxProfile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        List<String> message = Color.translate(List.of(
                "&7&m----------------------------------------",
                "&6&l" + pxProfile.getNameWithColor() + " &lStatistics",
                "&7",
                "&7■ &fKills: &6" + profile.getKills(),
                "&7■ &fDeaths: &6" + profile.getDeaths(),
                "&7■ &fKill Streak: &6" + profile.getKillStreak(),
                "",
                "&7■ &fHeads: &6" + profile.getHeads(),
                "&7■ &fSouls: &6" + profile.getSouls(),
                "",
                "&7■ &fWins: &6" + profile.getWins(),
                "&7■ &fLosses: &6" + profile.getLosses(),
                "&7&m----------------------------------------"
        ));

        for (String line : message)
            player.sendMessage(line);
    }
}
