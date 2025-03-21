package gg.scala.skywars.kit.impl.normal

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ArmorsmithKit : Kit("Armorsmith", Material.ANVIL, 15000, GameType.NORMAL, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.ANVIL))
        player.inventory.addItem(
            ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build()
        )
        player.inventory.addItem(ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.DAMAGE_ALL, 1).build())
        player.inventory.addItem(ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.ARROW_DAMAGE, 1).build())
        player.inventory.addItem(ItemStack(Material.EXP_BOTTLE, 64))
    }
}
