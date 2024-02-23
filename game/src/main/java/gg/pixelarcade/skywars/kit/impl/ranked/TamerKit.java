package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

public class TamerKit extends Kit implements Listener {

    @Override
    public String getName() {
        return "Tamer";
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
        return new ItemBuilder(Material.BONE).setName("&aTamer").setLore(Arrays.asList(
                "&6&m---------------------",
                "&7■ &fIron Armor",
                "&7■ &fDiamond Tamers Boots (Prot 2)",
                "&7■ &fDiamond Sword",
                "&7■ &fIron Pickaxe",
                "&7■ &fIron Axe",
                "&7■ &f5 Bones",
                "&7■ &f5 Wolf Spawn Eggs",
                "&6&m---------------------"
        )).toItemStack();
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack())
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.BONE, 5));
        player.getInventory().addItem(new ItemStack(Material.MONSTER_EGG, 5, (short) 95));

    }
}
