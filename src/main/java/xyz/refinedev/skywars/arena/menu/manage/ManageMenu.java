package xyz.refinedev.skywars.arena.menu.manage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.arena.menu.manageschematics.ManageSchematicsMenu;

import java.util.List;
import java.util.Map;


public class ManageMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Admin Management Menu";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        return ImmutableMap.of(
                4, new ManageArenaButton()
        );
    }

    private static class ManageArenaButton extends Button {

        public String getName(Player player) {
            return ChatColor.AQUA + "Manage Schematics";
        }

        public List<String> getDescription(Player player) {
            return ImmutableList.of();
        }

        public Material getMaterial(Player player) {
            return Material.IRON_PICKAXE;
        }

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(getMaterial(player)).setName(getName(player)).setLore(getDescription(player)).toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.closeInventory();
            Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicsMenu(), player);
        }

    }
}
