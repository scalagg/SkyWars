package gg.scala.skywars.region.commands

import gg.scala.skywars.SkyWarsLobby
import gg.scala.skywars.SkyWarsLobbyPlugin
import gg.scala.skywars.region.Region
import gg.scala.skywars.region.RegionHandler
import gg.scala.skywars.region.menu.RegionEditorMenu
import gg.scala.skywars.region.selection.RegionSelection
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.annotation.*
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import net.evilblock.cubed.util.Color
import org.bukkit.entity.Player

/**
 * @author alfie
 * @project Skywars / gg.scala.game.region.commands
 * @date 19/10/2023 / 16:12
 */
@AutoRegister
@CommandAlias("region||regions")
@CommandPermission("skywars.region")
object RegionCommands : ScalaCommand() {

    @Default
    @HelpCommand
    fun onHelp(help: CommandHelp) {
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
        val selection = RegionHandler.getSelection(player)
        if (!selection.isValidSelection) {
            player.sendMessage(Color.translate("&cYou must select a region first."))
            return
        }

        val region = Region(selection.region!!.bounds, name)
        RegionHandler.addRegion(region)
        RegionHandler.saveRegions()

        player.removeMetadata(RegionSelection.SELECTION_WAND_KEY, SkyWarsLobbyPlugin.instance)
        player.sendMessage(Color.translate("&eYou have created a region with the name &6$name&e."))
    }

    @Subcommand("delete")
    @Description("Delete a region")
    @CommandPermission("skywars.region.delete")
    fun delete(player: Player, @Name("name") name: String) {
        val region = RegionHandler.getRegion(name)
        if (region == null) {
            player.sendMessage(Color.translate("&cA region with that name does not exist."))
            return
        }

        RegionHandler.removeRegion(region)
        RegionHandler.saveRegions()
        player.sendMessage(Color.translate("&eYou have deleted the region with the name &6$name&e."))
    }

    @Subcommand("editor")
    @Description("Open the region editor")
    @CommandPermission("skywars.region.editor")
    fun editor(player: Player) {
        RegionEditorMenu().openMenu(player)
    }
}
