package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class ArmorsmithKit extends Kit {

    public ArmorsmithKit() {
        super("Armorsmith", Material.ANVIL, 15000, GameType.INSANE, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ANVIL));
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.ARROW_DAMAGE, 2).toItemStack());
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 64));
    }
}
