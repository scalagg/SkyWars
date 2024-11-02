package gg.pixelarcade.skywars.perk

import lombok.Getter
import org.bukkit.Material
import org.bukkit.entity.Player

@Getter
abstract class Perk protected constructor(
    val name: String,
    val displayMaterial: Material,
    val cost: Int,
    val rarity: PerkRarity,
    val description: String
) {
    open val isKitPerk: Boolean
        get() = false
    open val isGlobalPerk: Boolean
        get() = false

    abstract fun apply(player: Player)
}