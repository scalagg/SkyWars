package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

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
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.LEGENDARY;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&aKnight")
                .addEnchant(Enchantment.DAMAGE_ALL, 1)
                .addEnchant(Enchantment.DURABILITY, 4)
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Sword Sharpness 1 Unbreaking 4",
                        "&7■ &fChain Protection 1",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.CHAINMAIL_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DURABILITY, 4).toItemStack());
    }
}
