package gg.pixelarcade.skywars.kit.impl.casual.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&aKnight")
                .addEnchant(Enchantment.DAMAGE_ALL, 2)
                .addEnchant(Enchantment.DURABILITY, 5)
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Sword Sharpness 2 Unbreaking 5",
                        "&7■ &fIron Helmet Protection 2 Unbreaking 2",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchantment(Enchantment.DURABILITY, 2).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 2).addEnchantment(Enchantment.DURABILITY, 5).toItemStack());
    }
}
