package gg.pixelarcade.skywars.region;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import com.google.gson.reflect.TypeToken;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.region.listeners.RegionListener;
import gg.pixelarcade.skywars.region.selection.RegionSelection;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.region
 * @date 19/10/2023 / 15:49
 */

@Getter
public class RegionHandler {

    private final static String REGION_FILE = "regions.json";

    private final Map<String, Region> regions = new HashMap<>();

    public RegionHandler() {
        File folder = SkyWarsPlugin.getInstance().getDataFolder();
        File regionFile = new File(folder, "regions.json");

        try {
            if (regionFile.exists()) {
                try (Reader regionFileReader = Files.newReader(regionFile, Charsets.UTF_8)) {
                    Type regionListType = new TypeToken<List<Region>>() {
                    }.getType();
                    List<Region> regionList = SkyWarsPlugin.GSON.fromJson(regionFileReader, regionListType);

                    for (Region region : regionList) {
                        this.regions.put(region.getName(), region);
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Bukkit.getPluginManager().registerEvents(new RegionListener(), SkyWarsPlugin.getInstance());
    }

    public void saveRegions() {
        try {
            Files.write(
                    SkyWarsPlugin.GSON.toJson(regions.values()),
                    new File(SkyWarsPlugin.getInstance().getDataFolder(), REGION_FILE),
                    Charsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Region getRegion(Location location) {
        for (Region region : regions.values()) {
            if (region.getBounds().contains(location)) {
                return region;
            }
        }

        return null;
    }

    public Set<Region> getRegions() {
        return ImmutableSet.copyOf(regions.values());
    }

    public void addRegion(Region region) {
        regions.put(region.getName().toLowerCase(), region);
    }

    public void removeRegion(String name) {
        regions.remove(name.toLowerCase());
    }

    public Region getRegion(String regionName) {
        return regions.get(regionName.toLowerCase());
    }

    public RegionSelection getSelection(Player player) {
        if (player.hasMetadata(RegionSelection.SELECTION_WAND_KEY))
            return (RegionSelection) player.getMetadata(RegionSelection.SELECTION_WAND_KEY).get(0).value();

        return createSelection(player);
    }

    public RegionSelection createSelection(Player player) {
        RegionSelection selection = new RegionSelection();
        player.setMetadata(RegionSelection.SELECTION_WAND_KEY, new FixedMetadataValue(SkyWarsPlugin.getInstance(), selection));
        return selection;
    }

}
