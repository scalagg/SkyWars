package gg.pixelarcade.skywars.arena.menu.manageschematic;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

final class ToggleEnabledButton extends Button {

    private final ArenaSchematic schematic;

    ToggleEnabledButton(ArenaSchematic schematic) {
        this.schematic = Preconditions.checkNotNull(schematic, "schematic");
    }

    public String getName(Player player) {
        if (schematic.isEnabled()) {
            return ChatColor.RED + "Disable " + schematic.getName();
        } else {
            return ChatColor.GREEN + "Enable " + schematic.getName();
        }
    }

    public List<String> getDescription(Player player) {
        if (schematic.isEnabled()) {
            return ImmutableList.of(
                    ChatColor.WHITE + "Click to disable " + schematic.getName() + ", which will prevent matches",
                    ChatColor.WHITE + "being scheduled on these arenas. Admin",
                    ChatColor.WHITE + "commands will not be impacted."
            );
        } else {
            return ImmutableList.of(
                    ChatColor.WHITE + "Click to enable " + schematic.getName() + ", which will allow matches",
                    ChatColor.WHITE + "to be scheduled on these arenas."
            );
        }
    }

    public Material getMaterial(Player player) {
        // we invert our normal logic here to show the 'potential' state (similiar to
        // video player play/pause buttons)
        return schematic.isEnabled() ? Material.REDSTONE_BLOCK : Material.EMERALD_BLOCK;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(getMaterial(player)).setName(getName(player)).setLore(getDescription(player)).toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        schematic.setEnabled(!schematic.isEnabled());

        SkyWarsPlugin.getInstance().getArenaHandler().saveSchematics();
    }

}