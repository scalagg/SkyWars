package gg.pixelarcade.skywars.region.menu

import gg.pixelarcade.skywars.region.Region
import lombok.RequiredArgsConstructor
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.BackButton
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars
 * @date 20/10/2023 / 00:28
 */
class RegionEditMenu(private val region: Region) : Menu() {

    override fun getMinSize(): Int {
        return 45
    }

    override fun getTitle(player: Player): String {
        return "Editing " + region.name
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        buttons[0] = BackButton("Back") { _: Player? ->
            RegionEditorMenu().openMenu(player)
        }

        // first row
        buttons[12] = RegionLeavesButton(region)
        buttons[13] = RegionEnableButton(region)
        buttons[14] = RegionInteractButton(region)

        // second row
        buttons[21] = RegionDropsButton(region)
        buttons[22] = RegionDamageButton(region)
        buttons[23] = RegionPickupButton(region)

        // third row
        buttons[30] = RegionBreakButton(region)
        buttons[31] = RegionHungerButton(region)
        buttons[32] = RegionPlaceButton(region)

        return buttons
    }

    @RequiredArgsConstructor
    class RegionEnableButton(private val region: Region) : Button() {


        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.REDSTONE_TORCH_ON)
                .name("&6&lToggle Region")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.enabled))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.enabled = !region.enabled
        }
    }

    @RequiredArgsConstructor
    class RegionPlaceButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.GRASS)
                .name("&6&lPrevent Place Blocks")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.placeBlocks))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.placeBlocks = !region.placeBlocks
        }
    }

    @RequiredArgsConstructor
    class RegionBreakButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.GOLD_PICKAXE)
                .name("&6&lPrevent Break Blocks")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.breakBlocks))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.breakBlocks = !region.breakBlocks
        }
    }

    @RequiredArgsConstructor
    class RegionDropsButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.ANVIL)
                .name("&6&lPrevent Drop Items")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.dropItems))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.dropItems = !region.dropItems
        }
    }

    @RequiredArgsConstructor
    class RegionHungerButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.COOKED_BEEF)
                .name("&6&lPrevent Hunger Loss")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.hunger))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.hunger = !region.hunger
        }
    }

    @RequiredArgsConstructor
    class RegionPickupButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.ENCHANTED_BOOK)
                .name("&6&lPrevent Pickup Items")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.pickupItems))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.pickupItems = !region.pickupItems
        }
    }

    @RequiredArgsConstructor
    class RegionLeavesButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.LEAVES)
                .name("&6&lPrevent Leaves Decay")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.leavesDecay))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.leavesDecay = !region.leavesDecay
        }
    }

    @RequiredArgsConstructor
    class RegionInteractButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.STONE_BUTTON)
                .name("&6&lPrevent Interact")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.interact))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.interact = !region.interact
        }
    }

    @RequiredArgsConstructor
    class RegionDamageButton(private val region: Region) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.DIAMOND_SWORD)
                .name("&6&lPrevent Damage")
                .addToLore("&6■ &fStatus&7: &e" + colorBoolean(region.damage))
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            region.damage = !region.damage
        }
    }

    companion object {
        private fun colorBoolean(bool: Boolean): String {
            return if (bool) "&aEnabled" else "&cDisabled"
        }
    }
}
