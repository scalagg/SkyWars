package gg.pixelarcade.skywars.battlepass

import com.google.gson.annotations.JsonAdapter
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.serialize.ChallengeSetJsonAdapter
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.battlepass.tier.Tier
import gg.pixelarcade.skywars.battlepass.tier.TierHandler
import gg.pixelarcade.skywars.battlepass.tier.TierReward
import gg.pixelarcade.skywars.battlepass.tier.serializer.TierSetJsonAdapter
import org.bukkit.entity.Player
import java.util.*


class BattlePass {

    var premium = false
    var experience = 0
    var commandsExecuted = mutableSetOf<String>()

    @JsonAdapter(TierSetJsonAdapter::class) var claimedRewardsFree = mutableSetOf<Tier>()
    @JsonAdapter(TierSetJsonAdapter::class) var claimedRewardsPremium = mutableSetOf<Tier>()
    @JsonAdapter(ChallengeSetJsonAdapter::class) var completedChallenges = mutableSetOf<Challenge>()

    var requiresUpdate = false

    fun isPremium(): Boolean {
        return this.premium
    }

    @JvmName("setPremium1")
    fun setPremium(uuid: UUID, premium: Boolean) {
        this.premium = premium
        this.requiresUpdate = true
    }

    @JvmName("setExperience1")
    fun setExperience(uuid: UUID,exp: Int) {
        this.experience = exp
        this.requiresUpdate = true
    }

    @JvmName("getExperience1")
    fun getExperience(): Int {
        return this.experience
    }

    fun addExperience(player: Player,exp: Int) {
        this.experience += exp
    }

    fun isTierUnlocked(tier: Tier): Boolean {
        return this.experience >= tier.requiredExperience
    }

    fun getNextTier(): Tier? {
        return TierHandler.getAllTiers().sortedBy{it.requiredExperience}.firstOrNull{this.experience < it.requiredExperience}
    }

    fun getCurrentTier(): Tier? {
        return TierHandler.getAllTiers().sortedBy{it.requiredExperience}.lastOrNull{this.experience >= it.requiredExperience}
    }

    fun hasCompletedChallenge(player: Player,challenge: Challenge): Boolean {
        return if (challenge.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).hasCompletedChallenge(challenge)
        } else if(challenge.weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).hasCompletedChallenge(challenge)
        }else {
            this.completedChallenges.contains(challenge)
        }
    }

    fun completeChallenge(player: Player,challenge: Challenge) {
        this.experience += challenge.rewardXp

        if (challenge.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).completeChallenge(challenge)
        }else if(challenge.weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).completeChallenge(challenge)
        } else {
            this.completedChallenges.add(challenge)
        }
    }

    fun claimReward(player: Player, tier: Tier,reward: TierReward) {

        if (reward.isFreeReward()) {
            this.claimedRewardsFree.add(tier)
        } else {
            this.claimedRewardsPremium.add(tier)
        }
    }

    fun hasClaimedReward(tier: Tier,reward: TierReward): Boolean {
        return if (reward.isFreeReward()) {
            this.claimedRewardsFree.contains(tier)
        } else {
            this.claimedRewardsPremium.contains(tier)
        }
    }

    fun hasExecutedCommand(command: String): Boolean {
        return this.commandsExecuted.contains(command.trim().lowercase())
    }

    fun executedCommand(command: String) {
        this.requiresUpdate = true
        this.commandsExecuted.add(command.trim().lowercase())
    }

}