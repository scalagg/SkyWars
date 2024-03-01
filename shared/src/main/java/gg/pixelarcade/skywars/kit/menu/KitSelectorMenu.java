package gg.pixelarcade.skywars.kit.menu;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.kit.Kit;
import lombok.RequiredArgsConstructor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

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

        for (Kit value : SkyWarsShared.getInstance().getKitHandler().getKits()) {
            buttons.put(buttons.size(), new KitButton(value));
        }

        return buttons;
    }

    @RequiredArgsConstructor
    private static class KitButton extends Button {

        private final Kit kit;

        @Override
        public ItemStack getItem(Player player) {
            SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            ItemBuilder item = kit.getDisplayItem();

            if (profile.getSelectedKit().equalsIgnoreCase(kit.getName())) {
                item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            }

            return item.toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            profile.setSelectedKit(kit.getName().toLowerCase());
            profile.save();
        }

        @Override
        public boolean shouldUpdate(Player player, ClickType clickType) {
            return true;
        }
    }
}
