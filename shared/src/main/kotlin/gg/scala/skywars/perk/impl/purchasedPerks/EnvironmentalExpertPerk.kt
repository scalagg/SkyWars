package gg.scala.skywars.perk.impl.purchasedPerks

import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player

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
