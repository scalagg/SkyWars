package gg.scala.skywars.kit.impl.ranked

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BowmanKit : Kit("Bowman", Material.BOW, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.DIAMOND_HELMET)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemBuilder(Material.BOW).enchant(Enchantment.ARROW_DAMAGE, 3).build())
        player.inventory.addItem(ItemBuilder(Material.ARROW, 64).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_SWORD).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_PICKAXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_AXE).build())
    }
}
