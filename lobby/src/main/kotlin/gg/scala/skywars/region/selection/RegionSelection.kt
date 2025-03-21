package gg.scala.skywars.region.selection

import gg.scala.skywars.region.Region
import gg.scala.skywars.util.cuboid.Cuboid
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.scala.game.region.selection
 * @date 19/10/2023 / 16:16
 */
class RegionSelection {
    var position1: Location? = null
    var position2: Location? = null

    val isValidSelection: Boolean
        get() = position1 != null && position2 != null

    val region: Region?
        get() {
            if (!isValidSelection) return null

            return Region(
                _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid(
                    position1,
                    position2
                )
            )
        }

    companion object {
        const val SELECTION_WAND_KEY: String = "SelectionWand"
        @JvmField
        val SELECTION_WAND: ItemStack = ItemBuilder(Material.GOLD_AXE)
            .name(Color.translate("&f${Constants.ARROW_RIGHT} &e&lSelection Wand &f${Constants.ARROW_LEFT}"))
            .build()
    }
}
