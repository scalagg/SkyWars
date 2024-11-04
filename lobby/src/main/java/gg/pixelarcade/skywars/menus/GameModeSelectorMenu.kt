package gg.pixelarcade.skywars.menus

import com.cryptomorin.xseries.XMaterial
import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.menus.utils.FillerButton

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bungee.BungeeUtil.sendToServer
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @Author: Disunion
 * Name: GameModeSelectorMenu
 * Project SkyWarsLobby
 * Created At: 03/02/2024
 */
class GameModeSelectorMenu(private val gameTeamType: GameTeamType) : PaginatedMenu() {
    override fun getPrePaginatedTitle(player: Player): String {
        return "Play SkyWars"
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        when (gameTeamType) {
            GameTeamType.SOLO -> {
                buttons[1] = GameModeButton(GameType.NORMAL, GameTeamType.SOLO)
                buttons[3] = GameModeButton(GameType.INSANE, GameTeamType.SOLO)
                buttons[5] = GameModeButton(GameType.RANKED, GameTeamType.SOLO)
            }

            GameTeamType.DOUBLES -> {
                buttons[1] = GameModeButton(GameType.NORMAL, GameTeamType.DOUBLES)
                buttons[5] = GameModeButton(GameType.INSANE, GameTeamType.DOUBLES)
            }
        }

        for (i in 0..6) {
            buttons.computeIfAbsent(i) { k: Int? -> FillerButton() }
        }

        return buttons
    }

    
    class GameModeButton(private val gameType: GameType, private val gameTeamType: GameTeamType) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return when (gameType) {
                GameType.NORMAL -> {
                    ItemBuilder(XMaterial.WOODEN_SWORD)
                        .name("&6&lNormal Mode")
                        .addToLore("")
                        .addToLore("&eClick to join")
                        .addToLore("&ea random game!")
                        .build()
                }

                GameType.INSANE -> {
                    ItemBuilder(XMaterial.IRON_SWORD)
                        .name("&6&lInsane Mode")
                        .addToLore("")
                        .addToLore("&eClick to join")
                        .addToLore("&ea random game!")
                        .build()
                }

                GameType.RANKED -> {
                    ItemBuilder(XMaterial.DIAMOND_SWORD)
                        .name("&6&lRanked Mode")
                        .addToLore("")
                        .addToLore("&eClick to join")
                        .addToLore("&ea random game!")
                        .build()
                }
            }
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
//-- Switch case for the team type: Solo or Doubles
            when (gameTeamType) {
                GameTeamType.SOLO -> {
                    //-- Switch case for the game type: Ranked, Normal, Insane

                    when (gameType) {
                        GameType.RANKED, GameType.INSANE -> {
                            player.closeInventory()
                            player.sendMessage("Server hasn't been created yet")
                        }

                        GameType.NORMAL -> {
                            player.closeInventory()
                            sendToServer(player, "skywars-limbo-01")
                        }
                    }
                }

                GameTeamType.DOUBLES -> {
                    when (gameType) {
                        GameType.INSANE -> {
                            player.closeInventory()
                            player.sendMessage("Server hasn't been created yet")
                        }

                        GameType.NORMAL -> {
                            player.closeInventory()
                            player.sendMessage("Server hasn't been created yet")
                        }

                        GameType.RANKED -> {}
                    }
                }
            }
        }
    }
}