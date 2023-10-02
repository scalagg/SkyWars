package xyz.refinedev.skywars.kit.impl.ranked;

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

public class ArmorerKit extends Kit{

    @Override
    public String getName() {
        return "Armorer";
    }

    @Override
    public int getCost() {
        return 0;
    }

    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .setName("&aArmorer")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fDiamond Chestplate (Protection I)",
                        "&7■ &fDiamond Boots (Protection I)",
                        "&7■ &fIron Pickaxe",
                        "&7■ &fIron Axe",
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
        return Rarity.RARE;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .toItemStack(),
                new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack()

        };

    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE)
                .toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
    }
}
