package gg.scala.skywars.battlepass.challenge


import net.evilblock.cubed.menu.Button
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

interface ChallengeType : Listener {

    fun getName(): String
    fun getIcon():ItemStack
    fun getDescription():String

    fun startSetupPrompt(player: Player,id: String, lambda: (Challenge) -> Unit)

    fun getEditorButtons(challenge: Challenge): List<Button> {
        return emptyList()
    }

}