package gg.pixelarcade.skywars.game.listener

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.game.event.GameStartEvent
import gg.pixelarcade.skywars.game.event.PlayerDamagePlayerEvent
import gg.pixelarcade.skywars.game.event.PlayerKillEvent
import gg.pixelarcade.skywars.isEnvironmental
import gg.pixelarcade.skywars.items.ForceStartItem
import gg.pixelarcade.skywars.items.LeaveGameItem
import gg.pixelarcade.skywars.items.LoadoutItem
import gg.pixelarcade.skywars.items.StatisticItem
import gg.pixelarcade.skywars.perk.impl.purchasedPerks.BridgerPerk
import gg.pixelarcade.skywars.util.PlayerUtil
import gg.pixelarcade.skywars.util.SpigotConstants
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.lemon.util.QuickAccess.coloredName
import gg.tropic.game.extensions.cosmetics.CosmeticRegistry
import gg.tropic.game.extensions.cosmetics.messagebundles.KillMessageBundleCosmeticCategory
import gg.tropic.game.extensions.cosmetics.messagebundles.cosmetics.MessageBundle

import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import java.util.concurrent.TimeUnit

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.listener
 * @date 26/09/2023 / 22:12
 */

class GameListener(private val plugin: SkyWarsGame) : Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerJoinGame(event: PlayerJoinEvent) {
        val player = event.player
        val game = SkyWarsGame.gameInstance.gameHandler.game

        plugin.gameHandler.addPlayer(player)

        PlayerUtil.resetPlayer(player)

        player.inventory.setItem(0, LoadoutItem(player).getItem(player))
        player.inventory.setItem(1, StatisticItem(player).getItem(player))

        if (player.isOp) player.inventory.setItem(4, ForceStartItem(player).getItem(player))

        player.inventory.setItem(8, LeaveGameItem(player).getItem(player))
    }

    @EventHandler
    fun onGameStart(event: CgsGameEngine.CgsGameStartEvent?) {
        plugin.gameHandler.startGame()
    }

    @EventHandler
    fun onGameStart(event: CgsGameEngine.CgsGamePreStartEvent?) {
        plugin.gameHandler.startCountdown()
    }

    @EventHandler
    fun onGameStartZombie(event: GameStartEvent?) {
        val zombieSpawns: List<Location> = plugin.gameHandler.map.zombieSpawns
        if (zombieSpawns == null || zombieSpawns.isEmpty()) {
            println("No zombie spawns found for arena " + plugin.gameHandler.map.name)
            return
        }

        for (zombieSpawn in zombieSpawns) {
            val zombie = plugin.gameHandler.map.bounds.world.spawn(zombieSpawn, Zombie::class.java)
            zombie.removeWhenFarAway = false
            zombie.isBaby = false
            zombie.isVillager = false

            zombie.equipment.bootsDropChance = 0f
            zombie.equipment.leggingsDropChance = 0f
            zombie.equipment.chestplateDropChance = 0f
            zombie.equipment.helmetDropChance = 0f

            zombie.equipment.armorContents = arrayOf(
                ItemStack(Material.GOLD_HELMET),
                ItemStack(Material.GOLD_CHESTPLATE),
                ItemStack(Material.GOLD_LEGGINGS),
                ItemStack(Material.GOLD_BOOTS)
            )
        }
    }
    @EventHandler
    fun onGameStartSpices(event: GameStartEvent?) {
        val tntGeneratorSpawn: Location? = plugin.gameHandler.map.tntGeneratorSpawnLoc
        if (tntGeneratorSpawn == null) {
            println("No tnt generator spawn " + plugin.gameHandler.map.name)
            return
        }
        val item = tntGeneratorSpawn.world.dropItem(
            tntGeneratorSpawn.clone().add(0.0, 1.0, 0.0),
            SkyWarsGame.gameInstance.abilityHandler.getAbilityById("throwabletnt")?.getItem()
        )

        item.setMetadata("TNT_GEN_ITEM", FixedMetadataValue(SkyWarsGame.gameInstance.plugin, true))
    }

    @EventHandler
    fun onTntPickup(event: PlayerPickupItemEvent) {
        if(!event.item.hasMetadata("TNT_GEN_ITEM"))
            return
        val tntGeneratorSpawn: Location? = plugin.gameHandler.map.tntGeneratorSpawnLoc
        tntGeneratorSpawn?.block?.type = Material.IRON_BLOCK;
        plugin.gameHandler.tntRespawning = true
        plugin.gameHandler.tntRespawnsAt = System.currentTimeMillis() + 20_000L;


    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        if (!player.hasMetadata("frozen")) return

        if (event.from.x == event.to.x
            && event.from.z == event.to.z
        ) return

        player.teleport(event.from)
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player
        if (!player.hasMetadata("frozen")) return

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        if (!player.hasMetadata("frozen")) return

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player

        plugin.gameHandler.removePlayer(player)
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        event.deathMessage = null

        val player = event.entity

        if (player.lastDamageCause.cause == EntityDamageEvent.DamageCause.VOID) event.drops.clear()

        delayed(4L, Runnable {
            player.spigot().respawn()
            PlayerUtil.resetPlayer(player)
        })

        val displayName = coloredName(player, false)
        var deathMessage = SpigotConstants.GAME + displayName + "&f has died a horrible death."

        if (player.killer != null) {
            val killerName = coloredName(player.killer, false)

            // Gift the Killer their stats
            val killerProfile = SkyWarsShared.instance.getProfile(player.killer.uniqueId)
            killerProfile.incrementXP(10)
            killerProfile.incrementKills()
            killerProfile.addSouls(1)
            killerProfile.incrementCoins(35)
            killerProfile.save()

            // Send the Killer messages regarding their new stats
            player.killer.sendMessage("&6+35 coins!")
            player.killer.sendMessage("&b+1 Soul!")

            killerProfile.applyPerks(player.killer);


            //TODO: Play a sound when a player gets a kill
            deathMessage =
                "${SpigotConstants.GAME} $displayName &f was ${getMessageBundlePhrase(player.killer)}&f by $killerName&f."
        }

        Bukkit.broadcastMessage(Color.translate(deathMessage))

        val killEvent = PlayerKillEvent(player, player.killer, plugin.gameHandler.game)
        Bukkit.getPluginManager().callEvent(killEvent)

        if (player.killer != null) plugin.gameHandler.onDeath(player, player.killer)
        else plugin.gameHandler.onDeath(player, null)
    }

    private fun getMessageBundlePhrase(player: Player): String {
        val bundle = CosmeticRegistry
            .findRelatedTo(KillMessageBundleCosmeticCategory)
            .filter { like ->
                like.equipped(player)
            }
            .filterIsInstance<MessageBundle>()
            .flatMap { bundle -> bundle.phrases }

        if (bundle.isEmpty()) {
            return "slain"
        }

        return "${CC.GOLD}${bundle.random()}${CC.GRAY}"
    }

    @EventHandler
    fun onEntityDamageEvent(event: EntityDamageEvent) {
        if (event.entity !is Player) return

        event.isCancelled = !plugin.gameHandler.hasStarted()
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onEntityDamageEvent(event: EntityDamageByEntityEvent) {
        if (event.entity !is Player) return

        if (event.damager !is Player) return

        event.isCancelled = !plugin.gameHandler.hasStarted()

        if (!event.isCancelled) {
            val player = event.entity as Player
            val damager = event.damager as Player

            val damageEvent = PlayerDamagePlayerEvent(player, damager, plugin.gameHandler.game)
            Bukkit.getPluginManager().callEvent(damageEvent)
        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    fun onPerkBlockPlace(event: BlockPlaceEvent) {
        val player = event.player
        val profile = SkyWarsShared.instance.getProfile(player)?: return

        if(profile.selectedPerks.contains("Bridger Perk")) {
            (SkyWarsShared.instance.perkHandler.getPerkByName("Bridger Perk") as BridgerPerk).apply(player, event.block)
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    fun onPerkPlayerDamage(event: EntityDamageByEntityEvent) {
        if(event.entity !is Player) return

        val player = event.entity as Player
        val profile = SkyWarsShared.instance.getProfile(player)?: return

        if(profile.selectedPerks.contains("Ender Mastery Perk") && event.damager.type == EntityType.ENDER_PEARL) {
            event.isCancelled = true
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    fun onPerkPlayerDamage(event: EntityDamageEvent) {
        if(event.entity !is Player) return

        val player = event.entity as Player
        val profile = SkyWarsShared.instance.getProfile(player)?: return

        if(profile.selectedPerks.contains("Environmental Expert Perk") && event.cause.isEnvironmental()) {
            event.damage = (event.damage / .20)
        }
    }

}
