package xyz.refinedev.skywars.kit;

import lombok.Data;
import xyz.refinedev.skywars.kit.impl.casual.normal.*;
import xyz.refinedev.skywars.kit.impl.casual.normal.ArmorerKit;
import xyz.refinedev.skywars.kit.impl.casual.normal.MagicianKit;
import xyz.refinedev.skywars.kit.impl.casual.normal.MedicKit;
import xyz.refinedev.skywars.kit.impl.casual.normal.PyromancerKit;
import xyz.refinedev.skywars.kit.impl.casual.normal.ScoutKit;
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
        registerKit(new xyz.refinedev.skywars.kit.impl.ranked.ScoutKit());
        registerKit(new xyz.refinedev.skywars.kit.impl.ranked.MagicianKit());
        registerKit(new xyz.refinedev.skywars.kit.impl.ranked.ArmorerKit());
        registerKit(new ChampionKit());
        registerKit(new BowmanKit());
        registerKit(new AthleteKit());
        registerKit(new BlacksmithKit());
        registerKit(new xyz.refinedev.skywars.kit.impl.ranked.MedicKit());
        registerKit(new xyz.refinedev.skywars.kit.impl.ranked.PyromancerKit());
        registerKit(new TamerKit());
        registerKit(new PaladinKit());
//        --------------------
//        Register Normal Kits
        registerKit(new DefaultKit());
        registerKit(new ArmorerKit());
        registerKit(new ArmorSmithKit());
        registerKit(new CannoneerKit());
        registerKit(new RusherKit());
        registerKit(new EnchanterKit());
        registerKit(new EndermanKit());
        registerKit(new TrapperKit());
        registerKit(new MedicKit());
        registerKit(new SniperKit());
        registerKit(new KnightKit());
        registerKit(new PyromancerKit());
        registerKit(new RookieKit());
        registerKit(new SalmonKit());
        registerKit(new ScoutKit());
        registerKit(new MinerKit());
        registerKit(new MagicianKit());
//        --------------------


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

