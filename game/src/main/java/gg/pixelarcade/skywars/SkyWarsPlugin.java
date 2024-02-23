package gg.pixelarcade.skywars;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import xyz.refinedev.retention.api.Bootstrap;

import java.io.File;

@Getter
@Setter
public class SkyWarsPlugin extends Bootstrap {

    @Getter
    private static SkyWarsPlugin instance;

    private boolean debug = true;

    public SkyWarsPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {
        instance = this;

        new SkyWarsGame(this);
    }

    @Override
    public void disable() {
        SkyWarsGame.getGameInstance().getUpdaterThread().interrupt();
        SkyWarsGame.getInstance().stop();
    }


}
