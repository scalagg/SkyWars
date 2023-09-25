package xyz.refinedev.skywars.arena.menu.manageschematics;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.menu.manageschematic.ManageSchematicMenu;
import xyz.refinedev.skywars.handler.GameHandler;

import java.util.ArrayList;
import java.util.List;

final class ManageSchematicButton extends Button {

    private final ArenaSchematic schematic;

    ManageSchematicButton(ArenaSchematic schematic) {
        this.schematic = Preconditions.checkNotNull(schematic, "schematic");
    }

    public String getName(Player player) {
        return ChatColor.AQUA + schematic.getName();
    }

    public List<String> getDescription(Player player) {
        List<String> description = new ArrayList<>();

        description.add(ChatColor.GRAY + " » " + ChatColor.WHITE + "Enabled: " + ChatColor.AQUA + (schematic.isEnabled() ? "Yes" : "No"));

        return description;
    }

    public int getAmount(Player player) {
        return 1;
    }

    public Material getMaterial(Player player) {
        return schematic.isEnabled() ? Material.EMERALD_BLOCK : Material.REDSTONE_BLOCK;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(getMaterial(player)).setName(getName(player)).setLore(getDescription(player)).toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();
//        new ManageSchematicMenu(schematic).openMenu(player);

        Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
    }


}