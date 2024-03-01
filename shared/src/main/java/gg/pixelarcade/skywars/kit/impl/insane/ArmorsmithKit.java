package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

public class ArmorsmithKit extends Kit {
    @Override
    public String getName() {
        return "Armorsmith";
    }

    @Override
    public int getCost() {
        return 15000;
    }

    @Override
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.ANVIL)
                .setName("&aArmorsmith")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fAnvil",
                        "&7■ &fEnchanted Book Protection 3",
                        "&7■ &fEnchanted Book Sharpness 2",
                        "&7■ &fEnchanted Book Power 2",
                        "&7■ &f64 XP Bottles",
                        "&6&m---------------------"
                ));
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
