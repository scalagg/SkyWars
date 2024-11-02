package gg.pixelarcade.skywars.kit.impl.ranked

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.concurrent.ThreadLocalRandom

class BlacksmithKit : Kit("Blacksmith", Material.ANVIL, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        for (i in 0..4) {
            val enchantmentLevel = ThreadLocalRandom.current().nextInt(3) + 1
            val enchantment = Enchantment.values()[ThreadLocalRandom.current()
                .nextInt(Enchantment.values().size)]
            val enchantedBook = ItemStack(Material.ENCHANTED_BOOK)
            val meta = enchantedBook.itemMeta as EnchantmentStorageMeta
            meta.addStoredEnchant(enchantment, enchantmentLevel, true)
            enchantedBook.setItemMeta(meta)
            player.inventory.addItem(enchantedBook)
        }
    }
}
