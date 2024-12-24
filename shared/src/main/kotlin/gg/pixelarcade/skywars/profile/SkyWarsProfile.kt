package gg.pixelarcade.skywars.profile

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.leveling.enums.LevelEnum
import gg.pixelarcade.skywars.perk.Perk
import gg.scala.cache.uuid.ScalaStoreUuidCache
import gg.scala.cgs.common.player.statistic.GameSpecificStatistics
import gg.scala.lemon.util.QuickAccess
import gg.scala.store.controller.DataStoreObjectControllerCache
import gg.scala.store.storage.type.DataStoreStorageType
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.math.floor
import kotlin.math.max


class SkyWarsProfile(identifier: UUID) : GameSpecificStatistics(identifier) {
    var selectedKit = "default"

    var level = 0
    var xp = 0
    var coins = 0
    var killStreak = 0
    var highestKillStreak = 0
    var elo = 0
    var heads = 0
    var souls = 0
    var gamesPlayed = 0

    var ownedKits: MutableList<String> = ArrayList()
    var ownedPerks: MutableList<String> = ArrayList()
    var selectedPerks: MutableList<String> = ArrayList()

    var dailyQuests: MutableMap<Long, String> = HashMap()

    var canRate = false
    var arenasRated: MutableList<String> = ArrayList()
    var battlePass = BattlePass

    override fun save(): CompletableFuture<Void> {
        return DataStoreObjectControllerCache.findNotNull<SkyWarsProfile>()
            .save(this, DataStoreStorageType.MONGO)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun getPrestige(level: Int): LevelEnum {
        var prestige: LevelEnum = LevelEnum.ROOKIE

        // Iterate through the list of enums to find the one that fits the player's level
        for (prestiges in LevelEnum.entries) {
            if (level >= prestiges.minLevel) {
                prestige = prestiges
            }
        }

        return prestige
    }

    fun getXPBar(prestige: LevelEnum): String {
        //-- Variables

        val xpBar = StringBuilder()
        val percentage = (xp.toDouble() / prestige.xpPerLevel) * 100
        val progress = (floor(percentage) / 10).toInt()

        //-- Progress
        xpBar.append("  &7[&e")
        xpBar.append("●".repeat(max(0.0, progress.toDouble()).toInt()))
        xpBar.append("&7")
        xpBar.append("●".repeat(max(0.0, (10 - progress + 1).toDouble()).toInt()))
        xpBar.append("] ")

        //-- Percentage to next level
        xpBar.append(Math.round(percentage)).append("%")

        return xpBar.toString()
    }

    fun findNewDailyQuest() {
        dailyQuests.clear()

        val newQuest = SkyWarsShared.instance.questHandler.randomQuest
        dailyQuests[System.currentTimeMillis()] = newQuest.id

        this.save()
    }

    fun incrementCoins(amount: Int) {
        this.coins += amount
        this.save()
    }

    fun incrementLevel() {
        Bukkit.getPlayer(identifier).sendMessage(levelUpMessage())
        level++

        this.save()
    }

    fun incrementXP(amount: Int) {
        var newXP = xp + amount
        var prestige = getPrestige(level)

        while (newXP >= prestige!!.xpPerLevel) {
            incrementLevel()
            prestige = getPrestige(level)
            newXP -= prestige!!.xpPerLevel
            if (newXP >= prestige.xpPerLevel) {
                newXP -= prestige.xpPerLevel
            }
        }

        xp = newXP
        this.save()
    }

    fun incrementKills() {
        kills++
        killStreak++

        if (killStreak > highestKillStreak) highestKillStreak = killStreak

        this.save()
    }

    fun incrementDeaths() {
        killStreak = 0
        deaths++
        this.save()
    }

    fun incrementElo(amount: Int) {
        this.elo += amount
        this.save()
    }

    fun incrementWins() {
        wins++
        this.save()
    }

    fun incrementLosses() {
        losses++
        this.save()
    }

    fun addSouls(amount: Int) {
        this.souls += amount
        this.save()
    }

    fun addHeads(amount: Int) {
        this.heads += amount
        this.save()
    }

    fun removeSouls(amount: Int) {
        this.souls -= amount
        this.save()
    }

    fun addSelectedPerk(perk: Perk) {
        // if selectedperks != null

        val newSelectedPerks: MutableList<String> = ArrayList(this.selectedPerks)
        newSelectedPerks.add(perk.name)
        this.selectedPerks = newSelectedPerks
        this.save()
    }

    val name: String
        get() = ScalaStoreUuidCache.username(identifier) ?: "null"

    val niceName: String
        get() = QuickAccess.fetchColoredName(identifier)

    fun calculateFinalRating(currentRating: Int, wonGame: Boolean, kills: Int): Int {
        val ratingGain = calculateRatingGain(currentRating)
        val ratingChange = calculateRatingChange(currentRating, wonGame, kills)
        return currentRating + (if (wonGame) ratingGain else ratingChange)
    }

    /**
     * This method calculates the rating change based on the current rating, game result, and kills.
     *
     * @param elo The current rating of the player.
     * @param wonGame A boolean indicating whether the game was won.
     * @param kills The number of kills the player achieved in the game.
     * @return The rating change. If the game was won or the player achieved 2 or more kills, the change is 0.
     * If the current rating is less than 1000 and the game was lost with less than 2 kills, the change is -1.
     * If the current rating is 1000 or more and the game was lost with less than 2 kills, the change is -10.
     */
    private fun calculateRatingChange(elo: Int, wonGame: Boolean, kills: Int): Int {
        // TODO: I do not know if you want this to be a random number between two numbers
        //  or if you want it to be a set amount - Alfie
        if (wonGame || kills >= 2) return 0

        if (elo < 1000) return -1

        return -10
    }

    /**
     * This method calculates the rating gain based on the current rating.
     *
     * @param rating The current rating of the player.
     * @return The rating gain. If the current rating is less than 1000, the gain is 30.
     * If the current rating is 2000 or more, the gain is 10.
     * Otherwise, the gain is determined by the RATING_GAINS array.
     */
    private fun calculateRatingGain(rating: Int): Int {
        if (rating < 1000) return 30

        val index = (rating - 1000) / 100
        if (index >= RATING_GAINS.size) return 10

        return RATING_GAINS[RATING_GAINS.size - 1 - index]
    }

    private fun levelUpMessage(): Array<String> {
        val nextLevel = this.level + 1

        val toReturn = arrayOf(
            ChatColor.GOLD.toString() + "---------------------------",
            ChatColor.AQUA.toString() + "SkyWars Experience",
            ChatColor.translateAlternateColorCodes(
                '&',
                getPrestige(level)!!.color
            ) + "Level " + this.level + "      Level " + nextLevel,
            " " + ChatColor.translateAlternateColorCodes('&', getXPBar(getPrestige(level)!!)),
            ChatColor.GOLD.toString() + "---------------------------"
        )
        return toReturn
    }

    fun applyPerks(player: Player) {
        SkyWarsShared.instance.perkHandler.getPerkByName("Bulldozer Perk")!!.apply(player)
        SkyWarsShared.instance.perkHandler.getPerkByName("Juggernaut Perk")!!.apply(player)

        val kit = SkyWarsShared.instance.kitHandler.getKitByName(selectedKit)?: return

        val kitPerk = SkyWarsShared.instance.perkHandler.getPerkByName(kit.name + " Perk")?: return

        kitPerk.apply(player)
    }

    companion object {
        private val RATING_GAINS = intArrayOf(10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30)
    }
}