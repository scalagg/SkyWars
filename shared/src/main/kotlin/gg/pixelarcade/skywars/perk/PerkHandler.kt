package gg.pixelarcade.skywars.perk

import gg.pixelarcade.skywars.perk.impl.globalPerks.BulldozerPerk
import gg.pixelarcade.skywars.perk.impl.globalPerks.JuggernautPerk
import gg.pixelarcade.skywars.perk.impl.kitPerks.*
import gg.pixelarcade.skywars.perk.impl.purchasedPerks.*
import java.util.*


class PerkHandler {
    private val perks: MutableMap<String, Perk> = HashMap()

    init {
        this.registerPerk(ArmorerPerk())
        this.registerPerk(BlacksmithPerk())
        this.registerPerk(ChampionPerk())
        this.registerPerk(HealerPerk())
        this.registerPerk(HoundPerk())
        this.registerPerk(MagicianPerk())
        this.registerPerk(PaladinPerk())
        this.registerPerk(PyromancerPerk())
        this.registerPerk(ScoutPerk())
        this.registerPerk(SniperPerk())
        this.registerPerk(LastStandPerk())
        this.registerPerk(JuggernautPerk())
        this.registerPerk(BulldozerPerk())
        this.registerPerk(ArrowRecoveryPerk())
        this.registerPerk(BridgerPerk())
        this.registerPerk(EnderMasteryPerk())
        this.registerPerk(EnvironmentalExpertPerk())
        this.registerPerk(RusherPerk())
    }

    fun registerPerk(perk: Perk) {
        perks[perk.name.lowercase(Locale.getDefault())] = perk
    }

    fun getPerkByName(name: String): Perk? {
        return perks[name.lowercase(Locale.getDefault())]
    }

    fun getPerks(): Collection<Perk> {
        return perks.values
    }
}