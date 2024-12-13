package gg.pixelarcade.skywars.battlepass.challenge.menu.button

import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ChallengeButton(private val challenge: Challenge,private val battlePass: BattlePass) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.addAll(TextSplitter.split(text = challenge.getText(), linePrefix = "${ChatColor.GRAY}"))

        if (this.challenge.isProgressive()) {
            lore.add("")
            lore.add(this.challenge.getProgressText(player,this.battlePass))
        }

        if (this.battlePass.hasCompletedChallenge(player,this.challenge)) {
            lore.add("")
            lore.add("${ChatColor.GREEN}You've completed this challenge!")
        }

        val builder = ItemBuilder.of(Material.BOOK)
            .name("${this.challenge.name} ${ChatColor.GRAY}(${this.challenge.rewardXp} ${ChatColor.GOLD}${Constants.EXP_SYMBOL}${ChatColor.GRAY})")
            .setLore(lore)

        if (this.battlePass.hasCompletedChallenge(player,this.challenge)) {
            builder.glow()
        }

        return builder.build()
    }

}