package xyz.refinedev.skywars.arena;

import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.api.world.properties.SlimeProperties;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import xyz.refinedev.skywars.pxSkyWars;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.arena
 * @date 25/09/2023 / 23:26
 */

public class WorldManager {

    @Getter
    private final SlimePlugin slimePlugin;
    private final SlimeLoader slimeLoader;
    private final SlimePropertyMap slimePropertyMap;

    public WorldManager(SlimePlugin slimePlugin) {
        File slimeWorldsFolder = new File("slime_worlds");
        if (slimeWorldsFolder.exists() && slimeWorldsFolder.isDirectory()) {
            try {
                FileUtils.cleanDirectory(slimeWorldsFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.slimePlugin = slimePlugin;
        this.slimeLoader = Objects.requireNonNull(slimePlugin).getLoader("file");
        this.slimePropertyMap = new SlimePropertyMap();

        this.slimePropertyMap.setBoolean(SlimeProperties.ALLOW_MONSTERS, false);
        this.slimePropertyMap.setBoolean(SlimeProperties.ALLOW_ANIMALS, false);
        this.slimePropertyMap.setBoolean(SlimeProperties.PVP, true);
        this.slimePropertyMap.setString(SlimeProperties.DIFFICULTY, "normal");
    }

    public World createArenaWorld(ArenaSchematic schematic) {
        String worldName = schematic.getName() + "-swm-" + UUID.randomUUID();

        try {
            slimePropertyMap.setString(SlimeProperties.ENVIRONMENT, World.Environment.NORMAL.name());
            SlimeWorld slimeWorld = slimePlugin.createEmptyWorld(slimeLoader, worldName, true, slimePropertyMap);
            slimePlugin.generateWorld(slimeWorld);

            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                pxSkyWars.getInstance().getLogger().warning("Failed to load world " + worldName + "!");
                return null;
            }

            world.setDifficulty(org.bukkit.Difficulty.NORMAL);
            world.setPVP(true);
            world.setStorm(false);
            world.setThundering(false);
            world.setWeatherDuration(Integer.MAX_VALUE);
            world.setKeepSpawnInMemory(false);
            world.setAutoSave(false);
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setTime(6_000L);

            return world;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteWorld(World world) {
        try {
            Bukkit.unloadWorld(world, true);
            this.slimeLoader.deleteWorld(world.getName());
            world = null;
            System.gc();
        } catch (Exception e) {
            Bukkit.getLogger().warning("Failed to delete world " + world.getName() + "!");
            e.printStackTrace();
        }
    }

}
