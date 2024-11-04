package gg.pixelarcade.skywars.ability.command

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.ability.Ability
import gg.scala.commons.acf.BaseCommand
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.annotation.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.command
 * @date 03/10/2023 / 06:32
 */
@CommandAlias("ability")
class AbilityCommand : BaseCommand() {
    @Subcommand("give")
    @CommandPermission("skywars.ability.give")
    fun give(player: Player, @Name("abilityName") abilityName: String, @Optional @Name("amount") amount: Int) {
        val item = SkyWarsGame.gameInstance.abilityHandler.getAbilityById(abilityName)!!
            .getItem()
        item.amount = amount

        player.inventory.addItem(item)
        player.sendMessage("§eYou have been given the ability: §6$abilityName")
    }

    @Subcommand("list")
    @CommandPermission("skywars.ability.list")
    fun list(sender: CommandSender) {
        sender.sendMessage("§aAbility List:")
        SkyWarsGame.gameInstance.abilityHandler.cache.forEach { (string: String?, ability: Ability?) ->
            sender.sendMessage(
                "§e$string"
            )
        }
    }

    @HelpCommand
    @Syntax("[page]")
    fun doHelp(sender: CommandSender?, help: CommandHelp) {
        help.showHelp()
    }
}
