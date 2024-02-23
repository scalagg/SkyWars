package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.adapter.LobbyScoreboardAdapter;
import gg.pixelarcade.skywars.items.CosmeticItem;
import gg.pixelarcade.skywars.items.QueueItem;
import gg.pixelarcade.skywars.items.SettingsItem;
import gg.pixelarcade.skywars.listeners.LobbyListener;
import gg.pixelarcade.skywars.music.MusicHandler;
import gg.pixelarcade.skywars.music.commands.MusicCommand;
import gg.pixelarcade.skywars.region.RegionHandler;
import gg.pixelarcade.skywars.region.commands.RegionCommands;
import gg.pixelarcade.skywars.region.listeners.RegionListener;
import gg.pixelarcade.skywars.region.selection.listeners.RegionSelectionListener;
import gg.pixelarcade.skywars.scoreboard.api.Assemble;
import gg.pixelarcade.skywars.util.PlayerUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import xyz.refinedev.commands.PaperCommandManager;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.retention.api.Bootstrap;

import java.io.File;
import java.util.Arrays;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:28
 */

@Getter
public class SkyWarsLobbyPlugin extends Bootstrap {

    @Getter
    private static SkyWarsLobbyPlugin instance;

    public SkyWarsLobbyPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {
        instance = this;

        new SkyWarsLobby(this);
    }

    @Override
    public void disable() {
        SkyWarsLobby.getInstance().stop();
    }


}
