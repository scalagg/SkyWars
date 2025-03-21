package gg.scala.skywars.ability

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.ability.impl.*

import org.bukkit.entity.Player
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.scala.game.ability
 * @date 03/10/2023 / 06:09
 */

class AbilityHandler {
    val cache: MutableMap<String, Ability> = HashMap()

    init {
        cache["pumpkinpie"] = PumpkinPie()
        cache["throwabletnt"] = ThrowableTNT()
        cache["throwabledirt"] = ThrowableDirt()
        cache["safeteleport"] = SafeTeleport()
        cache["magnet"] = Magnet()
        cache["zombieguardian"] = ZombieGuardian()
    }

    fun getAbilityById(id: String): Ability? {
        return cache[id.lowercase(Locale.getDefault())]
    }

    fun giveAbility(player: Player, abilityName: String, amount: Int) {
        val item = SkyWarsGame.gameInstance.abilityHandler.getAbilityById(abilityName)!!.getItem()
        item.amount = amount

        player.inventory.addItem(item)
    }
}
