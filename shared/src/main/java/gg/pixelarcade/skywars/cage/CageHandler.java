package gg.pixelarcade.skywars.cage;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 18/03/2024 / 22:05
 */

@Getter
public class CageHandler {

    private final List<Cage> cages = new ArrayList<>();

    public CageHandler() {
        loadCages();

        if (cages.isEmpty())
            SkyWarsShared.getInstance().getPlugin().getLogger().severe("No cages were loaded!");
    }

    /**
     * Load all cages from the schematic folder and cache them
     * File Name: Example_Solos.schematic
     */
    public void loadCages() {
        File schematicFolder = new File(SkyWarsShared.getInstance().getPlugin().getDataFolder(), "schematics/cages");
        if (!schematicFolder.exists())
            schematicFolder.mkdirs();

        for (File file : schematicFolder.listFiles()) {
            if (!file.getName().endsWith(".schematic"))
                continue;

            String[] split = file.getName().split("_");
            String name = split[0];
            GameTeamType type = GameTeamType.valueOf(split[1].toUpperCase());

            SkyWarsShared.getInstance().getPlugin().getLogger().info("Loaded cage (" + name
                    + ") for " + type.name() + " games");
            cages.add(new Cage(name, type));
        }
    }

    /**
     * Get a random cage for the specified game type
     *
     * @param gameType the game type
     * @return a random cage
     */

    public Cage getRandomCage(GameTeamType gameType) {
        List<Cage> cages = new ArrayList<>();
        for (Cage cage : this.cages) {
            if (cage.getGameType() == gameType)
                cages.add(cage);
        }

        return cages.get((int) (Math.random() * cages.size()));
    }

    /**
     * Get a cage by name
     *
     * @param name the name
     * @return the cage
     */
    public Cage getCage(String name) {
        for (Cage cage : cages) {
            if (cage.getName().equalsIgnoreCase(name))
                return cage;
        }

        return null;
    }

}
