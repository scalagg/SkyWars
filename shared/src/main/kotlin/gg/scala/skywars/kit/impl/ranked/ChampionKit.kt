package gg.scala.skywars.kit.impl.ranked

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ChampionKit : Kit("Champion", Material.DIAMOND_SWORD, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemBuilder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 2).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_AXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_PICKAXE).build())
        player.inventory.addItem(ItemBuilder(Material.ANVIL).build())
        player.inventory.addItem(ItemBuilder(Material.ENCHANTED_BOOK, 3).enchant(Enchantment.DAMAGE_ALL, 1).build())
    }
}
