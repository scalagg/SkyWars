package gg.pixelarcade.skywars.hologram.task;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import lombok.RequiredArgsConstructor;

import org.bukkit.Bukkit;
import gg.pixelarcade.skywars.hologram.handler.HologramHandler;
import gg.pixelarcade.skywars.hologram.TickableHologram;

/**
 * This Project is property of Refine Development © 2021
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 9/15/2021
 * Project: PotPvPRP
 */

@RequiredArgsConstructor
public class HologramUpdateTask implements Runnable {

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        HologramHandler handler = SkyWarsPlugin.getInstance().getHologramHandler();

        for ( TickableHologram hologram : handler.getHolograms() ) {
            if (hologram.updateIn < 1) {
                Bukkit.getScheduler().runTask(SkyWarsPlugin.getInstance(), () -> {
                    hologram.tick();
                    hologram.updateIn = 20;
                });
                return;
            }

            hologram.updateIn = hologram.updateIn - 1;
            hologram.updateTime();
        }
    }
}
