package gg.pixelarcade.skywars.battlepass.weekly



import com.google.gson.annotations.JsonAdapter
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.serialize.ChallengeSetJsonAdapter
import java.util.*

class WeeklyChallengeProgress(val uuid: UUID) {

    var kills = 0
    var wins = 0
    var plays = 0
    var commandsExecuted = mutableSetOf<String>()


    @JsonAdapter(ChallengeSetJsonAdapter::class)
    var completedChallenges: MutableSet<Challenge> = mutableSetOf()

    @JvmName("getKills1")
    fun getKills(): Int {
        return this.kills
    }

    @JvmName("setKills1")
    fun setKills(amount: Int) {
        this.kills = amount
    }

    fun addKills(amount: Int) {
        this.kills += amount
    }

    @JvmName("getWins1")
    fun getWins(): Int {
        return this.wins
    }

    @JvmName("setWins1")
    fun setWins(amount: Int) {
        this.wins = amount
    }

    fun addWins(amount: Int) {
        this.wins += amount
    }
    @JvmName("getPlays1")
    fun getPlays(): Int {
        return this.plays
    }

    @JvmName("setPlays1")
    fun setPlays(amount: Int) {
        this.plays = amount
    }

    fun addPlays(amount: Int) {
        this.plays += amount
    }


    fun executedCommand(command: String) {
        this.commandsExecuted.add(command.trim().lowercase())
    }

    fun hasExecutedCommand(command: String): Boolean {
        return this.commandsExecuted.contains(command.trim().lowercase())
    }

    fun completeChallenge(challenge: Challenge) {
        this.completedChallenges.add(challenge)
    }

    fun hasCompletedChallenge(challenge: Challenge): Boolean {
        return this.completedChallenges.contains(challenge)
    }

}