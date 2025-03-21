package gg.scala.skywars.region.menu.buttons

import gg.scala.skywars.SkyWarsLobby
import gg.scala.skywars.region.Region
import gg.scala.skywars.region.menu.RegionEditMenu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.scala.game.region.menu.buttons
 * @date 19/10/2023 / 19:25
 */
class RegionsButton(private val region: Region) : Button() {

    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder(Material.PAPER)
            .name("&6&l" + region.name)
            .setLore(
                listOf<String>(
                    "",
                    "&6■ &fToggled&7: &e" + colorBoolean(region.enabled),
                    "&6■ &fBreak Blocks&7: &e" + colorBoolean(region.breakBlocks),
                    "&6■ &fPlace Blocks&7: &e" + colorBoolean(region.placeBlocks),
                    "&6■ &fDamage&7: &e" + colorBoolean(region.damage),
                    "&6■ &fHunger&7: &e" + colorBoolean(region.hunger),
                    "&6■ &fDrop Items&7: &e" + colorBoolean(region.dropItems),
                    "&6■ &fPickup Items&7: &e" + colorBoolean(region.pickupItems),
                    "&6■ &fInteract&7: &e" + colorBoolean(region.interact),
                    "&6■ &fLeaves Decay&7: &e" + colorBoolean(region.leavesDecay),
                    "",
                    "&a&lLEFT CLICK &ato edit the region.",
                    "&c&lRIGHT CLICK &cto delete the region.",
                    "&e&lSHIFT CLICK &eto teleport to region."
                )
            ).build()
    }


    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        when (clickType) {
            ClickType.LEFT -> RegionEditMenu(region).openMenu(player)
            ClickType.RIGHT -> {
                SkyWarsLobby.lobbyInstance.regionHandler.removeRegion(region)
                SkyWarsLobby.lobbyInstance.regionHandler.saveRegions()

                player.sendMessage(Color.translate("&eYou have deleted the region &6" + region.name + "&e."))
                player.closeInventory()
            }

            ClickType.SHIFT_RIGHT -> {
                player.closeInventory()
                player.teleport(region.bounds.center)
            }

            ClickType.SHIFT_LEFT -> {}
            ClickType.WINDOW_BORDER_LEFT -> {}
            ClickType.WINDOW_BORDER_RIGHT -> {}
            ClickType.MIDDLE -> {}
            ClickType.NUMBER_KEY -> {}
            ClickType.DOUBLE_CLICK -> {}
            ClickType.DROP -> {}
            ClickType.CONTROL_DROP -> {}
            ClickType.CREATIVE -> {}
            ClickType.UNKNOWN -> {}
        }
    }

    private fun colorBoolean(bool: Boolean): String {
        return if (bool) "&aEnabled" else "&cDisabled"
    }
}
