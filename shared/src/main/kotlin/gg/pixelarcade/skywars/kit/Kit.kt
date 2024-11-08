package gg.pixelarcade.skywars.kit

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.util.CalenderUtil
import gg.pixelarcade.skywars.util.PlayerUtil

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit
 * @date 26/09/2023 / 02:37
 */

abstract class Kit protected constructor(
    val name: String,
    val displayMaterial: Material,
    val cost: Int,
    val gameType: GameType,
    val rarity: KitRarity
) {
    abstract val armor: Array<ItemStack?>

    // TODO: This method should be to set the items in a map or something.
    // So the player can edit the kit.
    // For equipping the kit, that should be in the game module.
    abstract fun apply(player: Player)

    val potionEffects: List<PotionEffect>?
        get() = null

    fun equipKit(player: Player) {
        PlayerUtil.resetPlayer(player)

        apply(player)
        player.inventory.armorContents = armor
    }

    val isDefault: Boolean
        get() = false

    val isFree: Boolean
        get() = cost == 0 || CalenderUtil.isWeekend()
}
