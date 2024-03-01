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

public class KnightKit extends Kit {

    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public int getCost() {
        return 30000;
    }

    @Override
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.LEGENDARY;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&aKnight")
                .addEnchant(Enchantment.DAMAGE_ALL, 2)
                .addEnchant(Enchantment.DURABILITY, 5)
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Sword Sharpness 2 Unbreaking 5",
                        "&7■ &fIron Helmet Protection 2 Unbreaking 2",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 2).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.DURABILITY, 5).toItemStack());
    }
}
