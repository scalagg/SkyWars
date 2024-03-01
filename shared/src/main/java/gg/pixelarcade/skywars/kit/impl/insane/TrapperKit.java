package gg.pixelarcade.skywars.kit.impl.insane;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;

import java.util.Arrays;

public class TrapperKit extends Kit{

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
        return GameType.INSANE;
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
                        "&7■ &f4 Tripwire Hook",
                        "&7■ &f24 Cobweb",
                        "&7■ &f8 Piston",
                        "&7■ &f8 Slimeballs",
                        "&7■ &f32 Redstone Dust",
                        "&7■ &f2 Lever",
                        "&7■ &f4 Dispenser",
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
        player.getInventory().addItem(new ItemStack(Material.TRIPWIRE_HOOK, 4));
        player.getInventory().addItem(new ItemStack(Material.WEB, 24));
        player.getInventory().addItem(new ItemStack(Material.PISTON_BASE, 8));
        player.getInventory().addItem(new ItemStack(Material.SLIME_BALL, 8));
        player.getInventory().addItem(new ItemStack(Material.REDSTONE, 32));
        player.getInventory().addItem(new ItemStack(Material.LEVER, 2));
        player.getInventory().addItem(new ItemStack(Material.DISPENSER, 4));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
    }
}
