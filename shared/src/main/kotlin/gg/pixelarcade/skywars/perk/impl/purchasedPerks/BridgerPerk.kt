package gg.pixelarcade.skywars.perk.impl.purchasedPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player

//TODO: Not sure how to implement this yet
class BridgerPerk : Perk(
    "Bridger Perk", Material.WOOD, 1000, PerkRarity.RARE,
    "Grants 25% chance to not consume placeable blocks."
) {
    override fun apply(player: Player) {
    }
}
