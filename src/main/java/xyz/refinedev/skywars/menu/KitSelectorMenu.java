package xyz.refinedev.skywars.menu;

import lombok.RequiredArgsConstructor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.kit.PlayerKit;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.HashMap;
import java.util.Map;

public class KitSelectorMenu extends PaginatedMenu {

    @Override
    public String getTitle(Player player) {
        return "Kit Selector";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (PlayerKit value : PlayerKit.values()) {
            buttons.put(buttons.size(), new KitButton(value));
        }

        return buttons;
    }

    @RequiredArgsConstructor
    private static class KitButton extends Button {
        private final PlayerKit kit;

        @Override
        public ItemStack getItem(Player player) {
            SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());

            ItemBuilder item = new ItemBuilder(kit.getMaterial());
            item.setName("&c" + kit.getDisplayName());

            if (profile.getSelectedKit() == kit) {
                item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            }

            return item.toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            profile.setSelectedKit(kit);
        }

        @Override
        public boolean shouldUpdate(Player player, ClickType clickType) {
            return true;
        }
    }
}
