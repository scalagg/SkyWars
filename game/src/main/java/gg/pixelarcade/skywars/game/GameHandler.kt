package gg.pixelarcade.skywars.game

import com.lunarclient.apollo.module.richpresence.ServerRichPresence
import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.SkyWarsPlugin
import gg.pixelarcade.skywars.arena.engine.CgsSkywarsArena
import gg.pixelarcade.skywars.arena.model.SkyWarsArena
import gg.pixelarcade.skywars.cages.CageCategory
import gg.pixelarcade.skywars.cages.CageCosmeticLike
import gg.pixelarcade.skywars.cages.LocalCage
import gg.pixelarcade.skywars.cages.SchemaCageDefaultSchemas
import gg.pixelarcade.skywars.cages.sync.SchemaCageDataSync
import gg.pixelarcade.skywars.chests.LootableChest
import gg.pixelarcade.skywars.chests.type.RefillType
import gg.pixelarcade.skywars.game.event.GameEndEvent
import gg.pixelarcade.skywars.game.event.GameStartEvent
import gg.pixelarcade.skywars.game.event.PlayerJoinGameEvent
import gg.pixelarcade.skywars.game.event.PlayerLeaveGameEvent
import gg.pixelarcade.skywars.game.state.GameState
import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.game.task.GameTntGeneratorTask
import gg.pixelarcade.skywars.gamemode.SkywarsCgsGameMode
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.map.GameMap
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import gg.pixelarcade.skywars.setDiscordPresence
import gg.pixelarcade.skywars.util.DefaultFontInfo
import gg.pixelarcade.skywars.util.PlayerUtil
import gg.pixelarcade.skywars.util.ServerUtil
import gg.pixelarcade.skywars.util.Tuple
import gg.pixelarcade.skywars.util.cuboid.Cuboid
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.teams.CgsGameTeam
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.agnostic.sync.ServerSync
import gg.scala.lemon.util.QuickAccess
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.Tasks
import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import studio.pinkcloud.deploy.spigot.api.Deployer
import java.util.*
import kotlin.math.min

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.handler
 * @date 26/09/2023 / 21:16
 */
class GameHandler {
    val bounds: Cuboid;

    //TODO: make a method that can pass in a gameType when launching the server (depending on in what way it was launched)

    val game: Game;
    val map: GameMap;

    init {
        val cgsArena = CgsGameEngine.INSTANCE.gameArena as CgsSkywarsArena;
        val gamemode = CgsGameEngine.INSTANCE.gameMode as SkywarsCgsGameMode;

        val world = Bukkit.getWorld(cgsArena.getBukkitWorldName());

        val arena: SkyWarsArena = cgsArena.arena

        bounds = Cuboid(
            arena.boundsLower?.toBukkit(world),
            arena.boundsUpper?.toBukkit(world)
        )
        game = Game(
            gamemode.gameType, if (gamemode.isSoloGame()) {
                GameTeamType.SOLO
            } else {
                GameTeamType.DOUBLES
            }
        )
        map = GameMap(
            arena.name,
            bounds,
            game.gameType
        );

    }

    val cages = mutableMapOf<UUID, LocalCage>()

    var tntRespawning: Boolean = false
    var tntRespawnsAt: Long = -1

    fun stopCountdown() {
        game.state = GameState.WAITING
        Tasks.delayed(2L) {
            Bukkit.getOnlinePlayers().forEach {
                updateRichPresence(it)
            }
        }

    }

    fun startCountdown() {
        game.state = GameState.COUNTDOWN

        for (uuid in game.players) {
            val player: Player = Bukkit.getPlayer(uuid)

            // Paste the cage for the player
            val playerSpawn = map.spawns[CgsGameTeamService.getTeamOf(player.uniqueId)!!.id];

            val randomCosmetic = CageCategory
                .cosmetics()
                .firstOrNull { cosmetic -> cosmetic.equipped(player) } as CageCosmeticLike?

            val randomCage = randomCosmetic
                ?.cage
                ?.linkedSchema
                ?.let { schema ->
                    SchemaCageDataSync.cached().schemas[schema]
                }
                ?: SchemaCageDefaultSchemas.DEFAULT
            val baseBlock = playerSpawn.world
                .getBlockAt(playerSpawn)
                .getRelative(BlockFace.DOWN)

            randomCage.applyTo(baseBlock)
            player.teleport(playerSpawn)
            cages[player.uniqueId] = LocalCage(
                cage = randomCage,
                location = baseBlock.location.toVector(),
                owner = player.uniqueId
            )
            PlayerUtil.resetPlayer(player)
            //  player.setMetadata("frozen", FixedMetadataValue(SkyWarsPlugin.instance, this))
        }
        Tasks.delayed(2L) {
            Bukkit.getOnlinePlayers().forEach {
                updateRichPresence(it)
            }
        }

    }

    fun updateRichPresence(player: Player) {
        player.setDiscordPresence(
            ServerRichPresence.builder()
                .playerState("Playing")
                .gameState(StringUtils.capitalize(CgsGameEngine.INSTANCE.gameState.name.lowercase()))
                .gameName("SkyWars")
                .mapName(SkyWarsGame.gameInstance.gameHandler.map.name)
                .teamMaxSize(CgsGameEngine.INSTANCE.gameMode.getMaxTeams())
                .teamCurrentSize(-1)
                .build()
        )
    }

    fun startGame() {
        game.state = GameState.LIVE
        game.alivePlayers.addAll(game.players)

        game.active = true
        game.startedAt = Date()


        val chest: LootableChest = SkyWarsGame.gameInstance.chestHandler.getChest(game.gameType)!!
        chest.fillChests(game, RefillType.FIRST)

        Tasks.delayed(Game.SECOND_REFILL_DELAY.toLong(), Runnable { chest.fillChests(game, RefillType.SECOND) })
        Tasks.delayed(Game.THIRD_REFILL_DELAY.toLong(), Runnable { chest.fillChests(game, RefillType.THIRD) })
        Tasks.delayed(Game.SUDDEN_DEATH_COMMENCES.toLong(), Runnable { this.suddenDeath() })
        Bukkit.getScheduler().runTaskTimer(SkyWarsPlugin.instance, GameTntGeneratorTask(), 0L, 20L)

        for (uuid in game.players) {
            val player: Player = Bukkit.getPlayer(uuid)
            player.removeMetadata("frozen", SkyWarsPlugin.instance)

            PlayerUtil.resetPlayer(player)
            cages[player.uniqueId]?.apply {
                cage.removeAll(
                    location.toLocation(map.getLobbySpawn().world).block
                )
            }
            game.kills[uuid] = 0

            val skyWarsProfile: SkyWarsProfile = SkyWarsGame.gameInstance.getProfile(player.uniqueId)
            skyWarsProfile.incrementXP(10)
            skyWarsProfile.gamesPlayed++;

            if (player.inventory.firstEmpty() == 0) {
                val profile: SkyWarsProfile = SkyWarsGame.gameInstance.getProfile(player.uniqueId)
                val kit: Kit = SkyWarsGame.gameInstance.kitHandler.getKitByName(profile.selectedKit)!!
                if (kit.gameType == game.gameType) {
                    kit.equipKit(player)
                }
            }


        }

        val event = GameStartEvent(game)
        Bukkit.getPluginManager().callEvent(event)

        Tasks.delayed(2L) {
            Bukkit.getOnlinePlayers().forEach {
                updateRichPresence(it)
            }
        }

    }

    fun suddenDeath() {
        if (!game.active) return

        for (uuid in game.alivePlayers) {
            val player: Player = Bukkit.getPlayer(uuid) ?: continue

            player.teleport(map.suddenDeathSpawnLoc)
            player.sendMessage(Color.translate("&c&lSudden Death has started!"))
        }
    }

    fun onWin(winner: Player?) {
        if (winner == null) {
            println("No winner found for Game " + game.id + "!")
            endGame()
            return
        }

        game.winner = winner.uniqueId

        // Gift the winner their new stats
        val skyWarsProfile: SkyWarsProfile = SkyWarsGame.gameInstance.getProfile(winner.uniqueId)
        skyWarsProfile.incrementWins()
        skyWarsProfile.incrementXP(30)
        skyWarsProfile.incrementCoins(150)

        if (game.gameType == GameType.RANKED) {
            val kills = game.kills[winner.uniqueId]!!
            val elo: Int = skyWarsProfile.calculateFinalRating(skyWarsProfile.elo, true, kills)
            skyWarsProfile.incrementElo(elo)
        }

        skyWarsProfile.save()

        // for (message in this.sendWinningMessage(winner.uniqueId)) Bukkit.broadcastMessage(message)


        // Kicks all players out of the game in 5 seconds
        Tasks.delayed(20 * 5L, Runnable {
            ServerUtil.sendToLobby(winner)
            for (player in Bukkit.getOnlinePlayers()) {
                if (player == null) continue

                ServerUtil.sendToLobby(player)
            }
        })

        endGame()
    }

    fun endGame() {
        game.state = GameState.ENDED
        game.active = false
        game.endedAt = Date()

        val event = GameEndEvent(game)
        Bukkit.getPluginManager().callEvent(event)

        game.players.clear()
        game.alivePlayers.clear()
        game.spectators.clear()

        // Delete the server deployment in 8 seconds from now
        Tasks.delayed(
            20 * 8,
        ) {
            Deployer.deleteDeployment(ServerSync.local.id)
        }
    }

    fun playSound(sound: Sound?) {
        for (player in Bukkit.getOnlinePlayers()) player.playSound(player.location, sound, 1.0f, 1.0f)
    }

    fun addPlayer(player: Player) {
        if (game.state == GameState.LIVE) {
            player.sendMessage("§cYou cannot join this game as it has already started.")
            return
        }

        val event = PlayerJoinGameEvent(player, game)
        Bukkit.getPluginManager().callEvent(event)

        game.players.add(player.uniqueId)
        if (game.state == GameState.WAITING) {
            player.teleport(map.getLobbySpawn())
        } else {
            val playerSpawn = map.spawns[CgsGameTeamService.getTeamOf(player.uniqueId)!!.id];

            val randomCosmetic = CageCategory
                .cosmetics()
                .firstOrNull { cosmetic -> cosmetic.equipped(player) } as CageCosmeticLike?

            val randomCage = randomCosmetic
                ?.cage
                ?.linkedSchema
                ?.let { schema ->
                    SchemaCageDataSync.cached().schemas[schema]
                }
                ?: SchemaCageDefaultSchemas.DEFAULT
            val baseBlock = playerSpawn.world
                .getBlockAt(playerSpawn)
                .getRelative(BlockFace.DOWN)

            randomCage.applyTo(baseBlock)
            player.teleport(playerSpawn)
            cages[player.uniqueId] = LocalCage(
                cage = randomCage,
                location = baseBlock.location.toVector(),
                owner = player.uniqueId
            )
        }
    }

    fun removePlayer(player: Player) {
        if (game.state != GameState.LIVE) {
            game.players.remove(player.uniqueId)

            map.removeUsedSpawn(player.location)

            ServerUtil.sendToLobby(player)
            return
        }
        if (game.state == GameState.COUNTDOWN) {
            cages[player.uniqueId]?.apply {
                cage.removeAll(
                    location.toLocation(map.getLobbySpawn().world).block
                )
            }
        }

        onDeath(player, null)
        val event = PlayerLeaveGameEvent(player, game)
        Bukkit.getPluginManager().callEvent(event)
    }

    fun onDeath(player: Player, killer: Player?) {
        game.alivePlayers.remove(player.uniqueId)
        this.addSpectator(player)

        val profile: SkyWarsProfile = SkyWarsGame.gameInstance.getProfile(player.uniqueId)
        profile.incrementDeaths()
        profile.incrementLosses()

        if (game.gameType == GameType.RANKED) {
            val kills = game.kills[player.uniqueId]!!
            val elo: Int = profile.calculateFinalRating(profile.elo, false, kills)
            profile.incrementElo(elo)
        }

        profile.save()

        if (killer != null) {
            val killerProfile: SkyWarsProfile = SkyWarsGame.gameInstance.getProfile(killer.uniqueId)
            killerProfile.incrementKills()
            killerProfile.save()

            game.kills[killer.uniqueId] = game.kills.getOrDefault(killer.uniqueId, 0) + 1
        }

        // TODO: Add a way to grab the team type when initializing the game otherwise this will error
        // TODO: probably find a better way to do this
//        if (game.getGameTeamType() == GameTeamType.SOLO) {
        if (game.alivePlayers.size == 1) game.alivePlayers.stream()
            .findFirst()
            .ifPresentOrElse(
                { winner: UUID? -> this.onWin(Bukkit.getPlayer(winner)) },
                { this.onWin(null) })
        //        } else {
        // Team game
//        }
    }

    fun addSpectator(player: Player) {
        game.spectators.add(player.uniqueId)

        PlayerUtil.resetPlayer(player)

        player.teleport(map.getSpectatorSpawn())
        player.allowFlight = true
        player.isFlying = true

        for (alive in game.alivePlayers) {
            val alivePlayer: Player = Bukkit.getPlayer(alive) ?: continue

            alivePlayer.hidePlayer(player)
        }

        for (spectator in game.spectators) {
            val spectatorPlayer: Player = Bukkit.getPlayer(spectator) ?: continue

            spectatorPlayer.showPlayer(player)
        }
    }

    fun hasStarted(): Boolean {
        return game.state == GameState.LIVE
    }

    fun isSpectating(player: UUID?): Boolean {
        return game.spectators.contains(player)
    }

    fun sendWinningMessage(winner: UUID): List<String> {
        val topKillers = game.kills.entries.stream()
            .sorted(java.util.Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit((min(game.kills.size.toDouble(), 3.0).toLong()))
            .map { entry: Map.Entry<UUID, Int?> ->
                Tuple<UUID, String>(
                    entry.key,
                    QuickAccess.coloredName(entry.key)
                )
            }
            .toList()

        val firstKiller = topKillers[0]
        val secondKiller = topKillers[1]
        val thirdKiller = topKillers[2]

        return Color.translate(
            listOf(
                "&7&m----------------------------------------",
                centerMessage("&6&lSkyWars"),
                "",
                centerMessage("&eWinner &7- " + QuickAccess.coloredName(winner)),
                "",
                centerMessage("&e&l1st Killer &7- " + firstKiller.value + " &7(&e" + game.kills[firstKiller.key] + "&7)"),
                centerMessage("&6&l2nd Killer &7- " + secondKiller.value + " &7(&e" + game.kills[secondKiller.key] + "&7)"),
                centerMessage("&c&l3rd Killer &7- " + thirdKiller.value + " &7(&e" + game.kills[thirdKiller.key] + "&7)"),
                "",
                "&7&m----------------------------------------"
            )
        )
    }

    fun centerMessage(message: String?): String {
        if (message == null || message == "") {
            return ""
        }

        var messagePxSize = 0
        var previousCode = false
        var isBold = false

        for (c in message.toCharArray()) {
            if (c == '§') {
                previousCode = true
                continue
            }
            if (previousCode) {
                previousCode = false
                isBold = c == 'l' || c == 'L'
                continue
            }

            val dFI = DefaultFontInfo.getDefaultFontInfo(c)
            messagePxSize += if (isBold) dFI.boldLength else dFI.length
            messagePxSize++
        }

        val sb = StringBuilder()
        val halvedMessageSize = messagePxSize / 2
        val toCompensate = 154 - halvedMessageSize
        val spaceLength = DefaultFontInfo.SPACE.length + 1
        var compensated = 0
        while (compensated < toCompensate) {
            sb.append(" ")
            compensated += spaceLength
        }

        return sb.toString() + message
    }
}
