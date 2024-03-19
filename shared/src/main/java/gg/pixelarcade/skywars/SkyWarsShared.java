package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.cage.CageHandler;
import gg.pixelarcade.skywars.item.listeners.ItemListener;
import gg.pixelarcade.skywars.kit.KitHandler;
import gg.pixelarcade.skywars.leaderboard.LeaderboardHandler;
import gg.pixelarcade.skywars.perk.PerkHandler;
import gg.pixelarcade.skywars.profile.ProfileHandler;
import gg.pixelarcade.skywars.profile.listener.ProfileListener;
import gg.pixelarcade.skywars.server.ServerHandler;
import gg.pixelarcade.skywars.util.json.FileTypeAdapter;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.commands.PaperCommandManager;
import xyz.refinedev.lib.gson.Gson;
import xyz.refinedev.lib.gson.GsonBuilder;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;

import java.io.File;
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
    private final KitHandler kitHandler;
    private final PerkHandler perkHandler;
    private final CageHandler cageHandler;
    private final LeaderboardHandler leaderboardHandler;

    public final PaperCommandManager commandHandler;

    public SkyWarsShared(JavaPlugin plugin) {
        instance = this;
        this.plugin = plugin;

        Gson gson = SharedAPI.getInstance().getGson();
        GsonBuilder gsonBuilder = gson.newBuilder();
        gsonBuilder.registerTypeAdapter(File.class, new FileTypeAdapter());
        SharedAPI.getInstance().setGson(gsonBuilder.create());

        profileHandler = new ProfileHandler();
        serverHandler = new ServerHandler();
        kitHandler = new KitHandler();
        cageHandler = new CageHandler();
        perkHandler = new PerkHandler();
        leaderboardHandler = new LeaderboardHandler();
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
