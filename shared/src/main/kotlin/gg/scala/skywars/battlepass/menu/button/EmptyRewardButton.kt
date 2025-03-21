package gg.scala.skywars.battlepass.menu.button


import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class EmptyRewardButton : Button() {

    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.MINECART)
            .name("${ChatColor.GRAY}${ChatColor.BOLD}No Reward")
            .build()
    }


}