package gg.scala.skywars.battlepass

import com.google.gson.annotations.JsonAdapter
import gg.scala.skywars.battlepass.challenge.Challenge
import gg.scala.skywars.battlepass.challenge.serialize.ChallengeSetJsonAdapter
import gg.scala.skywars.battlepass.daily.DailyChallengeHandler
import gg.scala.skywars.battlepass.tier.Tier
import gg.scala.skywars.battlepass.tier.TierHandler
import gg.scala.skywars.battlepass.tier.TierReward
import gg.scala.skywars.battlepass.tier.serializer.TierSetJsonAdapter
import gg.scala.skywars.battlepass.weekly.WeeklyChallengeHandler
import org.bukkit.entity.Player
import java.util.*


object BattlePass {

    var premium = false
    var xp = 0
    var commandsExecuted = mutableSetOf<String>()

    @JsonAdapter(TierSetJsonAdapter::class) var claimedRewardsFree = mutableSetOf<Tier>()
    @JsonAdapter(TierSetJsonAdapter::class) var claimedRewardsPremium = mutableSetOf<Tier>()
    @JsonAdapter(ChallengeSetJsonAdapter::class) var completedChallenges = mutableSetOf<Challenge>()

    var requiresUpdate = false

    fun isPremium(): Boolean {
        return premium
    }

    fun setPremium(uuid: UUID, premium: Boolean) {
        BattlePass.premium = premium
        requiresUpdate = true
    }

    fun setExperience(uuid: UUID,exp: Int) {
        xp = exp
        requiresUpdate = true
    }

    fun getExperience(): Int {
        return xp
    }

    fun addExperience(player: Player,exp: Int) {
        xp += exp
    }

    fun isTierUnlocked(tier: Tier): Boolean {
        return xp >= tier.requiredExperience
    }

    fun getNextTier(): Tier? {
        return TierHandler.getAllTiers().sortedBy{it.requiredExperience}.firstOrNull{ xp < it.requiredExperience}
    }

    fun getCurrentTier(): Tier? {
        return TierHandler.getAllTiers().sortedBy{it.requiredExperience}.lastOrNull{ xp >= it.requiredExperience}
    }

    fun hasCompletedChallenge(player: Player,challenge: Challenge): Boolean {
        return if (challenge.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).hasCompletedChallenge(challenge)
        } else if(challenge.weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).hasCompletedChallenge(challenge)
        }else {
            completedChallenges.contains(challenge)
        }
    }

    fun completeChallenge(player: Player,challenge: Challenge) {
        xp += challenge.rewardXp

        if (challenge.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).completeChallenge(challenge)
        }else if(challenge.weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).completeChallenge(challenge)
        } else {
            completedChallenges.add(challenge)
        }
    }

    fun claimReward(player: Player, tier: Tier,reward: TierReward) {

        if (reward.isFreeReward()) {
            claimedRewardsFree.add(tier)
        } else {
            claimedRewardsPremium.add(tier)
        }
    }

    fun hasClaimedReward(tier: Tier,reward: TierReward): Boolean {
        return if (reward.isFreeReward()) {
            claimedRewardsFree.contains(tier)
        } else {
            claimedRewardsPremium.contains(tier)
        }
    }

    fun hasExecutedCommand(command: String): Boolean {
        return commandsExecuted.contains(command.trim().lowercase())
    }

    fun executedCommand(command: String) {
        requiresUpdate = true
        commandsExecuted.add(command.trim().lowercase())
    }

}