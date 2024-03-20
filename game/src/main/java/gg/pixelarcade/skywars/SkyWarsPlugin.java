package gg.pixelarcade.skywars;

import gg.pixelarcade.skywars.config.GameConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

@Getter
@Setter
public class SkyWarsPlugin extends JavaPlugin {

    @Getter
    private static SkyWarsPlugin instance;

    private boolean debug = true;


    @Override
    public void onEnable() {
        instance = this;

        GameConfig.IMP = new GameConfig();
        GameConfig.IMP.reload();

        new SkyWarsGame(this);
    }

    @Override
    public void onDisable() {
        SkyWarsGame.getInstance().stop();
    }


}
