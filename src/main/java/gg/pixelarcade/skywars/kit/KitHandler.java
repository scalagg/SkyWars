package gg.pixelarcade.skywars.kit;

import gg.pixelarcade.skywars.kit.impl.casual.insane.*;
import gg.pixelarcade.skywars.kit.impl.casual.insane.CannoneerKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.DefaultKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.EnchanterKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.EndermanKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.KnightKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.MinerKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.RookieKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.RusherKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.SalmonKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.SniperKit;
import gg.pixelarcade.skywars.kit.impl.casual.insane.TrapperKit;
import gg.pixelarcade.skywars.kit.impl.casual.normal.*;
import gg.pixelarcade.skywars.kit.impl.casual.normal.ArmorerKit;
import gg.pixelarcade.skywars.kit.impl.casual.normal.MagicianKit;
import gg.pixelarcade.skywars.kit.impl.casual.normal.MedicKit;
import gg.pixelarcade.skywars.kit.impl.casual.normal.PyromancerKit;
import gg.pixelarcade.skywars.kit.impl.casual.normal.ScoutKit;
import gg.pixelarcade.skywars.kit.impl.ranked.*;
import lombok.Data;
import gg.pixelarcade.skywars.kit.impl.casual.normal.*;
import gg.pixelarcade.skywars.kit.impl.ranked.*;
import gg.pixelarcade.skywars.SkyWarsPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.kit
 * @date 26/09/2023 / 02:39
 */

@Data
public class KitHandler {

    private final SkyWarsPlugin plugin;
    private final Map<String, Kit> kits = new HashMap<>();

    public KitHandler(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        // Register Kits Below

//        Register Ranked Kits
//        --------------------
        registerKit(new gg.pixelarcade.skywars.kit.impl.ranked.ScoutKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.ranked.MagicianKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.ranked.ArmorerKit());
        registerKit(new ChampionKit());
        registerKit(new BowmanKit());
        registerKit(new AthleteKit());
        registerKit(new BlacksmithKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.ranked.MedicKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.ranked.PyromancerKit());
        registerKit(new TamerKit());
        registerKit(new PaladinKit());
//        --------------------
//        Register Normal Kits
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.DefaultKit());
        registerKit(new ArmorerKit());
        registerKit(new ArmorSmithKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.CannoneerKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.RusherKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.EnchanterKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.EndermanKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.TrapperKit());
        registerKit(new MedicKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.SniperKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.KnightKit());
        registerKit(new PyromancerKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.RookieKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.SalmonKit());
        registerKit(new ScoutKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.normal.MinerKit());
        registerKit(new MagicianKit());
//        --------------------
//        Register Insane Kits
        registerKit(new DefaultKit());
        registerKit(new gg.pixelarcade.skywars.kit.impl.casual.insane.ArmorerKit());
        registerKit(new ArmorsmithKit());
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

