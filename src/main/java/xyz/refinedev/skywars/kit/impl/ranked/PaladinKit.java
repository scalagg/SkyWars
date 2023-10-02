package xyz.refinedev.skywars.kit.impl.ranked;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

public class PaladinKit extends Kit {
    @Override
    public String getName() {
        return "Paladin";
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
        return new ItemBuilder(Material.DIAMOND_SWORD).setName("&aPaladin").setLore(Arrays.asList(
                "&6&m---------------------",
                "&7■ &fIron Sword",
                "&7■ &fIron Pickaxe",
                "&7■ &fIron Axe",
                "&7■ &fIron Armor",
                "&7■ &fDiamond Leggings (Protection II)",
                "&7■ &fSplash Potion of Resistance (15seconds)",
                "&6&m---------------------"
        )).toItemStack();
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {

    }
}
