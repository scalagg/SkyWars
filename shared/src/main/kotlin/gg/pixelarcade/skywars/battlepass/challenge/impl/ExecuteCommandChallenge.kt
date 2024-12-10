package gg.pixelarcade.skywars.battlepass.challenge.impl



import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeType
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.util.PlayerUtil
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.Prompt
import org.bukkit.conversations.StringPrompt
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ExecuteCommandChallenge(id: String,var command: String) : Challenge(id) {

    override fun getText(): String {
        return "Execute the '$command' command"
    }

    override fun getType(): ChallengeType {
        return ExecuteCommandChallengeType
    }

    override fun meetsCompletionRequirements(player: Player,battlePass: BattlePass): Boolean {
        return if (this.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).hasExecutedCommand(this.command)
        } else if (this.weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).hasExecutedCommand(this.command)
        }else {
            battlePass.hasExecutedCommand(this.command)
        }
    }

    object ExecuteCommandChallengeType : ChallengeType {
        override fun getName(): String {
            return "Execute Command"
        }

        override fun getDescription(): String {
            return "Execute a certain command"
        }

        override fun getIcon(): ItemStack {
            return ItemStack(Material.DIAMOND_PICKAXE)
        }

        override fun startSetupPrompt(player: Player, id: String, lambda: (Challenge) -> Unit) {

            PlayerUtil.startPrompt(player,object : StringPrompt() {

                override fun getPromptText(p0: ConversationContext?): String {
                    return "${ChatColor.GREEN}Please type a command: "
                }

                override fun acceptInput(p0: ConversationContext?,input: String): Prompt? {
                    lambda.invoke(ExecuteCommandChallenge(id,input.trim()))
                    return END_OF_CONVERSATION
                }
            })


        }
    }

}