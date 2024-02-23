package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.item.listeners.ItemListener;
import gg.pixelarcade.skywars.profile.ProfileHandler;
import gg.pixelarcade.skywars.profile.listener.ProfileListener;
import gg.pixelarcade.skywars.server.ServerHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.commands.PaperCommandManager;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;

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
    private final ServerHandler serverHandler;

    public final PaperCommandManager commandHandler;

    public SkyWarsShared(JavaPlugin plugin) {
        instance = this;

        this.plugin = plugin;

        profileHandler = new ProfileHandler();
        serverHandler = new ServerHandler();
        commandHandler = Phoenix.getInstance().getCommandHandler();

        Arrays.asList(
                new ItemListener(),
                new ProfileListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));

        init();
    }

    public abstract void init();

    public void stop() {
        this.serverHandler.stopThreads();
    }

}
