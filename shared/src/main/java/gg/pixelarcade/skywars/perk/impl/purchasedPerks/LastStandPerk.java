package gg.pixelarcade.skywars.perk.impl.purchasedPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;

//TODO: Finish
public class LastStandPerk extends Perk {

    public LastStandPerk() {
        super("Last Stand", Material.APPLE, 1000, PerkRarity.RARE);
    }

    @Override
    public void apply(Player player) {

    }
}
