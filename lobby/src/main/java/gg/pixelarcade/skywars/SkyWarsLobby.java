package gg.pixelarcade.skywars;

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

    public SkyWarsLobby(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        lobbyInstance = this;

        regionHandler = new RegionHandler();
        queueHandler = new QueueHandler();
        musicHandler = new MusicHandler(new File(SkyWarsLobbyPlugin.getInstance().getDataFolder(), "music"));
        commandHandler = Phoenix.getInstance().getCommandHandler();

        Arrays.asList(
                new RegionListener(regionHandler),
                new RegionSelectionListener(regionHandler),
                new LobbyListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, SkyWarsLobbyPlugin.getInstance()));

        commandHandler.registerCommand(new MusicCommand());
        commandHandler.registerCommand(new RegionCommands());

        new Assemble(SkyWarsLobbyPlugin.getInstance(), new LobbyScoreboardAdapter());
    }

    public void giveItems(Player player) {
        PlayerUtil.resetPlayer(player);

        // this is so weird but otherwise last player to die doesn't get items for some reason :/
        Tasks.runLater(() -> {
            player.getInventory().setItem(1, new QueueItem(player).getItem(player));
            player.getInventory().setItem(2, new ProfileItem(player).getItem(player));
            player.getInventory().setItem(4, new CosmeticItem(player).getItem(player));
            player.getInventory().setItem(6, new SettingsItem(player).getItem(player));
            player.getInventory().setItem(7, new KitEditorItem(player).getItem(player));
        }, 2L);
    }
}
