package gg.pixelarcade.skywars.utils;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {
    public TaskUtil() {
    }

    public static void run(Runnable runnable) {
        SkyWarsPlugin.getInstance().getServer().getScheduler().runTask(SkyWarsPlugin.getInstance(), runnable);
    }

    public static void runTimer(Runnable runnable, long delay, long timer) {
        SkyWarsPlugin.getInstance().getServer().getScheduler().runTaskTimer(SkyWarsPlugin.getInstance(), runnable, delay, timer);
    }

    public static void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(SkyWarsPlugin.getInstance(), delay, timer);
    }

    public static void runLater(Runnable runnable, long delay) {
        SkyWarsPlugin.getInstance().getServer().getScheduler().runTaskLater(SkyWarsPlugin.getInstance(), runnable, delay);
    }

    public static void runSync(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            runnable.run();
        else
            Bukkit.getScheduler().runTask(SkyWarsPlugin.getInstance(), runnable);
    }

    public static void runAsync(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            Bukkit.getScheduler().runTaskAsynchronously(SkyWarsPlugin.getInstance(), runnable);
        else
            runnable.run();
    }
}
