package gg.pixelarcade.skywars.kit.impl.insane

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SalmonKit : Kit("Salmon", Material.COOKED_FISH, 30000, GameType.INSANE, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.IRON_HELMET)
                .enchant(Enchantment.WATER_WORKER, 1)
                .enchant(Enchantment.OXYGEN, 3).build(),
            ItemBuilder(Material.LEATHER_CHESTPLATE)
                .color(Color.RED).build(),
            ItemBuilder(Material.IRON_LEGGINGS).build(),
            ItemBuilder(Material.LEATHER_BOOTS)
                .color(Color.RED).enchant(Enchantment.DEPTH_STRIDER, 3).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.WATER_BUCKET, 3))
    }
}
