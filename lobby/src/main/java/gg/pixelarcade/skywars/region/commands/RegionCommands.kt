package gg.pixelarcade.skywars.region.commands

import gg.pixelarcade.skywars.SkyWarsLobby
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin
import gg.pixelarcade.skywars.region.Region
import gg.pixelarcade.skywars.region.RegionHandler
import gg.pixelarcade.skywars.region.menu.RegionEditorMenu
import gg.pixelarcade.skywars.region.selection.RegionSelection
import gg.scala.commons.acf.BaseCommand
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.annotation.*
import net.evilblock.cubed.util.Color
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region.commands
 * @date 19/10/2023 / 16:12
 */
@CommandAlias("region||regions")
@CommandPermission("skywars.region")
class RegionCommands : BaseCommand() {
    private val handler: RegionHandler = SkyWarsLobby.lobbyInstance.regionHandler

    @HelpCommand
    @Syntax("[page]")
    fun doHelp(sender: CommandSender?, help: CommandHelp) {
        help.showHelp()
    }

    @Subcommand("wand")
    @CommandPermission("skywars.region.wand")
    fun wand(player: Player) {
        if (player.inventory.contains(RegionSelection.SELECTION_WAND)) return

        //shouldn't make a region in a arena world
        if (player.world.name.contains("swm")) {
            player.sendMessage(Color.translate("&cYou cannot use this command in this world."))
            return
        }

        player.inventory.addItem(RegionSelection.SELECTION_WAND)
        player.sendMessage(Color.translate("&aYou have been given a region selection wand."))
    }

    @Subcommand("create")
    @Description("Create a region")
    @CommandPermission("skywars.region.create")
    fun create(player: Player, @Name("name") name: String) {
        val selection = handler.getSelection(player)
        if (!selection.isValidSelection) {
            player.sendMessage(Color.translate("&cYou must select a region first."))
            return
        }

        val region = Region(selection.region!!.bounds, name)
        handler.addRegion(region)
        handler.saveRegions()

        player.removeMetadata(RegionSelection.SELECTION_WAND_KEY, SkyWarsLobbyPlugin.instance)
        player.sendMessage(Color.translate("&eYou have created a region with the name &6$name&e."))
    }

    @Subcommand("delete")
    @Description("Delete a region")
    @CommandPermission("skywars.region.delete")
    fun delete(player: Player, @Name("name") name: String) {
        val region = handler.getRegion(name)
        if (region == null) {
            player.sendMessage(Color.translate("&cA region with that name does not exist."))
            return
        }

        handler.removeRegion(region)
        handler.saveRegions()
        player.sendMessage(Color.translate("&eYou have deleted the region with the name &6$name&e."))
    }

    @Subcommand("editor")
    @Description("Open the region editor")
    @CommandPermission("skywars.region.editor")
    fun editor(player: Player) {
        RegionEditorMenu().openMenu(player)
    }
}
