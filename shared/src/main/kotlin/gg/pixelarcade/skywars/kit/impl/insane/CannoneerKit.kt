package gg.pixelarcade.skywars.kit.impl.insane

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CannoneerKit : Kit("Canoneer", Material.TNT, 30000, GameType.INSANE, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.DIAMOND_BOOTS)
                .enchant(Enchantment.PROTECTION_EXPLOSIONS, 3)
                .enchant(Enchantment.PROTECTION_FALL, 4)
                .build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.TNT, 24))
        player.inventory.addItem(ItemStack(Material.REDSTONE_BLOCK, 4))
        player.inventory.addItem(ItemStack(Material.WATER_BUCKET))
        player.inventory.addItem(ItemStack(Material.WOOD_PLATE, 4))
    }
}
