package gg.pixelarcade.skywars.kit;

import gg.pixelarcade.skywars.kit.impl.normal.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit
 * @date 26/09/2023 / 02:39
 */

@Data
public class KitHandler {

    private final Map<String, Kit> kits = new HashMap<>();

    public KitHandler() {
        this.registerKit(new DefaultKit());
        this.registerKit(new RookieKit());
        this.registerKit(new ArmorerKit());
        this.registerKit(new ArmorsmithKit());
        this.registerKit(new CannoneerKit());
        this.registerKit(new EnchanterKit());
        this.registerKit(new EndermanKit());
        this.registerKit(new KnightKit());
        this.registerKit(new MagicianKit());
        this.registerKit(new MedicKit());
        this.registerKit(new MinerKit());
        this.registerKit(new MinerKit());
        this.registerKit(new PyromancerKit());
        this.registerKit(new RusherKit());
        this.registerKit(new SalmonKit());
        this.registerKit(new ScoutKit());
        this.registerKit(new SniperKit());
        this.registerKit(new TrapperKit());
    }

    public void registerKit(Kit kit) {
        kits.put(kit.getName().toLowerCase(), kit);
    }

    public Kit getKitByName(String name) {
        return kits.get(name.toLowerCase());
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

