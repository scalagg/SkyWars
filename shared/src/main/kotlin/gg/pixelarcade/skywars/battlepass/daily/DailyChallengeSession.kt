package gg.pixelarcade.skywars.battlepass.daily

import com.google.gson.annotations.JsonAdapter
import gg.pixelarcade.skywars.battlepass.BattlePassHandler
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeHandler
import gg.pixelarcade.skywars.battlepass.challenge.serialize.ChallengeSetJsonAdapter
import net.evilblock.cubed.util.time.TimeUtil
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.NoSuchElementException


class DailyChallengeSession(val uuid: UUID = UUID.randomUUID()) {

    var expiresAt: Long = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1L)

    var progress: MutableMap<UUID,DailyChallengeProgress> = hashMapOf()


    @JsonAdapter(ChallengeSetJsonAdapter::class) var challenges: Set<Challenge> = generateChallenges()

    @JvmName("getChallenges1")
    fun getChallenges(): Set<Challenge> {
        return challenges
    }

    private fun generateChallenges(): Set<Challenge> {
        val filtered = ChallengeHandler.getChallenges().filter { it.daily }.toMutableList()
        val generated = mutableSetOf<Challenge>()

        for (i in 0..filtered.size.coerceAtMost(BattlePassHandler.DAILY_CHALLENGES)) {
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

    fun getProgress(uuid: UUID): DailyChallengeProgress {
        return this.progress.getOrPut(uuid) { DailyChallengeProgress(uuid) }
    }

    fun hasExpired(): Boolean {
        return System.currentTimeMillis() >= expiresAt
    }

    fun getTimeRemaining(): String {
        return TimeUtil.formatIntoDetailedString(((this.expiresAt - System.currentTimeMillis()) / 1000L).toInt())
    }

}