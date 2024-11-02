package gg.pixelarcade.skywars.perk.impl.purchasedPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player

//TODO: Not sure how to implement this yet
class EnvironmentalExpertPerk :
    Perk(
        "Environmental Expert Perk", Material.LEATHER_CHESTPLATE, 1000, PerkRarity.RARE,
        "Reduces environmental damage by 20%"
    ) {
    /*
   
       @Override
       public void apply(Player player) {
           player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 1));
       }
        */
    override fun apply(player: Player) {
    }
}
