package gg.pixelarcade.skywars.region.menu;

import gg.pixelarcade.skywars.region.Region;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.menu.api.button.BackButton;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars
 * @date 20/10/2023 / 00:28
 */

@RequiredArgsConstructor
public class RegionEditMenu extends Menu {

    private final Region region;

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public String getTitle(Player player) {
        return "Editing " + region.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new BackButton(new RegionEditorMenu()));

        // first row
        buttons.put(12, new RegionLeavesButton(region));
        buttons.put(13, new RegionEnableButton(region));
        buttons.put(14, new RegionInteractButton(region));

        // second row
        buttons.put(21, new RegionDropsButton(region));
        buttons.put(22, new RegionDamageButton(region));
        buttons.put(23, new RegionPickupButton(region));

        // third row
        buttons.put(30, new RegionBreakButton(region));
        buttons.put(31, new RegionHungerButton(region));
        buttons.put(32, new RegionPlaceButton(region));

        return buttons;
    }

    private static String colorBoolean(boolean bool) {
        return bool ? "&aEnabled" : "&cDisabled";
    }

    @RequiredArgsConstructor
    public static class RegionEnableButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.REDSTONE_TORCH_ON)
                    .setName("&6&lToggle Region")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isEnabled()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setEnabled(!region.isEnabled());
        }
    }

    @RequiredArgsConstructor
    public static class RegionPlaceButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.GRASS)
                    .setName("&6&lPrevent Place Blocks")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isPlaceBlocks()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setPlaceBlocks(!region.isPlaceBlocks());
        }
    }

    @RequiredArgsConstructor
    public static class RegionBreakButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.GOLD_PICKAXE)
                    .setName("&6&lPrevent Break Blocks")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isBreakBlocks()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setBreakBlocks(!region.isBreakBlocks());
        }
    }

    @RequiredArgsConstructor
    public static class RegionDropsButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.ANVIL)
                    .setName("&6&lPrevent Drop Items")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isDropItems()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setDropItems(!region.isDropItems());
        }
    }

    @RequiredArgsConstructor
    public static class RegionHungerButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.COOKED_BEEF)
                    .setName("&6&lPrevent Hunger Loss")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isHunger()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setHunger(!region.isHunger());
        }
    }

    @RequiredArgsConstructor
    public static class RegionPickupButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.ENCHANTED_BOOK)
                    .setName("&6&lPrevent Pickup Items")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isPickupItems()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setPickupItems(!region.isPickupItems());
        }
    }

    @RequiredArgsConstructor
    public static class RegionLeavesButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.LEAVES)
                    .setName("&6&lPrevent Leaves Decay")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isLeavesDecay()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setLeavesDecay(!region.isLeavesDecay());
        }
    }

    @RequiredArgsConstructor
    public static class RegionInteractButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.STONE_BUTTON)
                    .setName("&6&lPrevent Interact")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isInteract()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setInteract(!region.isInteract());
        }
    }

    @RequiredArgsConstructor
    public static class RegionDamageButton extends Button {

        private final Region region;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.DIAMOND_SWORD)
                    .setName("&6&lPrevent Damage")
                    .addLoreLine("&6■ &fStatus&7: &e" + colorBoolean(region.isDamage()))
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            region.setDamage(!region.isDamage());
        }
    }

}
