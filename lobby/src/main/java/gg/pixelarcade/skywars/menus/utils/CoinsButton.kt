package gg.pixelarcade.skywars.menus.utils

import com.cryptomorin.xseries.XMaterial
import gg.pixelarcade.skywars.SkyWarsShared
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CoinsButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        val totalCoins = SkyWarsShared.instance.getProfile(player.uniqueId).coins

        return ItemBuilder(XMaterial.EMERALD)
            .name(ChatColor.GOLD.toString() + "Your Coins: " + totalCoins)
            .build()
    }
}
