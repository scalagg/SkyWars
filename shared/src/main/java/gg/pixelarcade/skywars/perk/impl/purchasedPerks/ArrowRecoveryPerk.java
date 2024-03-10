package gg.pixelarcade.skywars.perk.impl.purchasedPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ArrowRecoveryPerk extends Perk {

    public ArrowRecoveryPerk() {
        super("Arrow Recovery Perk", Material.ARROW, 1000, PerkRarity.RARE,
                "25% chance of getting your arrow back on a bow hit.");
    }

    @Override
    public void apply(Player player) {
        Random random = new Random();
        int chance = random.nextInt(1, 4);

        if (chance == 4) {
            player.getInventory().addItem(
                    new ItemStack(Material.ARROW)
            );
        }
    }
}
