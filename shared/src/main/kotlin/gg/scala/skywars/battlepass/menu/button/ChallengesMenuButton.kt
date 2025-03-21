package gg.scala.skywars.battlepass.menu.button

import gg.scala.skywars.battlepass.BattlePass
import gg.scala.skywars.battlepass.BattlePassHandler
import gg.scala.skywars.battlepass.challenge.menu.ChallengeMenu
import gg.scala.skywars.battlepass.daily.DailyChallengeHandler
import gg.scala.skywars.battlepass.weekly.WeeklyChallengeHandler
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter


import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengesMenuButton(private val daily: Boolean, private val weekly: Boolean, private val battlePass: BattlePass) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val description = arrayListOf<String>()

        if (this.daily) {
            description.addAll(TextSplitter.split(text = "New daily challenges will be generated in ${DailyChallengeHandler.getSession().getTimeRemaining()}.", linePrefix = ChatColor.GRAY.toString()))
        }
        if (this.weekly) {
            description.addAll(TextSplitter.split(text = "New weekly challenges will be generated in ${WeeklyChallengeHandler.getSession().getTimeRemaining()}.", linePrefix = ChatColor.GRAY.toString()))
        }


        if (!this.daily && !this.weekly && !this.battlePass.isPremium()) {
            description.add("")
            description.addAll(TextSplitter.split(text = BattlePassHandler.NOT_PREMIUM, linePrefix = "${ChatColor.RED}"))
        }


        return ItemBuilder.of(Material.BOOK)
            .name("${ChatColor.GOLD}${ChatColor.BOLD}${if (daily) "Daily" else if(weekly) { "Weekly" } else { "Premium"} } Challenges")
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {

        if (!this.daily && !this.weekly && !this.battlePass.isPremium()) {
            player.sendMessage("${ChatColor.RED}${BattlePassHandler.NOT_PREMIUM}")
            return
        }

        ChallengeMenu(this.daily).openMenu(player)
    }

}