package gg.scala.skywars.battlepass.challenge


import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.battlepass.challenge.impl.*
import gg.scala.skywars.battlepass.daily.DailyChallengeHandler
import gg.scala.skywars.battlepass.weekly.WeeklyChallengeHandler
import org.bukkit.Bukkit
import java.util.concurrent.ConcurrentHashMap

object ChallengeHandler {

    private val challengeTypes = arrayListOf<ChallengeType>()
    private val challenges = ConcurrentHashMap<String, Challenge>()

    init {
        this.register(ExecuteCommandChallenge.ExecuteCommandChallengeType)
        this.register(KillsChallenge.KillsChallengeType)
        this.register(GamesPlayedChallenge.GamesPlayedChallengeType)
    }

    fun register(challenge: Challenge) {
        challenges[challenge.id.lowercase()] = challenge
    }

    fun register(type: ChallengeType) {

        if (!challengeTypes.add(type)) {
            return
        }

        if(Bukkit.getPluginManager().isPluginEnabled("ScGameLobby"))
            return

        Bukkit.getServer().pluginManager.registerEvents(type, SkyWarsShared.instance.plugin)
    }

    fun destroy(challenge: Challenge) {
        challenges.remove(challenge.id.lowercase())
    }

    fun getAllChallenges():List<Challenge> {
        return arrayListOf<Challenge>().also { challenges ->
            challenges.addAll(getChallenges())
            challenges.addAll(DailyChallengeHandler.getSession().getChallenges())
            challenges.addAll(WeeklyChallengeHandler.getSession().getChallenges())
        }
    }

    fun getAllChallengeTypes():List<ChallengeType> {
        return challengeTypes
    }

    fun getChallenges():List<Challenge> {
        return challenges.values.toList()
    }

    fun getChallengeById(id: String): Challenge? {
        return challenges[id]
    }

}