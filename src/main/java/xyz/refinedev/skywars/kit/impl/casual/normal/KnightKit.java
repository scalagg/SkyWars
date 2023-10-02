package xyz.refinedev.skywars.kit.impl.casual.normal;

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
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&aKnight")
                .addEnchant(Enchantment.DAMAGE_ALL, 1)
                .addEnchant(Enchantment.DURABILITY, 4)
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Sword Sharpness 1 Unbreaking 4",
                        "&7■ &fChain Protection 1",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.CHAINMAIL_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.DURABILITY, 4).toItemStack());
    }
}
