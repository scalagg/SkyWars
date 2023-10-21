package gg.pixelarcade.skywars.arena;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import com.google.gson.reflect.TypeToken;
import com.grinderwolf.swm.api.SlimePlugin;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class ArenaHandler {

    public static final File WORLD_EDIT_SCHEMATICS_FOLDER = new File(JavaPlugin.getPlugin(WorldEditPlugin.class).getDataFolder(), "schematics");
    private static final String SCHEMATICS_FILE_NAME = "schematics.json";

    private final WorldManager worldManager = new WorldManager((SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager"));
    private final Map<String, ArenaSchematic> schematics = new TreeMap<>();

    public ArenaHandler() {
        File folder = SkyWarsPlugin.getInstance().getDataFolder();
        File schematicsFile = new File(folder, SCHEMATICS_FILE_NAME);

        try {
            if (schematicsFile.exists()) {
                try (Reader schematicsFileReader = Files.newReader(schematicsFile, Charsets.UTF_8)) {
                    Type schematicListType = new TypeToken<List<ArenaSchematic>>() {}.getType();
                    List<ArenaSchematic> schematicList = SkyWarsPlugin.GSON.fromJson(schematicsFileReader, schematicListType);

                    for (ArenaSchematic schematic : schematicList) {
                        this.schematics.put(schematic.getName(), schematic);
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveSchematic(ArenaSchematic schematic) {
        schematics.put(schematic.getName(), schematic);

        StringBuilder sb = new StringBuilder();
        sb.append(SkyWarsPlugin.GSON.toJson(schematic));

        try {
            Files.append(
                    sb,
                    new File(SkyWarsPlugin.getInstance().getDataFolder(), SCHEMATICS_FILE_NAME),
                    Charsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveSchematics() {
        try {
            Files.write(
                    SkyWarsPlugin.GSON.toJson(schematics.values()),
                    new File(SkyWarsPlugin.getInstance().getDataFolder(), SCHEMATICS_FILE_NAME),
                    Charsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerSchematic(ArenaSchematic schematic) {
        schematics.put(schematic.getName(), schematic);
    }
    public void unregisterSchematic(ArenaSchematic schematic) {
        schematics.remove(schematic.getName());
    }

    public Set<ArenaSchematic> getSchematics() {
        return ImmutableSet.copyOf(schematics.values());
    }
    public ArenaSchematic getSchematic(String schematicName) {
        return schematics.get(schematicName);
    }

    public ArenaSchematic getRandomArena(long matchSize, boolean ranked) {
        List<ArenaSchematic> possible = new ArrayList<>();

        for (ArenaSchematic schematic : schematics.values()) {
            if (!schematic.isEnabled())
                continue;

            if (schematic.getMaxPlayers() < matchSize || schematic.getMinPlayers() > matchSize)
                continue;

            if (ranked && !schematic.isSupportsRanked())
                continue;

            possible.add(schematic);
        }

        return possible.isEmpty() ? null : possible.get(ThreadLocalRandom.current().nextInt(possible.size()));
    }

}