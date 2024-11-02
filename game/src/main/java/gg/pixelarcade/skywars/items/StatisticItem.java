package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.SharedAPI;
import net.evilblock.cubed.util.Color;
import net.evilblock.cubed.util.bukkit.ItemBuilder;
import gg.pixelarcade.skywars.item.Item;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 08/10/2023 / 14:03
 */

public class StatisticItem extends Item {

    private final Player player;

    public StatisticItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BOOK)
                .name(Color.translate("&aStatistics &7(Right Click)"))
                .build();
    }

    @Override
    public void click(Action action) {
        String displayName = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getNameWithColor();
        SkyWarsProfile profile = SkyWarsGame.getGameInstance().getProfileHandler().getProfile(player.getUniqueId());

        List<String> message = Color.translate(List.of(
                "&7&m----------------------------------------",
                "&6&l" + displayName + " &lStatistics",
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
