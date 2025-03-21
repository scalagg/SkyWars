package gg.scala.skywars.kit

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.kit.impl.normal.*
import gg.scala.commons.preconfigure.PreConfigureSubTypeProcessor.objectInstance
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.scala.game.kit
 * @date 26/09/2023 / 02:39
 */

class KitHandler {
    private val kits: MutableMap<String, Kit> = HashMap()

    init {
        SkyWarsShared.instance.plugin.packageIndexer
            .getSubTypes<Kit>()
            .forEach {
                registerKit((it.objectInstance() ?: it.newInstance()) as Kit)
            }
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

