package gg.pixelarcade.skywars.battlepass.challenge


import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.challenge.impl.*
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeHandler
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
        this.challenges[challenge.id.lowercase()] = challenge
    }

    fun register(type: ChallengeType) {

        if (!this.challengeTypes.add(type)) {
            return
        }

        if(Bukkit.getPluginManager().isPluginEnabled("ScGameLobby"))
            return

        Bukkit.getServer().pluginManager.registerEvents(type, SkyWarsShared.instance.plugin)
    }

    fun destroy(challenge: Challenge) {
        this.challenges.remove(challenge.id.lowercase())
    }

    fun getAllChallenges():List<Challenge> {
        return arrayListOf<Challenge>().also { challenges ->
            challenges.addAll(getChallenges())
            challenges.addAll(DailyChallengeHandler.getSession().getChallenges())
            challenges.addAll(WeeklyChallengeHandler.getSession().getChallenges())
        }
    }

    fun getAllChallengeTypes():List<ChallengeType> {
        return this.challengeTypes
    }

    fun getChallenges():List<Challenge> {
        return this.challenges.values.toList()
    }

    fun getChallengeById(id: String): Challenge? {
        return this.challenges[id]
    }

}