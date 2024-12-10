package gg.pixelarcade.skywars.battlepass.weekly

import com.google.gson.annotations.JsonAdapter
import gg.pixelarcade.skywars.battlepass.BattlePassHandler
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeHandler
import gg.pixelarcade.skywars.battlepass.challenge.serialize.ChallengeSetJsonAdapter
import net.evilblock.cubed.util.time.TimeUtil
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.NoSuchElementException


class WeeklyChallengeSession(val uuid: UUID = UUID.randomUUID()) {

    var expiresAt: Long = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1L)

    var progress: MutableMap<UUID,WeeklyChallengeProgress> = hashMapOf()


    @JsonAdapter(ChallengeSetJsonAdapter::class)
    var challenges: MutableList<Challenge> = generateChallenges()

    @JvmName("getChallenges1")
    fun getChallenges(): List<Challenge> {
        return challenges
    }

    private fun generateChallenges(): MutableList<Challenge> {
        val filtered = ChallengeHandler.getChallenges().filter { it.weekly }.toMutableList()
        val generated = arrayListOf<Challenge>()

        for (i in 0..filtered.size.coerceAtMost(BattlePassHandler.WEEKLY_CHALLENGES)) {
            try {
                val challenge = filtered.random()
                filtered.remove(challenge)
                generated.add(challenge)
            } catch (e: NoSuchElementException) {
                break
            }
        }

        return generated
    }

    fun getProgress(uuid: UUID): WeeklyChallengeProgress {
        return this.progress.getOrPut(uuid) { WeeklyChallengeProgress(uuid) }
    }

    fun hasExpired(): Boolean {
        return System.currentTimeMillis() >= expiresAt
    }

    fun getTimeRemaining(): String {
        return TimeUtil.formatIntoDetailedString(((this.expiresAt - System.currentTimeMillis()) / 1000L).toInt())
    }

}