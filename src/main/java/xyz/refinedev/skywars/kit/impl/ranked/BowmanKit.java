package xyz.refinedev.skywars.kit.impl.ranked;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;

import java.util.Arrays;
import java.util.List;

public class BowmanKit extends Kit {
    @Override
    public String getName() {
        return "Bowman";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.BOW)
                .setName("&aBowman")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fDiamond Helmet with Protection 2",
                        "&7■ &fIron Armor",
                        "&7■ &fBow with Power 3",
                        "&7■ &fIron Sword",
                        "&7■ &fIron Pickaxe",
                        "&7■ &fIron Axe",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }
    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO);
    }
    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ARROW, 64).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
    }
}
