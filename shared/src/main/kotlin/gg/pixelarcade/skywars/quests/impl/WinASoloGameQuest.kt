package gg.pixelarcade.skywars.quests.impl

import gg.pixelarcade.skywars.game.event.GameEndEvent
import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.quests.Quest
import gg.pixelarcade.skywars.quests.QuestRewardType
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 21:42
 */
class WinASoloGameQuest : Quest("WIN_A_SOLO_GAME", QuestRewardType.TEST) {
    override fun getDisplayName(): String {
        return "Win a Solo Game"
    }

    override fun getDisplayIcon(): ItemStack {
        return ItemBuilder(Material.PAPER)
            .enchant(Enchantment.DURABILITY, 1)
            .addFlags(ItemFlag.HIDE_ENCHANTS)
            .build()
    }

    override fun getDescription(): List<String> {
        return listOf(
            "Win a solo game to complete this quest!"
        )
    }

    @EventHandler
    fun onGameWin(event: GameEndEvent) {
        val game = event.game
        if (game.winner == null) return

        if (game.gameTeamType != GameTeamType.SOLO) return

        val player = Bukkit.getPlayer(game.winner) ?: return

        completeQuest(player)
    }
}
