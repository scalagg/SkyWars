package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.achievement.AchievementHandler;
import gg.pixelarcade.skywars.cage.CageHandler;
import gg.pixelarcade.skywars.item.listeners.ItemListener;
import gg.pixelarcade.skywars.kit.KitHandler;
import gg.pixelarcade.skywars.leaderboard.LeaderboardHandler;
import gg.pixelarcade.skywars.perk.PerkHandler;
import gg.pixelarcade.skywars.profile.ProfileHandler;
import gg.pixelarcade.skywars.profile.listener.ProfileListener;
import gg.pixelarcade.skywars.quests.QuestHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.commands.PaperCommandManager;
import xyz.refinedev.phoenix.Phoenix;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:34
 */

@Getter
public abstract class SkyWarsShared {

    @Getter
    private static SkyWarsShared instance;

    private final JavaPlugin plugin;

    private final ProfileHandler profileHandler;
    private final KitHandler kitHandler;
    private final PerkHandler perkHandler;
    private final CageHandler cageHandler;
    private final QuestHandler questHandler;
    private final LeaderboardHandler leaderboardHandler;
    private final AchievementHandler achievementHandler;

    public final PaperCommandManager commandHandler;

    public SkyWarsShared(JavaPlugin plugin) {
        instance = this;

        this.plugin = plugin;

        profileHandler = new ProfileHandler();
        kitHandler = new KitHandler();
        cageHandler = new CageHandler();
        perkHandler = new PerkHandler();
        questHandler = new QuestHandler();
        leaderboardHandler = new LeaderboardHandler();
        achievementHandler = new AchievementHandler(this);
        commandHandler = Phoenix.getInstance().getCommandHandler();

        Arrays.asList(
                new ItemListener(),
                new ProfileListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));

        init();
    }

    public abstract void init();
    public abstract void stop();


}
