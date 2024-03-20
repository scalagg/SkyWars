package gg.pixelarcade.skywars.quests.impl;

import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.quests.Quest;
import gg.pixelarcade.skywars.quests.QuestRewardType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 22:19
 */

public class WinADoublesGameQuest extends Quest {

    public WinADoublesGameQuest() {
        super("WIN_A_DOUBLES_GAME", QuestRewardType.TEST);
    }

    @Override
    public String getDisplayName() {
        return "Win a Doubles Game";
    }

    @Override
    public ItemStack getDisplayIcon() {
        return new ItemBuilder(Material.PAPER)
                .addEnchant(Enchantment.DURABILITY, 1)
                .flags(ItemFlag.HIDE_ENCHANTS)
                .toItemStack();
    }

    @Override
    public List<String> getDescription() {
        return List.of(
                "Win a doubles game to complete this quest!"
        );
    }

    @EventHandler
    public void onGameWin(GameEndEvent event) {
        Game game = event.getGame();
        if (game.getWinner() == null)
            return;

        if (game.getGameTeamType() != GameTeamType.DOUBLES)
            return;

        Player player = Bukkit.getPlayer(game.getWinner());
        if (player == null)
            return;

        completeQuest(player);
    }
}
