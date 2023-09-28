package xyz.refinedev.skywars.arena;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import com.google.gson.reflect.TypeToken;
import com.grinderwolf.swm.api.SlimePlugin;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.skywars.pxSkyWars;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Facilitates easy access to {@link ArenaSchematic}s and to {@link Arena}s
 * based on their schematic+copy pair
 */
public final class ArenaHandler {

    public static final File WORLD_EDIT_SCHEMATICS_FOLDER = new File(JavaPlugin.getPlugin(WorldEditPlugin.class).getDataFolder(), "schematics");
    private static final String SCHEMATICS_FILE_NAME = "schematics.json";

    @Getter
    private final WorldManager worldManager = new WorldManager((SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager"));

    // schematic name -> ArenaSchematic instance
    private final Map<String, ArenaSchematic> schematics = new TreeMap<>();

    public void removeSchematic(String name) {
        schematics.remove(name);
    }

    public ArenaHandler() {
        File folder = pxSkyWars.getInstance().getDataFolder();

        File schematicsFile = new File(folder, SCHEMATICS_FILE_NAME);

        try {
            // parsed as a List<ArenaSchematic> and then inserted into Map<String, ArenaSchematic>
            if (schematicsFile.exists()) {
                try (Reader schematicsFileReader = Files.newReader(schematicsFile, Charsets.UTF_8)) {
                    Type schematicListType = new TypeToken<List<ArenaSchematic>>() {}.getType();
                    List<ArenaSchematic> schematicList = pxSkyWars.getInstance().getGson().fromJson(schematicsFileReader, schematicListType);

                    for (ArenaSchematic schematic : schematicList) {
                        this.schematics.put(schematic.getName(), schematic);
                    }
                }
            }
        } catch (IOException ex) {
            // just rethrow, can't recover from arenas failing to load
            throw new RuntimeException(ex);
        }
    }


    @SneakyThrows
    public void saveSchematics() {
        Files.write(
                pxSkyWars.getInstance().getGson().toJson(schematics.values()),
                new File(pxSkyWars.getInstance().getDataFolder(), SCHEMATICS_FILE_NAME),
                Charsets.UTF_8
        );
    }

    public void registerSchematic(ArenaSchematic schematic) {
        schematics.put(schematic.getName(), schematic);
    }

    public void unregisterSchematic(ArenaSchematic schematic) {
        schematics.remove(schematic.getName());
    }

    /**
     * Finds all schematic instances registered
     * @return immutable set of all schematics registered
     */
    public Set<ArenaSchematic> getSchematics() {
        return ImmutableSet.copyOf(schematics.values());
    }

    /**
     * Finds an ArenaSchematic by its id
     * @param schematicName schematic id to search with
     * @return ArenaSchematic present for the given id, if one exists
     */
    public ArenaSchematic getSchematic(String schematicName) {
        return schematics.get(schematicName);
    }

    public ArenaSchematic getRandomArena(long matchSize, boolean ranked) {
        List<ArenaSchematic> possible = new ArrayList<>();

        for (ArenaSchematic schematic : schematics.values()) {
            if (!schematic.isEnabled()) continue;
            if (schematic.getMaxPlayers() < matchSize || schematic.getMinPlayers() > matchSize) continue;
            if (ranked && !schematic.isSupportsRanked()) continue;

            possible.add(schematic);
        }

        return possible.isEmpty() ? null : possible.get(ThreadLocalRandom.current().nextInt(possible.size()));
    }

}