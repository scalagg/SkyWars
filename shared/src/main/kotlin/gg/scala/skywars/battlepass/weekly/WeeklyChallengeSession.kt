package gg.scala.skywars.battlepass.weekly

import com.google.gson.annotations.JsonAdapter
import gg.scala.skywars.battlepass.BattlePassHandler
import gg.scala.skywars.battlepass.challenge.Challenge
import gg.scala.skywars.battlepass.challenge.ChallengeHandler
import gg.scala.skywars.battlepass.challenge.serialize.ChallengeSetJsonAdapter
import net.evilblock.cubed.util.time.TimeUtil
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.NoSuchElementException


class WeeklyChallengeSession(val uuid: UUID = UUID.randomUUID()) {

    var expiresAt: Long = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1L)

    var progress: MutableMap<UUID, WeeklyChallengeProgress> = hashMapOf()


    @JsonAdapter(ChallengeSetJsonAdapter::class)
    var challenges: MutableSet<Challenge> = generateChallenges()

    @JvmName("getChallenges1")
    fun getChallenges(): Set<Challenge> {
        return challenges
    }

    private fun generateChallenges(): MutableSet<Challenge> {
        val filtered = ChallengeHandler.getChallenges().filter { it.weekly }.toMutableList()
        val generated =  mutableSetOf<Challenge>()

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