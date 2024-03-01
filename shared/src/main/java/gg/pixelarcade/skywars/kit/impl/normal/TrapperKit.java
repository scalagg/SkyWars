package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class TrapperKit extends Kit {

    @Override
    public String getName() {
        return "Trap Master";
    }

    @Override
    public int getCost() {
        return 20000;
    }

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.RARE;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.PISTON_BASE)
                .setName("&aTrap Master")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &f2 Tripwire Hook",
                        "&7■ &f8 Cobweb",
                        "&7■ &f4 Piston",
                        "&7■ &f4 Slimeballs",
                        "&7■ &f16 Redstone Dust",
                        "&7■ &f1 Lever",
                        "&7■ &f2 Dispenser",
                        "&7■ &f32 Arrow",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.TRIPWIRE_HOOK, 2));
        player.getInventory().addItem(new ItemStack(Material.WEB, 8));
        player.getInventory().addItem(new ItemStack(Material.PISTON_BASE, 4));
        player.getInventory().addItem(new ItemStack(Material.SLIME_BALL, 4));
        player.getInventory().addItem(new ItemStack(Material.REDSTONE, 16));
        player.getInventory().addItem(new ItemStack(Material.LEVER, 1));
        player.getInventory().addItem(new ItemStack(Material.DISPENSER, 2));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
    }
}
