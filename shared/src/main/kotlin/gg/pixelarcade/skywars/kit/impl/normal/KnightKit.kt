package gg.pixelarcade.skywars.kit.impl.normal

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class KnightKit : Kit("Knight", Material.IRON_SWORD, 30000, GameType.NORMAL, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.CHAINMAIL_BOOTS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 1).enchant(Enchantment.DURABILITY, 4)
                .build()
        )
    }
}
