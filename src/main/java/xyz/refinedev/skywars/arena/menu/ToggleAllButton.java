package xyz.refinedev.skywars.arena.menu;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class ToggleAllButton extends Button {

    private Set<String> allMaps;
    private Set<String> maps;
    
    public List<String> getDescription(Player player) {
        return ImmutableList.of();
    }

    public Material getMaterial(Player player) {
        return maps.isEmpty() ? Material.REDSTONE_TORCH_ON : Material.REDSTONE_TORCH_OFF;
    }

    public String getName(Player player) {
        return maps.isEmpty() ? ChatColor.GREEN + "Enable all maps" : ChatColor.RED + "Disable all maps";
    }

    @Override
    public ItemStack getItem(org.bukkit.entity.Player player) {
        return new ItemBuilder(getMaterial(player)).setName(getName(player)).setLore(getDescription(player)).toItemStack();
    }
    
    @Override
    public void clicked(Player player, ClickType clickType) {
        if (maps.isEmpty()) {
            maps.addAll(allMaps);
        } else {
            maps.clear();
        }
    }
}