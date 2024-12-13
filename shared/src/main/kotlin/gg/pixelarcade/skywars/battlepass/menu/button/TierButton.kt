package gg.pixelarcade.skywars.battlepass.menu.button


import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.tier.Tier
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.text.NumberFormat

class TierButton(private val tier: Tier, private val battlePass: BattlePass) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        return ItemBuilder.of(Material.STAINED_GLASS_PANE)
            .name("${ChatColor.GOLD}${ChatColor.BOLD}Tier ${this.tier.number}")
            .data(when {
                (this.battlePass.isTierUnlocked(this.tier)) -> 5
                (this.battlePass.getNextTier() == this.tier) -> 4
                else -> 14
            })
            .setLore(listOf(buildString{
                append("${ChatColor.GOLD}${Constants.EXP_SYMBOL}")
                append("${ChatColor.GRAY}${NumberFormat.getInstance().format(this@TierButton.tier.requiredExperience)}")
                append("${ChatColor.GOLD}/")
                append("${ChatColor.GRAY}${NumberFormat.getInstance().format(this@TierButton.battlePass.getExperience().coerceAtMost(this@TierButton.tier.requiredExperience))}")
            }))
            .build()
    }

}