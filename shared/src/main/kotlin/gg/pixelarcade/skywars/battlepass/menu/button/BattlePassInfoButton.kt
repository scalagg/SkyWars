package gg.pixelarcade.skywars.battlepass.menu.button


import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.scala.lemon.LemonConstants
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BattlePassInfoButton(private val battlePass: BattlePass) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("${ChatColor.GRAY}Current XP: ${ChatColor.GOLD}${this.battlePass.getExperience()}")
        lore.add("${ChatColor.GRAY}Current Tier: ${ChatColor.GOLD}${this.battlePass.getCurrentTier()?.number ?: 0}")

        if (!this.battlePass.isPremium()) {
            lore.add("")
            lore.addAll(TextSplitter.split(text = "The Premium BattlePass gives you access to extra rewards and content through premium challenges. Quit missing out and purchase from our store at ${ChatColor.YELLOW}${ChatColor.BOLD}${ChatColor.UNDERLINE}${LemonConstants.STORE_LINK}${ChatColor.RED}.", linePrefix = "${ChatColor.RED}"))
        }

        return ItemBuilder.of(Material.NETHER_STAR)
            .name("${ChatColor.GOLD}${ChatColor.BOLD}BattlePass")
            .setLore(lore)
            .build()
    }

}