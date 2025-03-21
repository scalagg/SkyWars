package gg.scala.skywars.battlepass.challenge.menu.button

import gg.scala.skywars.battlepass.challenge.ChallengeType
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengeTypeButton(private val lambda: (ChallengeType) -> Unit,private val type: ChallengeType) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = type.getDescription(), linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to select challenge")

        return ItemBuilder.of(this.type.getIcon().type)
            .name("${ChatColor.YELLOW}${ChatColor.BOLD}${type.getName()} Challenge")
            .setLore(lore)
            .data(this.type.getIcon().durability)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        this.lambda.invoke(this.type)
    }

}
