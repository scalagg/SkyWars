package gg.pixelarcade.skywars.kit.impl.insane

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class EndermanKit : Kit("Enderman", Material.ENDER_PEARL, 30000, GameType.INSANE, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        //    TODO: Make sure the pearls have a cooldown of 30 seconds for this kit.
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.ENDER_PEARL, 2))
    }
}
