package gg.scala.skywars.battlepass.menu


import gg.scala.skywars.battlepass.BattlePass
import gg.scala.skywars.battlepass.menu.button.*
import gg.scala.skywars.battlepass.tier.TierHandler
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.ItemUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class BattlePass2Menu(private val battlePass: BattlePass) : Menu() {

    init {
        updateAfterClick = true
    }

    private var page = 1


    override fun getTitle(player: Player): String {
        return "Battle Pass"
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {

        val buttons = mutableMapOf<Int, Button>()

        if (this.page > 1) {
            buttons[0] = PreviousPageButton()
        }

        buttons[2] = ChallengesMenuButton(true, false, this.battlePass)
        buttons[3] = ChallengesMenuButton(false, true, this.battlePass)
        buttons[4] = BattlePassInfoButton(this.battlePass)
        buttons[6] = ChallengesMenuButton(false, false, this.battlePass)
        buttons[8] = NextPageButton()

        val range = if (this.page == 1) {
            1..9
        } else {
            (((this.page - 1) * 9) + 1)..(this.page * 9)
        }

        for (tier in TierHandler.getAllTiers().filter { it.number in range }) {

            var tierSlotRemainder = (tier.number % 9) - 1

            if (tierSlotRemainder == -1) {
                tierSlotRemainder = 8
            }

            val slot = TIER_TOP_SLOTS[tierSlotRemainder]

            buttons[slot] = TierButton(tier, this.battlePass)

            if (tier.freeReward == null) {
                buttons[slot - 9] = EmptyRewardButton()
            } else {
                buttons[slot - 9] = RewardButton(tier, tier.freeReward!!, this.battlePass)
            }

            if (tier.premiumReward == null) {
                buttons[slot + 9] = EmptyRewardButton()
            } else {
                buttons[slot + 9] = RewardButton(tier, tier.premiumReward!!, this.battlePass)
            }

        }

        return buttons
    }

    override fun size(buttons: Map<Int, Button>): Int {
        return 6 * 9
    }

    companion object {

        private val TIER_TOP_SLOTS = (27..35).toList()

    }

    private inner class PreviousPageButton : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            val stack = ItemBuilder.of(Material.SKULL_ITEM)
                .name(
                    if (this@BattlePass2Menu.page > 1) {
                        "${ChatColor.YELLOW}${ChatColor.BOLD}Previous page"
                    } else {
                        "${ChatColor.GRAY}${ChatColor.BOLD}No previous page"
                    }
                ).build()
            return ItemUtils.applySkullTexture(
                stack,
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ=="
            )
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (this@BattlePass2Menu.page > 1) {
                this@BattlePass2Menu.page -= 1
            }
        }


    }

    private inner class NextPageButton : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            val stack = ItemBuilder.of(Material.SKULL_ITEM)
                .name(
                    if (this@BattlePass2Menu.page < 6) {
                        "${ChatColor.YELLOW}${ChatColor.BOLD}Next page"
                    } else {
                        "${ChatColor.GRAY}${ChatColor.BOLD}No next page"
                    }
                ).build()
            return ItemUtils.applySkullTexture(
                stack,
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19"
            )
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (this@BattlePass2Menu.page < 6) {
                this@BattlePass2Menu.page += 1
            }

        }


    }

}