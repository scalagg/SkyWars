package gg.pixelarcade.skywars.kit.impl.normal

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class RookieKit : Kit("Rookie", Material.LEATHER_CHESTPLATE, 15000, GameType.NORMAL, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.LEATHER_BOOTS)
                .color(Color.GRAY).build(),
            ItemBuilder(Material.LEATHER_LEGGINGS)
                .color(Color.GRAY).build(),
            ItemBuilder(Material.LEATHER_CHESTPLATE)
                .color(Color.GRAY).build(),
            ItemBuilder(Material.LEATHER_HELMET)
                .color(Color.GRAY).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.STONE_SWORD))
        player.inventory.addItem(ItemStack(Material.GLASS, 16))
        player.inventory.addItem(ItemStack(Material.COOKED_BEEF, 16))
    }
}
