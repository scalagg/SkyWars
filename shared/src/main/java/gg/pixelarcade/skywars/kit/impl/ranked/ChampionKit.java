package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.KitRarity;

import java.util.Arrays;

public class ChampionKit extends Kit {

    @Override
    public String getName() {
        return "Champion";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public GameType getGameType() {
        return GameType.RANKED;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("&aChampion")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Armor",
                        "&7■ &fDiamond Sword with Sharpness 2",
                        "&7■ &fIron Axe",
                        "&7■ &fIron Pickaxe",
                        "&7■ &fAnvil",
                        "&7■ &f3 Sharpness 1 Books",
                        "&6&m---------------------"
                ));
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ANVIL).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK, 3).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
    }
}
