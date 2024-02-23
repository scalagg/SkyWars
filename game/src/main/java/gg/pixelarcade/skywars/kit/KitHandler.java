package gg.pixelarcade.skywars.kit;

import gg.pixelarcade.skywars.kit.impl.casual.normal.DefaultKit;
import gg.pixelarcade.skywars.kit.impl.casual.normal.RookieKit;
import lombok.Data;
import gg.pixelarcade.skywars.SkyWarsPlugin;

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
        registerKit(new DefaultKit());
        registerKit(new RookieKit());
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

