package gg.scala.skywars.kit.impl.insane

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TrapperKit : Kit("Trap Master", Material.PISTON_BASE, 20000, GameType.INSANE, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.TRIPWIRE_HOOK, 4))
        player.inventory.addItem(ItemStack(Material.WEB, 24))
        player.inventory.addItem(ItemStack(Material.PISTON_BASE, 8))
        player.inventory.addItem(ItemStack(Material.SLIME_BALL, 8))
        player.inventory.addItem(ItemStack(Material.REDSTONE, 32))
        player.inventory.addItem(ItemStack(Material.LEVER, 2))
        player.inventory.addItem(ItemStack(Material.DISPENSER, 4))
        player.inventory.addItem(ItemStack(Material.ARROW, 32))
    }
}
