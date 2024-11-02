package gg.pixelarcade.skywars.kit.impl.insane

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ArmorerKit : Kit("Armorer", Material.IRON_CHESTPLATE, 20000, GameType.INSANE, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.DIAMOND_CHESTPLATE).build(),
            ItemBuilder(Material.CHAINMAIL_LEGGINGS).build(),
            ItemBuilder(Material.LEATHER_BOOTS)
                .color(Color.AQUA).build()
        )

    override fun apply(player: Player) {
    }
}
