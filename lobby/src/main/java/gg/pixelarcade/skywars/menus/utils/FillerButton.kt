package gg.pixelarcade.skywars.menus.utils

import com.cryptomorin.xseries.XMaterial
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class FillerButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE)
            .name("&r")
            .build()
    }
}
