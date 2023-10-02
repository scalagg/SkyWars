package xyz.refinedev.skywars.kit.impl.casual.normal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public ItemStack getDisplayItem() {
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
                ))
                .toItemStack();
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
