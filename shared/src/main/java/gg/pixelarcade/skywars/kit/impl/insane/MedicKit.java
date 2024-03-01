package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

public class MedicKit extends Kit {
    @Override
    public String getName() {
        return "Medic";
    }

    @Override
    public int getCost() {
        return 20000;
    }

    @Override
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.RARE;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.POTION)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15, 1), true)
                .setName("&aMedic")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &f3 Splash Potions of Healing 2",
                        "&7■ &f2 Splash Potions of Regen 2",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 2), true).toItemStack());

    }
}
