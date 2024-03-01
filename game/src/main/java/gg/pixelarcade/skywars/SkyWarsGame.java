package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.ability.AbilityHandler;
import gg.pixelarcade.skywars.ability.command.AbilityCommand;
import gg.pixelarcade.skywars.ability.listener.AbilityInteractListener;
//import gg.pixelarcade.skywars.map.GameMapHandler;
import gg.pixelarcade.skywars.chests.ChestHandler;
import gg.pixelarcade.skywars.config.GameConfig;
import gg.pixelarcade.skywars.game.GameHandler;
import gg.pixelarcade.skywars.game.commands.GameCommand;
import gg.pixelarcade.skywars.game.listener.GameListener;
import gg.pixelarcade.skywars.game.listener.PreventionListener;
import gg.pixelarcade.skywars.game.listener.SpectatorListener;
import gg.pixelarcade.skywars.kit.KitHandler;
import gg.pixelarcade.skywars.scoreboard.ScoreboardAdapter;
import gg.pixelarcade.skywars.scoreboard.api.Assemble;
import gg.pixelarcade.skywars.server.Server;
import gg.pixelarcade.skywars.server.thread.ServerUpdaterThread;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * @author alfie
 * @project SkyWarsGame
 * @date 27/10/2023 / 04:35
 */

@Getter
public class SkyWarsGame extends SkyWarsShared {

    @Getter
    private static SkyWarsGame gameInstance;

    private GameHandler gameHandler;
    //private GameMapHandler gameMapHandler;
    private GameConfig gameConfig;
    private KitHandler kitHandler;
    private ChestHandler chestHandler;
    private AbilityHandler abilityHandler;

    private Server gameServer;
    private ServerUpdaterThread updaterThread;

    public SkyWarsGame(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        gameInstance = this;

        gameConfig = new GameConfig();

        gameHandler = new GameHandler();
        kitHandler = new KitHandler();
        chestHandler = new ChestHandler();
        abilityHandler = new AbilityHandler();

        Arrays.asList(
                new AbilityInteractListener(this),
                new GameListener(this),
                new SpectatorListener(this),
                new PreventionListener(this)
        ).forEach(listener -> SkyWarsPlugin.getInstance().getServer().getPluginManager().registerEvents(
                listener, SkyWarsPlugin.getInstance()));

        commandHandler.registerCommand(new GameCommand());
        commandHandler.registerCommand(new AbilityCommand());

        gameServer = new Server();
        updaterThread = new ServerUpdaterThread();

        new Assemble(SkyWarsPlugin.getInstance(), new ScoreboardAdapter(this));
    }
}
