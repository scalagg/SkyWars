package gg.pixelarcade.skywars.menus.perks;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.perk.Perk;
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
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

public class ConfirmationMenu extends Menu {

    private final Perk perk;

    public ConfirmationMenu(Perk perk) {
        this.perk = perk;
        this.setBordered(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Are you sure?";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(this.getSlot(2, 1), new ConfirmButton(perk));
        buttons.put(this.getSlot(6, 1), new CancelButton());

        return buttons;
    }

    @RequiredArgsConstructor
    public static class ConfirmButton extends Button {

        private final Perk perk;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.STAINED_CLAY, 1, (short) 13)
                    .setName(ChatColor.GREEN + "Confirm")
                    .setLore(
                            ChatColor.GRAY + "Purchase " + perk.getName() + " for",
                            ChatColor.GRAY + String.valueOf(perk.getCost()) + " coins."
                    )
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins() >= perk.getCost()) {

                SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());

                int originalCoins = profile.getCoins();
                profile.getOwnedPerks().add(perk.getName());
                profile.setCoins(originalCoins - perk.getCost());

                Phoenix.getInstance().getMenuHandler().openMenu(new PurchasePerkMenu(), player);
                player.sendMessage(ChatColor.GREEN + "Purchased!");
            }
        }
    }

    public static class CancelButton extends Button {

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.STAINED_CLAY, 1, (short) 14)
                    .setName(ChatColor.RED + "Cancel")
                    .setLore(ChatColor.GRAY + "Return to previous menu.")
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            Phoenix.getInstance().getMenuHandler().openMenu(new PurchasePerkMenu(), player);
        }
    }
}
