package gg.pixelarcade.skywars.kit.impl.insane

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SniperKit : Kit("Sniper", Material.BOW, 20000, GameType.INSANE, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemBuilder(Material.BOW).enchant(Enchantment.ARROW_DAMAGE, 2).build())
        player.inventory.addItem(ItemStack(Material.ARROW, 24))
    }
}
