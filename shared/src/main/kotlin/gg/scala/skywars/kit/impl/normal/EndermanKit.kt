package gg.scala.skywars.kit.impl.normal

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class EndermanKit : Kit("Enderman", Material.ENDER_PEARL, 30000, GameType.NORMAL, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.ENDER_PEARL, 1))
    }
}
