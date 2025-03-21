package gg.scala.skywars.command

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.arena.container.ArenaDataSync
import gg.scala.skywars.arena.model.SkyWarsArena
import gg.scala.skywars.editor.arena.ArenaEditorSystem
import gg.scala.skywars.editor.arena.ArenaEditorSystem.scanPlayer
import gg.scala.skywars.editor.arena.ArenaEditorSystem.sendRequiredSetup
import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.map.MapManageServices
import gg.scala.skywars.map.MapManageServices.slimePlugin
import gg.scala.skywars.utils.SafeLocation
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.ConditionFailedException
import gg.scala.commons.acf.annotation.*
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.annotations.commands.customizer.CommandManagerCustomizer
import gg.scala.commons.command.ScalaCommandManager
import gg.scala.commons.issuer.ScalaPlayer
import net.evilblock.cubed.util.CC
import org.bukkit.entity.Player

/**
 * @date 11/19/2024
 * @author Moose1301
 */
@AutoRegister
@CommandAlias("arena")
@CommandPermission("op")
class ArenaCommand {
    @Default
    @HelpCommand
    fun onHelp(help: CommandHelp) {
        help.showHelp()
    }


    @CommandManagerCustomizer
    fun customize(manager: ScalaCommandManager) {
        manager.commandCompletions.registerAsyncCompletion("arenas") {
            SkyWarsShared.instance.arenaManager.getArenaIds()
        }
        manager.commandContexts.registerContext(SkyWarsArena::class.java) {
            val arg = it.popFirstArg()

            ArenaDataSync.cached().arenas[arg.lowercase()]
                ?: throw ConditionFailedException("No arena by that name was found.")
        }
        manager.commandContexts.registerContext(GameType::class.java) {
            val arg = it.popFirstArg()

            try {
                GameType.valueOf(arg.lowercase())
            } catch (ex: Exception) {
                throw ConditionFailedException("No arena by that name was found.")
            }
        }

    }

    @Subcommand("create")
    fun handleCreate(player: ScalaPlayer, id: String, gameType: GameType, displayName: String) {
        if (ArenaDataSync.cached().arenas.containsKey(id)) {
            player.sendMessage(CC.RED + "A arena with that name already exists.")
            return
        }
        val arena = SkyWarsArena(id, displayName, gameType, false);

        player.sendMessage(CC.GREEN + "Created arena with id $id.")

        ArenaDataSync.cached().arenas[id] = arena

        val loader = MapManageServices.loader;
        if (!loader.worldExists(id))
            slimePlugin.createEmptyWorld(MapManageServices.loader, id, false, MapManageServices.propertyMap)

    }

    @Subcommand("edit")
    fun handleEdit(player: Player, arena: SkyWarsArena) {
        if (!player.world.name.equals("world")) {
            player.sendMessage(CC.RED + "You are currently editing another Arena or Cage")
            return
        }
        ArenaEditorSystem.startEditing(player, arena)
    }

    @Subcommand("scan")
    fun handleScan(player: Player) {
        if (!player.hasMetadata("ARENA-EDIT")) {
            player.sendMessage(CC.RED + "You are currently not editing a Arena")
            return
        }
        val arena: SkyWarsArena = ArenaDataSync.cached().arenas[player.getMetadata("ARENA-EDIT")[0].value()]!!

        scanPlayer(player, arena)
        sendRequiredSetup(player, arena)
    }

    @Subcommand("setlower")
    fun handleLower(player: Player) {
        if (!player.hasMetadata("ARENA-EDIT")) {
            player.sendMessage(CC.RED + "You are currently not editing a Arena")
            return
        }
        val arena: SkyWarsArena = ArenaDataSync.cached().arenas[player.getMetadata("ARENA-EDIT")[0].value()]!!
        arena.boundsLower = SafeLocation(player.location)
        player.sendMessage(CC.GREEN + "Set Lower Bound")
    }
    @Subcommand("setupper")
    fun handleUpper(player: Player) {
        if (!player.hasMetadata("ARENA-EDIT")) {
            player.sendMessage(CC.RED + "You are currently not editing a Arena")
            return
        }
        val arena: SkyWarsArena = ArenaDataSync.cached().arenas[player.getMetadata("ARENA-EDIT")[0].value()]!!
        arena.boundsUpper = SafeLocation(player.location)
        player.sendMessage(CC.GREEN + "Set Upper Bound")
    }
    @Subcommand("teams")
    fun handleType(player: Player) {
        if (!player.hasMetadata("ARENA-EDIT")) {
            player.sendMessage(CC.RED + "You are currently not editing a Arena")
            return
        }
        val arena: SkyWarsArena = ArenaDataSync.cached().arenas[player.getMetadata("ARENA-EDIT")[0].value()]!!

        arena.supportsTeams = !arena.supportsTeams

        player.sendMessage(CC.GREEN + "Set Team Support to: " + if(arena.supportsTeams) { CC.GREEN + "Enabled" } else { CC.RED + "Disabled"})
    }
}
