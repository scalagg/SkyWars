package net.mccade.skywars;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.scoreboard.api.Assemble;
import lombok.Getter;
import net.mccade.skywars.config.LimboConfig;
import net.mccade.skywars.handlers.LobbyHandler;
import net.mccade.skywars.handlers.server.packet.ServerHandler;
import net.mccade.skywars.listeners.LobbyListener;
import net.mccade.skywars.listeners.QueueListener;
import net.mccade.skywars.scoreboard.ScoreboardAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class Limbo extends SkyWarsShared {

    @Getter
    private static Limbo lobbyInstance;

    private LimboConfig limboConfig;
    private LobbyHandler lobbyHandler;
    private ServerHandler serverHandler;

    public Limbo(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        lobbyInstance = this;

        limboConfig = new LimboConfig();
        lobbyHandler = new LobbyHandler();
        serverHandler = new ServerHandler();

        Arrays.asList(
                new LobbyListener(this),
                new QueueListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, LimboPlugin.getInstance()));

        new Assemble(LimboPlugin.getInstance(), new ScoreboardAdapter(this));

        LimboPlugin.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(LimboPlugin.getInstance(), "BungeeCord");

    }

    @Override
    public void stop() {
        //stop method
    }

    // give items
}
