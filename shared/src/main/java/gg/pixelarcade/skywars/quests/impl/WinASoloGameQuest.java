package gg.pixelarcade.skywars.quests.impl;

import gg.pixelarcade.skywars.quests.Quest;
import gg.pixelarcade.skywars.quests.QuestCompletionStrategy;
import gg.pixelarcade.skywars.quests.QuestRewardType;
import gg.pixelarcade.skywars.quests.strategy.WinASoloGameStrategy;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 16:08
 */

public class WinASoloGameQuest extends Quest {

    public WinASoloGameQuest() {
        super("win_a_solo_game", 1, QuestRewardType.TEST, new WinASoloGameStrategy());
    }

    @Override
    public String getDisplayName() {
        return "Win a Solo Game";
    }

    @Override
    public ItemStack getDisplayIcon() {
        return new ItemBuilder(Material.PAPER)
                .addEnchant(Enchantment.DURABILITY, 1)
                .flags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                .toItemStack();
    }

    @Override
    public List<String> getDescription() {
        return List.of("Win a solo game");
    }
}
