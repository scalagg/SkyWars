package xyz.refinedev.skywars.kit.impl.ranked;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

public class ChampionKit extends Kit{

    @Override
    public String getName() {
        return "Champion";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO, GameType.RANKED_DOUBLES);
    }

    @Override
    public ItemStack getDisplayItem() {
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
                ))
                .toItemStack();
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
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
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 2).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ANVIL).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK, 3).addEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack());
    }
}
