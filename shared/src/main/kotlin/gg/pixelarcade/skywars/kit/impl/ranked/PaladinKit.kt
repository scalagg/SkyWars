package gg.pixelarcade.skywars.kit.impl.ranked

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class PaladinKit : Kit("Paladin", Material.DIAMOND_SWORD, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
    }
}
