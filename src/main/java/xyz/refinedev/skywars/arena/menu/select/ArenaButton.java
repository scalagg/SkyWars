package xyz.refinedev.skywars.arena.menu.select;

import com.google.common.collect.Lists;
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
public class ArenaButton extends Button {

    private String mapName;
    private Set<String> maps;

    public List<String> getDescription(Player player) {
        List<String> lines = Lists.newLinkedList();

        boolean isEnabled = maps.contains(mapName);

        if (isEnabled) {
            lines.add(ChatColor.GRAY + "Click here to " + ChatColor.RED + "remove" + ChatColor.GRAY + " this arena from the selection.");
        } else {
            lines.add(ChatColor.GRAY + "Click here to " + ChatColor.GREEN + "add" + ChatColor.GRAY + " this arena to the selection.");
        }

        return lines;
    }

    public Material getMaterial(Player player) {
        boolean isEnabled = maps.contains(mapName);

        return isEnabled ? Material.MAP : Material.EMPTY_MAP;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(getMaterial(player)).setName(mapName).setLore(getDescription(player)).toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (maps.contains(mapName)) {
            maps.remove(mapName);

            player.sendMessage(ChatColor.RED + "Removed " + mapName + " from the selection.");
        } else {
            maps.add(mapName);

            player.sendMessage(ChatColor.GREEN + "Added " + mapName + " to your selection.");
        }

    }
}
