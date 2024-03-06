package gg.pixelarcade.skywars.kit.menu;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.menu.api.menus.ConfirmMenu;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author puugz
 * @since Mar 01, 2024
 */
public class KitManageMenu extends Menu {

    private final Kit kit;

    public KitManageMenu(Kit kit) {
        this.kit = kit;
//        this.setBordered(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Managing " + kit.getName() + " Kit";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        final SkyWarsProfile profile = SkyWarsShared.getInstance()
                .getProfileHandler()
                .getProfile(player.getUniqueId());

        buttons.put(this.getSlot(3, 1), new KitInfoButton());
        buttons.put(this.getSlot(4, 1), new BuyKitButton(profile));
        buttons.put(this.getSlot(5, 1), new EditKitButton());

        return buttons;
    }

    private void purchaseKit(Player player, SkyWarsProfile profile, Kit kit) {
        profile.getOwnedKits().add(kit.getName());
        profile.setCoins(profile.getCoins() - kit.getCost());
        profile.save();

        player.sendMessage(ChatColor.GREEN + "You have purchased the " + kit.getName() + " Kit!");
    }

    private class KitInfoButton extends Button {
        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(kit.getDisplayMaterial())
                    .setName(ChatColor.GOLD + kit.getName() + " Kit")
                    .toItemStack();
        }
    }

    @RequiredArgsConstructor
    private class BuyKitButton extends Button {

        private final SkyWarsProfile profile;

        private static final short STAINED_GLASS_YELLOW = 4;
        private static final short STAINED_GLASS_GREEN = 5;
        private static final short STAINED_GLASS_RED = 14;

        @Override
        public ItemStack getItem(Player player) {
            final boolean hasKit = false/*profile.getOwnedKits().contains(kit.getName())*/;
            final boolean canAffordKit = profile.getCoins() >= kit.getCost();

            final List<String> lore = new ArrayList<>();

            lore.add("");
            if (hasKit) {
                lore.add("&aYou already own this kit.");
            } else {
                lore.add("&fPrice: &e" + kit.getCost());
                lore.add("");
                lore.add(canAffordKit ? "&aClick to purchase for " + kit.getCost() + " coins."
                        : "&cYou cannot afford this kit.");
            }

            return new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDurability(hasKit ? STAINED_GLASS_YELLOW :
                            (canAffordKit ? STAINED_GLASS_GREEN : STAINED_GLASS_RED))
                    .setName("&a" + (hasKit ? "Owned" : "Purchase") + " " + kit.getName() + " Kit")
                    .setLore(lore)
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            final boolean hasKit = false/*profile.getOwnedKits().contains(kit.getName())*/;
            final boolean canAffordKit = profile.getCoins() >= kit.getCost();

            if (hasKit || !canAffordKit)
                return;

            final Menu confirmMenu = new ConfirmMenu(
                    "Are you sure you want to buy " + kit.getName() + " Kit for " + kit.getCost() + " coins?",
                    response -> {
                        if (response) {
                            purchaseKit(player, profile, kit);
                        }
                    },
                    true
            );

            Phoenix.getInstance().getMenuHandler()
                    .openMenu(confirmMenu, player);
        }
    }

    private static class EditKitButton extends Button {
        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.ANVIL)
                    .setName("&6Edit kit")
                    .setLore(
                            "",
                            "&aClick to edit kit."
                    )
                    .toItemStack();
        }
    }
}
