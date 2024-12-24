package gg.pixelarcade.skywars.chests.lootable.command

import gg.pixelarcade.skywars.cages.sync.CageDataSync
import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.lootable.menu.LootTableListMenu
import gg.pixelarcade.skywars.chests.lootable.model.LootTable
import gg.pixelarcade.skywars.chests.lootable.model.LootTableItem
import gg.scala.commons.acf.annotation.CommandAlias
import gg.scala.commons.acf.annotation.CommandPermission
import gg.scala.commons.acf.annotation.Default
import gg.scala.commons.acf.annotation.Name
import gg.scala.commons.acf.annotation.Subcommand
import gg.scala.commons.command.ScalaCommand
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.Color
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player


/**
 * @date 11/11/2024
 * @author Moose1301
 */
@CommandPermission("skywars.admin")
@CommandAlias("loottable")
object LootTableCommand : ScalaCommand() {
    @Subcommand("create")
    fun handleList(
        player: Player,
        @Name("ID") id: String,
        @Name("Display Name") displayName: String
    ) {


        if (LootTableDataSync.cached().tables.containsKey(id)) {
            player.sendMessage(CC.RED + "A loot table with the id " + CC.YELLOW + id + CC.RED + " already exists")
            return
        }
        val displayName = Color.translate(displayName)

        val table = LootTable(id, displayName)

        val cached = LootTableDataSync.cached()
        cached.tables[id] = table
        LootTableDataSync.sync(cached)

        player.sendMessage(CC.GREEN + "Created Loot Table with name " + displayName)
    }

    @Subcommand("additem")
    fun handleAdd(
        player: Player,
        @Name("Loot Table ID") table: LootTable,
        @Name("Chance") chance: Double
    ) {
        if (player.itemInHand == null || player.itemInHand.type === Material.AIR) {
            player.sendMessage(CC.RED + "You aren't holding a item")
            return
        }
        player.sendMessage(
            CC.GREEN + "Added " + player.itemInHand.type.name + " with a chance of " + chance + "%"
        )
       table.editAndSave {
           items.add(LootTableItem(player.itemInHand, chance))
       }
    }

    @Subcommand("min")
    fun handleMin(player: Player, @Name("Loot Table ID") table: LootTable, @Name("Min") min: Int) {
        player.sendMessage(CC.GREEN + table.id + " min items to " + min)
        table.editAndSave {
            minItems = min
        }
    }

    @Subcommand("max")
    fun handleMax(player: Player, @Name("Loot Table ID") table: LootTable, @Name("Max") max: Int) {
        player.sendMessage(CC.GREEN + table.id + " max items to " + max)
        table.editAndSave {
            maxItems = max
        }
    }


    @Subcommand("list") @Default
    fun handleMenu(player: Player) {
        LootTableListMenu().openMenu(player)
    }

    @Subcommand("generate")
    fun handleGenerate(player: Player, @Name("Loot Table") table: LootTable) {
        for ((stack) in table.generateRewards()) {
            player.inventory.addItem(stack)
        }
    }
}