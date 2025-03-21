package gg.scala.skywars.battlepass.challenge.menu.button

import gg.scala.skywars.battlepass.challenge.ChallengeHandler
import gg.scala.skywars.battlepass.challenge.menu.ChallengeEditMenu
import gg.scala.skywars.battlepass.challenge.menu.ChallengeSelectTypeMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.prompt.EzPrompt
import net.evilblock.cubed.util.bukkit.prompt.InputPrompt

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengeAddButton(private val daily: Boolean, private val weekly: Boolean) : Button() {

    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.NETHER_STAR)
            .name(buildString{
                append("${ChatColor.YELLOW}${ChatColor.BOLD}Create New ")

                if (this@ChallengeAddButton.daily) {
                    append("Daily ")
                }

                if (this@ChallengeAddButton.weekly) {
                    append("Weekly ")
                }

                append("Challenge")
            })
            .build()
    }
    

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        player.closeInventory()
        InputPrompt().acceptInput{_, input->

            if (ChallengeHandler.getChallengeById(input) != null) {
                player.sendMessage("${ChatColor.RED}A challenge's ID must be unique, and a challenge with the ID `$input` already exists.")
                return@acceptInput
            }

            ChallengeSelectTypeMenu { challengeType ->
                player.closeInventory()
                challengeType.startSetupPrompt(player, input.lowercase()) { challenge ->
                    challenge.daily = this.daily
                    challenge.weekly = this.weekly

                    ChallengeHandler.register(challenge)

                    player.sendMessage("${ChatColor.GREEN}Successfully created a new challenge.")

                    ChallengeEditMenu(this.daily,this.weekly, challenge).openMenu(player)
                }
            }.openMenu(player)}
            .withText("${ChatColor.GREEN}Please input an ID for the challenge.")
            .withRegex(EzPrompt.IDENTIFIER_REGEX)
            .start(player)
    }

}