package gg.pixelarcade.skywars.kit

import gg.pixelarcade.skywars.kit.impl.normal.*
import lombok.Data
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit
 * @date 26/09/2023 / 02:39
 */

class KitHandler {
    private val kits: MutableMap<String, Kit> = HashMap()

    init {
        this.registerKit(DefaultKit())
        this.registerKit(RookieKit())
        this.registerKit(ArmorerKit())
        this.registerKit(ArmorsmithKit())
        this.registerKit(CannoneerKit())
        this.registerKit(EnchanterKit())
        this.registerKit(EndermanKit())
        this.registerKit(KnightKit())
        this.registerKit(MagicianKit())
        this.registerKit(MedicKit())
        this.registerKit(MinerKit())
        this.registerKit(MinerKit())
        this.registerKit(PyromancerKit())
        this.registerKit(RusherKit())
        this.registerKit(SalmonKit())
        this.registerKit(ScoutKit())
        this.registerKit(SniperKit())
        this.registerKit(TrapperKit())
    }

    fun registerKit(kit: Kit) {
        kits[kit.name.toLowerCase()] = kit
    }

    fun getKitByName(name: String): Kit? {
        return kits[name.lowercase(Locale.getDefault())]
    }

    fun getKits(): Collection<Kit> {
        return kits.values
    }

    val defaultKit: Kit?
        get() {
            for (kit in kits.values) {
                if (kit.isDefault) {
                    return kit
                }
            }

            return null
        }
}

