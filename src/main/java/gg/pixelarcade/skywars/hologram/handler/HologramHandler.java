package gg.pixelarcade.skywars.hologram.handler;

import com.google.gson.reflect.TypeToken;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.hologram.TickableHologram;
import lombok.Getter;
import lombok.SneakyThrows;

import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.Bukkit;
import xyz.refinedev.api.storage.JsonStorage;
import gg.pixelarcade.skywars.hologram.meta.HologramData;
import gg.pixelarcade.skywars.hologram.task.HologramUpdateTask;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This Project is property of Refine Development © 2021
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 9/15/2021
 * Project: PotPvPRP
 */

@Getter
public final class HologramHandler {

    private final JsonStorage<List<TickableHologram>> hologramStorage;
    private final List<TickableHologram> holograms = new ArrayList<>();

    /**
     * Load and initiate holograms
     */
    public HologramHandler(SkyWarsPlugin skyWarsPlugin) {
        this.hologramStorage = new JsonStorage<>("holograms", skyWarsPlugin, SkyWarsPlugin.GSON);
        this.initiateCacheFromStorage();

        Bukkit.getScheduler().runTaskTimer(skyWarsPlugin, new HologramUpdateTask(), 0, 20L);
    }

    /**
     * Set up the storages from our cache
     */
    public void initiateCacheFromStorage() {
        Type hologramType = new TypeToken<List<TickableHologram>>(){}.getType();
        List<TickableHologram> holograms = this.hologramStorage.getData(hologramType);
        if (holograms == null || holograms.isEmpty()) return;

        Collections.reverse(holograms); // So that newer holograms are shown under older holograms, just aesthetic wise ig

        this.holograms.addAll(holograms);
    }

    public void unload() {
        this.hologramStorage.save(holograms);
    }

    /**
     * Save all holograms to the config
     */
    @SneakyThrows
    public void save() {
        this.hologramStorage.saveAsync(holograms);
    }

    public void delete(TickableHologram tickableHologram) {
        HologramData data = tickableHologram.getData();
        Hologram hologram = data.getHologram();

        if (hologram != null) {
            hologram.delete();
        }

        this.holograms.remove(tickableHologram);
        this.save();
    }

    public TickableHologram getByName(String name) {
        for ( TickableHologram hologram : this.holograms ) {
            HologramData meta = hologram.getData();
            if (meta.getName().equalsIgnoreCase(name)) return hologram;
        }
        return null;
    }

}
