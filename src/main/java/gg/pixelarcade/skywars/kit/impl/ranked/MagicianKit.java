package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

public class MagicianKit extends Kit {

    @Override
    public String getName() {
        return "Magician";
    }
    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.STICK)
                .setName("&aMagician")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Armor",
                        "&7■ &fDiamond Chestplate with Protection 3",
                        "&7■ &f2 Milk Buckets",
                        "&7■ &f3 Harming Potions",
                        "&7■ &f3 Weakness Potions",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }
    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO, GameType.RANKED_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_HELMET),
                new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .toItemStack(),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }
    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.MILK_BUCKET, 2)
                .toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 25 * 10, 1), true)
                .toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.HARM, 25 * 10, 1), true)
                .toItemStack());
    }
}
