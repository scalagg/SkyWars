package net.mccade.skywars.config;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import xyz.refinedev.phoenix.config.api.ConfigUtil;

import java.io.File;

public class LimboConfig extends ConfigUtil {

    @Ignore
    public static LimboConfig IMP = null;

    @Create
    public static SETTINGS SETTINGS;
//    public static TEMPLATE_NAMES TEMPLATE_NAMES;

    public void reload(){
        File file = new File("plugins/Limbo/config.yml");
        load(file);
        save(file);
    }

    public void saveReload(){
        File file = new File("plugins/Limbo/config.yml");
        save(file);
        load(file);
    }

    public static class SETTINGS {

        public int minPlayers = 2;
        public int maxPlayers = 12;
        public int serverStartPlayers = 2;
        public GameType gameType = GameType.NORMAL;
        public GameTeamType teamType = GameTeamType.SOLO;
    }
}
