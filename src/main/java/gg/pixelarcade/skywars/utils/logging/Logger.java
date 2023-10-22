package gg.pixelarcade.skywars.utils.logging;

import org.bukkit.*;
import org.bukkit.permissions.*;
import java.util.logging.*;
import org.bukkit.command.*;
import xyz.refinedev.phoenix.utils.Color;

public final class Logger {
    private static String customName;
    private static boolean logInConsole = true;

    public static void toggleConsoleLog() {
        logInConsole = !logInConsole;
    }

    public static void log(String message) {
        log(message, false);
    }
    public static void info(String message) {
        info(message, LoggerType.BLUE);
    }
    public static void info(String message, LoggerType type) {
        if (customName == null || customName.isEmpty()) {
            log(type.getColor() + "&l(!)&r " + type.getColor() + message, true);
        } else {
            log(type.getColor() + "&l(!)&r " + type.getColor() + "[" + customName + "] " + message, true);
        }
    }

    public static void green(String message) {
        log(message, LoggerType.GREEN);
    }

    public static void red(String message) {
        log(message, LoggerType.RED);
    }

    public static void aqua(String message) {
        log(message, LoggerType.AQUA);
    }

    public static void debug(String message) {
        log(message, LoggerType.DEBUG);
    }

    public static void log(String message, LoggerType type) {
        log(message, type, true);
    }

    public static void log(String message, LoggerType type, boolean arrowPrefix) {
        if (type == null) {
            log(message, true);
        } else if (type == LoggerType.DEBUG) {
            log((arrowPrefix ? "&6&l(!)&r " : "") + "&6DEBUG &8»&6 " + message, true);
        } else {
            log((arrowPrefix ? (type.getColor() + "&l(!)&r ") : "") + type.getColor() + message, true);
        }
    }
    public static void log(String message, boolean inGameLog) {
        String formattedMessage = Color.translate(message);
        if (logInConsole) {
            Bukkit.getLogger().info(ChatColor.stripColor(formattedMessage));
        }
        if (inGameLog) {
            sendToOnlineOps(formattedMessage);
        }
    }

    public static void serve(String message, Exception e) {
        Bukkit.getLogger().log(Level.SEVERE, message, e);
    }

    public static void msg(CommandSender sender, String message) {
        if (sender == null || message == null) {
            return;
        }
        sender.sendMessage(Color.translate(message));
    }
    public static void setName(String name) {
        customName = name;
    }

    static {
        Logger.logInConsole = true;
    }
    private static void sendToOnlineOps(String message) {
        Bukkit.getOnlinePlayers().stream()
                .filter(ServerOperator::isOp)
                .forEach(player -> msg(player, message));
    }
}
