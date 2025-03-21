package gg.scala.skywars.items

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.item.Item
import gg.scala.lemon.util.QuickAccess.coloredName
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import java.util.List

/**
 * @author alfie
 * @project Skywars / gg.scala.game.item.impl
 * @date 08/10/2023 / 14:03
 */
class StatisticItem(private val player: Player) : Item(player) {
    override fun getItem(player: Player): ItemStack {
        return ItemBuilder(Material.BOOK)
            .name(Color.translate("&aStatistics &7(Right Click)"))
            .build()
    }

    override fun click(action: Action) {
        val displayName = coloredName(player.uniqueId, false)
        val profile = SkyWarsGame.gameInstance.getProfile(player.uniqueId)

        val message = Color.translate(
            listOf(
                "&7&m----------------------------------------",
                "&6&l$displayName &lStatistics",
                "&7",
                "&7■ &fKills: &6" + profile.kills.value,
                "&7■ &fDeaths: &6" + profile.deaths.value,
                "&7■ &fKill Streak: &6" + profile.killStreak,
                "",
                "&7■ &fHeads: &6" + profile.heads,
                "&7■ &fSouls: &6" + profile.souls,
                "",
                "&7■ &fWins: &6" + profile.wins.value,
                "&7■ &fLosses: &6" + profile.losses.value,
                "&7&m----------------------------------------"
            )
        )

        for (line in message) player.sendMessage(line)
    }
}
