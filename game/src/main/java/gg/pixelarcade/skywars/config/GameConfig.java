package gg.pixelarcade.skywars.config;

import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Setter;
import org.bukkit.Location;
import xyz.refinedev.phoenix.config.api.ConfigUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This config file needs to be adjusted for each map
 * @Param GAMETYPE should be set via the deployer
 */

public class GameConfig extends ConfigUtil {
    public static SETTINGS SETTING;
    @Create
    @Ignore
    public static GameConfig IMP = null;

    public void reload(){
        File file = new File("plugins/SkyWars/config.yml");
        load(file);
        save(file);
    }

    public void saveReload(){
        File file = new File("plugins/SkyWars/config.yml");
        save(file);
        load(file);
    }

    public static class SETTINGS {
        public GameType type = GameType.CASUAL_SOLO;

    }


}
