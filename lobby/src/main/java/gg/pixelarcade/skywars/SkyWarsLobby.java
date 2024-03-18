package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.admin.commands.AdminExp;
import gg.pixelarcade.skywars.leaderboard.LeaderboardHologramHandler;
import gg.pixelarcade.skywars.leaderboard.LeaderboardNPCsHandler;
import gg.pixelarcade.skywars.profile.PersonalStatsNPCHandler;
import gg.pixelarcade.skywars.menus.GameModeSelectorMenu;
import gg.pixelarcade.skywars.queue.QueueNPCsHandler;
import gg.pixelarcade.skywars.scoreboard.LobbyScoreboardAdapter;
import gg.pixelarcade.skywars.items.*;
import gg.pixelarcade.skywars.listeners.LobbyListener;
import gg.pixelarcade.skywars.music.MusicHandler;
import gg.pixelarcade.skywars.music.commands.MusicCommand;
import gg.pixelarcade.skywars.queue.QueueHandler;
import gg.pixelarcade.skywars.region.RegionHandler;
import gg.pixelarcade.skywars.region.commands.RegionCommands;
import gg.pixelarcade.skywars.region.listeners.RegionListener;
import gg.pixelarcade.skywars.region.selection.listeners.RegionSelectionListener;
import gg.pixelarcade.skywars.scoreboard.api.Assemble;
import gg.pixelarcade.skywars.util.PlayerUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.commands.PaperCommandManager;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.io.File;
import java.util.Arrays;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 04:31
 */

@Getter
public class SkyWarsLobby extends SkyWarsShared {

    @Getter
    private static SkyWarsLobby lobbyInstance;
    private PaperCommandManager commandHandler;
    private MusicHandler musicHandler;
    private QueueHandler queueHandler;
    private RegionHandler regionHandler;
    private LeaderboardNPCsHandler leaderboardNPCsHandler;

    private QueueNPCsHandler queueNPCsHandler;

    private LeaderboardHologramHandler leaderboardHologramHandler;
    private PersonalStatsNPCHandler personalStatsNPCHandler;

    public SkyWarsLobby(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        lobbyInstance = this;

        regionHandler = new RegionHandler();
        queueHandler = new QueueHandler();
        musicHandler = new MusicHandler(new File(SkyWarsLobbyPlugin.getInstance().getDataFolder(), "music"));
        leaderboardNPCsHandler = new LeaderboardNPCsHandler();

        queueNPCsHandler = new QueueNPCsHandler();

        leaderboardHologramHandler = new LeaderboardHologramHandler();
        personalStatsNPCHandler = new PersonalStatsNPCHandler();

        commandHandler = Phoenix.getInstance().getCommandHandler();

        Arrays.asList(
                new RegionListener(regionHandler),
                new RegionSelectionListener(regionHandler),
                new LobbyListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, SkyWarsLobbyPlugin.getInstance()));

        commandHandler.registerCommand(new MusicCommand());
        commandHandler.registerCommand(new RegionCommands());
        commandHandler.registerCommand(new AdminExp());

        new Assemble(SkyWarsLobbyPlugin.getInstance(), new LobbyScoreboardAdapter());

        getPlugin().getServer().getMessenger().registerOutgoingPluginChannel(getPlugin(), "BungeeCord");
    }

    @Override
    public void stop() {
        super.stop();
        leaderboardNPCsHandler.destroy();

        queueNPCsHandler.destroy();

        leaderboardHologramHandler.destroy();
        personalStatsNPCHandler.destroy();
    }

    public void giveItems(Player player) {
        PlayerUtil.resetPlayer(player);

        // this is so weird but otherwise last player to die doesn't get items for some reason :/
        Tasks.runLater(() -> {
            player.getInventory().setItem(0, new SoloQueueItem(player).getItem(player));
            player.getInventory().setItem(1, new DoublesQueueItem(player).getItem(player));
            player.getInventory().setItem(3, new ProfileItem(player).getItem(player));
            player.getInventory().setItem(4, new CosmeticItem(player).getItem(player));
            player.getInventory().setItem(6, new SettingsItem(player).getItem(player));
            player.getInventory().setItem(7, new KitEditorItem(player).getItem(player));
        }, 2L);
    }
}