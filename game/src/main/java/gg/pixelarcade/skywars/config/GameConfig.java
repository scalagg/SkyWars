package gg.pixelarcade.skywars.config;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.util.cuboid.Cuboid;
import xyz.refinedev.phoenix.config.api.ConfigUtil;

import java.io.File;

/**
 * This config file needs to be adjusted for each map
 * @Param GAMETYPE should be set via the deployer
 */

public class GameConfig extends ConfigUtil {

    @Ignore
    public static GameConfig IMP = null;

    @Create
    public static SETTINGS SETTING;

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
        public String name = "world";
        public GameType type = GameType.NORMAL;
        public Cuboid bounds = new Cuboid(name, 62094, 20, 113, 61916, 74, -64);
    }


}
