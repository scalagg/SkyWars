package xyz.refinedev.skywars.kit;

import lombok.Data;
import xyz.refinedev.skywars.kit.impl.ranked.*;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.kit
 * @date 26/09/2023 / 02:39
 */

@Data
public class KitHandler {

    private final pxSkyWars plugin;
    private final Map<String, Kit> kits = new HashMap<>();

    public KitHandler(pxSkyWars plugin) {
        this.plugin = plugin;

        // Register Kits Below

//        Register Ranked Kits
//        --------------------
        registerKit(new ScoutKit());
        registerKit(new MagicianKit());
        registerKit(new ArmorerKit());
        registerKit(new ChampionKit());
        registerKit(new BowmanKit());
        registerKit(new AthleteKit());
        registerKit(new BlacksmithKit());
        registerKit(new MedicKit());
        registerKit(new PyromancerKit());
        registerKit(new TamerKit());
        registerKit(new PaladinKit());

    }

    public void registerKit(Kit kit) {
        kits.put(kit.getName().toLowerCase(), kit);
    }

    public Kit getKitByName(String name) {
        return kits.get(name);
    }

    public Collection<Kit> getKits() {
        return kits.values();
    }

    public Kit getDefaultKit() {
        for (Kit kit : kits.values()) {
            if (kit.isDefault()) {
                return kit;
            }
        }

        return null;
    }
}

