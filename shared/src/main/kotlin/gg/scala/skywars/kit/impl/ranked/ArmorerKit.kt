package gg.scala.skywars.kit.impl.ranked

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ArmorerKit : Kit("Armorer", Material.DIAMOND_CHESTPLATE, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .build(),
            ItemBuilder(Material.DIAMOND_BOOTS).build()

        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemBuilder(Material.IRON_PICKAXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_AXE).build())
    }
}
