//package gg.pixelarcade.skywars.map;
//
//import com.google.common.base.Charsets;
//import com.google.common.io.Files;
//import gg.pixelarcade.skywars.SkyWarsPlugin;
//import gg.pixelarcade.skywars.util.GsonProvider;
//;
//import lombok.SneakyThrows;
//
//import java.io.File;
//
//
//public final class GameMapHandler {
//
//    private final GameMap gameMap;
//    private static final String MAP_FILE_NAME = "gameMap.json";
//
//    @SneakyThrows
//    public GameMapHandler() {
//        File folder = SkyWarsPlugin.getInstance().getDataFolder();
//        File mapFile = new File(folder, "gameMap.json");
//        if (!mapFile.exists())
//            throw new NullPointerException("File 'gameMap.json' not found!");
//
//        GameMap map = GsonProvider.GSON.fromJson(Files.toString(mapFile, Charsets.UTF_8), GameMap.class);
//        if (map == null)
//            throw new NullPointerException("File 'gameMap.json' has invalid data!");
//
//        this.gameMap = map;
//    }
//
//}