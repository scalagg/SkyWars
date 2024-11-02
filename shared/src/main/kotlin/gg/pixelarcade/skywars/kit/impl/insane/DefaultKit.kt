package gg.pixelarcade.skywars.kit.impl.insane

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class DefaultKit : Kit("Default", Material.IRON_SWORD, 0, GameType.INSANE, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.IRON_SWORD))
        player.inventory.addItem(ItemStack(Material.IRON_PICKAXE))
        player.inventory.addItem(ItemStack(Material.IRON_AXE))
        player.inventory.addItem(ItemStack(Material.IRON_SPADE))
    }
}
