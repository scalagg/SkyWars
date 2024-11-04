package gg.pixelarcade.skywars.ability.impl

import gg.pixelarcade.skywars.ability.Ability
import gg.pixelarcade.skywars.ability.type.Interact
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.impl
 * @date 03/10/2023 / 06:12
 */
class PumpkinPie : Ability(), Interact {
    override val name: String
        get() = Color.translate("&6&lPumpkin Pie")

    override fun getItem(): ItemStack {
        return ItemBuilder(Material.PUMPKIN_PIE)
            .name(name)
            .addToLore(Color.translate("&7Right click to gain &a&lSpeed " + SPEED_DISPLAY_LEVEL + " &7for " + SPEED_DURATION + " seconds"))
            .build()
    }

    override fun onPlayerInteract(event: PlayerInteractEvent): Boolean {
        event.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, SPEED_DURATION * 20, SPEED_LEVEL))
        return true
    }

    companion object {
        private const val SPEED_LEVEL = 0
        private const val SPEED_DISPLAY_LEVEL = 1
        private const val SPEED_DURATION = 4
    }
}
