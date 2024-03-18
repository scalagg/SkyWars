package gg.pixelarcade.skywars.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.server.ServerData;

@UtilityClass
public class SpigotConstants {

    public static final String GAME_NAME = ChatColor.AQUA +ChatColor.BOLD.toString() + "Skywars " + ChatColor.DARK_GRAY + "► " + ChatColor.WHITE;
    public static final String QUEUE = ChatColor.AQUA + ChatColor.BOLD.toString() + "Queue " + ChatColor.DARK_GRAY + "► " + ChatColor.WHITE;
    public static final String GAME = ChatColor.AQUA + ChatColor.BOLD.toString() + "Game " + ChatColor.DARK_GRAY + "► " + ChatColor.WHITE;
    public static final String LOBBY = ChatColor.AQUA + ChatColor.BOLD.toString() + "Lobby " + ChatColor.DARK_GRAY + "► " + ChatColor.WHITE;
    public static final String CURRENT_SEASON = "1";
    public static final String PREFIX = "&6&lMC&e&lCADE&8 »&e ";

    public static ServerData getThisServer() {
        return SharedAPI.getInstance().getNetworkHandler().getServers()
                .stream().filter(it -> it.getPort() == Bukkit.getPort())
                .findFirst().orElse(null);
    }

}
