package gg.pixelarcade.skywars.quests.impl;

import gg.pixelarcade.skywars.quests.Quest;
import gg.pixelarcade.skywars.quests.QuestRewardType;
import gg.pixelarcade.skywars.quests.progression.impl.TwentyKillTracker;
import gg.pixelarcade.skywars.quests.strategy.TwentyKillsStrategy;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 16:46
 */

public class GetTwentyKillsQuest extends Quest {

    public GetTwentyKillsQuest() {
        super("get_twenty_kills", 1, QuestRewardType.TEST, new TwentyKillsStrategy());
    }

    @Override
    public String getDisplayName() {
        return "Get 20 Kills";
    }

    @Override
    public ItemStack getDisplayIcon() {
        return new ItemBuilder(Material.PAPER)
                .toItemStack();
    }

    @Override
    public List<String> getDescription() {
        return List.of("Get 20 kills");
    }
}
