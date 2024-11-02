package gg.pixelarcade.skywars.kit.impl.normal

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TrapperKit : Kit("Trap Master", Material.PISTON_BASE, 20000, GameType.NORMAL, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.TRIPWIRE_HOOK, 2))
        player.inventory.addItem(ItemStack(Material.WEB, 8))
        player.inventory.addItem(ItemStack(Material.PISTON_BASE, 4))
        player.inventory.addItem(ItemStack(Material.SLIME_BALL, 4))
        player.inventory.addItem(ItemStack(Material.REDSTONE, 16))
        player.inventory.addItem(ItemStack(Material.LEVER, 1))
        player.inventory.addItem(ItemStack(Material.DISPENSER, 2))
        player.inventory.addItem(ItemStack(Material.ARROW, 32))
    }
}
